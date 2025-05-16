# KN01: Docker Grundlagen

## A) Installation (20%)

- **Screenshots:**
  1. Webseite mit Container: ![Screenshot von Docker Webseite, mit einem Container](/m347-Container/x-resources/01/website.png)
  2. Docker Desktop mit Container: ![Screenshot von Docker Desktop, mit einem Container](/m347-Container/x-resources/01/desktop.png)

## B) Docker CLI (50%)

- **1.** Docker Version

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

- **2.** Docker search

  - **Ubuntu:**

    ```sh
    docker search ubuntu
    ```

  - **NGINX:**

    ```sh
    docker search nginx
    ```

- **3.** Erklärung des Befehls `docker run -d -p 80:80 docker/getting-started`:

  - `-d`: Steht für *detach*. Bedeutet das die Ausführung des Befehls bloss die ID des Containers ausgibt und den Container im Hintergrund läuft.
  - `-p 80:80`: Steht für *--publish*. Mappt den Port **80**. des Hosts auf Port **80** im Container.
  - `docker/getting-started`: Definiert das Image auf dem der neue Container basiert. In diesem Beispiel ist `docker` der Publisher des Images und `getting-started` ist das Image selbst.

- **4.** NGINX Container

  - **Commands:**

    1. `docker pull nginx:latest` - Pulls NGINX Image from Docker Hub.
    2. `docker create --name kn01-nginx -p 8081:80 nginx:latest` - Creates a Container named 'kn01-nginx', which maps the localhost port 8081 to port 80 in the container and uses the image 'nginx:latest' -> :latest is the tag for the images latest version.
    3. `docker start kn01-nginx` - Starts the previously created container 'kn01-nginx'.

  - **Screenshot:**

    ![NGINX standard page](/m347-Container/x-resources/01/nginx.png)

- **5.** `-d` (detached) vs. `-it` (interactive)

  - `docker run -d --name kn01-ubuntu-bg ubuntu`: Der Container wird im Hintergrund gestartet. Docker lädt das Image automatisch herunter, falls es nicht lokal vorhanden ist. Da Ubuntu ohne laufenden Prozess sofort beendet, wird der Container direkt in den Status „Exited“ versetzt.
  - `docker run -it --name kn01-ubuntu-it ubuntu:latest`: Mit `-it` startet der Container interaktiv und bindet das Terminal (`tty`) an eine Shell. Man landet direkt in der Bash des Containers. Solange die Shell aktiv ist, läuft der Container.

- **6.** Connect to a container's shell
  > **Screenshot:**
  >
  > ![Ausgeführter command service nginx status mit Resultat](/m347-Container/x-resources/01/service-nginx-status.png)
- **7.** List containers
  > **Screenshot:**
  >
  > ![Docker container status](/m347-Container/x-resources/01/docker-ps-a.png)
- **8. Command:** `docker stop kn01-nginx`
- **9. Command:** `docker rm $(docker ps -aq)`
- **10. Command:** `docker rmi ubuntu:latest nginx:latest`

## C) Registry und Repository (10%)

![Private empty repository on Docker Hub](/m347-Container/x-resources/01/docker-hub.png)

## D) Private Repository (20%)

- **Commands:**
  1. `docker login -u <username> --password-stdin` - Login to Docker
  2. `docker pull mariadb:latest` - Pulls the latest mariadb image
  3. `docker tag mariadb:latest <username>/<repository>:mariadb` - Creates a tag from mariadb:latest
  4. `docker push <username>/<repository>:mariadb` - Pushes the image to the registry '<repository>'

- > **Screenshot:**
  >
  > ![Private Repository on Docker Hub](/m347-Container/x-resources/01/docker-hub-repo.png)
