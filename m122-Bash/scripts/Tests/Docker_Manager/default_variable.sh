#!/usr/bin/env bash

echo -n "Enter a value: "; read VAR

VAR=${VAR:-default}

echo "VAR is ${VAR}"
if [ ${VAR} != 'default' ]; then
    echo "Test successful!"
else 
    echo "Test not successful!"
fi

echo -n "Choose how to continue: Restart: 1 | Exit: 2: "; read CHOICE

while true; do
    case ${CHOICE} in
    1)
        exec "$0"
        ;;
    2)
        exit 0
        ;;
    esac
done