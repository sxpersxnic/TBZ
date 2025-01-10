![TBZ-Logo](https://gitlab.com/ch-tbz-it/Stud/m293g/m293/-/raw/main/x_gitressources/tbz_logo.png)

# Web-Publishing (T2)

TBZ Informatik Modul 293 - Webauftritt erstellen und veröffentlichen

## Lernziele

- Ich kann Methoden zum Übertragen von Dateien nennen.
- Ich kann erklären wie FTP funktioniert und die Unterschiede von SFTP und FTPS nennen.
- Ich kann ein geeignetes Tool zur Publikation einer Webseite nennen.
- Ich kann eine Webseite manuell oder automatisiert mit einfachen Command Line Tools publizieren.
- Ich kann die Philosophie von Continous Integration erklären.
- Ich kann ein Tool für Continous Integration von Web-Applikationen nennen.
- Ich kann die CI/CD Tools von Gitlab oder Github anwenden.



## Webhosting

Unter Webhosting versteht man die Bereitstellung von Speicherplatz auf einem Webserver, der es Ihnen erlaubt eine Webseite im Internet zu publizieren. Dies kann auf verschiedene Arten geschehen, z.B.

- Klassisches Shared Hosting: Sie teilen sich einen Server/Ressourcen mit anderen. Entsprechend sind die Preise tiefer. Diese Art Hosting eignet sich vor allem für kleine Seiten und Seiten, die nicht Performance-relevant sind.
- Dediziertes Hosting: Sie haben einen eigenen Sever und entsprechend die gesamte Ressource zur Verfügung
- Cloud Hosting - App Hosting: Sie haben ihre Applikation in der Cloud. Die Cloudumgebung verwaltet die Ressourcen
- Cloud Hosting - Server Hosting: Sie haben einen eigenen virtuellen Server in der Cloud. Die Cloudumgebung verwaltet die Ressourcen.

Tatsächlich gibt es inzwischen wenige technologische Unterschiede zwischen den Lösungen. Auch die dedizierten Hostings und klassischen Shared Hostings haben im Hintergrund verteilte Server und Ressource (z.B. mit VMWare).

Für dieses Modul wichtig ist, dass sie im Interet einen Ort benötigen auf dem sie ihre Webseite publizieren können, so dass andere ihre Webseite besuchen können. Ein allgemeines Modell sieht wie folgt aus.

![hosting1](https://gitlab.com/ch-tbz-it/Stud/m293g/m293/-/raw/main/T2_Publishing/x_gitressources/Hosting_1.png)

## File Transfer Protocol (FTP)

FTP ist ein bereits älteres Protokoll zum Übertragen von Dateien über ein Netzwerk. Ähnlich wie HTTP handelt es sich um den Application Layer der Netzwerkschichten und baut auf TCP/IP. FTP war lange Zeit der Standart zur Übertragung von Dateien und für einfache Webseiten bei "Shared Hosting Dienstleistern" ist es oft noch das einzige Mittel zur Publikation von Webseiten. Es handelt sich hier um eine typsiche Client-Server-Architektur (wie auch HTTP). FTP implementiert **keine Verschlüsselung** und ist entsprechend unsicher!

Ein einfaches FTP-Tool bieten alle Windows- Linux, Mac und Unix-Systeme schon seit langem als integrierten Zusatz an. Zur einfacheren Automatisierung, können ganze Befehlssequenzen in einer (Skript-)Datei zusammengefasst werden. Mit der -s Option können dann alle Befehle auf ein Mal dem FTP-Programm übergeben werden.  

Um Dateien zu übertragen, werden die folgenden Angaben benötigt:

| Name | Beispiel | Beschreibung |
| --------- | ------------- | -------------------------------- |
| server | ftp.example.org | Server auf den sie die Dateien übertragen möchten. Oft ist der server name der gleiche wie die publizierte webseite, z.B. *www.example.org* |
| port | 21 | Der typische Port für FTP ist 21, er kann aber auf einen beliebigen Port geändert werden. Der Server, Client und die Firewall muss einfach entsprechend eingerichtet werden. |
| username | admin | Der Benutzername, um sich einzuloggen. |
| password | xxxxx | Das Passwort, um sich einzuloggen. |
| path | /www | Oft müssen sie auf dem Server in ein Unterverzeichnis navigieren, welches als Webroot-Verzeichnis dient. Es kann aber auch sein, dass der Pfad "/" ist. Dies bedeutet, dass ihre Webseite direkt im Hauptverzeichnis ihres FTP-Accounts liegt. |

Hier ist eine Anleitung für die beiden Dateien (run.bat und scriptdatei.ftp)
- <https://www.lautenbacher.io/allgemein/ftp-skripte-in-verbindung-mit-batch-skripten-unter-windows>
- <https://www.axel-hahn.de/batch/batchecke/beispiel-ftp>

![hosting1](https://gitlab.com/ch-tbz-it/Stud/m293g/m293/-/raw/main/T2_Publishing/x_gitressources/Hosting_2.png)

### FTP über SSL (FTPS)

Damit man Dateien mit FTP sicher übertragen kann, wurde das Protokoll gesichert mit SSL/TLS als Übertragungskanal und dadurch sind die Inhalte verschlüsselt.

### implicit vs explicit FTPS

*Implicit FTPS* ist die ältere Methode und verwendet einen zusätzlichen Port für den SSL-Verbindungsaufbau.

*Explicit FTPS* ist die neuere und auch verbreitete Methode und verwendet nur den genannten Port 21. Die meisten Server unterstützen nur noch *explicit FTPS*. Leider wird auch die Fallback-Variante auf das unsichere FTP oft noch unterstützt.

Weitere Details können sie gerne [hier nachlesen](https://www.ftptoday.com/blog/explicit-ftps-vs-implicit-ftps-what-you-need-to-know).



## Secure File Transfer Protocol (SFTP)

SFTP verwendet nicht FTP, sondern SSH (Secure Shell) als Protokoll, welches Verschlüsselung unterstützt. SSH wurde ursprünglich als Alternative zu telnet und rlogin entwickelt.

Die Angaben sind ähnlich zu FTP, mit dem Unterschied, dass SFTP den Port 22 verwendet. Mit SSH ist es zusätzlich möglich eine Key-Datei mit Private-Key zu verwenden, so dass kein Benutzername und Passwort verwendet wird. Dies natürlich nur, wenn der Server diese Art von Verbindung zulässt.

Quellen:

- <https://www.ssh.com/academy/ssh/protocol>
- <https://www.ssh.com/academy/ssh/sftp>
- <https://www.goanywhere.com/blog/2011/10/20/sftp-ftps-secure-ftp-transfers>



## Tools für Upload und Automatisierung

Die verschiedenen Betriebssysteme bieten alle FTP Tools in ihrer Umgebung an (CMD, Bash, etc). Windows FTP.exe auch das built-in Tool von MacOs unterstützen aber kein SSL/TLS. Zusätzliche Applikationen sind notwendig.

Am Besten werden zusätzliche Applikationen wie [FileZilla](https://filezilla-project.org/) oder [WinSCP](https://winscp.net/eng/index.php) oder [Cyberduck](https://cyberduck.io/) installiert.

**FileZilla** kann für jede Plattform heruntergeladen werden und ist das verbreitetste Programm für SFTP/FTPS. Leider unterstützt es (in der Gratis-Version) keine Command Line Tools.

**WinSCP** unterstützt [Command Line Tools](https://winscp.net/eng/docs/scripting) und kann daher für eine Automatisierung verwendet werden. WinSCP wird nur **für Windows** angeboten.

**Cyberduck** ist eine Alternative zu WinSCP, welche auch unter MacOS unterstützt wird und [Command Line Tools](https://docs.duck.sh/cli/) anbietet.



## Continous Integration / Continous Deployment (CI / CD)

Mit CI/CD bezeichnet man Prozesse, die sicherstellen, dass automatisch und möglicherweise regelmässig Code (oft Webapplikationen) publiziert wird.

Stellen sie sich das folgende Szenario vor. Sie arbeiten zusammen mit einem Team an einer Web Applikation. Sie möchten jeweils nachdem sie Änderung an der Applikation bestätigt und auf Gitlab/Github hochgeladen haben, dass die Applikation automatische neu publiziert wird. Sie möchten vermeiden, dass sie die Publikation jeweils manuell anstossen müssen und werden versuchen dies zu automatisieren.

Folgendes Bild zeigt einen möglichen Continous Integration Prozess. Andere Szenarien sind möglich, z.B. dass jeweils über Nacht eine neue Version publiziert wird.

![ci](x_gitressources/CI-examples.png).

CI/CD Prozesse enthalten aber nicht nur Publikationsprozesse, sondern auch weitere Automatisierungen wie automatisierte Testausführung (z.B. Unit Tests, Integration Tests, System Tests).

Umfangreiche CI/CD Prozess werden kaum lokal durch den Entwickler ausgelöst, sondern über eine Software, welche auf einem Server installiert ist. Beispiel für solche Tools sind:

- Gitlab Github: Beide Plattformen haben CI/CD Tools.
- Jenkins: Open Source und wohl eines der verbreitetsten Tools überhaupt.
- Teamcity: von JetBrains und unterstützt ebenfalls eine grosse Bandbreite von Technologien.
- Bamboo: Von Atlassian und ist zusammen mit JIRA entsprechend gewachsen.
- Azure DevOps Server: Speziell für die Azure Cloud Umgebung.   



## Checkpoints

- [ ] Ich kann Methoden zum Übertragen von Dateien nennen.
- [ ] Ich kann erklären wie FTP funktioniert und die Unterschiede von SFTP und FTPS nennen.
- [ ] Ich kann ein geeignetes Tool zur Publikation einer Webseite nennen.
- [ ] Ich kann eine Webseite manuell oder automatisiert mit einfachen Command Line Tools publizieren.
- [ ] Ich kann die Philosophie von Continous Integration erklären.
- [ ] Ich kann ein Tool für Continous Integration von Web-Applikationen nennen.
- [ ] Ich kann die CI/CD Tools von Gitlab oder Github anwenden.