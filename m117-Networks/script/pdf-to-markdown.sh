#!/bin/bash

# Check if a filename is provided
if [ -z "$1" ]
then
    echo "No PDF file provided. Please provide a PDF file to convert."
    exit 1
fi

# Check if the file exists
if [ ! -f "$1" ]
then
    echo "File not found!"
    exit 1
fi

# Convert the PDF to text
pdftotext "$1" - | sed 's/^/    /' > output.md
