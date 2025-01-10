import { fetchProfilePosts, fetchUserById } from "@/lib/data";
import { formatDateToLocal } from "@/lib/utils";
import clsx from "clsx";
import Link from "next/link";
import React from "react";
import { postPath } from "@/lib/definitions";

/**
 * Description placeholder
 *
 * @export
 * @async
 * @param {{id: string}} param0
 * @param {string} param0.id
 * @returns {Promise<React.JSX.Element>}
 */
export default async function ProfilePosts({ id }: {id: string}): Promise<React.JSX.Element> {

  const profilePosts = await fetchProfilePosts(id);
  const user = await fetchUserById(id);
  
  return (      
    <div>
      <div>
        <div>
          <h2 className="font-bold text-base">Posts by {user.username}</h2>
        </div>
        <div className="flex grow flex-col py-4">
          <div className="rounded-md">
            {profilePosts.map((post, i) => {
          const dateStr = post.date + '';
          return (
              <div key={post.id}>
              <Link
                href={postPath(post.id)}
                className={clsx(
                  'flex flex-row items-center justify-between p-2 hover:bg-[--hoverbg] transition-color',
                  {
                    'border-t': i !== 0,
                  },
                )}
                >
                <div className="flex flex-col items-start justify-center w-full">
                  <div className="flex flex-row items-center justify-between w-full">        
                    <p className="truncate text-sm font-semibold md:text-based">
                      {post.title}
                    </p>
                    <p className="text-sm">
                      {formatDateToLocal(dateStr)}
                    </p>                    
                  </div>                  
                </div>
              </Link>
            </div>
          );
          })}
          </div>
        </div>
        </div>
    </div>    
  );
}
