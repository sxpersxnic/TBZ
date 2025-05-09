#!/usr/bin/sh

cd ${TBZ}/m165-NoSQL

docker pull cassandra:latest
docker run --name cassandra -p 9042:9042 -p 9160:9160 -d cassandra:latest

docker exec -it cassandra cqlsh