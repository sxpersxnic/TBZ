#!/bin/bash

# VARIABLES

DEV_PWD="/mnt/c/Development/coding/Scripting/PROJECT-SCRIPTS/A_verzeichnisse_und_dateien_anlegen"
DESTINATION="dev_dir"
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

# TEMPLATE CREATION
mkdir -p ${DESTINATION}/_templates
touch ${DESTINATION}/_templates/datei-1.txt ${DESTINATION}/_templates/datei-2.docx ${DESTINATION}/_templates/datei-3.pdf

# UI
display_menu() {
    clear
    echo -e "${BOLD}${PINK}Choose an option: ${RESET}"
    echo -e "${YELLOW}1.${RESET}${WHITE} Create classes${RESET}"
    echo -e "${YELLOW}2.${RESET}${WHITE} Add students to classes${RESET}"
    echo -e "${YELLOW}3.${RESET}${WHITE} Restart${RESET}"
    echo -e "${YELLOW}4.${RESET}${WHITE} Exit${RESET}"
}

# FUNCTION

# FUNCTION TO CREATE CLASS
create_class() {
    mkdir -p ${DEV_PWD}/${DESTINATION}/_schulklassen
        
    echo -n -e "${WHITE}Enter class names (separated by space): ${RESET}";
    read -r -a CLASSNAME

    for CLASS in "${CLASSNAME[@]}"; do
        touch "${DEV_PWD}/${DESTINATION}/_schulklassen/${CLASS}.txt"
        echo -e "${ORANGE}Class ${CLASS} created successfully!${RESET}"
    done
}

# FUNCTION TO ADD STUDENTS TO CLASSES
add_student() {
    echo -e "${YELLOW}Available classes: ${RESET}"
    for CLASS_FILE in "${DEV_PWD}/${DESTINATION}/_schulklassen"/*.txt; do
        echo "$(basename "$CLASS_FILE" .txt)"
    done

    echo -n -e "${WHITE}Enter name of class to add students to: ${RESET}"; 
    read -r STUDENT_CLASS

    if [[ -f "${DEV_PWD}/${DESTINATION}/_schulklassen/${STUDENT_CLASS}.txt" ]]; then
        echo -e "${WHITE}Enter student names for class ${STUDENT_CLASS} (one name per line, type 'done' when finished): ${RESET}"
        while IFS= read -r STUDENT_NAME && [ "${STUDENT_NAME}" != "done" ]; do
            echo -e "${STUDENT_NAME}" >> "${DEV_PWD}/${DESTINATION}/_schulklassen/${STUDENT_CLASS}.txt"
        done
        echo " "
        echo -e "${YELLOW}Students added to class ${STUDENT_CLASS}: ${RESET}"
        echo " "
        cat ${DESTINATION}/_schulklassen/${STUDENT_CLASS}.txt
        echo " "
        echo "Filestructure added:"
        tree ${DESTINATION}/_templates ${DESTINATION}/_schulklassen
    else
        echo "Class ${STUDENT_CLASS} does not exist!"
    fi
}


# RESTART & QUIT
restart() {
    exec "$0"
}

quit() {
    exit 0
}


cd ${DEV_PWD}
display_menu
while true; do
    
    echo " "
    echo -n -e "${WHITE}Enter command: ${RESET}"; read CHOICE
    sleep 1
    case ${CHOICE} in
        1) 
            create_class
            ;;
        2)  
            add_student
            ;;
        3)
            clear
            echo -e "${ITALIC}${ORANGE}RESTARTING...${RESET}"
            sleep 0.5
            restart
            ;;
        4)
            echo -e "${ITALIC}${ORANGE}EXIT SCRIPT...${RESET}"
            sleep 0.5
            quit
            ;;
        *)
            echo -e "${YELLOW}Invalid input. Please enter valid key!${RESET}"
            ;;
    esac
done