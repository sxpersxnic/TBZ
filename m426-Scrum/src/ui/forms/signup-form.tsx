'use client';

import Link from "next/link";
import { Button } from "../components/button";
import InputField from "../components/input-field";
import { FormState } from "@/lib/definitions";
import { useActionState } from "react";
import Error from "../components/error";
import { signup } from "@/lib/auth/core";
import Message from "../components/message";
import { ExclamationCircleIcon } from "@heroicons/react/24/outline";

/**
 * Description placeholder
 *
 * @export
 * @returns {*}
 */
export default function SignupForm() {
  const initialState: FormState = { message: null, errors: {} }
  const [state, action, pending] = useActionState(signup, initialState)

  return (
    <form action={action} className="flex flex-col justify-center items-center w-full h-full">
      <div className="flex flex-col justify-center items-center p-4 md:p-6 md:w-80">
        
        <div className="w-full mb-2">
          <InputField type="Username" />
          {state?.errors?.username && <Error error={state.errors.username}/>}
        </div>
        
        <div className="w-full mb-2">
          <InputField type="Email" />
          {state?.errors?.email && <Error error={state.errors.email}/>}
        </div>
        
        <div className="w-full mb-2">
          <InputField type="Password" />          
          {state?.errors?.password && 
            <div className="flex flex-col">
              <div className="flex flex-row gap-1">
                <ExclamationCircleIcon className="h-4 text-red-500"/>
                <p className="text-xs text-red-500 flex flex-row justify-start items-center">Password must:</p>
              </div>
              <ul className="flex flex-col text-xs text-red-500 justify-start items-start px-6 appearance-auto">{state?.errors?.password.map((err) => (
                  <li>{err}</li>
                ))}
              </ul>
            </div>
          }
        </div>
        
      </div>
      <div>
        {state?.message && <Message message={state?.message} />}
      </div>
      <div className="flex md:justify-end mt-6 px-8 gap-4">
        <div className="flex flex-row items-center justify-end mb-2 gap-4">      
            <Link
              href="/"
              className="flex h-10 items-center rounded-lg bg-red-500 dark:bg-red-800 px-4 text-sm font-medium transition-colors hover:bg-red-600"
              >
              Cancel
            </Link>
            <Button type="submit" disabled={pending} aria-disabled={pending}>
              { pending ?"Loading..." : "Submit" }
            </Button>
        </div>
      </div>
    </form>
  );
}