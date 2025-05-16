#!/usr/bin/sh

cd ${TBZ}/m165-NoSQL/.docker/redis

docker build -t mkampus/m165:redis -f Dockerfile .

docker push mkampus/m165:redis

docker run -d \
  --name redis \
  -p 6379:6379 \
  -v redis_data:/data \
  mkampus/m165:redis