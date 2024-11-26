import { fetchUserById } from "@/lib/data";
import Breadcrumbs from "@/ui/navigation/breadcrumbs";
import Image from "next/image";

/**
 * Description placeholder
 *
 * @export
 * @async
 * @param {{ id: string }} param0
 * @param {string} param0.id
 * @returns {unknown}
 */
export default async function ProfileUser({ id }: { id: string }) {

  //TODO: Remove when description can be added to profile.
  const description = "Qui duis aute sunt Qui duis aute sunt Qui duis aute sunt Qui duis aute aute aute aute aute aute aute aute Qui duis aute aute aute Qui duis aute aute aute Qui duis aute aute aute Qui duis aute aute aute Qui duis aute aute aute"

  const user = await fetchUserById(id);

  return (
    <>
      <Breadcrumbs
        breadcrumbs={[
          { label: 'Posts', href: '/blog' },
          { label: `${user.username}`, href: `/blog/${id}/profile`, active: true },
        ]}
      />
      <div className="flex flex-col justify-center items-start w-full h-full">
        <div className="flex flex-row w-full h-24 md:h-22 items-center gap-2 justify-start mb-2 md:mb-0">
          <div className="mr-4">
            <Image
              src={user.image_url}
              alt={`${user.username}'s profile picture`}
              className="rounded-full border-2 border-[--foreground] hover:border-[--hovertext] transition-colors"
              width={64}
              height={64}
              quality={100}
              placeholder="data:image/user/default-32x32.png"
            />
          </div>
          <p className="truncate text-lg font-semibold md:text-lg">
            {user.username}
          </p>
        </div>
        <div className="w-full h-auto">
          <p className="font-extralight text-xs text-balance text-start pb-2 md:pb-2 border-b border-[--foreground]">{description}</p>
        </div>
      </div>
    </>
  );
}