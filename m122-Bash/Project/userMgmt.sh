#!/usr/bin/bash

# PROJECT TURNED IN
# RECEIVED POINTS: 2O

# TODO: Error Handling 
# TODO Logging 
# TODO Input Validation 
# TODO Confirmation Prompts 
# TODO Password Management 
# TODO User Modification 
# TODO Group Modification 
# TODO Backup & Restore 
# TODO User Reports 
# TODO Interactive Shell 
# TODO Security Enhancements 
# TODO Documentation 
# TODO Integration with Configuration Management Tools 
# TODO Cross-Platform Compatibility 
# TODO UI Improvements

# VARIABLES

# - LINKS -

# Gitlab
LINK='https://gitlab.com/ch-tbz-it/Stud/m122/-/tree/main/04_Bash_Aufg?ref_type=heads'

# MGMT

# USER
USER_LIST='./files/user.txt'

# GROUP
GROUP_LIST='./files/group.txt'


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

# FUNCTIONS

# - UI FUNCTIONS -

# MESSAGE AT SCRIPT START

#
##          <- SHOW ACTIONS ->
user_help() {
    echo "_________________________________________________________________________"
    echo "|                                                                       |"
    echo -e "|                             ${YELLOW}<< ACTIONS >>${RESET}                             |"
    echo "|_______________________________________________________________________|"
    echo "|                                                                       |"
    echo "|                                                                       |"
    echo "|                     |                        |                        |"
    echo -e "|       Add: ${WHITE}[A]${RESET}      |      Delete: ${WHITE}[D]${RESET}       |       Search: ${WHITE}[S]${RESET}      |"
    echo "|                     |                        |                        |"
    echo "|                                                                       |"
    echo "|                     |                        |                        |"
    echo -e "|      List: ${WHITE}[L]${RESET}      |      Modify: ${WHITE}[M]${RESET}       |                        |"
    echo "|                     |                        |                        |"
    echo "|                                                                       |"
    echo "|_______________________________________________________________________|"
    echo "|                                                                       |"
    echo -e "|                         ${YELLOW}<< PROGRAM ACTIONS >>${RESET}                         |"
    echo "|_______________________________________________________________________|"
    echo "|                                                                       |"
    echo "|                     |                        |                        |"
    echo -e "|      Quit: ${WHITE}[Q]${RESET}      |       Help: ${WHITE}[H]${RESET}        |      Restart: ${WHITE}[R]${RESET}      |"
    echo "|                     |                        |                        |"
    echo "|                                                                       |"
    echo "|_______________________________________________________________________|"
    echo " "
    echo " "
    echo "_________________________________________________________________________"
    echo "|                                                                       |"
    echo -e "|                              ${YELLOW}<< TEST >>${RESET}                               |"
    echo "|_______________________________________________________________________|"
    echo "|                                                                       |"
    echo "|                                                                       |"
    echo -e "|                               Test: ${WHITE}[T]${RESET}                               |"
    echo "|                                                                       |"
    echo "|                                                                       |"
    echo "|_______________________________________________________________________|"
    echo " "
    echo " "
}
display_header() {
        echo "_________________________________________________________________________"
    echo "|                                                                       |"
    echo "|                       __________________________                      |"
    echo "|                      |                          |                     |"
    echo -e "|                      |   ${PINK}<< USERMANAGEMENT >>${RESET}   |                     |"
    echo "|                      |                          |                     |"
    echo -e "|                      |           ${PINK}----${RESET}           |                     |"
    echo "|                      |                          |                     |"
    echo -e "|                      |            ${PINK}by${RESET}            |                     |"
    echo "|                      |                          |                     |"
    echo -e "|                      |          ${PINK}[AP23c]${RESET}         |                     |"
    echo "|                      |__________________________|                     |"
    echo "|                                                                       |"
    echo "|                                                                       |"
    echo "|                                                                       |"
    echo "|                               CONTENT:                                |"
    echo "|                                                                       |"
    echo "|                               -------                                 |"
    echo "|                                                                       |"
    echo "|                                                                       |"
    echo "|                FEATURES FOR USERS: add, delete search                 |" 
    echo "|                                                                       |"
    echo "| FEATURES FOR GROUPS: create, add users, search groups, display group  |" 
    echo "|                                                                       |"
    echo "|_______________________________________________________________________|"
}

