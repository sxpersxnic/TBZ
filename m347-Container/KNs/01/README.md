# KN01: Docker Grundlagen

## A) Installation (20%)

1. Webseite mit Container: ![Screenshot von Docker Webseite, mit einem Container](/m347-Container/x-resources/01/website.png)
2. Docker Desktop mit Container: ![Screenshot von Docker Desktop, mit einem Container](/m347-Container/x-resources/01/desktop.png)

## B) Docker CLI (50%)

1. Überprüfen Sie die Docker-Version. Welchen Befehl müssen Sie dafür verwenden?

  ```sh
  $ docker -v
  ```
 
2. Suchen Sie nach de, offiziellen **ubuntu** und **nginx** Docker-Image auf Docker Hub mit dem Befehl `$ docker search`.

  ```sh
  $ docker search ubuntu nginx
  ```

3. In Teil A mussten Sie den Befehl `docker run -d -p 80:80 docker/getting-started` ausführen. Erklären Sie die verschiedenen Parameter.

  - `-d`:
  - `-p 80:80`: Ports des Containers, in unserem fall, wird der Container lokal, sowie im Container selbst über Port 80 *(http)* laufen.
  - `docker/getting-started`: Definiert das Image auf dem der neue Container basiert. In diesem Beispiel ist `docker` der Publisher des Images und `getting-started` ist das Image selbst.

4. 

## C) Registry und Repository (10%)

## D) Private Repository (20%)
