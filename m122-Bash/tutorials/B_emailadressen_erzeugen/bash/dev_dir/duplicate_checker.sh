#!/usr/bin/bash

# STAGE 7

# VARIABLES
DEV_DIR='/mnt/c/Development/coding/Scripting/PROJECT-SCRIPTS/B_emailadressen_erzeugen/dev_dir/data'

declare -A counts

while read -r name
do
    (( counts[${name}]++ ))

    if (( counts[${name}] > 1 ))
    then
        unique_name="{name}_${counts[$name]}"
        echo "$name -> ${unique_name}"
    else 
        echo "$name"
    fi
done < ${DEV_DIR}/names.txt