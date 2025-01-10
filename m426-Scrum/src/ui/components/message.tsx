import { ExclamationCircleIcon } from "@heroicons/react/24/outline";

/**
 * Description placeholder
 *
 * @export
 * @param {{ message: string }} param0
 * @param {string} param0.message
 * @returns {*}
 */
export default function Message({ message }: { message: string }) {
  return (
    <p className="text-red-500 flex flex-row justify-start items-center gap-1"><ExclamationCircleIcon className="h-4"/><span className="text-xs">{message}</span></p>
  );
}