#!/bin/bash

echo -n "Enter word to search in cwd: "; read ${SEARCH_WORD}

LOG_FILE="./search_results.log"

> ${LOG_FILE}

find . -type f | while read -r file; do
	grep -Hn "${SEARCH_WORD}" "${file}" >> "${LOG_FILE}"
done

echo "Search completed. Results are logged in ${LOG_FILE}"

echo -n "Press enter for new search or Exit (q): "; read ${CHOICE}

case ${CHOICE} in
	
	q|Q)
		echo "Have a nice day!"
		clear
		exit 0
		;;
	*)
		echo "Starting new search..."
		clear
		exec "$0"
		;;

esac
