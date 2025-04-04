#!/usr/bin/bash

# STAGE 2

# VARIABLES
DEV_DIR='/mnt/c/Development/coding/Scripting/m122-Projects/B_emailadressen_erzeugen/bash/dev_dir/data'

get_salutation() {
    local gender="$1"
    if [ "${gender}" == "Male" ]; then
        echo "Lieber"
    elif [ "${gender}" == "Female" ]; then
        echo "Liebe"
    else 
        echo "Liebe*r"
    fi
}
while IFS=';' read -r email password
do
    # Extract firstname and lastname from the email address
    firstname=$(echo "${email}" | cut -d'.' -f1)
    lastname=$(echo "${email}" | cut -d'.' -f2 | cut -d'@' -f1)

    salutation=$(get_salutation "${gender}")

    # Generate letter content
    cat << EOF > "${firstname}.${lastname}.brf"
Technische Berufsschule Zürich
Ausstellungsstrasse 70
8005 Zürich


streetNum,zipCode

Zürich, den $(date +%d.%m.%Y)
                        ${firstname} ${lastname}
                        [street] [place]

${salutation} ${firstname},

Es freut uns, Sie im neuen Schuljahr begrüssen zu dürfen.

Damit Sie am ersten Tag sich in unsere Systeme einloggen
können, erhalten Sie hier Ihre neue Emailadresse und Ihr
Initialpasswort, das Sie beim ersten Login wechseln müssen.

Emailadresse:   ${email}
Passwort:       ${password}

Mit freundlichen Grüßen

<name>
(TBZ-IT-Service)

admin.it@tbz.ch, Abt. IT: +41 44 446 96 60
EOF
done < ${DEV_DIR}/YYYY-MM-DD_HH-SS_mailimports.csv