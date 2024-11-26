// import NextAuth from 'next-auth';
// import Credentials from 'next-auth/providers/credentials';
// import { authConfig } from './auth.config';
// import { z } from 'zod';
// import type { User } from '@/lib/definitions';
// import bcrypt from 'bcrypt';
// import { db } from '@/db';
// import { eq } from 'drizzle-orm';
// import { users } from '@/db/schema';

// async function getUser(email: string) {
//   try {
//     const user = await db.select().from(users).where(eq(users.email, email));
//     return user[0];
//   } catch (error) {
//     console.error('Failed to fetch user:', error);
//     throw new Error('Failed to fetch user.');
//   }
// }
 
// export const { auth, signIn, signOut } = NextAuth({
//   ...authConfig,
//   providers: [
//     Credentials({
//       async authorize(credentials) {
//         const parsedCredentials = z
//           .object({ email: z.string().email(), password: z.string().min(8) })
//           .safeParse(credentials);
 
//         if (parsedCredentials.success) {
//           const { email, password } = parsedCredentials.data;
//           const user = await getUser(email);
//           if (!user) return null;
//           const passwordsMatch = await bcrypt.compare(password, user.password);
//           if (passwordsMatch) return user;
//         }
 
//         return null;
//       },
//     }),
//   ],
// });