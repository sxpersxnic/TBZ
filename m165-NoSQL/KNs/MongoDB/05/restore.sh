#!/usr/bin/sh

SNAPSHOT_ID="snap-0db2c16f3e776dbb3"
INSTANCE_ID="i-063a1fef4da8e64cf"
OLD_VOLUME_ID="vol-0da9b36843cb684be"
AVAILABILITY_ZONE="us-east-1a"

#aws ec2 restore-snapshot --snapshot-id ${SNAPSHOT_ID} --availability-zone ${AVAILABILITY_ZONE} --volume-type gp3
#aws ec2 stop-instances --instance-ids ${INSTANCE_ID}
#sleep 30
#aws ec2 detach-volume --volume-id ${OLD_VOLUME_ID}

read -p "Enter the new volume ID: " VOLUME_ID
aws ec2 attach-volume --volume-id ${VOLUME_ID} --instance-id ${INSTANCE_ID} --device /dev/sda1
aws ec2 start-instances --instance-ids ${INSTANCE_ID}