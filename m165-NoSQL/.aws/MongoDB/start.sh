#!/usr/bin/sh

EC2_IP="52.204.228.129"
INSTANCE_ID="i-063a1fef4da8e64cf"

echo "[+] Instance starting..."
aws ec2 start-instances --instance-ids ${INSTANCE_ID}
echo "[+] Instance ${INSTANCE_ID} started at ${EC2_IP}"
echo "[+] Waiting for instance to be ready..."
sleep 30
echo "[+] Instance is ready"
echo "[+] Connecting to instance (ubuntu@${EC2_IP})..."
ssh -i ~/.ssh/id_rsa ubuntu@${EC2_IP}