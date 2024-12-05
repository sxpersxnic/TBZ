/**
 * Description placeholder
 *
 * @param {string} dateStr
 * @param {string} [locale='en-US']
 * @returns {*}
 */
export const formatDateToLocal = (
  dateStr: string,
  locale: string = 'en-US',
) => {
  const date = new Date(dateStr);
  const options: Intl.DateTimeFormatOptions = {
    day: 'numeric',
    month: 'short',
    year: 'numeric',
  };
  const formatter = new Intl.DateTimeFormat(locale, options);
  return formatter.format(date);
};

/**
 * Generates Pagination for pages.
 * 
 * - If the total number of pages is 7 or less, all pages are displayed without any ellipsis.
 * 
 * - If the current page is among the first 3 pages, the first 3, an ellipsis and the last 2 pages are shown.
 *
 * - If the current page is among the last 3 pages, the first 2, an ellipsis and the last 3 pages are shown.
 * 
 * - If the current page is somewhere in the middle, the first page, an ellipsis, the current page and its neighbors, another ellipsis, and the last page are shown.
 * 
 * @param {number} currentPage
 * @param {number} totalPages
 * @returns {*}
 */
export const generatePagination = (currentPage: number, totalPages: number) => {
  // If the total number of pages is 7 or less,
  // display all pages without any ellipsis.
  if (totalPages <= 7) {
    return Array.from({ length: totalPages }, (_, i) => i + 1);
  }

  // If the current page is among the first 3 pages,
  // show the first 3, an ellipsis, and the last 2 pages.
  if (currentPage <= 3) {
    return [1, 2, 3, '...', totalPages - 1, totalPages];
  }

  // If the current page is among the last 3 pages,
  // show the first 2, an ellipsis, and the last 3 pages.
  if (currentPage >= totalPages - 2) {
    return [1, 2, '...', totalPages - 2, totalPages - 1, totalPages];
  }

  // If the current page is somewhere in the middle,
  // show the first page, an ellipsis, the current page and its neighbors,
  // another ellipsis, and the last page.
  return [
    1,
    '...',
    currentPage - 1,
    currentPage,
    currentPage + 1,
    '...',
    totalPages,
  ];
};

/**
 * Utility function to generate a random
 *
 * @param {number} length
 * @param {string} chars
 * @returns {string} - Random hex string
 */
function getRandomHex(length: number, chars: string = '0123456789abcdef'): string {
  let result = '';
  for (let i = 0; i < length; i++) {
      const randomIndex = Math.floor(Math.random() * chars.length);
      result += chars[randomIndex];
  }
  return result;
}

/**
 * Generates a valid UUIDv4.
 * 
 * Format: **xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx**
 *  - x: A random Hex string `(0123456789abcdef)`
 *  - 4: Version
 *  - y: Variant `(ab89)`
 *
 * @export
 * @returns {string} 36 Characters long String.
 */
export function getUuid(): string {
  const variant: string = 'ab89';
  const uuid: string = `${getRandomHex(8)}-${getRandomHex(4)}-4${getRandomHex(3)}-${getRandomHex(1, variant)}${getRandomHex(3)}-${getRandomHex(12)}`;

  return uuid;
}