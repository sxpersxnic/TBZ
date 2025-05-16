# KN01: Docker Grundlagen

## A - Installation

### Screenshots

  1. **Getting Started** Website of Docker hosted in a Container:
    ![Screenshot of Docker's 'Getting Started' Website hosted in a Container](/m347-Container/x-resources/01/website.png)

  2. Docker Desktop Dashboard, showing the **Getting Started** Container:
    ![Screenshot von Docker Desktop, mit einem Container](/m347-Container/x-resources/01/desktop.png)

## B - Docker CLI

### 1. Docker Version

  ```sh
  docker -v

  # Output:
  $ Docker version 27.5.1-1, build 9f9e4058019a37304dc6572ffcbb409d529b59d8

  # Or

  docker version

  # Ouput:
  $ Client:
      Version:           27.5.1-1
      API version:       1.47
      Go version:        go1.22.11
      Git commit:        9f9e4058019a37304dc6572ffcbb409d529b59d8
      Built:             Tue Jan 21 23:46:20 UTC 2025
      OS/Arch:           linux/amd64
      Context:           default

    Server:
      Engine:
        Version:          27.5.1-1
        API version:      1.47 (minimum version 1.24)
        Go version:       go1.22.11
        Git commit:       4c9b3b011ae4c30145a7b344c870bdda01b454e2
        Built:            Tue Jan 21 23:46:50 2025
        OS/Arch:          linux/amd64
        Experimental:     false
      containerd:
        Version:          1.7.26-1
        GitCommit:        753481ec61c7c8955a23d6ff7bc8e4daed455734
      runc:
        Version:          1.1.15-1
        GitCommit:        bc20cb4497af9af01bea4a8044f1678ffca2745c
      docker-init:
        Version:          0.19.0
        GitCommit:        de40ad0
  ```

### 2. Docker search

- **Ubuntu:**

  ```sh
  docker search ubuntu
  ```

- **NGINX:**

  ```sh
  docker search nginx
  ```

### 3. Explanation

**Command:** `docker run -d -p 80:80 docker/getting-started`

- `-d`: Stands for *detach*. This means the execution of the command only returns the ID of the started Container and the Container runs in the background.
- `-p 80:80`: Stands for *--publish*. Mapps the port **80** of the *Host* to the port **80** of the Container.
- `docker/getting-started`: Defines the Image which the new Container will be based on. In this example `docker` is the publisher of the Image and `getting-started` is the Image itself.

### 4. NGINX Container

#### Commands

1. `docker pull nginx:latest` - Pulls NGINX Image from Docker Hub.
2. `docker create --name kn01-nginx -p 8081:80 nginx:latest` - Creates a Container named 'kn01-nginx', which maps the localhost port 8081 to port 80 in the container and uses the image 'nginx:latest' -> :latest is the tag for the images latest version.
3. `docker start kn01-nginx` - Starts the previously created container 'kn01-nginx'.

#### Screenshot

![NGINX standard page](/m347-Container/x-resources/01/nginx.png)

### 5. `-d` (detached) vs. `-it` (interactive)

- `docker run -d --name kn01-ubuntu-bg ubuntu`: The Container runs in the background. Docker pulls the Image automatically, if it doesn't exist locally. Because Ubuntu stops without running process, the Container is instantly set to status "Exited".

- `docker run -it --name kn01-ubuntu-it ubuntu:latest`: With the flag `-it` *(interactive)* starts the Container interactiv and binds the Terminal (`tty`) to a Shell. On start up, you land directly in the Shell *(e.g. Bash, Zsh, Fish etc. )*. As long as the Shell is active, the Container keeps running.

### 6. Connect to a container's shell

> **Screenshot:**
>
> ![Executed command 'service nginx status' with result](/m347-Container/x-resources/01/service-nginx-status.png)

### 7. List containers

> **Screenshot:**
>
> ![Docker container status](/m347-Container/x-resources/01/docker-ps-a.png)

### 8. Command: `docker stop kn01-nginx`

### 9. Command: `docker rm $(docker ps -aq)`

### 10. Command: `docker rmi ubuntu:latest nginx:latest`

## C) Registry und Repository (10%)

![Private empty repository on Docker Hub](/m347-Container/x-resources/01/docker-hub.png)

## D) Private Repository (20%)

### Commands

1. `docker login -u <username> --password-stdin` - Login to Docker
2. `docker pull mariadb:latest` - Pulls the latest mariadb image
3. `docker tag mariadb:latest <username>/<repository>:mariadb` - Creates a tag from mariadb:latest
4. `docker push <username>/<repository>:mariadb` - Pushes the image to the registry '<repository>'

> **Screenshot:**
>
> ![Private Repository on Docker Hub](/m347-Container/x-resources/01/docker-hub-repo.png)
