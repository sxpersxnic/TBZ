# ePortfolio Melvin Kampus - 24-05-2024

# Netzwerkadressierung aufgaben

1. Beantworten sie folgende Fragen zum Thema **Hostnamen** :

    - **a.** Gibt es Einschränkungen beim Hostnamen wie z.B. max. Anzahl Zeichen,
       Sonderzeichen, Gross-Kleinschreibung etc.

    - **b.** Wo kann man bei Microsoft WINDOWS 10/11 den Hostnamen eintragen?

    **ANTWORTEN:**

    - **a.**
        Ja, es gibt Einschränkungen für Hostnamen. Sie dürfen nicht mehr als 255 Zeichen enthalten und können nur *alphanumerische* Zeichen (A-Z, a-z, 0-9), Bindestriche (-) und Punkte (.) enthalten. Sie sind nicht case-sensitiv.

    - **b.**
        In Windows 10/11 kann man den Hostnamen ändern, indem man zu Systemeigenschaften -> Computername -> Ändern navigiert.

2. Beantworten sie folgende Fragen zum Thema **MAC-Adresse** :

    - **a.** Für was steht die Abkürzung MAC?

    - **b.** Wo bzw. wie kann man bei Microsoft WINDOWS 10/11 die MAC-Adresse
       abfragen?

    - **c.** Wie lautet die MAC-Adresse ihres Notebooks? Schreiben sie diese in binärer und dezimaler Schreibweise auf.

    **Antworten**

    - **a.**
        Die Abkürzung MAC steht für **M**edia**A**ccess**C**ontrol. Jeder Computer hat eine eingravierte MAC adresse welche einzigartig ist.

    - **b.**
        In Windows 10/11 kann man die MAC-Adresse finden, indem man ein CLI (Powershell, CMD) öffnet und ```ipconfig/all``` eingibt. Die MAC-Adresse wird als physische Adresse aufgeführt.

    - **c.**
        Hexa: ```00-15-5D-99-FC-90```
        Dezimal: ```0-21-93-153-252-144```
        Binär: ```0000.0000-0001.0101-0101.1101-1001.1001-1111.1100-1001.0000```
        

3. Beantworten sie folgende Fragen zum Thema **IP-Adresse** :
    - **a.** Für was steht die Abkürzung IP?

    - **b.** Spricht man bei der IP-Adresse von einer logischen oder physischen
       Adresse?

    - **c.** Wo kann man bei Microsoft WINDOWS 10/11 die IP-Adresse eintragen?

    - **d.** Wie lautet die aktuelle IP-Adresse ihres Notebooks? Schreiben sie diese in binärer und dezimaler Schreibweise auf.
    
    **ANTWORTEN**

    - **a.** Die Abkürzung *IP* steht für **I**nternet **P**rotocol.
    - **b.** Die IP-Adresse ist eine logische Adresse.
    - **c.** In Windows 10/11 kann man die IP-Adresse einstellen, indem man zu Netzwerk- und Freigabecenter -> Adaptereinstellungen ändern -> Rechtsklick auf das Netzwerkinterface -> Eigenschaften -> Internetprotokoll Version 4 (TCP/IPv4) -> Eigenschaften navigiert.
    - **d.** Aktuelle IP-Adresse:
        Dezimal: 10.62.110.71
        Binär: 00001010.00111110.01101110.01000111


4. Erklären sie in ein paar Worten, was Subnetting für Vorteile hat.

    **ANTWORT**
    Subnetting ermöglicht die Unterteilung eines Netzwerks in kleinere, logisch separate Netzwerke (Subnetze). Subnetting bietet mehrere Vorteile, wie verbessertes Netzwerkmanagement, erhöhte Netzwerkleistung und verbesserte Sicherheit.

5. In den folgenden Fallbeispielen (Fall-1 bis Fall-3) sind die Bilder nicht ganz komplett.
    Entweder fehlt das Dezimaläquivalent oder die binäre Darstellung. Ergänzen sie bitte:

- Fall-1

    **IPV4-Adresse**
    - Dezimal: 10.35.3.112
    - Binär: 00001010.00100011.00000011.01110000

    **Subnetzmaske**
    - Dezimal: 255.0.0.0
    - Binär: 11111111.00000000.00000000.00000000
    
    - CIDR: /24
    (Die IP-Adresse ist im PC binär gespeichert. Zwecks einfacherer Handhabung nutzen wir die Dezimalschreibweise.
    Die Subnetzmaske kann in Dezimal- oder CIDR-Schreibweise notiert werden.)

    - Netz-ID: .......................................
    - Host-ID: .......................................
    - Anzahl IP’s im Subnetz: .......................................
    - Anzahl Hosts im Subnetz: .......................................
    - Netzwerkadresse: .......................................
    - Broadcastadresse: .......................................

