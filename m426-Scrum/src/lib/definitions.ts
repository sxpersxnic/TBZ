import { z } from 'zod';
import { SessionOptions } from 'iron-session';

/**
 * Description placeholder
 *
 * @export
 * @typedef {User}
 */
export type User = {
  id:         string;
  username:   string;
  email:      string;
  password:   string;
  image_url:  string;
};


/**
 * Description placeholder
 *
 * @export
 * @typedef {FetchUser}
 */
export type FetchUser = {
  id: string;
  username: string;
  email: string;
  image_url: string;
} | null

/**
 * Description placeholder
 *
 * @export
 * @typedef {Post}
 */
export type Post = {
  id:         string;
  author_id:  string;
  title:      string;
  body:       string;
  date:       string;
}

/**
 * Description placeholder
 *
 * @export
 * @typedef {SessionPayload}
 */
export type SessionPayload = {
  userId:     string,
  expiresAt:  Date,
}

/**
 * Description placeholder
 *
 * @export
 * @typedef {UsersPosts}
 */
export type UsersPosts = {
  user_id:    string;
  post_id:    string;
  session_id: string;
  username:   string;
  email:      string;
  image_url:  string;
  title:      string;
  body:       string;
  date:        string;
}

/**
 * Description placeholder
 *
 * @export
 * @typedef {UserRecord}
 */
export type UserRecord = {
  id: string;
  username: string;
  email: string;
  image_url: string;
}

/**
 * Description placeholder
 *
 * @export
 * @typedef {PostPreview}
 */
export type PostPreview = {
  id:         string;
  author_id:  string;
  username:   string;
  image_url:  string;
  date:       Date;
  title:      string;
}

/**
 * Description placeholder
 *
 * @export
 * @typedef {UserPreview}
 */
export type UserPreview = {
  id:           string;
  image_url:    string;
  username:     string;
  total_posts:  number;
}

/**
 * Description placeholder
 *
 * @export
 * @typedef {PostView}
 */
export type PostView = {
  id: string;
  author_id: string;
  username: string;
  image_url: string;
  title: string;
  body: string;
  date: Date;
}

/**
 * Description placeholder
 *
 * @export
 * @typedef {UsersTable}
 */
export type UsersTable = {
  id:         string;
  image_url?: string;
  username:   string;
  email:      string;
  password:   string;
}

/**
 * Description placeholder
 *
 * @export
 * @typedef {PostsTable}
 */
export type PostsTable = {
  id:         string;
  author_id:  string;
  username: string;
  email: string;
  image_url: string;
  title:      string;
  body:       string;
  date:       Date;
}

/**
 * Description placeholder
 *
 * @export
 * @typedef {SessionsTable}
 */
export type SessionsTable = {
  id:       string;
  userId:   string;
  expires:  Date;
  created:  Date;
}

/**
 * Description placeholder
 *
 * @export
 * @typedef {PostForm}
 */
export type PostForm = {
  id:     string;
  title:  string;
  body:   string;
}

/**
 * Description placeholder
 *
 * @export
 * @typedef {CardData}
 */
export type CardData = {
  numberOfUsers: number;
  numberOfPosts: number;
}

/**
 * Description placeholder
 *
 * @export
 * @typedef {Revenue}
 */
export type Revenue = {
  month:      string;
  author_id:  string;
  revenue:    number;
}

/**
 * Description placeholder
 *
 * @type {*}
 */
export const SignUpFormSchema = z.object({
  username: z
    .string()
    .min(2, { message: 'Username must be at least 2 characters long.' })
    .trim(),
  email: z
    .string()
    .email({ message: 'Please enter a valid email.' })
    .trim(),
  password: z
    .string()
    .min(8, { message: 'Be at least 8 characters long' })
    .regex(/[a-zA-z]/, { message: 'Contain at least one letter.' })
    .regex(/[0-9]/, {message: 'Contain at least one number.' })
    .regex(/[^a-zA-Z0-9]/, {
      message: 'Contain at least one special character.',
    })
    .trim(),
})

/**
 * Description placeholder
 *
 * @type {*}
 */
export const SignInFormSchema = z.object({
  username: z.string().trim().min(1, { message: 'Missing Username'}),
  password: z.string().trim().min(1, { message: 'Missing Password'}),
})

/**
 * Description placeholder
 *
 * @type {*}
 */
export const PostFormSchema = z.object({
  title: z.string().min(1, "Missing Title").trim(),
  body: z.string().min(1, "Missing Body").trim()
});

/**
 * Description placeholder
 *
 * @export
 * @typedef {FormState}
 */
export type FormState = 
| {
    errors?: {
      username?: string[];
      email?: string[];
      password?: string[];
    };
    message?: string | null;
  }
| undefined;

/**
 * Description placeholder
 *
 * @export
 * @typedef {PostFormState}
 */
export type PostFormState =
| {
    errors?: {
      title?: string[];
      body?: string[];
    };
    message?: string | null;
  } 
| undefined;

/**
 * Description placeholder
 *
 * @type {"/user/default-32x32.png"}
 */
export const defaultPfp = '/user/default-32x32.png';


/**
 * Description placeholder
 *
 * @param {string} id
 * @returns {string}
 */
export const postPath = (id: string) => {
  return `/blog/${id}/post`;
}

/**
 * Description placeholder
 *
 * @param {string} id
 * @returns {string}
 */
export const profilePath = (id: string) => {
  return `/blog/${id}/profile`;
}

export const ascending: string[] = [
  "ASC",
  "asc",
  "ascending",
  "ASCENDING"
];

export const descending: string[] = [
  "DESC",
  "desc",
  "descending",
  "DESCENDING"
];

export interface SessionData {
  userId?: string;
  isLoggedIn?: boolean;
  isBlocked?: boolean;
}

export const defaultSession: SessionData = {
  isLoggedIn: false
}

export const sessionOptions: SessionOptions = {
  password: process.env.SECRET!,
  cookieName: 'session',
  cookieOptions: {
    httpOnly: true,
    secure: process.env.NODE_ENV === "production"
  }
}
