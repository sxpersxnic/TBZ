import { fetchPostsPages } from "@/lib/data";
import Search from "@/ui/components/search";
import { Suspense } from "react";
import Loading from "./loading";
import Pagination from "@/ui/navigation/pagination";
import SortedPosts from "@/ui/blog/post/sorted-posts";

/**
 * Description placeholder
 *
 * @typedef {SearchParams}
 */
type SearchParams = Promise<{ [key: string]: string | string[] | undefined }>

/**
 * Description placeholder
 *
 * @export
 * @async
 * @param {{
 *   searchParams: SearchParams
 * }} props
 * @returns {Promise<React.JSX.Element>}
 */
export default async function Page(props: {
  searchParams: SearchParams
}): Promise<React.JSX.Element> {

  const searchParams = await props.searchParams;
  const query = searchParams.query || '';
  const currentPage = Number(searchParams.page) || 1;
  const totalPages = await fetchPostsPages(query);

  return (
    <div className="w-full">
      <div className="mt-4 flex items-center justify-between gap-2 md:mt-8">
        <Search placeholder="Search posts..." />
      </div>
      <Suspense key={currentPage} fallback={<Loading/>}>
        <SortedPosts query={query} currentPage={currentPage}  />
      </Suspense>
      <div className="mt-5 flex w-full justify-center">
        <Pagination totalPages={totalPages} />
      </div>
    </div>
  );
}