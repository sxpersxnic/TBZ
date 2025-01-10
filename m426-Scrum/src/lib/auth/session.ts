import { getIronSession } from "iron-session";
import { cookies } from "next/headers";
import { SessionData, sessionOptions, defaultSession } from "../definitions";
import { db } from "@/db";
import { eq } from "drizzle-orm";
import { users } from "@/db/schema";

export async function getSession() {
  const session = await getIronSession<SessionData>(await cookies(), sessionOptions);

  if (!session.isLoggedIn) {
    session.isLoggedIn = defaultSession.isLoggedIn;
  }

  session.isBlocked = false

  return session;
}

export async function useSession() {
  const session = await getSession();

  const id = session.userId as string;
  const user = db.query.users.findFirst({ where: eq(users.id, id)})

  if (user === undefined) {
    return null;
  }
  
  return user;
}