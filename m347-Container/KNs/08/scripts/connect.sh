#!/usr/bin/sh

# master, node{1..3}
INSTANCE=${1:-'master'}

# Elastic IPs
MASTER_IP='54.210.174.232'
NODE_1_IP='3.88.97.113'
NODE_2_IP='54.145.87.68'
NODE_3_IP='3.225.21.238'

# Target IP
CONNECT_IP=${MASTER_IP}

case ${INSTANCE} in
  master)
	CONNECT_IP=${MASTER_IP}
	;;
  node1)
	CONNECT_IP=${NODE_1_IP}
	;;
  node2)
	CONNECT_IP=${NODE_2_IP}
	;;
  node3)
	CONNECT_IP=${NODE_3_IP}
	;;
  *)
	echo "Unknown instance: ${INSTANCE}"
	exit 1
esac

echo "Connecting to ${INSTANCE} at ${CONNECT_IP}..."

# Connection through SSH on user ubuntu at target IP
ssh -i ~/.ssh/id_rsa ubuntu@${CONNECT_IP}
