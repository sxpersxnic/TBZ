# KN01: Docker Grundlagen

## A. Installation

### Screenshots

1. **Getting Started** Website of Docker, hosted in a Container:

    ![Screenshot of Docker's 'Getting Started' Website hosted in a Container](/m347-Container/x-resources/01/website.png)

2. **Docker Desktop Dashboard**, showing the **Getting Started** Container:

    ![Screenshot von Docker Desktop, mit einem Container](/m347-Container/x-resources/01/desktop.png)

---

## B. Docker CLI

### 1. Docker Version

```sh
docker -v
# Output:
# Docker version 27.5.1-1, build 9f9e4058019a37304dc6572ffcbb409d529b59d8

# Or

docker version
# Output:
# Client:
#   Version:           27.5.1-1
#   API version:       1.47
#   Go version:        go1.22.11
#   Git commit:        9f9e4058019a37304dc6572ffcbb409d529b59d8
#   Built:             Tue Jan 21 23:46:20 UTC 2025
#   OS/Arch:           linux/amd64
#   Context:           default
#
# Server:
#   Engine:
#     Version:          27.5.1-1
#     API version:      1.47 (minimum version 1.24)
#     Go version:       go1.22.11
#     Git commit:       4c9b3b011ae4c30145a7b344c870bdda01b454e2
#     Built:            Tue Jan 21 23:46:50 2025
#     OS/Arch:          linux/amd64
#     Experimental:     false
#   containerd:
#     Version:          1.7.26-1
#     GitCommit:        753481ec61c7c8955a23d6ff7bc8e4daed455734
#   runc:
#     Version:          1.1.15-1
#     GitCommit:        bc20cb4497af9af01bea4a8044f1678ffca2745c
#   docker-init:
#     Version:          0.19.0
#     GitCommit:        de40ad0
```

### 2. Docker Search

- **Ubuntu:**

    ```sh
    docker search ubuntu
    ```

- **NGINX:**

    ```sh
    docker search nginx
    ```

### 3. Command Explanation

**Command:**

```sh
docker run -d -p 80:80 docker/getting-started
```

- `-d`: _detach_ — runs the container in the background and returns the container ID.
- `-p 80:80`: _--publish_ — maps port **80** of the host to port **80** of the container.
- `docker/getting-started`: the image to use (publisher: `docker`, image: `getting-started`).

### 4. NGINX Container

#### Commands

1. `docker pull nginx:latest` — Pulls the NGINX image from Docker Hub.
2. `docker create --name kn01-nginx -p 8081:80 nginx:latest` — Creates a container named 'kn01-nginx', mapping localhost port 8081 to container port 80, using the latest NGINX image.
3. `docker start kn01-nginx` — Starts the previously created container 'kn01-nginx'.

#### Screenshot

![NGINX standard page](/m347-Container/x-resources/01/nginx.png)

### 5. `-d` (detached) vs. `-it` (interactive)

- `docker run -d --name kn01-ubuntu-bg ubuntu`  
    Runs the container in the background. If the image does not exist locally, Docker pulls it automatically. Since Ubuntu stops without a running process, the container status is instantly set to "Exited".

- `docker run -it --name kn01-ubuntu-it ubuntu:latest`  
    The `-it` flag (_interactive_) starts the container interactively and binds the terminal (`tty`) to a shell. On startup, you land directly in the shell (e.g., Bash, Zsh, Fish, etc.). As long as the shell is active, the container keeps running.

### 6. Connect to a Container's Shell

> **Screenshot:**
>
> ![Executed command 'service nginx status' with result](/m347-Container/x-resources/01/service-nginx-status.png)

### 7. List Containers

> **Screenshot:**
>
> ![Docker container status](/m347-Container/x-resources/01/docker-ps-a.png)

### 8. Stop a Container

```sh
docker stop kn01-nginx
```

### 9. Remove All Containers

```sh
docker rm $(docker ps -aq)
```

### 10. Remove Images

```sh
docker rmi ubuntu:latest nginx:latest
```

---

## C. Registry und Repository (10%)

![Private empty repository on Docker Hub](/m347-Container/x-resources/01/docker-hub.png)

---

## D. Private Repository (20%)

### Commands

1. `docker login -u <username> --password-stdin` — Login to Docker
2. `docker pull mariadb:latest` — Pulls the latest MariaDB image
3. `docker tag mariadb:latest <username>/<repository>:mariadb` — Creates a tag from mariadb:latest
4. `docker push <username>/<repository>:mariadb` — Pushes the image to the registry `<repository>`

### Screenshot

![Private Repository on Docker Hub](/m347-Container/x-resources/01/docker-hub-repo.png)
