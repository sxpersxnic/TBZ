#!/usr/bin/sh

cd ${TBZ}/m165-NoSQL/.docker/cassandra

docker build -t mkampus/m165:cassandra -f Dockerfile .

docker push mkampus/m165:cassandra

docker run -d \
  --name cassandra \
  -p 9042:9042 \
  -v cassandra_data:/var/lib/cassandra \
  mkampus/m165:cassandra