- Fall-2

    **IPV4-Adresse**
    - Dezimal: 172.16.43.87
    - Binär: 10101100.00010000.00101011.01010111
    
    **Subnetzmaske**
    - Dezimal: 255.255.0.0
    - Binär: 11111111.11111111.00000000.00000000
    - CIDR: /16
    - Netz-ID: .......................................
    - Host-ID: .......................................
    - Anzahl IP’s im Subnetz: .......................................
    - Anzahl Hosts im Subnetz: .......................................
    - Netzwerkadresse: .......................................
    - Broadcastadresse: .......................................

- Fall-3

    **IPV4-Adresse**
    - Dezimal: 192.168.17.37
    - Binär: 11000000.10101000.00010001. 00100101
    
    **Subnetzmaske**
    - Dezimal: 255.255.255.0
    - Binär: 11111111.111111.11111111.00000000
    - CIDR: /8
    - Netz-ID: .......................................
    - Host-ID: .......................................
    - Anzahl IP’s im Subnetz: .......................................
    - Anzahl Hosts im Subnetz: .......................................
    - Netzwerkadresse: .......................................
    - Broadcastadresse: .......................................

6. Wo kann man bei Microsoft Windows 10/11 den Standardgateway (DefaultRouter)
    eintragen?

    **ANTWORT**

    In Microsoft Windows 10/11 kann man den Standardgateway (DefaultRouter) in den Netzwerkeinstellungen eingeben. Man geht dazu zu den Einstellungen -> Netzwerk & Internet -> Ethernet oder Wi-Fi -> Netzwerk- und Freigabecenter -> Adaptereinstellungen ändern -> Rechtsklick auf die Verbindung -> Eigenschaften -> Internetprotokoll Version 4 (TCP/IPv4) -> Eigenschaften.

7. Wichtige IPv4-Adressen:
- a. Wie lauten die drei für **LAN** s reservierte IP-Adressbereiche?

    **ANTWORT**

    Die drei für LANs reservierten IP-Adressbereiche sind: - 10.0.0.0 bis 10.255.255.255 - 172.16.0.0 bis 172.31.255.255 - 192.168.0.0 bis 192.168.255.255

- b. Wie lautet die Loopbackadresse ( Localhost ) und was bezweckt sie?

    **ANTWORT**

    Die Loopbackadresse (Localhost) ist 127.0.0.1. Sie wird verwendet, um Netzwerkverbindungen zu testen und auf den eigenen Computer zuzugreifen.

- c. Unter welchen Voraussetzungen werden sie eine APIPA -Adresse (Zero-Conf)
erhalten? In welchem Bereich liegt diese?
    
    **ANTWORT**

    Eine APIPA-Adresse (Zero-Conf) erhalten Sie, wenn DHCP nicht verfügbar ist und Ihr Computer keine statische IP-Adresse hat. Diese Adressen liegen im Bereich 169.254.0.1 bis 169.254.255.254.

8. Bestimmen sie von der folgenden IP-Adresse die Netz-ID und Host-ID:
    **192.168.3.37/24**

    **ANTWORT**
    - Netz-ID: 192.168.3.0
    - Host-ID: 0.0.0.37

9. Geben sie für die folgende IP-Adresse die Netzwerkadresse und Broadcastadresse
    an: **78.23.49.123 / 255.255.255.**

    **ANTWORT**
    - Netzwerkadresse: 78.23.49.0
    - Broadcastadresse: 78.23.49.255

10. Wie beurteilen sie diese Adresse: **78.256.125.12 / 255.255.248.**

    **ANTWORT**

    Die Adresse 78.256.125.12 / 255.255.248.0 ist ungültig, da der zweite Oktett größer als 255 ist. Die Adresse muss zwischen 0 und 255 liegen.

11. Können sie ihrem PC die Adresse **172.30.0.0** vergeben?

    **ANTWORT**

    Nein, Sie können Ihrem PC die Adresse 172.30.0.0 nicht zuweisen, da dies eine Netzwerkadresse ist.

