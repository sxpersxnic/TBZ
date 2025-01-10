# KN07 20.12.2024 #

## A) Datenbank im PAAS Modell ##

![Screenshot MySQL Workbench mit ausgeführtem Query](/m346-Cloud/Images/KN07/MYSQL-QUERY.png)

### Vorteile PAAS/SAAS ###

Eine Datenbank lokal zu installieren ist sinnvoll für die Entwicklungsumgebung.
In der Produktion, ist es jedoch nicht gerade nützlich,
da das Lokale Geräte weniger Leistung als ein Server hat
und das Geräte ständig laufen muss.
Mit einem PAAS/SAAS Service,
läuft die Datenbank in der Cloud und ist somit von überall erreichbar.
Ein weiterer Vorteil ist die Netzwerkstruktur einer Applikation.
Hat man z.B. einen Webserver
und nur dieser Server soll Zugriff auf die Datenbank haben,
kann man die beiden Server ganz einfach in ein eigenes Netz einteilen.
Nun ist die Datenbank nur über die private Addresse erreichbar.
Dies ist viel sicherer,
da nun keine Queries direkt gegen die Datenbank ausgeführt werden können.

## B) PAAS Applikationen erstellen ##

![Screenshot für die veränderten Bereiche](/m346-Cloud/Images/KN07/CONFIG-1.png)
![Screenshot für die veränderten Bereiche](/m346-Cloud/Images/KN07/CONFIG-2.png)
![Screenshot für die veränderten Bereiche](/m346-Cloud/Images/KN07/CONFIG-3.png)
![Screenshot für die veränderten Bereiche](/m346-Cloud/Images/KN07/CONFIG-4.png)
![Screenshot für die veränderten Bereiche](/m346-Cloud/Images/KN07/CONFIG-5.png)
![Screenshot für die veränderten Bereiche](/m346-Cloud/Images/KN07/CONFIG-6.png)

### Erläuterung Auswahlen ###

**Platform: Go, why?**
Go is a cloud-native language, developed for cloud-computing. Thats why i chose Golang.
Additionally it's fast, efficient and compared to e.g. C, it has no risk memory loss.

**Monitoring interval: 1 Min, why?**
If a problem occurs its easier to find the origin of the problem,
when metrics are reported frequently.

**Environment type: Load balanced, why?**
To auto-scale the application for best performance.

**Instances: Min 1 Max 2, why?**
Since the educational Budget isnt very high,
i chose to waste as few resources as possible.

**Database: mysql, why?**
Since the only options to choose from were either not 
enabling database or creating a new one,
i rather chose to create a new one than not having a database.
I couldn't figure out how i can connect my already existing RDS MariaDB Database.

## C) Erstellte Ressourcen/Objekte und CloudFormation ##

![Beanstalk Application](/m346-Cloud/Images/KN07/BEANS.png)

### CloudFormation vs. Cloud-Init ###

While cloud-init works as a script, CloudFormation uses predefined templates stored in your account. Cloud-init defined what inside an instance must be configured, CloudFormation Templates define Hardware, Network and scaling configurations.

![Screenshot der automatisch erstellten EC2-Objekte](/m346-Cloud/Images/KN07/EC2.png)

![Screenshot der CloudFormation Ressourcen für PAAS Anwendung](/m346-Cloud/Images/KN07/CLOUDFORMATION.png)
