import { getSession } from '@/lib/auth/session';
import type { NextAuthConfig } from 'next-auth';
 
export const authConfig = {
  pages: {
    signIn: '/auth/login',
  },
  callbacks: {
    async authorized({ request: { nextUrl } }) {
      const session = await getSession();
      const isLoggedIn = !!session.isLoggedIn;
      const isOnBlog = nextUrl.pathname.startsWith('/blog');
      if (isOnBlog) {
        if (isLoggedIn) return true;
        return false; // Redirect unauthenticated users to login page
      } else if (isLoggedIn) {
        return Response.redirect(new URL('/blog', nextUrl));
      }
      return true;
    },
  },
  providers: [], // Add providers with an empty array for now
} satisfies NextAuthConfig;