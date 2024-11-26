import { fetchFilteredPosts } from "@/lib/data";
import PostSorter from "./post-sort";

export default async function SortedPosts({
  query,
  currentPage
}: {
  query: string | string[];
  currentPage: number;
}) {
  const posts = await fetchFilteredPosts(query, currentPage);

  return (
    <div>
      <PostSorter posts={posts} />
    </div>
  );
}