#!/usr/bin/bash

# VARIABLES
DEV_DIR='/mnt/c/Development/coding/Scripting/m122-Projects/B_emailadressen_erzeugen/dev_dir/data'
CSV_FILE='YYYY-MM-DD_HH-SS_mailimports.csv'
MOCK_DATA='mock_data.csv'

# STAGE 1

while IFS=, read -r id firstname lastname gender street streetNum zipCode place
do
    # Generate email address
    email=$(echo "${firstname}.${lastname}@edu.tbz.ch" | tr '[:upper:]' '[:lower:]')

    # Generate password
    password=$(openssl rand -base64 12 | tr -dc 'a-zA-Z0-9' | head -c12)

    # Write email address and password to csv
    echo "$email;$password" >> ${DEV_DIR}/${CSV_FILE}
done < ${DEV_DIR}/${MOCK_DATA}
