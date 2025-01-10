#!/usr/bin/bash

SOURCE_DIR="/mnt/c/Development"
BACKUP_DIR="/mnt/c/Users/melvi/OneDrive/Backups"
BACKUP_NAME="dev_backup_$(date +%Y%m%d_%H%M%S).tar.gz"

printf "Backup destination: ${BACKUP_DIR}/${BACKUP_NAME} Directory to be backed up: ${SOURCE_DIR}\n\n"
tar -czf ${BACKUP_DIR}/${BACKUP_NAME} ${SOURCE_DIR}