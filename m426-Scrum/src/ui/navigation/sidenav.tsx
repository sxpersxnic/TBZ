import NavLinks from "./nav-links";
import { ArrowRightStartOnRectangleIcon } from "@heroicons/react/24/outline";
import Logo from "../components/logo";
import { signout } from "@/lib/auth/core";

/**
 * Description placeholder
 *
 * @export
 * @returns {*}
 */
export default function SideNav() {
  return (
    <div className="flex h-full flex-col px-3 py-4 md:px-2">      
        <div className="w-full md:h-30">
          <Logo />
        </div>
      <div className="flex grow flex-row justify-between space-x-2 md:flex-col md:space-x-0 md:space-y-2">
        <NavLinks />
        <div className="hidden h-auto w-full grow rounded-md md:block"></div>
        <form
          action={
            async () => {
              'use server';
              await signout();
            }
          }
        >
          <button className="flex h-[48px] w-full grow items-center justify-center gap-2 rounded-md border-2 border-[--foreground] p-3 text-sm font-medium md:flex-none md:justify-start md:p-2 md:px-3">
            <ArrowRightStartOnRectangleIcon className="w-6" />
            <div className="hidden md:block">Sign Out</div>
          </button>
        </form>
      </div>
    </div>
  );
}