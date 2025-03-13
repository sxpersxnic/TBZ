# AWS #

## Konfiguration ##

1. **Webserver:**
    - 1 Core
    - 2 GB RAM
    - 20 GB Speicher
    - Ubuntu
    - **t3.small**
    - **20 GB EBS General Purpose SSD (gp3)**

2. **Datenbankserver:**
    - 2 Cores
    - 4 GB RAM
    - 100 GB Speicher
    - Ubuntu
    - **t3.medium**
    - **100 GB EBS General Purpose SSD (gp3)**

3. **Backup-Speicher:**
    - **Amazon S3**
    - **Schätzung des Speicherbedarfs:**
      - 7 tägliche Backups: 7 x 100 GB = *700 GB*
      - 4 wöchentliche Backups: 4 x 100 GB = *400 GB*
      - 3 monatliche Backups: 3 x 100 GB = *300 GB*
      - **Gesamtspeicherbedarf:** *700 GB* + *400 GB* + *300 GB* = *1.4 TB*

## Rechnung ##

**Region:** US East (N. Virginia)

![Kostenschätzung des Webservers](/m346-Cloud/Images/KN10/AWS-WEBSERVER.png)
![Kostenschätzung des Datenbankservers](/m346-Cloud/Images/KN10/AWS-DATENBANKSERVER.png)
![Kostenschätzung des Backup-Speichers](/m346-Cloud/Images/KN10/AWS-BACKUP.png)
![Gesamte Kostenschätzung](/m346-Cloud/Images/KN10/AWS-TOTAL.png)

## Erklärung der Auswahl ##

Die Instanz Typen für den Webserver und den Datenbankserver,
stimmen von den Komponenten her überein.
Im Gesamten weicht die Infrastruktur nicht von der On Premise Lösung ab.
