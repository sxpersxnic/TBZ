# KN01: Docker Grundlagen

## A) Installation (20%)

- **Screenshots:**
  1. Webseite mit Container: ![Screenshot von Docker Webseite, mit einem Container](/m347-Container/x-resources/01/website.png)
  2. Docker Desktop mit Container: ![Screenshot von Docker Desktop, mit einem Container](/m347-Container/x-resources/01/desktop.png)

## B) Docker CLI (50%)

**1.** Überprüfen Sie die Docker-Version. Welchen Befehl müssen Sie dafür verwenden?

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

**2.** Suchen Sie nach de, offiziellen **ubuntu** und **nginx** Docker-Image auf Docker Hub mit dem Befehl `$ docker search`.

- **Ubuntu:**

  ```sh
  docker search ubuntu
  ```

- **NGINX:**

  ```sh
  docker search nginx
  ```

**3.** In Teil A mussten Sie den Befehl `docker run -d -p 80:80 docker/getting-started` ausführen. Erklären Sie die verschiedenen Parameter.

- `-d`: Steht für *detach*. Bedeutet das die Ausführung des Befehls bloss die ID des Containers ausgibt und den Container im Hintergrund läuft.
- `-p 80:80`: Steht für *--publish*. Mappt den Port **80**. des Hosts auf Port **80** im Container.
- `docker/getting-started`: Definiert das Image auf dem der neue Container basiert. In diesem Beispiel ist `docker` der Publisher des Images und `getting-started` ist das Image selbst.

**4.** Mit dem **nginx** Image verfahren Sie wie folgt. Wir zeigen, dass der Befehl `docker run`, das gleiche ist wie die drei Befehle `docker pull`, `docker create` und `docker start` hintereinander ausgeführt.

  1. Laden Sie das Image herunter mit dem Befehl `docker pull`
  2. Erstellen Sie ein Container mit dem Befehl `docker create`. Exponieren Sie den Port 8081 mit dem Mapping auf den Port 80, so dass Sie anschliessend die URL [localhost:8081](http://localhost:8081)
  3. Starten Sie das Image mit dem Befehl `docker start`. Evtl. müssen Sie einen laufenden Container zuerst stoppen, weil der Port bereits verwendet wird.
  4. Erstellen Sie einen **Screenshot** der Standard-Seite von nginx mit der URL sichtbar.

- **Commands:**

  ```sh
  # 1. Pulls NGINX Image from Docker Hub.
  docker pull nginx:latest
  # 2. Creates a Container named 'kn01-nginx', which maps the localhost port 8081 to port 80 in the container and uses the image 'nginx:latest' -> :latest is the tag for the images latest version.
  docker create --name kn01-nginx -p 8081:80 nginx:latest
  # 3. Starts the previously created container 'kn01-nginx'.
  docker start kn01-nginx
  ```

- **Screenshot:**

  ![NGINX standard page](/m347-Container/x-resources/01/nginx.png)

**5.** Mit dem **ubuntu** Image verfahren Sie wie folgt. Wir zeigen, dass nicht jedes Image im Hintergrund ausgeführt werden kann.

  1. Erstellen und starten Sie einen Container mit dem Befehl `docker run -d`. **Kommentieren** Sie was mit dem Container geschieht in 3-5 Sätzen. Wurde das Image automatisch heruntergeladen? Konnte es starten?
  2. Erstellen und starten Sie einen Container mit dem Befehl `docker run -it`. **Kommentieren** Sie was nun geschieht in 3-5 Sätzen.

- `docker run -d --name kn01-ubuntu-bg ubuntu`: Der Container wird im Hintergrund gestartet. Docker lädt das Image automatisch herunter, falls es nicht lokal vorhanden ist. Da Ubuntu ohne laufenden Prozess sofort beendet, wird der Container direkt in den Status „Exited“ versetzt.
- `docker run -it --name kn01-ubuntu-it ubuntu:latest`: Mit `-it` startet der Container interaktiv und bindet das Terminal (`tty`) an eine Shell. Man landet direkt in der Bash des Containers. Solange die Shell aktiv ist, läuft der Container.

**6.** Stellen Sie sicher, dass Ihr **nginx**-Container **bereits läuft**. Öffnen Sie nun nachträglich eine interaktive Shell. Der Unterschied zu vorher ist, dass Sie nicht den Container mit interactiver Shell starten, sondern eine Shell eines laufenden Containers öffnen. Der Befehl is `docker exec -t kn01-nginx /bin/bash`

  1. Führen Sie den Befehl `service nginx status` aus. Erstellen Sie einen **Screenshot** des Befehls und des Resultats. Sie sehen, dass Sie sich innerhalb des Docker-Images bewegen können. ![Ausgeführter command service nginx status mit Resultat](/m347-Container/x-resources/01/service-nginx-status.png)
  2. Beenden Sie die interaktive Shell im Docker-Container (mit dem Befehl `exit`). Beachten Sie, dass das Image automatisch wieder beendet.

**7.** Überprüfen Sie den Status des Container. Erstellen Sie einen **Screenshot** des Befehls und des Resultats.

![Docker container status](/m347-Container/x-resources/01/docker-ps-a.png)

**8.** Stoppen Sie nun noch den Container des nginx Images mit dem entsprechenden Docker-Befehl

- **Command:** `docker stop kn01-nginx`

**9.** Entfernen Sie alle Container mit dem entsprechenden Docker-Befehl.

- **Command:** `docker rm $(docker ps -aq)`

**10.** Entfernen Sie die beiden Images von Ihrer lokalen Umgebung mit dem entsprechenden Docker-Befehl.

- **Command:** `docker rmi ubuntu:latest nginx:latest`

## C) Registry und Repository (10%)

## D) Private Repository (20%)
