#!/usr/bin/sh

# Task A

echo "PWD: $(pwd)"

docker run -d \
	-it \
	--name kn05a \
	--mount type=bind,source="$(pwd)"/target,target=/app \
	nginx:latest