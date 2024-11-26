import Link from "next/link";
import InputField from "../components/input-field";
import { useFormStatus } from "react-dom";
import { createPost } from "@/lib/actions";
import { PostFormState } from "@/lib/definitions";
import { useActionState } from "react";
import { Button } from "../components/button";
import Error from "../components/error";

/**
 * Description placeholder
 *
 * @export
 * @returns {*}
 */
export default function CreateForm() {
  const initialState: PostFormState = { message: null, errors: {} }
  const { pending } = useFormStatus();
  const [state, formAction] = useActionState(createPost, initialState)

  return (
    <form action={formAction} className="flex flex-col justify-center items-center w-full h-full">
      <div className="flex flex-col justify-center items-center p-4 md:p-6 md:w-80">
        <div className="w-full mb-2">
          <InputField type="Title" autoFocus />
          {state?.errors?.title && <Error error={state.errors.title}/>}
        </div>
        <div className="w-full h-20">
          <InputField type="Body" className="flex flex-row justify-end items-end h-20"/>
          {state?.errors?.body && <Error error={state.errors.body}/>}
        </div>
      </div>
      <div className="flex md:justify-end mt-6 px-8 gap-4">
        <div className="flex flex-row items-center justify-end mb-2 gap-4">      
            <Link
              href="/blog"
              className="flex h-10 items-center rounded-lg bg-red-500 dark:bg-red-800 px-4 text-sm font-medium transition-colors hover:bg-red-600"
              >
              Cancel
            </Link>
            <Button type="submit" disabled={pending} aria-disabled={pending}>Create</Button>
        </div>
      </div>
    </form>
  );
}