#!/usr/bin/sh

EC2_IP='52.204.228.129'
USER='ubuntu'

# Connect to the EC2 instance
ssh -i ~/.ssh/id_rsa ${USER}@${EC2_IP}