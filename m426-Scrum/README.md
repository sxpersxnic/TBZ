# PH-Studio Blog WebApp for TBZ Module 426

Visit: [Here](https://m426-ph-studio-xi.vercel.app/)

## About

### Connect to your friends like never before

With simple posts, you can now let your family, friends and everyone in the world know what is going on in your life!

### Features

Our App comes with many easy to use features, that make your experience even better! **Check them out:**

#### Create

Blog about your life with simple posts. In the Navigationbar, click on the *Plus-Icon* to get to the *Createform*. Enter your title to catch viewers and tell them your story in the body of your post:

![Screenshot of the Createform](/m426-Scrum/public/doc/CREATEFORM.png)

#### Edit

You forgot to mention something in your post? Nothing to worry about!

You can now edit your posts by simply navigating to your profile. Click on the *User-Icon* to get to your profile. Now to the right of the screen you see a *Pencil-Icon*. By clicking the *Pencil-Icon* you get to the *Editform*. Now you can savely edit your post!

![Screenshot of the Editform](/m426-Scrum/public/doc/EDITFORM.png)

#### View

You can view posts in many different ways:

1. Home
   You can discover most recent posts    on the *Homepage*.

   ![Screenshot of the Homepage](/m426-Scrum/public/doc/HOMEPAGE.png)

2. Profile
   If you rather want to view the        posts of a specific user, click on    there profile picture or username     to get to their profile. There you    will find all posts of that user

   ![Screenshot of the Profilepage](/m426-Scrum/public/doc/PROFILEPAGE.png)

3. Post view
   To actually read the content of a     post, click on the title of the       post preview, it does not matter      from where you do this. This          action will take you to the post,     where you can read everything         about it!

   ![Screenshot of the Postpage](/m426-Scrum/public/doc/POSTPAGE.png)

#### Sort

By default, the posts on the *Homepage*, are sorted by the creation date of the posts. If you prefer to sort them by the username of the author, simply select your preference through the *Sort-Dropdown* on the *Homepage*.

![Screenshot of the Sort-Dropdown](/m426-Scrum/public/doc/SORT.png)

#### Search

If you know only the username of a person or the title of a post you would like to read. Enter the term you search for in the *Searchfield* on the *Homepage*.

![Screenshot of the Searchfield](/m426-Scrum/public/doc/SEARCH.png)

#### Delete

When you have posts you would not like to be on your profile, delete them and nobody will remember them.

Navigate to your profile by clicking the *User-Icon* in the Navigationbar.
On your profile, select the *Trash-Icon*, on the right of the post.

![Screenshot of the Delete feature](/m426-Scrum/public/doc/DELETE.png)

## Get Started with Vercel

1. Clone the Repository

```sh
   git clone https://github.com/sxpersxnic/m426-ph-studio.git
```

2. Set the Environment variables according to `.env.example`
3. Create an account on [Vercel](https://vercel.com/login)
4. Link your Github, Gitlab or BitBucket Account.
5. Create a Remote Repository on Github/Gitlab/BitBucket
6. Push the Local Repository to your *Remote*
7. Import the Repository to *Vercel*
8. Upload your `.env` to Vercel `your-project > Settings > Environment Variables`
9. Setup a *PostgreSQL* Database on Vercel `your-project > Storage`
10. Seed your database by either navigating to the api route in your current deployment `url-to-your-deployment/api/seed`.
    If you prefer to seed the database locally, read the following guide to get your app running locally.

## Get Started Locally

1. Clone the Repository

```sh
git clone https://github.com/sxpersxnic/m426-ph-studio.git
```

2. Setup a *PostgreSQL* Database, see [Postgres Guide](https://www.postgresql.org/docs/current/tutorial-start.html)
3. Set the Environment variables according to `.env.example` in a new file `.env.local`
4. Install the dependencies, for development I used **pnpm**, but feel free to choose an other package manager.
   Run the command using your chosen package manager:

   ```sh
     pnpm install
     # or
     npm install
     # or
     yarn install
     # or
     bun install
   ```

5. Run the local development server:

   ```sh
     pnpm dev
     # or
     npm run dev
     # or
     yarn dev
     # or
     bun dev
   ```

7. Finally seed your database by navigating to the api route `http://localhost:3000/api/seed` .

Don't worry if you want to deploy your project later on, just follow the guide above.

## Technical details

### [Next.js](https://nextjs.org)

Next.js is a React framework for building full-stack web applications. You use React Components to build user interfaces, and Next.js for additional features and optimizations.

Under the hood, Next.js also abstracts and automatically configures tooling needed for React, like bundling, compiling, and more. This allows you to focus on building your application instead of spending time with configuration.

Whether you're an individual developer or part of a larger team, Next.js can help you build interactive, dynamic, and fast React applications.

### [Typescript](https://typescriptlang.org/)

TypeScript adds additional syntax to JavaScript to support a tighter integration with your editor. Catch errors early in your editor.

### [PostgreSQL](https://postgresql.org/)

PostgreSQL is a powerful, open source object-relational database system with over 35 years of active development that has earned it a strong reputation for reliability, feature robustness, and performance.

### [Drizzle ORM](https://orm.drizzle.team)

Drizzle ORM is a headless TypeScript ORM with a head.

It looks and feels simple, performs on day 1000 of your project,
lets you do things your way, and is there when you need it.

It’s the only ORM with both relational and SQL-like query APIs, providing you the best of both worlds when it comes to accessing your relational data. Drizzle is lightweight, performant, typesafe, non-lactose, gluten-free, sober, flexible and serverless-ready by design. Drizzle is not just a library, it’s an experience.

### [TailwindCSS](https://tailwindcss.com)

Rapidly build modern websites without ever leaving your HTML.
A utility-first CSS framework packed with classes like `flex`, `pt-4`, `text-center` and `rotate-90` that can be composed to build any design, directly in your markup.

### [Vercel](https://vercel.com)

Vercel is a platform for developers that provides the tools, workflows, and infrastructure you need to build and deploy your web apps faster, without the need for additional configuration.
Vercel supports popular frontend frameworks out-of-the-box, and its scalable, secure infrastructure is globally distributed to serve content from data centers near your users for optimal speeds.

During development, Vercel provides tools for real-time collaboration on your projects such as automatic preview and production environments, and comments on preview deployments.

## License

This repository is licensed with the [MIT](LICENSE) license.