12. Ihr PC hat folgende Netzwerkeinstellungen:
    **IP: 10.23.65.128 / 16**
    **Standardgateway: 10.24.0.1 / 16**
    Wie beurteilen sie diese Situation?

    **ANTWORT**

    In dieser Situation gibt es ein Problem. Das Standardgateway sollte sich im selben Subnetz wie der PC befinden. Da die IP-Adresse des PCs 10.23.65.128/16 ist, befindet sich der PC im Subnetz 10.23.0.0/16. Das Standardgateway 10.24.0.1 befindet sich jedoch im Subnetz 10.24.0.0/16. Daher kann der PC das Standardgateway nicht erreichen.

13. Welche der beiden Adressen ist aus dem privaten Adressbereich:
    **10.255.255.254** oder **172.15.123. 1**

    **ANTWORT**

    Die Adresse 10.255.255.254 ist aus dem privaten Adressbereich.

14. Wie beurteilen sie diese IP-Adresse: **169.254.0.1 / 16**

    **ANTWORT**

    Die IP-Adresse 169.254.0.1/16 ist eine APIPA-Adresse (Automatic Private IP Addressing). Sie wird verwendet, wenn ein DHCP-Server nicht verfügbar ist und der Computer keine statische IP-Adresse hat.

15. Wann verwenden sie diese IP-Adresse: **127.0.0.**

    **ANTWORT**

    Die IP-Adresse 127.0.0.1 wird verwendet, um Netzwerkverbindungen zu testen und auf den eigenen Computer zuzugreifen. Sie wird auch als Loopback-Adresse bezeichnet.

