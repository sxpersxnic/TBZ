'use client';

import { generatePagination } from "@/lib/utils";
import { ArrowLeftIcon, ArrowRightIcon } from "@heroicons/react/24/outline";
import clsx from "clsx";
import Link from "next/link";
import { usePathname, useSearchParams } from "next/navigation";
import React from "react";

/**
 * Description placeholder
 *
 * @export
 * @param {{ totalPages: number }} param0
 * @param {number} param0.totalPages
 * @returns {*}
 */
export default function Pagination({ totalPages }: { totalPages: number }) {
  const pathname = usePathname();
  const searchParams = useSearchParams();
  const currentPage = Number(searchParams.get('page')) || 1;
  
  const allPages = generatePagination(currentPage, totalPages);

  const createPageUrl = (pageNumber: number | string) => {
    const params = new URLSearchParams(searchParams);
    params.set('page', pageNumber.toString());
    return `${pathname}?${params.toString()}`;
  };

  return (
    <>
      <div className="inline-flex">
        <PaginationArrow 
          direction="left"
          href={createPageUrl(currentPage - 1)}
          isDisabled={currentPage <= 1}
        />

        <div className="flex -space-x-px">
          {allPages.map((page, index) => {
            let position: 'first' | 'last' | 'single' | 'middle' | undefined;

            if (index === 0) position = 'first';
            if (index === allPages.length - 1) position = 'last';
            if (allPages.length === 1) position = 'single';
            if (page === '...') position = 'middle';

            return (
              <PaginationNumber
                key={page}
                href={createPageUrl(page)}
                page={page}
                position={position}
                isActive={currentPage === page}
              />
            );
          })}
        </div>

        <PaginationArrow
          direction="right"
          href={createPageUrl(currentPage + 1)}
          isDisabled={currentPage >= totalPages}
        />
      </div>
    </>
  );
}

/**
 * Description placeholder
 *
 * @param {{
 *   page: number | string;
 *   href: string;
 *   isActive: boolean;
 *   position?:'first' | 'last' | 'middle' | 'single';
 * }} param0
 * @param {(string | number)} param0.page
 * @param {string} param0.href
 * @param {boolean} param0.isActive
 * @param {("first" | "last" | "middle" | "single")} param0.position
 * @returns {*}
 */
function PaginationNumber({
  page,
  href,
  isActive,
  position,
}: {
  page: number | string;
  href: string;
  isActive: boolean;
  position?:'first' | 'last' | 'middle' | 'single';
}) {
  const className = clsx(
    'flex h-10 w-10 items-center justify-center text-sm border',
    {
      'rounded-l-md': position === 'first' || position === 'single',
      'rounded-r-md': position === 'last' || position === 'single',
      'z-10 bg-[--foreground] border-[--foreground] text-[--background]': isActive,
      'hover:bg-[--hoverbg] hover:text-[--hovertext]': !isActive && position !== 'middle',
      'text-[--foreground]': position === 'middle',
    },
  );

  return isActive || position === 'middle' ? (
    <div className={className}>{page}</div>
  ) : (
    <Link href={href} className={className}>
      {page}
    </Link>
  );
};

/**
 * Description placeholder
 *
 * @param {{
 *   href: string;
 *   direction: 'left' | 'right';
 *   isDisabled?: boolean;
 * }} param0
 * @param {string} param0.href
 * @param {("left" | "right")} param0.direction
 * @param {boolean} param0.isDisabled
 * @returns {*}
 */
function PaginationArrow({
  href,
  direction,
  isDisabled,
}: {
  href: string;
  direction: 'left' | 'right';
  isDisabled?: boolean;
}) {
  const className = clsx(
    'flex h-10 w-10 items-center justify-center rounded-md border',
    {
      'pointer-events-none': isDisabled,
      'hover:bg-[--hoverbg] hover:text-[--hovertext]': !isDisabled,
      'mr-2 md:mr-4': direction === 'left',
      'ml-2 md:ml-4': direction === 'right',
    },
  );

  const icon =
    direction === 'left' ? (
      <ArrowLeftIcon className="w-4" />
    ) : (
      <ArrowRightIcon className="w-4" />
    );
  return isDisabled ? (
    <div className={className}>{icon}</div>
  ) : (
    <Link className={className} href={href}>{icon}</Link>
  );
}