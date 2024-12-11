# KN07 20.12.2024

## A) Datenbank im PAAS Modell

![Screenshot MySQL Workbench mit ausgeführtem Query](/m346-Cloud/Images/KN07/MYSQL.png)

### Vorteile PAAS/SAAS 

Eine Datenbank lokal zu installieren ist sinnvoll für die Entwicklungsumgebung. 
In der Produktion, ist es jedoch nicht gerade nützlich, da das Lokale Geräte weniger Leistung als ein Server hat und das Geräte ständig laufen muss.
Mit einem PAAS/SAAS Service, läuft die Datenbank in der Cloud und ist somit von überall erreichbar.
Ein weiterer Vorteil ist die Netzwerkstruktur einer Applikation. Hat man z.B. einen Webserver und nur dieser Server soll Zugriff auf die Datenbank haben, kann man die beiden Server ganz einfach in ein eigenes Netz einteilen. Nun ist die Datenbank nur über die private Addresse erreichbar. Dies ist viel sicherer, da nun keine Queries direkt gegen die Datenbank ausgeführt werden können.

## B) PAAS Applikationen erstellen

![Screenshot für die veränderten Bereiche](/m346-Cloud/Images/KN07/CONFIG.png)

### Erläuterung Auswahlen

## C) Erstellte Ressourcen/Objekte und CloudFormation

### CloudFormation vs. Cloud-Init

![Screenshot der automatisch erstellten EC2-Objekte](/m346-Cloud/Images/KN07/EC2-OBJECTS.png)

![Screenshot der CloudFormation Ressourcen für PAAS Anwendung](/m346-Cloud/Images/KN07/CLOUDFORMATION.png)
