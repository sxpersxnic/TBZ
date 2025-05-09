#!/usr/bin/sh

cd ${TBZ}/m165-NoSQL/.docker/neo4j

docker build -t mkampus/m165:neo4j -f Dockerfile .

docker push mkampus/m165:neo4j

docker run -d \
  --name neo4j \
  -p 7474:7474 \
  -p 7687:7687 \
  -v neo4j_data:/data \
  mkampus/m165:neo4j