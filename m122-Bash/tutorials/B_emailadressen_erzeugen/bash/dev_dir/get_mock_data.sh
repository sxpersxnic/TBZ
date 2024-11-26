#!/usr/bin/bash

# STAGE 0

# VARIABLES
DEV_DIR='/mnt/c/Development/coding/Scripting/m122-Projects/B_emailadressen_erzeugen/dev_dir/data'

echo "Downloading MOCK_DATA.csv file..."
wget https://haraldmueller.ch/schueler/m122_projektunterlagen/b/MOCK_DATA.csv > ${DEV_DIR}/mock_data.csv
echo "Downloaded MOCK_DATA.CSV file"