import { ExclamationCircleIcon } from "@heroicons/react/24/outline";

/**
 * Description placeholder
 *
 * @export
 * @param {{ error: string[] }} param0
 * @param {{}} param0.error
 * @returns {*}
 */
export default function Error({ error }: { error: string[] }) {
  return (
    <p className="text-red-500 flex flex-row justify-start items-center gap-1">
      <ExclamationCircleIcon className="h-4"/>
    <ul className="text-xs">{error.map((err) => (
      <li>{err}</li>
    ))}</ul></p>
  );
}