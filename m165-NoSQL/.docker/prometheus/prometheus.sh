#!/usr/bin/sh

cd ${TBZ}/m165-NoSQL/.docker/prometheus

docker build -t mkampus/m165:prometheus -f Dockerfile .

docker push mkampus/m165:prometheus

docker run -d \
  --name prometheus \
  -p 9090:9090 \
  -v "$(pwd)/prometheus.yml":/etc/prometheus/prometheus.yml:ro \
  mkampus/m165:prometheus