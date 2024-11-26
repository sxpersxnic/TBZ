import ProfilePosts from "@/ui/blog/profile/profile-posts";
import ProfileUser from "@/ui/blog/profile/profile-user";
import Loading from "./loading";
import { Suspense, use } from "react";

type Params = Promise<{ id: string }>

export default function Page(props: {
  params: Params 
}) {
  const params = use(props.params);
  const id = params.id;
  return (
    <main>            
      <div className="mt-6 grid grid-cols-1 gap-6">
        <Suspense fallback={<Loading />}>
          <ProfileUser id={id}/>
        </Suspense> 
      </div>
      <div className="mt-6 grid grid-cols-1 gap-6">
        <Suspense fallback={<Loading />}>
          <ProfilePosts id={id} />
        </Suspense> 
      </div>
    </main>
  );
}