import Link from "next/link";
import { ArrowRightIcon } from '@heroicons/react/24/outline';
import Logo from "@/ui/components/logo";

/**
 * Homepage
 *
 * @default
 * @export
 * @returns {React.JSX.Element} HTML
 */
export default function Page(): React.JSX.Element {
  return (
    <main className="flex min-h-screen flex-col p-6 w-full">
      <Logo />
      <div className="mt-4 flex grow justify-end gap-4 md:flex-row">
        <div className="flex flex-col justify-center gap-6 rounded-lg px-6 py-10 md:w-2/5 md:px-20 ">
          <Link
            href="/blog"
            className="flex items-center gap-5 self-start rounded-lg border-2 border-[--foreground] px-6 py-3 text-sm font-medium transition-colors md:text-base"
          >
            <span>Blog</span> <ArrowRightIcon className="w-5 md:w-6" />
          </Link>  
        </div>
      </div>
    </main>
  );
}
