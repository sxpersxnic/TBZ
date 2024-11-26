#!/usr/bin/bash

# STAGE 4

# VARIABLES
DEV_DIR='/mnt/c/Development/coding/Scripting/PROJECT-SCRIPTS/B_emailadressen_erzeugen/dev_dir'

# EXECUTE SCRIPTS
source ${DEV_DIR}/get_mock_data.sh
source ${DEV_DIR}/generate_emails_passwords.sh
source ${DEV_DIR}/paper_letter.sh
source ${DEV_DIR}/archive.sh

echo "Please see the attachment for new TBZ email adresses." | mail -s "New TBZ Email Addresses" -A ${DEV_DIR}/backup/YYYY-MM-DD_newMailadr_AP23_Name.tar.gz recipient@example.com

