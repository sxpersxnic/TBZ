import { fetchFilteredPosts } from "@/lib/data";
import { postPath, profilePath } from "@/lib/definitions";
import { formatDateToLocal } from "@/lib/utils";
import clsx from "clsx";
import Image from 'next/image';
import Link from "next/link";

/**
 * Description placeholder
 *
 * @export
 * @async
 * @param {{
 *   query: string | string[];
 *   currentPage: number;
 * }} param0
 * @param {(string | {})} param0.query
 * @param {number} param0.currentPage
 * @returns {unknown}
 */
export default async function PostsTable({
  query,
  currentPage
}: {
  query: string | string[];
  currentPage: number;
}) {
  const posts = await fetchFilteredPosts(query, currentPage);

  return (
    <div className="mt-6 flow-root w-full">
      <div className="inline-block min-w-full align-middle">
        <div className="p-2 md:pt-0">
          <div className="md:hidden">
            {posts?.map((post) => (
              <div
                key={post.id}
                className="mb-2 w-full rounded-md p-4"
              >
                <div className="flex items-center justify-between border-b pb-4 w-full">
                  <div className="mb-2 flex items-center gap-2 justify-between w-full">
                    <Link href={`/profile/${post.author_id}`} className="flex flex-row items-center">
                      <Image
                        src={post.image_url}
                        className="mr-2 rounded-full"
                        width={28}
                        height={28}
                        alt={`${post.username}'s profile picture`}
                      />
                      <p>{post.username}</p>
                    </Link>
                    <p className="text-sm">{formatDateToLocal(post.date.toISOString())}</p>
                  </div>
                </div>
                <div className="flex w-full items-center justify-between pt-4">
                  <div>
                    <Link href={`/blog/${post.id}/post`} className="text-xl font-medium">{post.title}</Link>
                  </div>
                </div>
              </div>
            ))}
          </div>
          <div className="hidden min-w-full md:table">
            <div className="border rounded-md border-[--foreground] overflow-hidden">
              {posts.map((post, i) => {
                const dateStr = post.date.toISOString();

                return (
                  <div
                    key={post.id}
                    className={clsx(
                      'flex flex-row items-center justify-between py-4 hover:bg-[--hoverbg] hover:text-[--hovertext] px-6 transition-colors',
                      {
                        'border-t border-[--foreground]': i !== 0,
                      },
                    )}
                  >
                    <div className="flex flex-col items-start justify-center w-full">
                      <div className="flex flex-row items-center justify-between w-full mb-6">
                        <Link href={profilePath(post.author_id)} className="flex flex-row items-center justify-between w-full">
                          <div className="flex flex-row items-center">
                            <Image
                              src={post.image_url}
                              alt={`${post.username}'s profile picture`}
                              className="mr-4 rounded-full"
                              width={32}
                              height={32}
                            />
                            <p className="truncate text-sm font-semibold md:text-based">
                              {post.username}
                            </p>
                          </div>
                          <div className="flex flex-row items-center justify-center">
                            <p className="text-sm">{formatDateToLocal(dateStr)}</p>
                          </div>
                        </Link>
                      </div>
                      <div className="flex flex-row items-center justify-start w-full">
                        <Link href={postPath(post.id)} className="truncate text-xl font-medium md:text-base w-full">
                          {post.title}
                        </Link>
                      </div>
                    </div>
                  </div>
                );
              })}
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}