16. Die beiden folgenden PCs sind über einen Switch verbunden. Die IP-Adressen
    lauten: **172.16.3.48/24** und **172.16.4.126/**
    Können sich die beiden PCs gegenseitig anpingen?

    **ANTWORT**

    Die beiden PCs können sich nicht gegenseitig anpingen, da sie sich in verschiedenen Subnetzen befinden. Der erste PC befindet sich im Subnetz 172.16.3.0/24 und der zweite PC im Subnetz 172.16.4.0/24.

17. Wie beurteilen sie diese Adresse: **172.22.17.201** mit Subnetzmaske **255.0.0.**

    **ANTWORT**

    Die Adresse 172.22.17.201 mit der Subnetzmaske 255.0.0.0 ist gültig. Sie befindet sich im Subnetz 172.0.0.0/8.

18. Sowohl Switchs wie auch Router müssen die **eintreffenden Datenpakete**
    **analysieren**. Welche Angabe benötigt der Switch und welche der Router?

    **ANTWORT**

    Ein Switch benötigt die MAC-Adresse, um Datenpakete zu analysieren und weiterzuleiten. Ein Router benötigt die IP-Adresse, um Datenpakete zu analysieren und weiterzuleiten.

19. Ihr Hackerfreund prahlt damit, dass es ihm gelungen sei, die **MAC-Adresse** seiner
    Netzwerkkarte auf die ihrige zu ändern und damit ihre Node-Locked-License
    geschützte Applikation mitzuverwenden. Beide PCs befinden sich zurzeit im selben
    Subnetz. Was ist das Problem dabei? Wie sähe es aus, wenn sich ihr Freund in
    einem anderen Subnetz befände?

    **ANTWORT**

    Wenn mein Freund die MAC-Adresse seiner Netzwerkkarte auf meine ändert, kann dies zu IP-Konflikten führen, da beide PCs die gleiche MAC-Adresse haben. Wenn sich mein Freund in einem anderen Subnetz befindet, sollte es kein Problem geben, da die MAC-Adressen innerhalb eines Subnetzes eindeutig sein müssen.

20. Was versteht man beim **Hub** unter einer **Kollisionsdomäne**?

    **ANTWORT**

    Eine Kollisionsdomäne ist ein Netzwerksegment, in dem Datenkollisionen auftreten können. Bei einem Hub gehört das gesamte Netzwerk zu einer einzigen Kollisionsdomäne.

21. Was versteht man beim **Switch** unter einer **Kollisionsdomäne**?

    **ANTWORT**

    Bei einem Switch ist jede Port eine eigene Kollisionsdomäne. Das bedeutet, dass Kollisionen nur zwischen zwei Geräten auftreten können, die an denselben Switch-Port angeschlossen sind.

22. Ab welchem ISO-OSI-Layer ist ein **Switch protokolltransparent**?

    **ANTWORT**

    Ein Switch ist ab dem Data-Link-Layer (Layer 2) des ISO-OSI-Modells protokolltransparent.

23. Welche Angaben enthält die **SAT-Tabelle** eines Switchs?

    **ANTWORT**

    Die SAT-Tabelle (Source Address Table) eines Switches enthält Informationen über die MAC-Adressen der angeschlossenen Geräte und die zugehörigen Ports.

24. Nach dem Auspacken und Verkabeln eines Switches muss dieser zuerst einmal
    „angelernt“ werden, weil er noch nicht weiss, an welchem Ethernetport welcher PC
    angeschlossen ist. Wie verhält sich der Switch während dieser **Anlernzeit**? Machen
    sie eine Aussage zur Kollisionsdomäne.

    **ANTWORT**

    Während der Anlernzeit sendet der Switch alle eingehenden Datenpakete an alle Ports (außer dem Port, an dem das Paket eingegangen ist). Dies ermöglicht es dem Switch, die MAC-Adressen der angeschlossenen Geräte zu lernen und in der SAT-Tabelle zu speichern.

25. Switch sind bekanntlich effizienter als Hub, weil sie die Kommunikationspartner direkt
    miteinander verbinden. Können sie sich einen Anwendungsfall vorstellen, wo es
    trotzdem zu **Datenstau** kommen kann? Stichwort Bottle-Neck/Flaschenhals, Uplink.

    **ANTWORT**

    Ein Datenstau kann auftreten, wenn mehrere Geräte gleichzeitig Daten über den Uplink-Port senden wollen. Der Uplink-Port kann ein Flaschenhals sein, wenn seine Bandbreite nicht ausreicht, um den gesamten Datenverkehr zu bewältigen.

26. **Netzwerkadressierung – Praxisteil mit CISCO-Pakettracer:**
    Erstellen sie in CISCO Packet-Tracer gemäss folgenden Vorgaben je ein Netzwerk
    und untersuchen sie dieses. Vor allem interessieren uns die tatsächlichen IP-
    Adressen und die Erreichbarkeit der verschiedenen Geräte. Wichtige Befehle sind
    dabei **ping** und **ipconfig**.
    Verwenden sie je nach Situation die folgenden Cisco-Komponenten:
       - PC (Normaler Desktop-PC)
       - Switch: 2960
       - Einfacher Router: 4331
       - Router für Standortverbindung: PT-Router
       - DHCP-Server: Server mit DHCP-Dienst
    Die Wahl von geeigneten IP-Adressen aus dem privaten IP-Bereich ist ihnen
    überlassen und gehört zum Auftrag. Erstellen sie zuerst immer ein logisches Topo
    mit allen Namens - und Adressangaben. ( IPERKA )
    Tipp: Überprüfen sie jeweils, ob das Interface des Netzwerkgeräts (Netzwerkschnittstelle) aktiv ist.
    Haben sie auch an die Standardgateway-Einstellungen (Default-Router) gedacht?
    
    **ANTWORTEN**
- a. Zwei PC’s an Switch-1 angeschlossen, zwei weitere an Switch-2. Switch-
und Switch-2 sind über ein Ethernetkabel miteinander verbunden. Statische
IP-Adressierung. Alle PC’s im selben Subnetz.


- b. Wie Aufgabe 1 mit dem Unterschied, dass die IP-Adressen dynamisch über
einen DHCP-Server zugewiesen werden. Der DHCP-Server ist an Switch-
angeschlossen.


- c. Wie Aufgabe 2. Allerdings kann der DHCP-Server nicht erreicht werden, da er
z.B. offline, ausgeschaltet oder defekt ist.


- d. Zwei PC’s in Subnetz-1 an Switch-1 angeschlossen, zwei weitere PC’s in
Subnetz-2 an Switch-2. Switch-1 und Switch-2 sind über ein Ethernetkabel
miteinander verbunden. Statische IP-Adressierung.


- e. Wie Aufgabe 4 mit dem Unterschied, dass die beiden Switch’s nicht direkt,
sondern über einen Router verbunden sind. Statische IP-Adressierung.


- f. Für die Lernturbos (Optional):
Zwei PC’s in Subnetz-A, zwei PC’s in Subnetz-B und zwei PC’s in Subnetz-C.
Subnetz-A mit Subnetz-B durch Router-X verbunden. Subnetz-C mit Router-Y
verbunden. Router-X und Router-Y mit serieller Leitung (Telefon) verbunden.
Dies ergibt Subnetz-D. Statische IP-Adressierung. Private IP-Adressen.
Dieses Layout entspricht einer sogenannten Standortverbindung über eine
Zweidraht-Standleitung mit z.B. einer Telefonleitung.
(Hinweis: Hier müssen die Routingtabellen der beiden Router ergänzt werden.
Der Dozent kann ihnen da sicher weiterhelfen.)