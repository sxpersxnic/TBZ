#!/usr/bin/bash

# STAGE 5

# VARIABLES
DEV_DIR='/mnt/c/Development/coding/Scripting/m122-Projects/B_emailadressen_erzeugen/bash/dev_dir/backup'

ftp -n <<EOF
open ftp.example.com
user username password
put ${DEV_DIR}/YYYY-MM-DD_newMailadr_AP23c_Name.tar.gz
EOF
