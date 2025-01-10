#!/usr/bin/bash

#   <<-- PART 1 | alphaScript.sh -->>

# << VARIABLES >>

DEV_DIR='/mnt/c/Development/coding/Scripting/M122-Projects/A_verzeichnisse_und_dateien_anlegen/bash'
GITLAB_REPO='https://gitlab.com/harald.mueller/aktuelle.kurse/-/tree/master/m122/moegliche-LB2-AufgabenProjekte/A_verzeichnisse-und-dateien-anlegen?ref_type=heads'

# COLORS
RED='\033[38;2;255;0;0m'
BLUE='\033[38;2;0;0;255m'
GREEN='\033[38;2;0;200;0m'
YELLOW='\033[38;2;255;255;0m'
ORANGE='\033[38;2;255;100;0m'
PINK='\033[38;2;255;0;255m'
WHITE='\033[38;2;255;255;255m'
RESET='\033[0m'

# << SCRIPT >>

# < CREATE REQUIRED DIRECTORIES >

# CHANGE INTO THE DIRECTORY TO DEVELOPE IN
cd ${DEV_DIR}
echo -e "${ORANGE}Changed to directory:${RESET}"
echo -e "${YELLOW}${DEV_DIR}${RESET}"

# CREATE DIRECTORY IN WHICH WE WILL STORE OUT DATA FOR CLEANER STRUCTURE
mkdir -p dev_dir
cd dev_dir
echo -e "${ORANGE}Created and changed to directory:${RESET} ${YELLOW}${DEV_DIR}/dev_dir${RESET}"

# CREATE DIRECTORY "_templates" AND ADD FILES
mkdir -p _templates
echo -e "${ORANGE}Created directory:${RESET} ${YELLOW}_templates${RESET}"
touch _templates/datei-1.txt _templates/datei-2.docx _templates/datei-3.pdf
echo -e "${ORANGE}Added files:${RESET}" 
echo -e "${YELLOW}datei-1.txt${RESET}" 
echo -e "${YELLOW}datei-2.docx${RESET}"
echo -e "${YELLOW}datei-3.pdf${RESET}"

# CREATE DIRECTORY "_schulklassen"
mkdir -p _schulklassen
echo -e "${ORANGE}Created directory:${RESET} ${YELLOW}_schulklassen${RESET}"

# < FILL IN DATA >

# M122-AP22b.txt
echo "Amherd" > _schulklassen/M122-AP22b.txt
echo "Baume" >> _schulklassen/M122-AP22b.txt
echo "Keller" >> _schulklassen/M122-AP22b.txt
echo "Müller" >> _schulklassen/M122-AP22b.txt
echo "Meier" >> _schulklassen/M122-AP22b.txt
echo "Bachmann" >> _schulklassen/M122-AP22b.txt
echo "Schuhmacher" >> _schulklassen/M122-AP22b.txt
echo "Hirschi" >> _schulklassen/M122-AP22b.txt
echo "Brunner" >> _schulklassen/M122-AP22b.txt
echo "Anhof" >> _schulklassen/M122-AP22b.txt
echo "Dübendorf" >> _schulklassen/M122-AP22b.txt
echo "Kelcher" >> _schulklassen/M122-AP22b.txt

echo -e "${ORANGE}Filled in data in file:${RESET} ${YELLOW}M122-AP22b.txt${RESET}"

# M122-AP22c.txt
echo "Arslan" > _schulklassen/M122-AP22c.txt
echo "Buehler" >> _schulklassen/M122-AP22c.txt
echo "Camenisch" >> _schulklassen/M122-AP22c.txt
echo "Trulli" >> _schulklassen/M122-AP22c.txt
echo "Meier" >> _schulklassen/M122-AP22c.txt
echo "Hartmann" >> _schulklassen/M122-AP22c.txt
echo "Läupi" >> _schulklassen/M122-AP22c.txt
echo "Seiler" >> _schulklassen/M122-AP22c.txt
echo "Schirmer" >> _schulklassen/M122-AP22c.txt
echo "Eisenhauer" >> _schulklassen/M122-AP22c.txt
echo "Aebisch" >> _schulklassen/M122-AP22c.txt
echo "Schmid" >> _schulklassen/M122-AP22c.txt

echo -e "${ORANGE}Filled in data in file:${RESET} ${YELLOW}M122-AP22c.txt${RESET}"

# M122-AP22d.txt
echo "Dorn" >> _schulklassen/M122-AP22d.txt
echo "Egger" >> _schulklassen/M122-AP22d.txt
echo "Fuchs" >> _schulklassen/M122-AP22d.txt
echo "Gasser" >> _schulklassen/M122-AP22d.txt
echo "Huber" >> _schulklassen/M122-AP22d.txt
echo "Imhof" >> _schulklassen/M122-AP22d.txt
echo "Jaggi" >> _schulklassen/M122-AP22d.txt
echo "Kunz" >> _schulklassen/M122-AP22d.txt
echo "Lehmann" >> _schulklassen/M122-AP22d.txt
echo "Meyer" >> _schulklassen/M122-AP22d.txt
echo "Nussbaum" >> _schulklassen/M122-AP22d.txt
echo "Oberholzer" >> _schulklassen/M122-AP22d.txt

echo -e "${ORANGE}Filled in data in file:${RESET} ${YELLOW}M122-AP22d.txt${RESET}"

# OUTPUT OF FILESTRUCTURE
echo -e "${ORANGE}Datastructure created:${RESET}"
tree _templates _schulklassen

echo -e "${PINK}Process finished successful!${RESET}"