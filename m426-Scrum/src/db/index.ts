import { drizzle } from 'drizzle-orm/vercel-postgres';
import { NewUser, posts, users } from './schema';
import * as schema from './schema';
import { desc, eq, ilike, count } from 'drizzle-orm';
import { sql } from '@vercel/postgres';

/**
 * Description placeholder
 *
 * @type {(VercelPgDatabase<Record<string, never>> & { : any; })}
 */
export const db = drizzle(sql, { schema });

/**
 * Description placeholder
 *
 * @async
 * @param {NewUser} user
 * @returns {unknown}
 */
export const insertUser = async (user: NewUser) => {
  return db.insert(users).values(user).returning();
};
