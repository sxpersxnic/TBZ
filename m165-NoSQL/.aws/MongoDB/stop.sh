#!/usr/bin/sh

EC2_IP="52.204.228.129"
INSTANCE_ID="i-0aa96cefac15e1692"

echo "[+] Instance stopping..."
aws ec2 stop-instances --instance-ids ${INSTANCE_ID}
echo "[+] Instance ${INSTANCE_ID} stopped."