# Azure #

## Konfiguration ##

1. **Webserver:**
    - 1 Core
    - 2 GB RAM
    - 20 GB Speicher
    - Ubuntu
    - **B1ms**
    - **16 GB Standard SSD**

2. **Datenbankserver:**
    - 2 Cores
    - 4 GB RAM
    - 100 GB Speicher
    - Ubuntu
    - **B2ms**
    - **128 GB Standard SSD**

3. **Backup-Speicher:**
    - **Azure Blob Storage**
    - **Cool Tier Speicherklasse**
    - **1.4 TB**

## Rechnung ##

**Region:** East US

![Gesamte Kostenschätzung](/m346-Cloud/Images/KN10/AZURE.png)

## Erklärung der Auswahl ##

Da es nicht möglich ist genau 20 GB oder 100 GB SSD Speicher auszuwählen,
entschied ich mich beim Webserver für bloss *16 GB*,
dafür beim Datenbankserver für *128 GB*. Der Grund dafür ist,
dass man die Speichernutzung des Webservers optimieren kann und
auch mit *16 GB*, wird es die Performance nicht beeinträchtigen.
Beim Datenbankserver jedoch, ist es wichtig, dass der Speicher ausreicht.
Reicht der Datenbankspeicher nicht, gehen womöglich wichtige Daten verloren.