# Display UI
display_ui() {
    clear
    display_header
    user_help
}


#          <- TEST ->

testing() {
    echo -e "${ITALIC}${ORANGE}LOADING TEST...${RESET}"
    sleep 1
    echo "________________________________________"
    echo "|                                      |"
    echo "|                 COLOR                |"
    echo "|______________________________________|"
    echo ""
    echo -e "${RED}<--      THIS IS A TEST IN RED       -->${RESET}"
    echo -e "${ORANGE}<--     THIS IS A TEST IN ORANGE     -->${RESET}"
    echo -e "${YELLOW}<--     THIS IS A TEST IN YELLOW     -->${RESET}"
    echo -e "${GREEN}<--     THIS IS A TEST IN GREEN      -->${RESET}"
    echo -e "${WHITE}<--     THIS IS A TEST IN WHITE      -->${RESET}"
    echo -e "${PINK}<--     THIS IS A TEST IN PINK       -->${RESET}"
    echo -e "${BLUE}<--     THIS IS A TEST IN BLUE       -->${RESET}"
    echo ""
    echo "________________________________________"
    echo "|                                      |"
    echo "|                 FONT                 |"
    echo "|______________________________________|"
    echo "                                        "
    echo -e "${BOLD}<--   THIS IS A TEST WITH BOLD TEXT  -->${RESET}"
    echo -e "${ITALIC}<--  THIS IS A TEST WITH ITALIC TEXT -->${RESET}"
}

#          - USER FUNCTIONS -

#          <- ADD NEW USER(S) ->
add_user() {
    echo -n -e "${WHITE}Enter username(s): ${RESET}"; read -r USERNAMES;
    for USERNAME in $USERNAMES; do
        if id "${USERNAME}" >/dev/null 2>&1; then
            echo -e "${YELLOW}User ${USERNAME} already exists.${RESET}"
        else
            sudo useradd "${USERNAME}"
            echo -e "${BOLD}${GREEN}User ${USERNAME} created successfully.${RESET}"
        fi
    done
}

#          <- DELETE USER ->
delete_user() {
    echo -n -e "${WHITE}Enter username(s): ${RESET}"; read -r USERNAMES;
    for USERNAME in $USERNAMES; do
        if ! id "${USERNAME}" >/dev/null 2>&1; then
            echo -e "${YELLOW}User ${USERNAME} does not exist.${RESET}"
            return 1
        fi
    done
    echo -n -e "${WHITE}Confirm deletion of the provided users [y/N]: ${RESET}"; read CONFIRMATION
    case $CONFIRMATION in
        Y|y)
            for USERNAME in $USERNAMES; do
                sudo userdel --remove-home "${USERNAME}"
                echo -e "${BOLD}${GREEN}User ${USERNAME} deleted successfully.${RESET}"
            done
            ;;
        *)
            echo -e "${RED}Deletion of users canceled.${RESET}"
            ;;
    esac
}
#          <- SEARCH USER AND HIDE SYSTEM USER ->
list_user() {
    while IFS=: read -r USERNAME _ GID _ _ _ homedir _; do
        GROUP_NAME=$(getent group "$GID" | cut -d: -f1)
        echo -e "USER: ${ITALIC}${WHITE}${USERNAME}${RESET} ${BLUE}|${RESET} GROUP: ${ITALIC}${YELLOW}${GROUP_NAME}${RESET} ${BLUE}|${RESET} GID: ${ITALIC}${ORANGE}${GID}${RESET}"
    done </etc/passwd
}
#          <- SEARCH USER BY PROMPT ->
search_user() {
    echo -n -e "${WHITE}Enter filter prompt (press Enter for all users): ${RESET}"; read PROMPT;
    if [ -z "${PROMPT}" ]; then
        echo -e "${ITALIC}${ORANGE}SEARCHING ALL USERS...${RESET}"
        sleep 1
        echo -e "${BOLD}${GREEN}All users: ${RESET}"
        list_user
    else
        echo -e "${ITALIC}${ORANGE}SEARCHING USERS...${RESET}"
        sleep 1
        echo -e "${BOLD}${GREEN}All users containing '${PROMPT}':${RESET}"
        list_user | grep "${PROMPT}"
    fi
    echo "____________"
}

