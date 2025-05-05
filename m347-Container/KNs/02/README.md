# KN02: Dockerfile

## A) Dockerfile I

- [Dockerfile](./.docker/Dockerfile)
- Build command: `docker build -f .docker/Dockerfile -t <username>/m347:kn02a .`
- Push command: `docker push <username>/m347:kn02a`
- Start command: `docker run -d --name kn02-nginx -p 80:80 <username>/m347:kn02a`
- ![Screenshot of Docker Desktop, showing image kn02a](/m347-Container/x-resources/02/desktop.png)
- ![Screenshot of the website](/m347-Container/x-resources/02/www.png)


## B) Dockerfile II

- **DB:**
  - ![Telnet](/m347-Container/x-resources/02/db-telnet.png)
  - [Dockerfile](./.docker/db.Dockerfile)
  - Commands:
    - `docker build -f .docker/db.Dockerfile -t <username>/m347:kn02b-db .`
    - `docker run -d --name kn02b-db -p 3307:3306 <username>/m347:kn02b-db`
- **Web:**
  - ![Screenshot of site info.php](/m347-Container/x-resources/02/info-php.png)
  - ![Screenshot of site db.php](/m347-Container/x-resources/02/db-php.png)
  - **Commands:**
    - `docker build -f .docker/web.Dockerfile -t <username>/m347:kn02b-web .`
    - `docker run -d --name kn02b-web -p 80:80 <username>/m347:kn02b-web`
  - [db.php](./php/db.php)

In my approach i used docker compose, to provide a network for the connection between web and db. And because only the command `docker compose up` is needed to build and run the services.