#!/usr/bin/sh

METRICS_IP="52.203.244.41"
PROMETHEUS_IP="44.218.79.132"

case "$1" in
	metrics)
		IP=${METRICS_IP}
		;;
	prometheus)
		IP=${PROMETHEUS_IP}
		;;
	*)
		echo "Usage: $0 {metrics|prometheus}"
		exit 1
		;;
esac

ssh -i ~/.ssh/id_rsa ubuntu@${IP}