#          - GROUP FUNCTIONS -


#          <- CREATE NEW GROUP ->
add_group(){
    echo -n -e "${WHITE}Enter groupname(s): ${RESET}"; read -r GROUPNAMES;
    for GROUPNAME in ${GROUPNAMES}; do
        if id "${GROUPNAME}" >/dev/null 2>&1; then
            echo -e "${YELLOW}Group ${GROUPNAME} already exists.${RESET}"
        else
            sudo groupadd "${GROUPNAME}"
            echo -e "${BOLD}${GREEN}Group ${GROUPNAME} created successfully.${RESET}"
        fi
    done
}

#          <- ADD USER TO GROUP ->
# Add user to specified group
addUserToGroup() {
    echo -n -e "${WHITE}Enter group: ${RESET}"; read GROUP;
    echo -n -e "${WHITE}Enter username(s): ${RESET}"; read USERNAMES;
    if getent group "${GROUP}" >/dev/null 2>&1; then
        echo -e "${ITALIC}${ORANGE}ADDING ${USERNAMES} TO ${GROUP}...${RESET}"
        sleep 1
        for USERNAME in ${USERNAMES}; do
            if id "${USERNAME}" >/dev/null 2>&1; then
                sudo usermod -aG "${GROUP}" "${USERNAME}"
                echo -e "${BOLD}${GREEN}User ${USERNAME} added to group ${GROUP}.${RESET}"
            else
                echo -e "${RED}User ${USERNAME} does not exist.${RESET}"
            fi
        done
    else
        echo -e "${RED}Group ${GROUP} not found.${RESET}"
    fi
}

removeUserFromGroup() {
    echo -n -e "${WHITE}Enter group: ${RESET}"; read GROUP;
    echo -n -e "${WHITE}Enter username(s): ${RESET}"; read USERNAMES;
    if getent group "${GROUP}" >/dev/null 2>&1; then
        echo -e "${ITALIC}${ORANGE}REMOVING ${USERNAMES} FROM ${GROUP}...${RESET}"
        sleep 1
        for USERNAME in ${USERNAMES}; do
            if id "${USERNAME}" >/dev/null 2>&1; then
                sudo deluser "${USERNAME}" "${GROUP}"
                echo -e "${BOLD}${GREEN}User ${USERNAME} removed from group ${GROUP}.${RESET}"
            else
                echo -e "${RED}User ${USERNAME} does not exist.${RESET}"
            fi
        done
    else
        echo -e "${RED}Group ${GROUP} not found.${RESET}"
    fi
}

# <- DELETE GROUP ->
delete_group(){
    echo -n -e "${WHITE}Enter groupname(s) to be deleted: ${RESET}"; read -r GROUPNAMES;
    for GROUPNAME in ${GROUPNAMES}; do
        if ! getent group "${GROUPNAME}" >/dev/null 2>&1; then
            echo -e "${YELLOW}Group ${GROUPNAME} does not exist.${RESET}"
            return 1
        fi
    done

    echo -n -e "${WHITE}Confirm deletion of the provided groups [y/N]: ${RESET}"; read -r CONFIRMATION
    case $CONFIRMATION in
        Y|y)
            for GROUPNAME in ${GROUPNAMES}; do
                sudo groupdel "${GROUPNAME}"
                echo -e "${BOLD}${GREEN}Group ${GROUPNAME} deleted successfully.${RESET}"
            done
            ;;
        *)
            echo -e "${RED}Deletion of groups canceled.${RESET}"
            ;;
    esac
}

