'use client';

import { HomeIcon, MagnifyingGlassIcon, PlusIcon, UserIcon } from "@heroicons/react/24/outline";
import { usePathname } from "next/navigation";
import clsx from 'clsx';
import Link from "next/link";
import React from "react";

/**
 * Description placeholder
 *
 * @type {{}}
 */
const links = [
  { name: 'Home',     href: '/blog',         icon: HomeIcon            },
  { name: 'Profile',  href: `/blog/profile`, icon: UserIcon            },
  { name: 'Create',   href: '/blog/create',  icon: PlusIcon            },
];

/**
 * Description placeholder
 *
 * @export
 * @returns {*}
 */
export default function NavLinks() {
  const pathname = usePathname();

  return (
    <>
      {links.map((link) => {
        const LinkIcon = link.icon;
        return (
          <Link
            key={link.name}
            href={link.href}
            className={clsx(
              'flex h-[48px] grow items-center justify-center gap-2 rounded-md border-2 border-[--foreground] p-3 text-sm font-medium md:flex-none md:justify-start md:p-2 md:px-3 md:transition-color',
              {
                'bg-[--foreground] text-[--background]': pathname === link.href,
                'md:hover:bg-[--hoverbg] md:hover:text-[--hovertext]': pathname !== link.href
              },
            )}
          >
            <LinkIcon className="w-6" />
            <p className="hidden md:block">{link.name}</p>
          </Link>
        );
      })}
    </>
  );
}