#!/usr/bin/sh

MANAGER_IP='3.208.44.43'
WORKER_1_IP='3.223.157.147'
WORKER_2_IP='52.72.5.99'

log() {
	NAME=$1
	IP=$2
	echo "Connecting to ${NAME} at ${IP}..."
}

connect() {
	NODE_NAME=$1
	if [ -z "$NODE_NAME" ]; then
		echo "Usage: $0 <node_name>"
		exit 1
	fi

	case $NODE_NAME in
		manager)
			log "Manager" "$MANAGER_IP"
			ssh -i ~/.ssh/id_rsa ubuntu@${MANAGER_IP}
			;;
		worker-1|node-1)
			log "Worker 1" "$WORKER_1_IP"
			ssh -i ~/.ssh/id_rsa ubuntu@${WORKER_1_IP}
			;;
		worker-2|node-2)
			log "Worker 2" "$WORKER_2_IP"
			ssh -i ~/.ssh/id_rsa ubuntu@${WORKER_2_IP}
			;;
		*)
			echo "Unknown node name: $NODE_NAME"
			exit 1
			;;
	esac
}

connect "$1"