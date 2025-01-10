'use client';

import { clsx } from 'clsx';
import Link from 'next/link';
import { usePathname } from 'next/navigation';

/**
 * Description placeholder
 *
 * @interface Breadcrumb
 * @typedef {Breadcrumb}
 */
interface Breadcrumb {
  /**
   * Description placeholder
   *
   * @type {string}
   */
  label: string;
  /**
   * Description placeholder
   *
   * @type {string}
   */
  href: string;
  /**
   * Description placeholder
   *
   * @type {?boolean}
   */
  active?: boolean;
}

/**
 * Description placeholder
 *
 * @export
 * @param {{
 *   breadcrumbs: Breadcrumb[];
 * }} param0
 * @param {{}} param0.breadcrumbs
 * @returns {*}
 */
export default function Breadcrumbs({
  breadcrumbs,
}: {
  breadcrumbs: Breadcrumb[];
}) {

  return (
    <nav aria-label="Breadcrumb" className="mb-6 block">
      <ol className="flex text-xl md:text-2xl">
        {breadcrumbs.map((breadcrumb, index) => (
          <li
            key={breadcrumb.href}
            aria-current={breadcrumb.active}
            className={clsx(
              breadcrumb.active ? 'text-[--foreground]' : 'text-gray-500 dark:text-gray-400',
            )}
          >
            <Link href={breadcrumb.href}>{breadcrumb.label}</Link>
            {index < breadcrumbs.length - 1 ? (
              <span className="mx-3 inline-block">/</span>
            ) : null}
          </li>
        ))}
      </ol>
    </nav>
  );
}