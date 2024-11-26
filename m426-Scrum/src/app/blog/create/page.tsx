'use client';
import CreateForm from "@/ui/forms/create-form";
import Breadcrumbs from "@/ui/navigation/breadcrumbs";

/**
 * Description placeholder
 *
 * @export
 * @returns {*}
 */
export default function Page() {
  return ( 
    <main className="flex flex-col w-full h-fit justify-center px-4 border-2 rounded-lg shadow-md">      
      <div className="flex flex-col items-center w-full p-2">
        <div className="flex w-full justify-center items-center">
          <CreateForm />
        </div>
      </div>
    </main>
  );
}