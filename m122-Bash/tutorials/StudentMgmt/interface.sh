#!/usr/bin/env bash

# Renders a text-based list of options that can be selected by the user
# using up, down, and enter keys, and returns the chosen option.
#
# Arguments: list of options, maximum of 256
#            "opt1" "opt2" ...
# Return value: selected index (0 for opt1, 1 for opt2, etc.)
function select_option {
    # Helper functions for terminal control and key input
    ESC=$(printf "\033")
    cursor_blink_on() { printf "$ESC[?25h"; }
    cursor_blink_off() { printf "$ESC[?25l"; }
    cursor_to() { printf "$ESC[$1;${2:-1}H"; }
    print_option() { printf "   $1 "; }
    print_selected() { printf "  $ESC[7m $1 $ESC[27m"; }
    get_cursor_row() { IFS=';' read -sdR -p $'\E[6n' ROW COL; echo ${ROW#*[}; }
    key_input() {
        read -s -n3 key 2>/dev/null >&2
        if [[ $key = "$ESC[A" ]]; then echo up; fi
        if [[ $key = "$ESC[B" ]]; then echo down; fi
        if [[ $key = "" ]]; then echo enter; fi
    }

    # Initially print empty new lines (scroll down if at the bottom of the screen)
    for opt; do printf "\n"; done

    # Determine current screen position for overwriting the options
    local lastrow=$(get_cursor_row)
    local startrow=$(($lastrow - $#))

    # Ensure cursor and input echoing are back on upon a Ctrl+C during read -s
    trap "cursor_blink_on; stty echo; printf '\n'; exit" 2
    cursor_blink_off

    local selected=0
    while true; do
        # Print options by overwriting the last lines
        for ((i=0; i<$#; i++)); do
            cursor_to $(($startrow + $i))
            if [ $i -eq $selected ]; then
                print_selected "${!i}"
            else
                print_option "${!i}"
            fi
        done

        # Get user input
        case $(key_input) in
            up) 
                ((selected--)); [ $selected -lt 0 ] && selected=$(($# - 1))
                ;;
            down) 
                ((selected++)); [ $selected -ge $# ] && selected=0
                ;;
            enter) 
                break
                ;;
        esac
    done

    # Clean up and return the selected index
    cursor_blink_on
    return $selected
}

# Example usage:
options=("Option 1" "Option 2" "Option 3")
select_option "${options[@]}"
selected_index=$?
echo "Selected option: ${options[$selected_index]}"
