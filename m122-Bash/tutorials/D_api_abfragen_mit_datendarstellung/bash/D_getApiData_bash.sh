#!/usr/bin/bash

# D_api_abfrage_mit_datendarstellung

# VARIABLES

# LINKS
GITLAB='https://gitlab.com/ch-tbz-it/Stud/m122/-/tree/main/10_Projekte_LB2/D_api-abfragen-mit-datendarstellung'
GET_API=$(curl -s 'https://api.ipify.org?format=json' | jq -r '.ip' )
SHOW_DATA="Data received: ${GET_API}"
# with wget: GET_API_DATA=$(wget '' | jq -r '.parent.child')
# with curl: GET_API_DATA=$(curl '' | jq -r '.parent.child')
DIRECTORY='/mnt/c/Development/coding/Scripting/PROJECT-SCRIPTS/D_api_abfragen_mit_datendarstellung/bash'

# COLORS
RED='\033[38;2;255;0;0m'
BLUE='\033[38;2;0;0;255m'
GREEN='\033[38;2;0;200;0m'
YELLOW='\033[38;2;255;255;0m'
ORANGE='\033[38;2;255;100;0m'
PINK='\033[38;2;255;0;255m'
WHITE='\033[38;2;255;255;255m'
RESET='\033[0m'

# SCRIPT
echo ${SHOW_DATA} >> ./API_DATA.log
printf "Data saved to:\n\n ./API_DATA.log\n\n"