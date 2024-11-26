#!/usr/bin/env bash

# Path to the script to be tested
SCRIPT_TO_TEST="/mnt/c/Users/melvi/OneDrive - TBZ/2024_2024_Y1/2023_2024_Y1_S1/M117/script/ePortfolioProcess.sh"
LOG_DIR="/mnt/c/Users/melvi/OneDrive - TBZ/2024_2024_Y1/2023_2024_Y1_S1/M117/log"
LOGFILE="${LOG_DIR}/test_log_$(date +%d-%m-%Y).log"
TOAST_PATH="C:\Users\melvi\OneDrive - TBZ\2024_2024_Y1\2023_2024_Y1_S1\M117\script\toast.ps1"

# Create log directory if it doesn't exist
mkdir -p "${LOG_DIR}"

exec 3>&1 1>>${LOGFILE} 2>&1

# Run the script and capture the exit status
${SCRIPT_TO_TEST}
EXIT_STATUS=$?

# Check the exit status and send a message to all logged in users with the result
if [ ${EXIT_STATUS} -eq 0 ]
then
    echo "[$(date)] Test was successful" >> ${LOGFILE}
    echo "Test Success: The test was successful." | wall
    powershell.exe -File "${TOAST_PATH}" -message "Test Success: The test was successful."
else
    echo "[$(date)] Test failed" >> ${LOGFILE}
    echo "Test Failure: The test failed." | wall
    powershell.exe -File "${TOAST_PATH}" -message "Test Failure: The test failed."
fi
