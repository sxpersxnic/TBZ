#!/usr/bin/bash

# ASCII
# Colors
RED='\033[38;2;255;0;0m'
BLUE='\033[38;2;0;0;255m'
BRIGHT_BLUE='\033[38;2;105;105;255m'
GREEN='\033[38;2;0;255;0m'
YELLOW='\033[38;2;255;255;0m'
ORANGE='\033[38;2;255;100;0m'
PINK='\033[38;2;255;0;255m'
WHITE='\033[38;2;255;255;255m'
RESET='\033[0m'
# Fontstyle/Fontweight
BOLD='\033[1m'
ITALIC='\033[3m'

# Get user inputs
echo -e -n "${WHITE}Enter pwd (absolute path): ${RESET}"; read PWD
echo -e -n "${WHITE}Enter path to logs (absolute path): ${RESET}"; read LOG_FILE
echo -e -n "${WHITE}Enter path to configuration file (absolute path): ${RESET}"; read CONF_FILE
echo -e -n "${WHITE}Enter path to database migration directory (absolute path): ${RESET}"; read MIGRATIONS_DIR
echo -e -n "${WHITE}Enter host adress ${RESET}${PINK}(Default is 127.0.0.1)${RESET}${WHITE}: ${RESET}"; read HOST
echo -e -n "${WHITE}Enter port on which spring boot application is runnig${RESET}${PINK}(Default is 8080)${RESET}${WHITE}: ${RESET}"; read SPRING_PORT

# Validate inputs
for var in PWD LOG_FILE CONF_FILE MIGRATIONS_DIR; do
    if [ -z "${!var}" ]; then
        echo -e "${RED}${BOLD}Error: ${VAR} is not set!${RESET}"
        exit 1
    fi
done

# Set default host if not provided
if [ -z "${HOST}" ]; then
    echo -e "${YELLOW}${ITALIC}Setting host to: ${RESET}${PINK}127.0.0.1${RESET}"
    HOST="localhost"
fi

# Set default port if not provided
if [ -z "${SPRING_PORT}" ]; then
    echo -e "${YELLOW}${ITALIC}Setting port to: ${RESET}${PINK}8080${RESET}"
    SPRING_PORT="8080"
fi

# Load configuration
source ${CONF_FILE}

# Start MySQL server
echo -e "${YELLOW}${ITALIC}Starting MySQL server...${RESET}"
sudo service mysql start
if [ $? -ne 0 ]; then
    echo -e "${RED}${BOLD}Failed to start MySQL server!${RESET}"
    exit 1
fi

#Navigate to project dir
echo -e "${YELLOW}${ITALIC}Changing to directory: ${PWD}${RESET}"
cd ${PWD}

# Run database migrations
if [ -n "${MIGRATIONS_DIR}" ]; then
    echo -e "${YELLOW}${ITALIC}Running database migrations...${RESET}"
    ./gradlew flywayMigrate -i
    if [ $? -ne 0 ]; then
    echo -e "${RED}${BOLD}Failed to run database migrations!${RESET}"
    exit 1
    fi
fi

# Run tests
echo -e "${YELLOW}${ITALIC}Running tests...${RESET}"
./gradlew test
if [ $? -ne 0 ]; then
    echo -e "${RED}${BOLD}Tests failed!${RESET}"
    exit 1
fi

# Run Spring Boot application
echo -e "${YELLOW}${ITALIC}Running Spring Boot application...${RESET}"
./gradlew bootRun &

# Get the process id of the last command run in the background
PID=$!

# Wait for application to start
sleep 10

# Check application health
echo -e "${YELLOW}${ITALIC}Checking application health...${RESET}"
curl --fail http://${HOST}:${SPRING_PORT}/actuator/health || exit 1

# Tail the logs
echo -e "${YELLOW}${ITALIC}Tailing logs...${RESET}"
tail -f ${LOG_FILE}

# When this script exits, stop the Spring Boot application
trap "echo '${ORANGE}Stopping Spring Boot application...${RESET}'; kill ${PID}" EXIT