#!/usr/bin/sh

cd ${TBZ}/m165-NoSQL/.docker/mongodb

docker build -t mkampus/m165:mongodb -f Dockerfile .

docker push mkampus/m165:mongodb

docker run -d \
  --name mongodb \
  -p 27017:27017 \
  -v mongo_data:/data/db \
  mkampus/m165:mongodb