#!/usr/bin/env bash

# Automation for creating ePortfolio and asset directory
DIR="/mnt/c/Users/melvi/OneDrive - TBZ/2024_2024_Y1/2023_2024_Y1_S1/M117"
EPORTFOLIO_DIR="${DIR}/ePortfolios"
LOG_DIR="${DIR}/log"
DATE=$(date +%d-%m-%Y)
ASSETS_DIR="${DIR}/assets"
FILE="ePortfolio_${DATE}.md"
LOGFILE="${LOG_DIR}/log_${DATE}.log"
TOAST_PATH="C:\Users\melvi\OneDrive - TBZ\2024_2024_Y1\2023_2024_Y1_S1\M117\script\toast.ps1"
PDF_PATH="${DIR}/PDFs"

# Variable to store all log messages
LOG_MESSAGES=""

# Function to log messages
log_message() {
    local MESSAGE="[$(date)] $1"
    echo "${MESSAGE}" | tee -a ${LOGFILE}
    LOG_MESSAGES="${LOG_MESSAGES}\n${MESSAGE}"
}

# Function to handle errors
handle_error() {
    local ERROR_MESSAGE="Failed to $1"
    log_message "${ERROR_MESSAGE}"
    powershell.exe -File "${TOAST_PATH}" -message "Script Failure: ${ERROR_MESSAGE}. Log Messages: ${LOG_MESSAGES}"
    exit 1
}

mkdir -p "${LOG_DIR}"
mkdir -p "${PDF_DIR}/resources_${DATE}" || handle_error "create directory ${PDF_DIR}/resources_${DATE}"
mkdir -p "${ASSETS_DIR}/assets_${DATE}" || handle_error "create directory ${ASSETS_DIR}/assets_${DATE}"

log_message "Starting script"

cd "${DIR}" || handle_error "change directory to ${DIR}"

# Check if the file already exists and has content
if [ -s "${EPORTFOLIO_DIR}/${FILE}" ]
then
    log_message "File ${EPORTFOLIO_DIR}/${FILE} already exists and has content. Skipping file creation."
else
    touch "${EPORTFOLIO_DIR}/${FILE}" || handle_error "create file ${EPORTFOLIO_DIR}/${FILE}"
    echo "# ePortfolio - ${DATE}" > ${EPORTFOLIO_DIR}/${FILE} || handle_error "write to file ${EPORTFOLIO_DIR}/${FILE}"
fi

# Automation for Gitlab remote
git add . || handle_error "add files to git"
git commit -m "ePortfolio entry for ${DATE}" || handle_error "commit to git"
git push -u gitlab main || handle_error "push to gitlab"

log_message "Script ended"

# Send a PowerShell notification with the result
powershell.exe -File "${TOAST_PATH}" -message "Script Success: The script ran successfully. Log Messages: ${LOG_MESSAGES}"
