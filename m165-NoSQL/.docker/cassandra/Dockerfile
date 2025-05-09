FROM cassandra:latest

EXPOSE 9042

VOLUME [ "/var/lib/cassandra" ]

# Cluster config
ENV CASSANDRA_CLUSTER_NAME="m165-Cluster" \
	CASSANDRA_DC="DC1" \
	CASSANDRA_RACK="RAC1"