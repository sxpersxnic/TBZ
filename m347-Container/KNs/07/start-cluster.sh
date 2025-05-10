#!/usr/bin/bash

# Requirements:
# - AWS CLI installed and configured
# - 4 EC2 instances (1 Master, 3 Nodes)

AWS_EC2_INSTANCE_MASTER_IP="54.210.174.232"
AWS_EC2_INSTANCE_NODE1_IP="3.88.97.113"
AWS_EC2_INSTANCE_NODE2_IP="54.145.87.68"
AWS_EC2_INSTANCE_NODE3_IP="3.225.21.238"

AWS_EC2_INSTANCE_MASTER_ID="i-03cccc64ff410ca65"
AWS_EC2_INSTANCE_NODE1_ID="i-0556154d13dbca6fe"
AWS_EC2_INSTANCE_NODE2_ID="i-0c21858a081f3eefd"
AWS_EC2_INSTANCE_NODE3_ID="i-061590c56e8dc92d1"

aws ec2 start-instances --instance-ids $AWS_EC2_INSTANCE_MASTER_ID $AWS_EC2_INSTANCE_NODE1_ID $AWS_EC2_INSTANCE_NODE2_ID $AWS_EC2_INSTANCE_NODE3_ID

echo "[+] Instances starting..."
sleep 30
echo "[+] Instances started"

ping -c 3 $AWS_EC2_INSTANCE_MASTER_IP | grep -q "64 bytes from" && echo "[+] Master instance is reachable" || echo "[!] Master instance is not reachable";
ping -c 3 $AWS_EC2_INSTANCE_NODE1_IP | grep -q "64 bytes from" && echo "[+] Node 1 instance is reachable" || echo "[!] Node 1 instance is not reachable";
ping -c 3 $AWS_EC2_INSTANCE_NODE2_IP | grep -q "64 bytes from" && echo "[+] Node 2 instance is reachable" || echo "[!] Node 2 instance is not reachable";
ping -c 3 $AWS_EC2_INSTANCE_NODE3_IP | grep -q "64 bytes from" && echo "[+] Node 3 instance is reachable" || echo "[!] Node 3 instance is not reachable";

echo "[+] Instances reachable"