'use client';

import Link from "next/link";
import { Button } from "../components/button";
import InputField from "../components/input-field";
import { useActionState } from "react";
import { ExclamationCircleIcon } from "@heroicons/react/24/outline";
import { signin } from "@/lib/auth/core";
import { FormState } from "@/lib/definitions";

/**
 * Description placeholder
 *
 * @export
 * @returns {*}
 */
export default function SigninForm() {
  const initialState: FormState = { message: null, errors: {} }
  const [state, action, pending] = useActionState(signin, initialState)

  return (
    <form action={action} className="flex flex-col justify-center items-center w-full h-full">
      <div className="flex flex-col justify-center items-center p-4 md:p-6 md:w-80">

        <div className="w-full mb-2">
          <InputField type="Email" />
        </div>

        <div className="w-full mb-2">
          <InputField type="Password" />
        </div>

      </div>
      <div className="flex flex-col md:justify-end mt-6 px-8 gap-4">
        <div className="w-full flex flex-row gap-2 justify-center items-center">
          {state?.message && (
            <>
              <ExclamationCircleIcon className="h-5 w-5 text-red-500" />
              <p className="text-sm text-red-500">{state?.message}</p>
            </>
          )}
        </div>
        <div className="flex flex-row items-center justify-end mb-2 gap-4">
          <Link
            href="/"
            className="flex h-10 items-center rounded-lg bg-red-500 dark:bg-red-800 px-4 text-sm font-medium transition-colors hover:bg-red-600"
          >
            Cancel
          </Link>
          <Button type="submit" disabled={pending} aria-disabled={pending}>
            {pending ? "Loading..." : "Submit"}
          </Button>
          <Link href="/auth/register" className="flex items-center border-2 border-[--foreground] h-10 px-4 text-sm font-medium transition-colors hover:bg-[--hoverbg] hover:text-[--hovertext] rounded-lg">
            Create Account
          </Link>
        </div>
      </div>
    </form>
  );
}