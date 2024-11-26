#!/usr/bin/bash

# < VARIABLES >

group_list="./files/group.txt"
user_list="./files/user.txt"
link="https://gitlab.com/ch-tbz-it/Stud/m122/-/tree/main/04_Bash_Aufg?ref_type=heads"

RED='\033[38;2;255;0;0m'
GREEN='\033[32m'
YELLOW='\033[33m'
RESET='\033[0m'

BOLD='\033[1m'
ITALIC='\033[3m'

# < FUNCTIONS >

### - UI FUNCTIONS -

#### - MESSAGE AT SCRIPT START -

display_ui() {
    # clear # - CLEAR TERMINAL -
    echo "_________________________________________________________________________"
    echo "|                                                                       |"
    echo "|                                                                       |"
    echo "|                        |    USERMANAGEMENT    |                       |"
    echo "|                                                                       |"
    echo "|                                                                       |"
    echo "|                                   by                                  |"
    echo "|                                                                       |"
    echo "|                        | Melvin Kampus AP23c  |                       |"
    echo "|                                                                       |"
    echo "| _____________________________________________________________________ |"
    echo "|                                                                       |"
    echo "|                                                                       |"
    echo "|                         DESCRIPTION OF SCRIPT:                        |"
    echo "|                                                                       |"
    echo "|                                                                       |"
    echo "|                                                                       |"
    echo "|                FEATURES FOR USERS: add, delete search                 |" 
    echo "|                                                                       |"
    echo "|                FEATURES FOR GROUPS: create, add users                 |" 
    echo "|                                                                       |"
    echo "| _____________________________________________________________________ |"
    user_help
}

#### - SHOW USER OPTIONS -
user_help() {
    echo "_________________________________________________________________________"
    echo "|                                                                       |"
    echo "|                            < USER ACTIONS >                           |"
    echo "|_______________________________________________________________________|"
    echo "|                                                                       |"
    echo "|                     |                        |                        |"
    echo "|    Add user: [A]    |    Delete user: [D]    |    Search user: [S]    |"
    echo "|                     |                        |                        |"
    echo "|                                                                       |"
    echo "|_______________________________________________________________________|"
    echo "|                                                                       |"
    echo "|                           < GROUP ACTIONS >                           |"
    echo "|_______________________________________________________________________|"
    echo "|                                                                       |"
    echo "|                     |                        |                        |"
    echo "|  Create group: [C]  | Add user to group: [G] |   Search group: [V]    |"
    echo "|                     |                        |                        |"
    echo "|                                                                       |"
    echo "|_______________________________________________________________________|"
    echo "|                                                                       |"
    echo "|                          < PROGRAM ACTIONS >                          |"
    echo "|_______________________________________________________________________|"
    echo "|                                                                       |"
    echo "|                                   |                                   |"
    echo "|             Exit: [Q]             |             Help: [H]             |"
    echo "|                                   |                                   |"
    echo "|                                                                       |"
    echo "|_______________________________________________________________________|"
    echo " "
    echo " "
    echo "_________________________________________________________________________"
    echo "|                                                                       |"
    echo "|                               < TEST >                                |"
    echo "|_______________________________________________________________________|"
    echo "|                                                                       |"
    echo "|                                                                       |"
    echo "|                               Test: [T]                               |"
    echo "|                                                                       |"
    echo "|                                                                       |"
    echo "|_______________________________________________________________________|"
    echo " "
    echo " "
}

### - TEST -

testing() {
    echo ""
    echo -e "${YELLOW}TEST:${RESET} <-- THIS IS A TEST -->"
    echo ""
}
### - USER FUNCTIONS -

#### - ADD NEW USER -
add_user() {
    echo -n "Enter new username: "; read new_user;
        if id "$new_user" >/dev/null 2>&1; then
            echo "User $new_user exists already, please select a different name."
        else
            echo "ADDING USER...";
            sudo useradd $new_user
            echo "User $new_user added successfully."
        fi
}

