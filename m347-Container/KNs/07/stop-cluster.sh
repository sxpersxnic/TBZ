#!/usr/bin/bash

# Requirements:
# - AWS CLI installed and configured
# - 4 EC2 instances (1 Master, 3 Nodes)

AWS_EC2_INSTANCE_MASTER_ID="i-03cccc64ff410ca65"
AWS_EC2_INSTANCE_NODE1_ID="i-0556154d13dbca6fe"
AWS_EC2_INSTANCE_NODE2_ID="i-0c21858a081f3eefd"
AWS_EC2_INSTANCE_NODE3_ID="i-061590c56e8dc92d1"

aws ec2 stop-instances --instance-ids $AWS_EC2_INSTANCE_MASTER_ID $AWS_EC2_INSTANCE_NODE1_ID $AWS_EC2_INSTANCE_NODE2_ID $AWS_EC2_INSTANCE_NODE3_ID

echo "[+] Instances stopped"