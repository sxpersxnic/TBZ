"use client";

import React, { useState, useEffect } from "react";
import Image from "next/image";
import clsx from "clsx";
import { formatDateToLocal } from "src/lib/utils";
import Link from "next/link";
import { postPath, PostsTable, profilePath } from "@/lib/definitions";

export default function PostSorter({ posts }: { posts: PostsTable[] }) {
  const [sortBy, setSortBy] = useState<"date" | "username">("date");
  const [sortedPosts, setSortedPosts] = useState<PostsTable[]>(sortPosts(posts, sortBy));
  
  useEffect(() => {
    setSortedPosts(sortPosts(posts, sortBy));
  }, [posts, sortBy]);

  const handleSortChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
    setSortBy(event.target.value as "date" | "username");
  };
  
  function renderPosts(posts: PostsTable[]) {
    return (
      <div className="mt-6 flow-root w-full">
        <div className="inline-block min-w-full align-middle">
          <div className="p-2 md:pt-0">
            <div className="md:hidden">
              {posts.map((post) => (
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
  };

  return (
    <div className="flex flex-col mb-4 w-full">
      <div className="flex flex-row items-center justify-end mt-2 px-2 gap-2 h-12">
        <label htmlFor="sortSelect" className="text-xs font-medium">Sort by:</label>
        <select
          id="sortSelect"
          value={sortBy}
          onChange={handleSortChange}
          className="px-2 py-1 border rounded-md h-10 max-h-10 overflow-hidden bg-[--background] border-[--foreground] text-sm"
          >
          <option value="date" className="text-xs font-thin">Date</option>
          <option value="username" className="text-xs font-thin">Username</option>
        </select>
      </div>
      <div>
        {renderPosts(sortedPosts)}
      </div>
    </div>
  );
};

function sortPosts(posts: PostsTable[], sortBy: "date" | "username"): PostsTable[] {
  return [...posts].sort((a, b) => {
    if (sortBy === "date") {
      return new Date(b.date).getTime() - new Date(a.date).getTime();
    } else if (sortBy === "username") {
      return a.username.localeCompare(b.username);
    }
    return 0;
  });
};