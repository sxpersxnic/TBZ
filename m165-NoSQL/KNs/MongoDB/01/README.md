# MongoDB 01

## A) Installation

- Cloud-Init File: [here](/m165-NoSQL/aws/MongoDB/cloud-init.yml)

- Screenshot von Compass: ![Screenshot von MongoDB Compass](/m165-NoSQL/x-resources/m/01/compass.png)

- Erklärung Connection String: 

- `sed` Command:

## B) Erste Schritte GUI

- Einzufügendes Dokument: ![JSON eines MongoDB Dokument](/m165-NoSQL/x-resources/m/01/json.png)

- Eingefügtes Dokument: ![Eingefügtes JSON Dokument in MongoDB Compass](/m165-NoSQL/x-resources/m/01/document.png)

- Export Datei: [here](./Kampus.Melvin.json)

- Erklärung Datentypen: Bei einem Datum besteht das Risiko, dass der Wert versehentlich als String gespeichert/intepretiert wird und so zu Fehler führen kann, wenn Applikationen sich darauf verlassen, dass der richtige Datentyp verwendet wird und z.B. kein Parsing vornehmen.

## C) Erste Schritte Shell

- MongoDB-Shell in Compass: ![MongoDB-Shell in Compass](/m165-NoSQL/x-resources/m/01/mongosh.png)

- MongoDB-Shell auf AWS Linux-Server: ![MongoDB-Shell auf AWS Linux-Server](/m165-NoSQL/x-resources/m/01/aws.png)

- Erklärung Befehle 1-5:
	1. `show dbs;`: Zeigt alle Datenbanken des Clusters an.
	2. `show databases;`: Zeigt alle Datenbanken des Clusters an.
	3. `use Kampus;`: Verwendet die Datenbank 'Kampus'.
	4. `show collections;`: Zeigt alle Collections der Datenbank 'Kampus'.
	5. `show tables;`: Zeigt alle Tables der Datenbank 'Kampus'.

- Collections vs. Tables: