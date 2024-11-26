#!/usr/bin/bash

# '\033[' -> Start of ansi code (ANSI Code for 'ESC')
# '\033[38;2; ... m' -> Foreground rgb
# '\033[48;2; ... m' -> Background rgb
# 'm' -> End of ansi code

# ANSI Colors:

## Foreground

### \033[30m - Black
### \033[31m - Red
### \033[32m - Green
### \033[33m - Yellow
### \033[34m - Blue
### \033[35m - Magenta
### \033[36m - Cyan
### \033[37m - White

## Background

### \033[40m - Black
### \033[41m - Red
### \033[42m - Green
### \033[43m - Yellow
### \033[44m - Blue
### \033[45m - Magenta
### \033[46m - Cyan
### \033[47m - White

START='\033['
END='m'
RESET='\033[0m'

FG='38'
BG='48'

DEFAULT_COLOR='\033[37m'

BOLD='\033[1m'
ITALIC='\033[3m'

GITLAB_REPO="https://gitlab.com/ch-tbz-it/Stud/m346/m346scripts.git"

# Function to set RGB color
# Params: type (F=Foreground, B=Background), RED, GREEN, BLUE
function rgb() {
  local type=${1:-F}
  local r=${2}
  local g=${3}
  local b=${4}

  if [ "${type}" == "F" ]; then
    echo -e "${START}${FG};2;${r};${g};${b}${END}"
  elif [ "${type}" == "B" ]; then
    echo -e "${START}${BG};2;${r};${g};${b}${END}"
  fi
}

# Function to convert hex to RGB
# Params: type (F=Foreground, B=Background), HEX
function hex() {
  local type=${1:-F}
  local hex=${2}
  local red=$((16#${hex:1:2}))
  local green=$((16#${hex:3:2}))
  local blue=$((16#${hex:5:2}))
  rgb "${type}" ${red} ${green} ${blue}
}

function newLine() {
 echo " "
}

# Function to print text with custom color and style
# Params: text, [color_code], [type (F=Foreground, B=Background)], [style_code1, style_code2, ...]
function printCustom() {
  local color=${1:-${DEFAULT_COLOR}}
  local text="${2}"
  shift 2
  local styles=""
  for style in "$@"; do
    styles+="${style}"
  done
    newLine
    echo -e "${color}${styles}>>> ${text}${RESET}"
    newLine
}

function printLn() {
  local color=${1:-${DEFAULT_COLOR}}
  local text="${2}"
  shift 2
  local styles=""
  for style in "$@"; do
    styles+="${style}"
  done
    newLine
    echo -e -n "${color}${styles}>>> ${text}${RESET}"
}

# Colors
YELLOW_FG=$(rgb F 250 255 78)
RED_FG=$(rgb F 255 0 0)
GREEN_FG=$(rgb B 78 255 132)
ORANGE_FG=$(rgb F 255 155 78)
PINK_FG=$(rgb F 238 78 255)
BLUE_FG=$(rgb F 153 204 255)

# Function to display error messages
function printError() {
  local text=$1
  printCustom "${RED_FG}" "${text}" "${BOLD}"
}

# Function to display success messages
function printSuccess() {
  local text=$1
  printCustom "${GREEN_FG}" "${text}" "${BOLD}"
}

function logMessage() {
  local level=${1}
  local text=${2}
  local log_file;
  local log_dir;
  local color;

  case ${level} in
    "ERROR"|"error")
      level="ERROR"
      color=${RED_FG}
      log_dir="${HOME}/kn03/log/error"
    ;;
    "INFO"|"info")
      level="INFO"
      color=${BLUE_FG}
      log_dir="${HOME}/kn03/log/info"
    ;;
    "SUCCESS"|"success")
      level="SUCCESS"
      color=${GREEN_FG}
      log_dir="${HOME}/kn03/log/success"
    ;;
  esac

  log_file="${log_dir}/log_$(date +%Y-%m-%d).log"
  mkdir -p "${log_dir}"
  echo ">>> [$(date +%Y-%m-%d\ %H:%M:%S)] [${level}] ${text}" >> "${log_file}"
}

function newError() {
  printError "${1}"
  logMessage "ERROR" "${1}"
}

function runCommand() {
  local cmd=${1}
  local retries=3
  local count=0
  local success=0

  while [ ${count} -lt ${retries} ]; do
    eval "${cmd}"
    # shellcheck disable=SC2181
    if [ $? -eq 0 ]; then
      success=1
      logMessage "SUCCESS" "Successfully executed ${cmd}"
      printCustom "${GREEN_FG}" "Successfully executed ${cmd}"
      break
    else
      count=$((count + 1))
      newError "Failed to execute: ${cmd}. Retry ${count}/${retries}"
    fi
  done

  if [ ${success} -eq 0 ]; then
    newError "Failed to execute: ${cmd} after ${retries} retries"
  fi
}

function installPackage() {
  local package=${1}
  printCustom "${PINK_FG}" "Installing: ${package}"
  runCommand "sudo apt install ${package}"
}

function restartService() {
  local service=${1}
  printCustom "${PINK_FG}" "Restarting: ${service}"
  runCommand "sudo systemctl restart ${service}"
}

function removeAll() {
  runCommand "sudo apt remove apache2 php libapache2-mod-php mariadb-server php-mysqli"
}

printLn "${YELLOW_FG}" "Set MySQL Password (NOTE: LP CAN SEE PWD): "; read -r -s MySQL_Pwd;

# Install packages
printCustom "${PINK_FG}" "Updating: apt"
runCommand "sudo apt update"

printCustom "${PINK_FG}" "Upgrading: apt"
runCommand "sudo apt upgrade"

removeAll
installPackage "apache2"
installPackage "php"
installPackage "libapache2-mod-php"
installPackage "mariadb-server"
installPackage "php-mysqli"

# Config
printCustom "${PINK_FG}" "Configuring: MySQL"
runCommand "sudo mysql -sfu root -e \"GRANT ALL ON *.* TO 'admin'@'%' IDENTIFIED BY '${MySQL_Pwd}' WITH GRANT OPTION;\""    

restartService "mariadb.service"
restartService "apache2"

# Change to home dir
printCustom "${ORANGE_FG}" "Creating and changing to dir: ~/kn03"

runCommand "sudo rm -r ${HOME}/kn03"
runCommand "mkdir -p ${HOME}/kn03"
cd "${HOME}/kn03" || { printError "Error: failed to change directory to ${HOME}/kn03"; logMessage "ERROR" "Failed to change directory to ${HOME}/kn03"; exit 1;}

printCustom "${PINK_FG}" "Cloning git repository: ${GITLAB_REPO}"
runCommand "git clone ${GITLAB_REPO}"

#! There should be something wrong in the ./m346scripts/KN03/ dir
logMessage "INFO" "Copying .php files into filesystem of apache webserver"
printCustom "${ORANGE_FG}" "Copying: ./m346scripts/KN03/*.php To: /var/www/html"
runCommand "sudo cp ./m346scripts/KN03/*.php /var/www/html"

printCustom "${BLUE_FG}" "Manually search Securitygroups and configure rules, so both port 80 and 22 can pass. Make sure to only edit incoming rules NEVER outgoing!" "${BOLD}" "${ITALIC}"
printLn "${YELLOW_FG}" "Continue? [Y|n]"; read -r choice

case ${choice} in
  N|n) exit 1; ;;
  *) ;;
esac

IPv4=$(hostname -I | awk '{print $1}')
URL="http://example.com"

printCustom "${PINK_FG}" "Sending Request to IPv4: ${IPv4}"
runCommand "sudo curl ${IPv4}"
printCustom "${BLUE_FG}" "Forwarding to URL: ${URL}" "${ITALIC}"
runCommand "sudo curl -H \"X-Forwarded-For: ${IPv4}\" ${URL}"

# shellcheck disable=SC2181
if [ $? -eq 0 ]; then
  logMessage "SUCCESS" "Script executed successfully! ☺"
else
  printError "An error occured during script execution!"
  printLn "${YELLOW_FG}" "Restart? [y|N]: "; read -r restartChoice

  case ${restartChoice} in
    y|Y)
      printCustom "${ORANGE_FG}" "Restarting..."
      exec "$0"
    ;;

    *)
      printCustom "${ORANGE_FG}" "See ${HOME}/kn03/log/error for error logs"
      exit 1;
    ;;
  esac
fi