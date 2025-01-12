# KN10 31.01.2025 #

## Ausgangssituation ##

Ihre Firma hat in frühen Jahren eine eigene CRM-Software entwickelt
und nutzt die nach wie vor.
Da sie aber in die Jahre gekommen ist, überlegt sich Ihre Firma in die Cloud zu migrieren.
Sie sollen dabei die Abklärungen treffen und Kostenrechnung erstellen.
Mögliche Optionen sind die folgenden Modelle *Rehosting*, *Replatforming*, *Repurchasing*.

Bisher wurde die Applikation *On Premise* betrieben mit folgender Spezifikation:

|Service|CPU Cores|Speicher|RAM|OS|Anzahl|
|-------|---------|--------|---|-----|------|
|Web Server|1|20GB|2GB|Ubuntu|1|
|DB Server|2|100GB|4GB|Ubuntu|1|

- Backup-Speicher für DB Daten
    - Täglich für letzte 7 Tage
    - Wöchentlich für letzten Monat
    - Monatlich für letzte drei Monate
- Anzahl Benutzer: 30

## A) Kostenrechnung IAAS - Rehosting (60%) ##

### AWS ###

![Screenshots Kostenrechnung EC2](/m346-Cloud/Images/KN10/EC2.png)
![Screenshots Kostenrechnung RDS](/m346-Cloud/Images/KN10/RDS.png)
![Screenshots Kostenrechnung Total](/m346-Cloud/Images/KN10/AWS-TOTAL.png)

#### Erläuterung Komponenten Auswahl ###

|Component|Reasoning|
|---------|---------|
|VPC|Virtual Private Cloud for creating a Subnet, so the database won't need a public IPv4 Address|
|Private Subnet||
|EC2: Web server|EC2 Instance replacing the Web Server|
|RDS: Database|RDS Database replacing the Database Server|
|Lambda: Backup|Lambda function for backing up the database|

#### Abweichungen zur *On Premise* Infrastruktur ####

For the replacemenet of the database server we could have used a second EC2 instance,
 but since AWS provides the RDS service we save us the maintainance of the instance.
Additionally we use a AWS Lambda function for backing up the database.

#### Begründung der Auswahl ####

- VPC: Used to create Subnets
- EC2: Web Server will run on EC2 Instance
- RDS: Database runs on RDS service.
- Lambda: Two Lambda functions used.
One for backing up the database and one for cleaning up instances.

### Azure ###

![Screenshots Kostenrechnung CPU](/m346-Cloud/Images/KN10/CPU.png)
![Screenshots Kostenrechnung RAM](/m346-Cloud/Images/KN10/RAM.png)
![Screenshots Kostenrechnung Disk](/m346-Cloud/Images/KN10/DISK.png)

#### Erläuterung Komponenten Auswahl ###

#### Abweichungen zur *On Premise* Infrastruktur ####

#### Begründung der Auswahl ####

## B) Kostenrechnung PAAS - Replatforming ##

## C) Kostenrechnung SAAS - Repurchasing ##

## D) Interpretation der Resultate ##
