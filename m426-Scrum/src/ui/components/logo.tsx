"use client";

import { ChatBubbleLeftIcon } from "@heroicons/react/24/outline";
import Link from "next/link";
import { usePathname } from "next/navigation";

/**
 * Description placeholder
 *
 * @export
 * @returns {*}
 */
export default function Logo() {

  const pathname = usePathname();

  if (pathname === "/") { 
    return (
      <div className="flex h-14 md:h-28 w-full shrink-0 items-center rounded-lg solide border-2 border-[--foreground] p-2 md:p-4 mb-2 justify-start">
        <div className="flex flex-row w-full items-center text-[--foreground] justify-between md:justify-normal leading-none" >
          <ChatBubbleLeftIcon className="w-6 md:w-14" />
        </div>
      </div>
    );
  } else if (pathname === "/blog") {
    return (
      <div className="flex h-14 md:h-36 w-full shrink-0 items-center md:items-end rounded-lg solide border-2 border-[--foreground] p-2 md:p-4 mb-2 justify-start">
        <div className="flex flex-row w-full items-center text-[--foreground] justify-between md:justify-normal leading-none" >
          <ChatBubbleLeftIcon className="w-6 md:w-14" />
        </div>
      </div>
    );
  } else {
    return (
      <Link href="/blog" className="flex h-14 md:h-36 w-full shrink-0 items-center md:items-end rounded-lg solide border-2 border-[--foreground] p-2 md:p-4 mb-2 justify-start">
        <div className="flex flex-row w-full items-center text-[--foreground] justify-between md:justify-normal leading-none" >
          <ChatBubbleLeftIcon className="w-6 md:w-14" />
        </div>
      </Link>
    );
  }
}