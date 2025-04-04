#!/usr/bin/bash

DEV_DIR='/mnt/c/Development/coding/Scripting/PROJECT-SCRIPTS/B_emailadressen_erzeugen/dev_dir'
GITLAB='https://gitlab.com/harald.mueller/aktuelle.kurse/-/tree/master/m122/moegliche-LB2-AufgabenProjekte/B_emailadressen-erzeugen?ref_type=heads'
# MAKE SCRIPTS EXECUTABLE

# STAGE 0
sudo chmod +x ${DEV_DIR}/get_mock_data.sh
# STAGE 1
sudo chmod +x ${DEV_DIR}/generate_emails_passwords.sh
# STAGE 2
sudo chmod +x ${DEV_DIR}/paper_letter.sh
# STAGE 3
sudo chmod +x ${DEV_DIR}/archive.sh
# STAGE 4
sudo chmod +x ${DEV_DIR}/beta_script.sh
# STAGE 5
sudo chmod +x ${DEV_DIR}/transfer_archive.sh
#TODO Stage 6 CronJob
# STAGE 7
sudo chmod +x ${DEV_DIR}/duplicate_checker.sh

# STAGE 0
echo "Stage 0 will be executed in beta_script ->"
# STAGE 1
echo "Stage 1 will be executed in beta_script ->"
# STAGE 2
echo "Stage 2 will be executed in beta_script ->"
# STAGE 3
echo "Stage 3 will be executed in beta_script ->"
# STAGE 4
echo "Stage 4"
source ${DEV_DIR}/beta_script.sh
# STAGE 5
echo "Stage 5"
source ${DEV_DIR}/transfer_archive.sh
# STAGE 6
echo "Stage 6 CronJob automation"
# STAGE 7
echo "Stage 7"
source ${DEV_DIR}/duplicate_checker.sh
