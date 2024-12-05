import bcrypt from 'bcrypt';
import { db } from '@vercel/postgres';
import { dummyUser, posts, revenue, users } from '@/lib/placeholder-data';
import { revalidatePath } from 'next/cache';
import { redirect } from 'next/navigation';

/**
 * Description placeholder
 *
 * @type {*}
 */
const client = await db.connect();

/**
 * Description placeholder
 *
 * @async
 * @void
 */
async function clear() {
  await client.sql`DROP TABLE posts`;
  await client.sql`DROP TABLE sessions`;
  await client.sql`DROP TABLE users`;
}

/**
 * Description placeholder
 *
 * @async
 * @returns {unknown}
 */
async function seedUsers() {
  await client.sql`CREATE EXTENSION IF NOT EXISTS "uuid-ossp"`;
  await client.sql`
    CREATE TABLE IF NOT EXISTS users (
      id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
      username VARCHAR(255) NOT NULL UNIQUE,
      email TEXT NOT NULL UNIQUE,
      image_url VARCHAR(255) NOT NULL,
      password TEXT NOT NULL
    );
  `;
  const insertedUsers = await Promise.all(
    users.map(async (user) => {
      const hashedPassword = await bcrypt.hash(user.password, 10);
      return client.sql`
        INSERT INTO users (id, username, email, image_url, password)
        VALUES (${user.id}, ${user.username}, ${user.email}, ${user.image_url}, ${hashedPassword})
        ON CONFLICT (id) DO NOTHING;
      `;
    }),
  );

  return insertedUsers;
}

/**
 * Description placeholder
 *
 * @async
 * @returns {unknown}
 */
async function seedDummy() {
  await client.sql`CREATE EXTENSION IF NOT EXISTS "uuid-ossp"`;
  await client.sql`
    CREATE TABLE IF NOT EXISTS users (
      id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
      username VARCHAR(255) NOT NULL UNIQUE,
      email TEXT NOT NULL UNIQUE,
      image_url VARCHAR(255) NOT NULL,
      password TEXT NOT NULL
    );
  `;
  const insertedUsers = await Promise.all(
    dummyUser.map(async (user) => {
      const hashedPassword = await bcrypt.hash(user.password, 10);
      return client.sql`
        INSERT INTO users (id, username, email, image_url, password)
        VALUES (${user.id}, ${user.username}, ${user.email}, ${user.image_url}, ${hashedPassword})
        ON CONFLICT (id) DO NOTHING;
      `;
    }),
  );

  return insertedUsers;
}


/**
 * Description placeholder
 *
 * @async
 * @returns {unknown}
 */
async function seedPosts() {
  await client.sql`CREATE EXTENSION IF NOT EXISTS "uuid-ossp"`;

  await client.sql`
    CREATE TABLE IF NOT EXISTS posts (
      id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
      author_id UUID NOT NULL,
      title TEXT NOT NULL,
      body TEXT NOT NULL,
      date DATE NOT NULL,
      CONSTRAINT fk_author
      FOREIGN KEY (author_id) REFERENCES users(id)
    );
  `;

  const insertedPosts = await Promise.all(
    posts.map(
      (post) => client.sql`
        INSERT INTO posts (id, author_id, title, body, date)
        VALUES (${post.id}, ${post.author_id}, ${post.title}, ${post.body}, ${post.date})
        ON CONFLICT (id) DO NOTHING;
      `,
    ),
  );

  return insertedPosts;
}


/**
 * Description placeholder
 *
 * @async
 * @returns {*}
 */
async function seedSessions() {

  await client.sql`
    CREATE TABLE IF NOT EXISTS sessions (
      id UUID PRIMARY KEY,
      user_id UUID NOT NULL,
      expires_at DATE NOT NULL,
      CONSTRAINT fk_user
      FOREIGN KEY (user_id) REFERENCES users(id)
    );
  `;
}

/**
 * Description placeholder
 *
 * @async
 * @returns {unknown}
 */
async function seedRevenue() {
  await client.sql`
    CREATE TABLE IF NOT EXISTS revenue (
      month VARCHAR(4) NOT NULL UNIQUE,
      revenue INT NOT NULL
    );
  `;

  const insertedRevenue = await Promise.all(
    revenue.map(
      (rev) => client.sql`
        INSERT INTO revenue (month, revenue)
        VALUES (${rev.month}, ${rev.revenue})
        ON CONFLICT (month) DO NOTHING;
      `,
    ),
  );

  return insertedRevenue;
}

/**
 * Description placeholder
 *
 * @export
 * @async
 * @returns {*}
 */
export async function GET() {
  try {
    console.log("Starting database seeding...");
    await client.sql`BEGIN`;

    await clear();

    console.log("Seeding dummy...");
    await seedDummy();

    console.log("Seeding users...");
    await seedUsers();
    
    console.log("Seeding posts...");
    await seedPosts();
    
    console.log("Seeding sessions...");
    await seedSessions();
    
    console.log("Seeding revenue...");
    await seedRevenue();
    
    console.log("Seedings completed, commiting...");
    await client.sql`COMMIT`;
    
    console.log("Successfully committed!");
    Response.json({ message: 'Database seeded successfully' });
    revalidatePath('/');
    redirect('/');
  } catch (error) {
    await client.sql`ROLLBACK`;
    console.error(error);
    redirect('/');
  }
}