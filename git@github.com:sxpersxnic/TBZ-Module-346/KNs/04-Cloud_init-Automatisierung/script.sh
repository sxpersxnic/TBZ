#!/usr/bin/bash


BOLD='\033[1m'
ITALIC='\033[3m'

YELLOW='#faff4e'
RED='#ff0000'
GREEN='#4eff84'
ORANGE='#ffa74f'
PINK='#ee4eff'
BLUE='#99ccff'

# Function to set RGB color
# Params: type (F=Foreground, B=Background), RED, GREEN, BLUE
function hex() {
  local hex=${1:-'#000000'}
  local type=${2:-"F"}
  local r=$((16#${hex:1:2}))
  local g=$((16#${hex:3:2}))
  local b=$((16#${hex:5:2}))

  if [ "${type}" == "F" ]; then
    echo -e "\033[38;2;${r};${g};${b}m"
  elif [ "${type}" == "B" ]; then
    echo -e "\033[38;2;${r};${g};${b}m"
  fi
}

function print() {
  local colorHex=${1:-"#000000"}
  local text="${2}"
  local type="${3:-"F"}"
  local color=$(hex ${colorHex} ${type})
  local styles=""

  if [ "$#" -gt 2 ]; then
    shift 3
  else 
    shift 2
  fi
  
  for style in "$@"; do
    styles+="${style}"
  done

  echo -e "${color}${styles}>>> ${text}\033[0m"
}

# print ${YELLOW} "This is yellow text" "F"
# print ${RED} "This is red bold text" "F" ${BOLD} 
# print ${GREEN} "This is green bold background text" "B" ${BOLD}
# print ${ORANGE} "This is orange italic text" "F" ${ITALIC}
# print ${PINK} "This is pink bold italic text" "F" ${BOLD} ${ITALIC}
# print ${BLUE} "This is blue bold italic background text" "B" ${BOLD} ${ITALIC}

function runCmd() {
  local cmd=${1}
  local retries=3
  local count=0
  local success=0

  while [ ${count} -lt ${retries} ]; do
    eval "${cmd}"
    if [ $? -eq 0 ]; then
      success=1
      print "${GREEN}" "Successfully executed ${cmd}" "F"
      break
    else
      count=$((count + 1))
      print "${RED}" "Failed to execute: ${cmd}. Retry ${count}/${retries}" "F" "${BOLD}"
    fi
  done

  if [ ${success} -eq 0 ]; then
      print "${RED}" "Failed to execute: ${cmd} after ${retries}" "F" "${BOLD}"
  fi
}

function install() {
  local package=${1}
  print "${PINK}" "Installing: ${package}" "F"
  runCmd "sudo apt install ${package}"
}

function restart() {
  local service=${1}
  print "${PINK}" "Restarting: ${service}" "F"
  runCmd "sudo systemctl restart ${service}"
}

function remove() {
  local package=${1}
  print "${ORANGE}" "Removing: ${package}" "F"
  runCmd "sudo apt remove ${package}"
}

function aptUp() {
  print "${PINK}" "Updating: apt" "F"
  runCmd "sudo apt update"

  print "${PINK}" "Upgrading: apt" "F"
  runCmd "sudo apt upgrade"
}

print ${BLUE} "[i] Install package [r] Restart service [rm] Remove package [u] update & upgrade apt" "B"
echo -e -n "$(hex ${BLUE} "F")>>> Enter function to execute: "; read -r choice

if [ ${choice} == "q" ]; then
  exit 0
fi

case ${choice} in
  i|I)  
    echo -e -n "$(hex ${BLUE} "F")>>> Enter Package to install: "; read -r package
    
    if [ "${package}" == "q" ]; then
      clear
      exec "$0"
    else 
      install ${package}
    fi
  ;;
  r|R)
    echo -e -n "$(hex ${BLUE} "F")>>> Enter Service to restart: "; read -r service
    
    if [ "${package}" == "q" ]; then
      clear
      exec "$0"
    else 
      restart ${service}
    fi
  ;;
  rm|RM|Rm)
    echo -e -n "$(hex ${BLUE} "F")>>> Enter Package to remove: "; read -r package
    
    if [ "${package}" == "q" ]; then
      clear
      exec "$0"
    else 
      remove ${package}
    fi
  ;;
  u|U)
    aptUp
esac

exec "$0"