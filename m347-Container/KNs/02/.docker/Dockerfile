# FROM defines the base image for the container
FROM nginx:latest

# WORKDIR sets the working directory inside the container.
WORKDIR /usr/share/nginx/html

# COPY copies the files from the host to the container
COPY html .

# EXPOSE makes the container's port available to the outside
EXPOSE 80