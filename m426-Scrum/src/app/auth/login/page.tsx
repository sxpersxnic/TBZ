import SigninForm from "@/ui/forms/signin-form";

/**
 * Description placeholder
 *
 * @export
 * @async
 * @returns {unknown}
 */
export default function Page() {
  return (
    <main className="flex flex-col w-full h-screen items-center justify-center py-8">
      <div className="rounded-lg shadow-md p-2">
        <h1 className="font-medium text-xl">Login</h1>
        <div className="flex w-full justify-center items-center">
          <SigninForm />
        </div>
      </div>
    </main>
  );
}