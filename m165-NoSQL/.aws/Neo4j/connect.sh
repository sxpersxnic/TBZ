#!/usr/bin/sh

EC2_IP='3.225.21.238'
USER='ubuntu'

# Connect to the EC2 instance
ssh -i ~/.ssh/id_rsa ${USER}@${EC2_IP}