#          <- DISPLAY GROUPS WITH MEMBERS->
display_groups_with_members() {
    while IFS=: read -r GROUPNAME _ GID MEMBERS; do 
        MEMBER_COUNT=$(echo "${MEMBERS}" | tr ',' '\n' | wc -l)
        GROUP_PERMISSIONS=$(stat -c "%A" /home/${GROUPNAME})
        echo -e "GROUP: ${ITALIC}${WHITE}${GROUPNAME}${RESET} ${BLUE}|${RESET} GID: ${ITALIC}${ORANGE}${GID}${RESET} ${BLUE}|${RESET} MEMBERS: ${ITALIC}${YELLOW}${MEMBER_COUNT}${RESET} ${BLUE}|${RESET} PERMISSIONS: ${ITALIC}${PINK}${GROUP_PERMISSIONS}${RESET}";
    done </etc/group
}
#          <- SEARCH GROUP ->
search_group() {
    echo -n -e "${WHITE}Enter group name (press Enter for all groups): ${RESET}"; read PROMPT
    if [ -z "${PROMPT}" ]; then
        echo -e "${ITALIC}${ORANGE}LISTING ALL GROUPS...${RESET}"
        sleep 1
        display_groups_with_members
    else
        echo -e "${ITALIC}${ORANGE}Searching ${PROMPT}...${RESET}"
        sleep 1
        display_groups_with_members | grep -i "${PROMPT}"
    fi
}
#          <- DISPLAY MEMBERS OF GROUP ->
display_group_members() {
    echo -n -e "${WHITE}Enter group name: ${RESET}"; read GROUPNAME
    echo "Members of group $GROUPNAME: "
    getent group "$GROUPNAME" | cut -d: -f4 | tr ',' '\n'
}

#          - PRGRAM FUNCTIONS -
add() {
    echo -n -e "${WHITE}ADD: User [U] | Group [G]: ${RESET}"; read CHOICE
    case ${CHOICE} in 
        U|u)
            add_user
        ;;
        G|g)
            add_group
        ;;
    esac
}
delete() {
  echo -n -e "${WHITE}DELETE: User [U] | Group [G]: ${RESET}"; read CHOICE
    case ${CHOICE} in 
        U|u)
            delete_user
        ;;
        G|g)
            delete_group
        ;;
    esac  
}
search() {
  echo -n -e "${WHITE}SEARCH: User [U] Group [G]: ${RESET}"; read CHOICE
    case ${CHOICE} in 
        U|u)
            search_user
        ;;
        G|g)
            search_group
        ;;
    esac  
}
modify() {
  echo -n -e "${WHITE}MODIFY: User [U] Group [G]: ${RESET}"; read CHOICE
    case ${CHOICE} in 
        U|u)
            search_user
        ;;
        G|g)
            echo -n -e "${WHITE}Remove user from group [R] Add User to group [A]: "; read CHOICE
            case ${CHOICE} in
            R|r)
                removeUserFromGroup
                ;;
            A|a) 
                addUserToGroup
                ;;
            esac
        ;;
    esac  
}


#          <- RESTART->
restart() {
    exec "$0"
}

#          <- EXIT ->
quit() {
    exit 0
}


#          << MAIN LOOP >>
display_ui
while true; do
    echo " "
    echo -n -e "${WHITE}Enter command: ${RESET}"; read CHOICE
    sleep 1
    case ${CHOICE} in
        A|a)
            add 2> ./errors/addErr.log 2> ./errors/addErr.txt
            ;;
        D|d)  
            delete 2> ./errors/deleteErr.log
            ;;
        S|s)
            search 2> ./errors/searchErr.log
            ;;  
        L|l)
            display_group_members 2> ./errors/displayGroupMembersErr.log 
            ;;
        M|m)
            modify 2> ./errors/modifyErr.log
            ;;
        H|h)  
            user_help 2> ./errors/helpErr.log
            ;;
        Q|q)
            echo -e "${ITALIC}${ORANGE}EXIT SCRIPT...${RESET}"; 
            sleep 1;
            quit 2> ./errors/exitErr.log
            ;;    
        R|r)
            clear
            echo -e "${ITALIC}${ORANGE}RESTARTING...${RESET}"; 
            sleep 1;
            restart 2> ./errors/restartErr.log 
            ;;
        T|t)
            testing 2> ./errors/testErr.log
            ;;
        *)
            echo -e "${YELLOW}Invalid input. Please enter valid key or enter [H] for help${RESET}"
            ;;
    esac
done