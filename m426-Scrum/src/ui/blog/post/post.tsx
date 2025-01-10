import Image from "next/image";
import { formatDateToLocal } from "src/lib/utils";
import { postPath, profilePath } from "@/lib/definitions";
import Link from "next/link";
import { fetchPostById } from "@/lib/data";
import Breadcrumbs from "@/ui/navigation/breadcrumbs";

/**
 * Description placeholder
 *
 * @export
 * @async
 * @param {{ id: string }} param0
 * @param {string} param0.id
 * @returns {unknown}
 */
export default async function Post({ id }: { id: string }) {
  const post = await fetchPostById(id);
  const dateStr = post.date + '';

  return (
    <>
      <div>
        <Breadcrumbs
          breadcrumbs={[
            { label: 'Posts', href: '/blog' },
            { label: `${post.username}`, href: profilePath(post.author_id) },
            { label: `${post.title}`, href: postPath(id), active: true },
          ]}
        />
      </div>
      <div className="flex w-full h-full flex-col">
        <div className="flex grow flex-col justify-between rounded-xl p-4">
          <div className="px-6 border rounded-md">

            <div
              className="flex flex-row items-center justify-between py-4"
            >
              <div className="flex flex-col items-start justify-center w-full">
                <div className="flex flex-row items-center justify-between w-full mb-6 text-base py-4 border-b">
                  <Link href={profilePath(post.author_id)} className="flex flex-row items-center justify-center">
                    <Image
                      src={post.image_url}
                      alt={`${post.username}'s profile picture`}
                      className="mr-4 rounded-full"
                      width={32}
                      height={32}
                    />
                    <p className="truncate font-semibold md:text-based">
                      {post.username}
                    </p>
                  </Link>
                  <div className="flex flex-row items-center justify-center">
                    <p className="text-sm">{formatDateToLocal(dateStr)}</p>
                  </div>
                </div>
                <div className="flex flex-col items-start justify-start">
                  <div className="flex flex-row items-center justify-start mb-2">
                    <h1 className="truncate text-xl font-medium">
                      {post.title}
                    </h1>
                  </div>
                  <div className="flex flex-col items-start justify-start">
                    <p className="truncate text-sm font-medium text-wrap">
                      {post.body}
                    </p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}