import Post from "@/ui/blog/post/post";
import { use } from "react";

/**
 * Description placeholder
 *
 * @typedef {Params}
 */
type Params = Promise<{ id: string }>

/**
 * Description placeholder
 *
 * @export
 * @param {{ 
 *   params: Params
 *  }} props
 * @returns {*}
 */
export default function Page(props: { 
  params: Params
 }) {
  const params = use(props.params);
  const id = params.id;

  return (
    <main>      
      <div> 
        <Post id={id} />
      </div>
    </main>
  );
}