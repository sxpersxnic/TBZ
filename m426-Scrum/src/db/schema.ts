import {
  text,
  pgTable,
  timestamp,
  varchar,
  integer,
} from 'drizzle-orm/pg-core';
import { InferInsertModel } from 'drizzle-orm';
import { defaultPfp } from '@/lib/definitions';
import { getUuid } from '@/lib/utils';

/**
 * Description placeholder
 *
 * @type {*}
 */
export const users = pgTable(
  "users",
  {
    id: varchar('id', { length: 255 }).primaryKey().notNull().$defaultFn(() => getUuid()),
    username: varchar('username', { length: 255 }).unique().notNull(),
    email: text('email').unique().notNull(),
    image_url: varchar('image_url', { length: 255 }).default(defaultPfp),
    password: text('password').notNull()
  }
);

/**
 * Description placeholder
 *
 * @type {*}
 */
export const posts = pgTable(
  "posts", 
  {
    id: varchar('id', { length: 255 }).primaryKey().notNull().$defaultFn(() => getUuid()),
    authorId: varchar('author_id', { length: 255 })
      .references(() => users.id)
      .notNull(),
    title: text('title').notNull(),
    body: text('body').notNull(),
    date: timestamp('date').notNull().defaultNow()
  }
);


/**
 * Description placeholder
 *
 * @type {*}
 */
export const sessions = pgTable(
  "sessions",
  {
    id: varchar('id', { length: 255 }).primaryKey().notNull().$defaultFn(() => getUuid()),
    userId: varchar('user_id', { length: 255 })
      .references(() => users.id)
      .notNull(),
    expiresAt: timestamp('expires_at').notNull()
  }
);

/**
 * Description placeholder
 *
 * @type {*}
 */
export const revenue = pgTable(
  "revenue",
  {
    month: varchar({ length: 4 }).notNull().unique(),
    revenue: integer().notNull()
  }
)

/**
 * Description placeholder
 *
 * @export
 * @typedef {NewUser}
 */
export type NewUser = InferInsertModel<typeof users>;
/**
 * Description placeholder
 *
 * @export
 * @typedef {NewPost}
 */
export type NewPost = InferInsertModel<typeof posts>;
/**
 * Description placeholder
 *
 * @export
 * @typedef {NewSession}
 */
export type NewSession = InferInsertModel<typeof sessions>;
/**
 * Description placeholder
 *
 * @export
 * @typedef {NewRevenue}
 */
export type NewRevenue = InferInsertModel<typeof revenue>;