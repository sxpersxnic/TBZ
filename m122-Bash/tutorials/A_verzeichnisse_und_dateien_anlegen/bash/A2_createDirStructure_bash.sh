#!/bin/bash

#   <<-- PART 2 | betaScript.sh -->>

# << VARIABLES >>
DEV_DIR='/mnt/c/Development/coding/Scripting/M122-Projects/A_verzeichnisse_und_dateien_anlegen/bash/dev_dir'
GITLAB_REPO='https://gitlab.com/harald.mueller/aktuelle.kurse/-/tree/master/m122/moegliche-LB2-AufgabenProjekte/A_verzeichnisse-und-dateien-anlegen?ref_type=heads'

# << SCRIPT >>

# RUN A1_createDirStructure_bash TO MAKE SURE PART 1 IS EXECUTED BEFORE PART 2
sudo chmod +x ./A1_createDirStructure_bash.sh
source ./A1_createDirStructure_bash.sh

# CHANGE TO DIRECTORY "_schulklassen"
cd ./_schulklassen

# READ AND PROCESS ALL CLASS FILES
echo -n "Processing data...";

for class_file in ./*.txt; do
    class_name=$(basename "$class_file" .txt)  # EXTRACT CLASSNAMES
    mkdir -p "./gen/$class_name"  # CREATING OF CLASS DIRECTORY

    # READ STUDENTNAMES FROM CLASS FILE AND PROCESS
    while IFS= read -r student_name; do
        mkdir -p "./gen/$class_name/$student_name"  # CREATE DIRECTORY FOR STUDENT
        cp -r ../_templates/* "./gen/$class_name/$student_name"  # COPY TEMPLATE TO STUDENT DIRECTORY
    done < "$class_file"
done

# OUTPUT DATA STRUCUTRE
echo ""
echo "Datastructure created: "
tree gen
echo "Process executed successfully!"