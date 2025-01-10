'use server';

import { redirect } from "next/navigation";
import { defaultPfp, FormState, SignUpFormSchema } from "../definitions";
import { db } from "@/db";
import { users } from "@/db/schema";
import bcrypt from 'bcrypt';
import { eq } from 'drizzle-orm';
import { getSession } from "./session";

/**
 * - Validates fields
 * - Hash password
 * - Check for existence of User
 * - Insert User into database
 *
 * @export
 * @async
 * @param {FormState} state
 * @param {FormData} formData
 * @returns {Promise<FormState>}
 */
export async function signup(state: FormState, formData: FormData): Promise<FormState> {
  
  // 1. Validating form fields
  const validatedFields = SignUpFormSchema.safeParse({
    username: formData.get('username'),
    email: formData.get('email'),
    password: formData.get('password')
  });

  // If any invalid form fields, return early 
  if (!validatedFields.success) {
    return {
      errors: validatedFields.error.flatten().fieldErrors
    };
  }

  // 2. Prepare data for insertion into database
  const { username, email, password } = validatedFields.data;
  const hashedPassword = await bcrypt.hash(password, 10);

  // 3. Check for existence
  const existingUser = await db.query.users.findFirst({
    where:  eq(users.email, email) || 
            eq(users.username, username)
  });

  if (existingUser) {
    return {
      message: 'User already exists, please use a different login.' 
    }
  }

  // 4. Insert user into database
  const data = await db
    .insert(users)
    .values({ username, email, image_url: defaultPfp, password: hashedPassword })
    .returning({ id: users.id });
  
  const user = data[0]

  if (!user) {
    return {
      message: 'An error occured while creating your account.'
    };
  }

  // 5. Redirect to signin
  redirect("/auth/login");
}

export async function signin(prevState: FormState, formData: FormData): Promise<FormState> {
  
  const session = await getSession();

  const email = formData.get("email") as string;
  const password = formData.get("password") as string;

  const foundUsers = await db.select().from(users).where(eq(users.email, email));
  
  const user = foundUsers[0];
  const hash = user.password;

  const passwordsMatch = await bcrypt.compare(password, hash);

  if (!passwordsMatch) { return { message: "Invalid Credentials" }; }

  session.userId = user?.id;
  session.isLoggedIn = true;

  await session.save();
  redirect("/blog");
};

export async function signout(): Promise<void> {
  try {
    const session = await getSession();
    session.destroy();
    redirect("/");
  } catch (err) {
    console.error("There was an Error during signout: ", err);
    throw new Error('Failed to signout');
  }
}