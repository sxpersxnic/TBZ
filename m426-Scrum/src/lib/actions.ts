'use server';

import { revalidatePath } from 'next/cache';
import { redirect } from 'next/navigation';
import { sql } from '@vercel/postgres';
import { PostFormSchema, PostFormState } from './definitions';
import { useSession } from './auth/session';
import { db } from '@/db';
import { eq } from 'drizzle-orm';
import { posts } from '@/db/schema';

/**
 * Description placeholder
 *
 * @export
 * @async
 * @param {PostFormState} prevState
 * @param {FormData} formData
 * @returns {unknown}
 */
export async function createPost(prevState: PostFormState, formData: FormData) {
  const user = await useSession();

  if (!user) {
    return {
      message: 'Author could not be found!'
    }
  }

  const validatedFields = PostFormSchema.safeParse({
    title: formData.get('title'),
    body: formData.get('body'),
  });

  if (!validatedFields.success) {
    return {
      errors: validatedFields.error.flatten().fieldErrors,
      message: 'Missing Fields. Failed to Create Post.',
    }
  }

  const { title, body } = validatedFields.data;
  const date = new Date().toISOString().split('T')[0];

  try {
    
    await sql`
      INSERT INTO posts (author_id, title, body, date)
      VALUES (${user.id}, ${title}, ${body}, ${date})
    `
  } catch (error) {
    return {
      message: 'Database Error: Failed to Create Post.',
    };
  }

  const data = await sql`
      SELECT *
      FROM posts
      WHERE 
        author_id = ${user.id} AND
        title = ${title} AND
        body = ${body} AND 
        date = ${date};
  `;

  const post = data.rows;

  revalidatePath(`/blog/${post[0].id}/post`);
  redirect(`/blog/${post[0].id}/post`);
}

/**
 * Description placeholder
 *
 * @export
 * @async
 * @param {string} id
 * @param {PostFormState} prevState
 * @param {FormData} formData
 * @returns {unknown}
 */
export async function updatePost(
  id: string,
  prevState: PostFormState,
  formData: FormData,
) {
  const validatedFields = PostFormSchema.safeParse({
    title: formData.get('title'),
    body: formData.get('body'),
  });

  if (!validatedFields.success) {
    return {
      errors: validatedFields.error.flatten().fieldErrors,
      message: 'Missing Fields. Failed to Update Post.',
    };
  }

  const { title, body } = validatedFields.data;

  try {
    await db.update(posts).set({title, body}).where(eq(posts.id, id))

  } catch (error) {
    return { message: 'Database Error: Failed to Update Post.'};
  }

  redirect('/profile');
}

/**
 * Description placeholder
 *
 * @export
 * @async
 * @param {string} id
 * @returns {void}
 */
export async function deletePost(id: string): Promise<void> {
  try {
    await sql`DELETE FROM posts WHERE id = ${id}`;
    revalidatePath('/blog');
    redirect('/blog');
  } catch (error) {
    console.error('Database Error: Failed to Delete Post.');
  }
}