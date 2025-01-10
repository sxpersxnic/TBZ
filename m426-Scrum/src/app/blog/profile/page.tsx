import { useSession } from "@/lib/auth/session";
import { fetchProfilePosts } from "@/lib/data";
import { defaultPfp, postPath } from "@/lib/definitions";
import { formatDateToLocal } from "@/lib/utils";
import { DeletePost, UpdatePost } from "@/ui/blog/profile/profile-buttons";
import clsx from "clsx";
import Image from "next/image";
import Link from "next/link";
import { notFound } from "next/navigation";

/**
 * Description placeholder
 *
 * @export
 * @returns {*}
 */
export default async function Page() {

  const user = await useSession();

  if (!user) {
    notFound();
  }

  const posts = await fetchProfilePosts(user.id);

  let pfp = defaultPfp

  if (user.image_url) {
    pfp = user.image_url;
  }

  return (
    <main className="w-full flex flex-col">
      <div className="">
        <div>
          <h1>Profile</h1>
        </div>
        <div>
          <Image
            src={pfp}
            alt="Your profile picture"
            width={64}
            height={64}
            className=""
          />
          <div>
            <h2>{user.username}</h2>
          </div>
        </div>
      </div>
      <div>
        <div>
          <h2>Your posts</h2>
        </div>
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
                        <div className="flex flex-row w-full justify-between items-center">
                          <Link href={postPath(post.id)} className="text-xl font-medium w-fit">{post.title}</Link>
                          <div className="flex flex-row gap-1">
                            <UpdatePost id={post.id} />
                            <DeletePost id={post.id} />
                          </div>
                        </div>
                      </div>
                    </div>
                    <div className="flex w-full items-center justify-between pt-4">
                        <p className="text-sm">{formatDateToLocal(post.date.toISOString())}</p>
                      <div>
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
                          'flex flex-row items-center justify-between py-4 px-6',
                          {
                            'border-t border-[--foreground]': i !== 0,
                          },
                        )}
                      >
                        <div className="flex flex-col items-start justify-center w-full">
                          <div className="flex flex-row items-center justify-between w-full mb-6">
                            <Link href={postPath(post.id)} className="truncate text-xl font-medium md:text-base w-fit px-2 rounded-md hover:bg-[--hoverbg] hover:text-[--hovertext] transition-colors">
                              {post.title}
                            </Link>
                            <div className="flex flex-row gap-1">
                              <UpdatePost id={post.id} />
                              <DeletePost id={post.id} />  
                            </div>
                          </div>
                            <div className="flex flex-row items-center justify-center">
                              <p className="text-sm">{formatDateToLocal(dateStr)}</p>
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
      </div>
    </main>
  );
}