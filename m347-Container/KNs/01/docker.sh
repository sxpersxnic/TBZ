#!/usr/bin/sh

# A) Installation
docker run -d --name getting-started -p 80:80 docker/getting-started # Runs the 'getting-started' container from docker/getting-started image

# B) Docker CLI

# 1. Docker Version
docker -v # Checks Docker version
docker version # Checks Docker version (extended)

# 2. Search images
docker search ubuntu # Searches for 'ubuntu' images
docker search nginx # Searches for 'nginx' images


# 4. Creating an NGINX container
docker pull nginx:latest # Pulls the latest NGINX image
docker create --name kn01-nginx -p 8081:80 nginx:latest # Creates a container named 'kn01-nginx' from the latest NGINX image and maps port 8081 to port 80.
docker start kn01-nginx # Starts the 'kn01-nginx' container

# 5. -d vs. -it
docker run -d --name kn01-ubuntu-d ubuntu:latest # Runs a detached container named 'kn01-ubuntu-d' from the latest Ubuntu image
docker run -it --name kn01-ubuntu-it ubuntu:latest # Runs an interactive container named 'kn01-ubuntu-it' from the latest Ubuntu image

# 6. Interactive Shell in running NGINX container
echo "[i] Run: 'service nginx status' in the container"
docker exec -it kn01-nginx /bin/bash # Executes an interactive bash shell in the running 'kn01-nginx' container

# 7. Checking container status
docker ps -a # Lists all containers

# 8. Stopping NGINX container
docker stop kn01-nginx # Stops the 'kn01-nginx' container

# 9. Removing all containers
docker rm $(docker ps -aq) # Removes all containers

# 10. Removing all images
docker rmi ubuntu:latest nginx:latest # Removes the specified images
# OR
docker rmi $(docker images -q) # Removes all images

# D) Private Repository
docker_hub_username=""
docker login -u ${docker_hub_username} --password-stdin # Logs into Docker Hub with the provided username and password

docker pull nginx:latest # Pulls the latest NGINX image
docker tag nginx:latest ${docker_hub_username}/m347:nginx # Tags the NGINX image with the repository m347
docker push ${docker_hub_username}/m347:nginx # Pushes the tagged image to Docker Hub

docker pull mariadb:latest # Pulls the latest MariaDB image
docker tag mariadb:latest ${docker_hub_username}/m347:mariadb # Tags the MariaDB image with the repository m347
docker push ${docker_hub_username}/m347:mariadb # Pushes the tagged image to Docker Hub