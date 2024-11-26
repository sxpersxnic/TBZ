#!/usr/bin/bash

# VARIABLES

DEV_PWD="/mnt/c/Development/coding/Scripting/PROJECT-SCRIPTS/A_verzeichnisse_und_dateien_anlegen/dev_directories"

# - ASCII -
# COLORS
RED='\033[38;2;255;0;0m'
BLUE='\033[38;2;0;0;255m'
GREEN='\033[38;2;0;200;0m'
YELLOW='\033[38;2;255;255;0m'
ORANGE='\033[38;2;255;100;0m'
PINK='\033[38;2;255;0;255m'
WHITE='\033[38;2;255;255;255m'
RESET='\033[0m'

# FONT
BOLD='\033[1m'
ITALIC='\033[3m'

# UI

example_file_creation() {
    echo -e "${WHITE}YOU WILL ENTER THREE INPUTS: 1. FILENAME(S), example:${RESET} ${PINK}helloWorld${RESET} ${WHITE}2. DATATYPE(S), example:${RESET} ${YELLOW}.txt${RESET} ${WHITE}3. AMOUNT OF FILES, example:${RESET} ${ORANGE}3${RESET}"
}

# FUNCTIONS

create_dir() {
    echo -n -e "${WHITE}Enter new directory name(s): ${RESET}"; read -r DIRECTORIES;
    for ${DIRECTORY} in ${DIRECTORIES}; do
        cd ${DEV_PWD}
        mkdir ${DIRECTORY}
    done

}

touch_files() {
    example_file_creation
    echo -n -e "${WHITE}Enter new file name(s): ${RESET}"; read -r FILENAMES;
    echo -n -e "${WHITE}Enter amount of files to create: ${RESET}"; read -r AMOUNT;
    for ${FILENAME} in ${FILENAMES}; do
    done

}

#          <- RESTART->
restart() {
    exec "$0"
}

#          <- EXIT ->
quit() {
    exit 0
}

# MAIN
while true; do
    echo " "
    echo -n -e "${WHITE}Enter command: ${RESET}"; read CHOICE
    sleep 1
    case ${CHOICE} in
        D|d) 
            create_dir
            ;;
        F|f) 
            touch_files
            ;;
        R|r)
            clear
            echo -e "${ITALIC}${ORANGE}RESTARTING...${RESET}"; 
            sleep 1;
            restart 
            ;;

        Q|q)
            echo -e "${ITALIC}${ORANGE}EXIT SCRIPT...${RESET}"; 
            sleep 1;
            quit
            ;;    
        *)
            echo -e "${YELLOW}Invalid input. Please enter valid key or enter [H] for help${RESET}"
            ;;
    esac
done
