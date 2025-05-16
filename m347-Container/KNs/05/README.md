# KN05: Arbeit mit Speicher

## A) Bind mounts

- Commands: `docker run -d -it --name kn05a --mount type=bind,source="$(pwd)"/target,target=/app nginx:latest`

- Screencast: [here](/m347-Container/x-resources/05/a.mp4)

## B) Volumes

- Commands: [script](./.docker/with-shared-volume.sh)

- Screencast: [here](/m347-Container/x-resources/05/b.mp4)

## C) Speicher mit Docker Compose

- Speicher Container 1: ![Mount information of Container 1](/m347-Container/x-resources/05/nginx1.png)
- Speicher Container 2: ![Mount information of Container 2](/m347-Container/x-resources/05/nginx2.png)
- Docker compose file: [docker-compose.yml](./docker-compose.yml)