#### - HIDE SYSTEM USER -
search_users() {
    while IFS=: read -r username _ _ _ _ homedir _; do
        echo "$username"
    done </etc/passwd
}

#### - SEARCH USER BY PROMPT -
show_specific_users() {
    echo -n "Enter filter prompt (press Enter for all users): "; read prompt;
    if [ -z "$prompt" ]; then
        echo "SEARCHING ALL USERS..."
        echo "All users: "
        search_users
    else
        echo "SEARCHING USERS..."
        echo "All users that contain '$prompt':"
        search_users | grep "$prompt"
    fi
    echo "- - - - - - -"
}

#### - DELETE USER -
delete_user() {
    echo -n "Enter username of user to be deleted: "; read delete_user;
        if id "$delete_user" >/dev/null 2>&1
        then
            echo -n "Confirm deletion of $delete_user [y/N]: "; read confirmation
            case $confirmation in
                Y|y)
                    echo "DELETING USER..."
                    sudo userdel $delete_user
                    echo "User $delete_user was deleted successfully!"
                    ;;
                *)
                    echo -e "\033[31mProcess canceled\033[0m"
                    ;;
                    
            esac
        else
            echo "$delete_user does not exist."
        fi
}
### - GROUP FUNCTIONS -

search_groups() {
    echo -n "Enter group name (press Enter for all groups): "; read prompt
    if [ -z "$prompt" ]; then
        echo "Listing all groups:"
        cut -d: -f1 /etc/group
    else
        echo "Searching for groups containing '$prompt':"
        grep -i "$prompt" /etc/group | cut -d: -f1
    fi
}
#### - CREATE NEW GROUP -
create_group() {
    echo -n "Enter new groupname: "; read new_group;
        if id "$new_group" >/dev/null 2>&1; then
            echo "Group $new_group exists already, select new name."
        else
            sudo groupadd $new_group
            echo "Group $new_group was created successfully!"
        fi
}

#### - ADD USER TO GROUP -
addUserToGroup() {
    
    echo -n "Enter username: "; read username;
    echo -n "Enter group: "; read grouplist;

    if id "$username" >/dev/null 2>&1; then
        echo "User $username exists."

        if [ -f "$group_list" ]; then
            echo "Grouplist found."

            while IFS= read -r group; do
                sudo usermod -aG "$group" "$username"
                echo "User $username added to group $group."
            done < "$group_list"
        else
            echo "Group not found."
        fi
    else
        echo "User $username does not exist."
    fi
}

# < MAIN LOOP >
display_ui
while true; do
    echo " "
    echo -n "Enter command: "; read choice
    sleep 2
    # Überprüfen, welche Option ausgewählt wurde
    case $choice in
        [Aa])
            add_user 2> ./errors/addUserErr.log
            ;;
        [Dd])  
            delete_user 2> ./errors/deleteUserErr.log
            ;;
        [Cc]) 
            create_group 2> ./errors/createGroupErr.log
            ;;    
        [Ss])
            show_specific_users 2> ./errors/searchUserErr.log
            ;;
        [Vv])
            search_groups 2> ./errors/searchGroupsErr.log
            ;;
        [Gg])  
            addUserToGroup 2> ./errors/addToGroupErr.log
            ;;
        [Hh])  
            user_help 2> ./errors/helpErr.log
            ;;
        [Qq])
            echo "EXIT SCRIPT..."; exit 0 2> ./errors/exitErr.log
            ;;    
        [Rr])
            clear
            echo "RESTARTING..."; exec "$0" 2> ./errors/restartErr.log
            ;;
        [Tt])
            testing 2> ./errors/testErr.log
            ;;
        *)
            echo "Invalid input. Please enter valid key or enter [H] for help"
            ;;
    esac
    
    # sleep 0.5 # - wait 0.5 seconds until continue

done