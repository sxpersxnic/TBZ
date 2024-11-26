import { deletePost } from "@/lib/actions";
import { PencilIcon, TrashIcon } from "@heroicons/react/24/outline";
import Link from "next/link";

/**
 * Description placeholder
 *
 * @export
 * @param {{ id: string }} param0
 * @param {string} param0.id
 * @returns {*}
 */
export function DeletePost({ id }: { id: string }) {
  const deleteInvoiceWithId = deletePost.bind(null, id);
 
  return (
    <form action={deleteInvoiceWithId}>
      <button type="submit" className="rounded-md border p-2 md:hover:bg-[--hoverbg] md:hover:text-[--hovertext]">
        <span className="sr-only">Delete</span>
        <TrashIcon className="w-4" />
      </button>
    </form>
  );
}

/**
 * Description placeholder
 *
 * @export
 * @param {{ id: string }} param0
 * @param {string} param0.id
 * @returns {*}
 */
export function UpdatePost({ id }: { id: string }) {
  return (
    <Link
      href={`/blog/${id}/edit`}
      className="rounded-md border p-2 md:hover:bg-[--hoverbg] md:hover:text-[--hovertext]"
    >
      <PencilIcon className="w-4"/>
    </Link>
  );
}
