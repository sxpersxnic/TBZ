'use client';

import Link from "next/link";
import { Button } from "../components/button";
import { updatePost } from "@/lib/actions";
import { PostView, PostFormState } from "@/lib/definitions";
import { useActionState } from "react";
import InputField from "../components/input-field";
import Error from "../components/error";

/**
 * Description placeholder
 *
 * @export
 * @param {{ post: PostView }} param0
 * @param {PostView} param0.post
 * @returns {*}
 */
export default function EditForm({ post }: { post: PostView }) {
  const initialState: PostFormState = { message: null, errors: {} }
  const updatePostWithId = updatePost.bind(null, post.id)
  const [state, formAction, isPending] = useActionState(updatePostWithId, initialState)
2
  return (
    <form action={formAction} className="flex flex-col justify-center items-center w-full h-full">
      <div className="flex flex-col justify-center items-center p-4 md:p-6 md:w-80">
        <div className="w-full mb-2">
          <InputField type="Title" text={post.title} autoFocus />
          {state?.errors?.title && <Error error={state.errors.title}/>}
        </div>
        <div className="w-full">
          <InputField type="Body" text={post.body} />
          {state?.errors?.body && <Error error={state.errors.body}/>}
        </div>
      </div>
      {state?.message && <p>{state?.message}</p>}
      <div className="flex md:justify-end mt-6 px-8 gap-4">
        <div className="flex flex-row items-center justify-end mb-2 gap-4">      
            <Link
              href="/blog"
              className="flex h-10 items-center rounded-lg bg-red-500 dark:bg-red-800 px-4 text-sm font-medium transition-colors hover:bg-red-600"
              >
              Cancel
            </Link>
            <Button type="submit" disabled={isPending} aria-disabled={isPending}>Save</Button>
        </div>
      </div>
    </form>
  );
}