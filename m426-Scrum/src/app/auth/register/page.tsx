import SignupForm from "@/ui/forms/signup-form";

/**
 * Description placeholder
 *
 * @export
 * @async
 * @returns {unknown}
 */
export default async function Page() {
  return (
    <main className="flex flex-col w-full h-screen items-center justify-center py-8">
      <div className="rounded-lg shadow-md p-2">
        <h1 className="font-medium text-xl">Create Account</h1>
        <div className="flex w-full justify-center items-center">
          <SignupForm />
        </div>
      </div>
    </main>
  );
}