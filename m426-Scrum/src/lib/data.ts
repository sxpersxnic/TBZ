import { QueryResult, sql } from '@vercel/postgres';
import { PostPreview, PostsTable, PostView, UserPreview, UserRecord, UsersTable } from './definitions';

/**
 * Function for getting latest posts sorted by date (descending)
 * @param {number} limit - The number of posts to fetch
 * @returns {Promise<QueryResult<PostPreview>>} - A promise that resolves to an array of post previews
 */
export async function fetchLatestPosts(limit: number): Promise<QueryResult<PostPreview>> {
  try {
    const data = await sql<PostPreview>`
      SELECT posts.id, posts.author_id, users.username, users.image_url, posts.date, posts.title
      FROM posts
      JOIN users ON posts.author_id = users.id
      ORDER BY posts.date DESC
      LIMIT ${limit}
    `;

    return data;
  } catch (error) {
    console.error('Database Error:', error);
    throw new Error('Failed to fetch the latest posts.');
  }
}

/**
 * Fetches the number of posts and users for card data
 * @returns {Promise<{ numberOfUsers: number, numberOfPosts: number }>} - A promise that resolves to an object containing the number of users and posts
 */
export async function fetchCardData(): Promise<{ numberOfUsers: number; numberOfPosts: number; }> {
  try {
    const postCountPromise = sql`SELECT COUNT(*) FROM posts`;
    const userCountPromise = sql`SELECT COUNT(*) FROM customers`;

    const data = await Promise.all([
      postCountPromise,
      userCountPromise,
    ]);

    const numberOfPosts = Number(data[0].rows[0].count ?? '0');
    const numberOfUsers = Number(data[1].rows[0].count ?? '0');

    return {
      numberOfUsers,
      numberOfPosts,
    };
  } catch (error) {
    console.error('Database Error:', error);
    throw new Error('Failed to fetch card data.');
  }
}

/**
 * Number of items per page for pagination
 * @type {number}
 */
const ITEMS_PER_PAGE: number = 6;

/**
 * Function for searching posts with pagination
 * @param {string | string[]} query - The search query
 * @param {number} currentPage - The current page number for pagination
 * @returns {Promise<PostsTable[]>} - A promise that resolves to an array of filtered posts
 */
export async function fetchFilteredPosts(
  query: string | string[],
  currentPage: number,
): Promise<PostsTable[]> {
  const offset = (currentPage - 1) * ITEMS_PER_PAGE;

  try {
    const posts = await sql<PostsTable>`
      SELECT
        posts.id,
        posts.author_id,
        users.username,
        users.email,
        users.image_url,
        posts.title,
        posts.body,
        posts.date
      FROM posts
      JOIN users ON posts.author_id = users.id
      WHERE
        users.username ILIKE ${`%${query}%`} OR
        users.email ILIKE ${`%${query}%`} OR
        posts.date::text ILIKE ${`%${query}%`} OR
        posts.title ILIKE ${`%${query}%`} OR
        posts.body ILIKE ${`%${query}%`}
        
      ORDER BY posts.date DESC
      LIMIT ${ITEMS_PER_PAGE} OFFSET ${offset}
    `;

    return posts.rows;
  } catch (error) {
    console.error('Database Error:', error);
    throw new Error('Failed to fetch posts.');
  }
}

/**
 * Fetches the total number of pages for the given query
 * @param {string | string[]} query - The search query
 * @returns {Promise<number>} - A promise that resolves to the total number of pages
 */
export async function fetchPostsPages(query: string | string[]): Promise<number> {
  try {
    const count = await sql`SELECT COUNT(*)
    FROM posts
    JOIN users ON posts.author_id = users.id
    WHERE
      users.username ILIKE ${`%${query}%`} OR
      users.email ILIKE ${`%${query}%`} OR
      posts.date::text ILIKE ${`%${query}%`} OR
      posts.title ILIKE ${`%${query}%`} OR
      posts.body ILIKE ${`%${query}%`}
  `;

    const totalPages = Math.ceil(Number(count.rows[0].count) / ITEMS_PER_PAGE);
    return totalPages;
  } catch (error) {
    console.error('Database Error:', error);
    throw new Error('Failed to fetch total number of posts.');
  }
}

/**
 * Fetches a post by its ID
 * @param {string} id - The ID of the post
 * @returns {Promise<PostView>} - A promise that resolves to the post data
 */
export async function fetchPostById(id: string): Promise<PostView> {
  try {
    const data = await sql<PostView>`
      SELECT
        users.username,
        users.image_url,
        posts.id,
        posts.author_id,
        posts.title,
        posts.body,
        posts.date
      FROM posts
      JOIN users ON posts.author_id = users.id
      WHERE posts.id = ${id};
    `;

    const post = data.rows;

    console.log(post);
    return post[0];
  } catch (error) {
    console.error('Database Error:', error);
    throw new Error('Failed to fetch post.');
  }
}

/**
 * Fetches all users
 * @returns {Promise<UserPreview[]>} - A promise that resolves to an array of user previews
 */
export async function fetchUsers(): Promise<UserPreview[]> {
  try {
    const data = await sql<UserPreview>`
      SELECT
        id,
        COUNT(post_ids),
        image_url,
        username
      FROM users
      ORDER BY users ASC
    `;

    const users = data.rows;
    return users;
  } catch (err) {
    console.error('Database Error:', err);
    throw new Error('Failed to fetch all users.');
  }
}

/**
 * Fetches filtered users based on the query
 * @param {string} query - The search query
 * @returns {Promise<UsersTable[]>} - A promise that resolves to an array of filtered users
 */
export async function fetchFilteredUsers(query: string): Promise<UsersTable[]> {
  try {
    const data = await sql<UsersTable>`
        SELECT
          users.id,
          users.name,
          users.email,
          users.image_url,
          COUNT(posts.id) AS total_posts,
        FROM users
        LEFT JOIN posts ON users.id = posts.author_id
        WHERE
          users.username ILIKE ${`%${query}%`} OR
      users.email ILIKE ${`%${query}%`}
        GROUP BY users.id, users.username, users.email, users.image_url
        ORDER BY users.username ASC
      `;

    const users = data.rows;
    return users;
  } catch (err) {
    console.error('Database Error:', err);
    throw new Error('Failed to fetch user table.');
  }
}

/**
 * Fetches a user by their ID
 * @param {string} id - The ID of the user
 * @returns {Promise<UserRecord>} - A promise that resolves to the user data
 */
export async function fetchUserById(id: string): Promise<UserRecord> {
  try {
    const data = await sql<UserRecord>`
      SELECT
        users.id,
        users.username,
        users.email,
        users.image_url
      FROM users
      WHERE users.id = ${id};
    `;

    const user = data.rows;

    console.log(user);
    return user[0];
  } catch (error) {
    console.error('Database Error:', error);
    throw new Error('Failed to fetch user.');
  }
}

/**
 * Fetches posts for a user's profile by their ID
 * @param {string} id - The ID of the user
 * @returns {Promise<PostPreview[]>} - A promise that resolves to an array of post previews
 */
export async function fetchProfilePosts(id: string): Promise<PostPreview[]> {
  try {
    const data = await sql<PostPreview>`
      SELECT posts.id, users.username, users.image_url, posts.date, posts.title
      FROM posts
      JOIN users ON posts.author_id = users.id
      WHERE posts.author_id = ${id} 
      ORDER BY posts.date DESC
    `;

    const profilePosts = data.rows.map((post) => ({
      ...post,
    }));

    return profilePosts;
  } catch (error) {
    console.error('Database Error:', error);
    throw new Error('Failed to fetch the Profiles posts.');
  }
}