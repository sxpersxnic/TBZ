#!/usr/bin/bash

# C_systemleistung_abfragen

# VARIABLES

# LINKS
GITLAB='https://gitlab.com/ch-tbz-it/Stud/m122/-/tree/main/10_Projekte_LB2/C_systemleistung-abfragen?ref_type=heads'
DIRECTORY='/mnt/c/Development/coding/Scripting/M122-Projects/C_systemleistung_abfragen/bash'

# SCRIPT

# REDIRECT ALL OUTPUT TO LOG FILE
exec >> ${DIRECTORY}/log/SYS_DATA.log

# 1.) Formatted content

# 1.1.) GET DATA
#   2.  Current IP-Adress                   (NET)
PUBLIC_IP_ADRESS=$(curl -s 'https://api.ipify.org?format=json' | jq -r '.ip' )
LAN_IP_ADRESS=$(hostname -I | awk '{print $1}')

#   1.  Hostname of system                  (SYS)
HOSTNAME=$(uname -n)
#   3.  Version of operating system         (SYS)
OS_VERSION=$(uname -r)
#   12. Current system uptime              (SYS)
UPTIME=$(uptime -p)
UPTIME_SINCE=$(uptime -s)
#   13. Current system time                 (SYS)
SYS_TIME=$(date)

#   4.  Modelname of CPU                    (CPU)
#   5.  Count of CPU-Cores                  (CPU)
# CPU_CORES=$(nproc)
CPU=$(cat /proc/cpuinfo | grep 'model name' | uniq | awk -v cores=$(nproc) -F: '{print "Model: " $2 " | Cores: " cores}')

#   6.  Total + used RAM                   (RAM)
RAM=$(free -h | grep Mem | awk '{print "Total: " $2 " | Used: " $3}')

#   7.  Size of available storage           (SSD)
#   8.  Size of free storage                (SSD)
SSD=$(df -h --total | grep C: | awk '{print "Available: " $2 " | Used: " $3 " / " $5 " | Free: " $4}')

#   9.  Complete size of filesystem         (SSD)
#   10. Size of used storage in filesystem  (SSD)
#   11. Size of free storage in filesystem  (SSD)
FILESYSTEM_STORAGE=$(df -h --total | grep total | awk '{print "Total: " $2 " | Used: " $3 " / " $5 " | Free: " $4}')

#  14. Seperator for next output           ( - )
COUT_FINISH="====================PROCESS-FINISHED-SUCCESSFULLY===================="

classify_ip() {
    ip=$1
    IFS='.' read -ra ADDR <<< "$ip"
    if [[ ${ADDR[0]} -ge 1 && ${ADDR[0]} -le 126 ]]; then
        echo "Class A"
    elif [[ ${ADDR[0]} -ge 128 && ${ADDR[0]} -le 191 ]]; then
        echo "Class B"
    elif [[ ${ADDR[0]} -ge 192 && ${ADDR[0]} -le 223 ]]; then
        echo "Class C"
    else
        echo "IP is not Class A, B or C"
    fi
}


# 1.2.) DISPLAY DATA
printf "\n-- SYSTEM --\n\nHostname: %s\n\nPublic IP Adress ($(classify_ip ${PUBLIC_IP_ADRESS})): %s\n\nLAN IP Adress ($(classify_ip ${LAN_IP_ADRESS})): %s\n\nOS version: %s\n\n-- COMPONENTS --\n\nCPU:\n%s\n\nRAM:\n%s\n\nSSD:\n%s\n\nFilesystem (total storage):\n%s\n\n-- TIME --\n\nSystem time: %s\nUptime:  %s\nRunning since: %s\n\n%s\n\n\n" "$HOSTNAME" "$PUBLIC_IP_ADRESS" "$LAN_IP_ADRESS" "$OS_VERSION" "$CPU" "$RAM" "$SSD" "$FILESYSTEM_STORAGE" "$SYS_TIME" "$UPTIME" "$UPTIME_SINCE" "$COUT_FINISH"
