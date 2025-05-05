# KN02: Dockerfile

## A) Dockerfile I

- [Dockerfile](./.docker/Dockerfile)
- Build command: `docker build -f .docker/Dockerfile -t <username>/m347:kn02a .`
- Push command: `docker push <username>/m347:kn02a`
- Start command: `docker run -d --name kn02-nginx -p 80:80 <username>/m347:kn02a`
- ![Screenshot of Docker Desktop, showing image kn02a](/m347-Container/x-resources/02/desktop.png)
- ![Screenshot of the website](/m347-Container/x-resources/02/www.png)


## B) Dockerfile II

### Approach I

- **DB:**
  - ![Telnet](/m347-Container/x-resources/02/db-telnet.png)
  - [Dockerfile](./.docker/db.Dockerfile)
  - Commands:
    - `docker build -f .docker/db.Dockerfile -t <username>/m347:kn02b-db .`
    - `docker run -d --name kn02b-db -p 3306:3306 <username>/m347:kn02b-db`
- **Web:**
  - ![Screenshot of site info.php](/m347-Container/x-resources/02/info-php.png)
  - ![Screenshot of site db.php](/m347-Container/x-resources/02/db-php.png)
  - **Commands:**
    - `docker build -f .docker/web.Dockerfile -t <username>/m347:kn02b-web .`
    - `docker run -d --name kn02b-web --link kn02b-db:kn02b-db -p 80:80 <username>/m347:kn02b-web`
  - [db.php](./php/db.php)

### Approach II

> I prefer to work with docker compose, since it's faster to write, easier to execute and makes it easier to maintain. Especially when working with networks and/or volumes.

- [Docker compose](./docker-compose.yml)
- **Command:** `docker compose up`
