# MongoDB 01

## A. Installation

- Cloud-Init File: [cloud-init.yml](../../../.aws/MongoDB/cloud-init.yml)

- Screenshot von Compass: ![Screenshot von MongoDB Compass](../../../x-res/m/01/compass.png)

- Erklärung Connection String: Bei dem Parameter `authSource` handelt es sich um die Datenbank, in der die Benutzer gespeichert sind. Bei MongoDB ist dies standardmäßig die `admin`-Datenbank. Der Parameter `retryWrites` sorgt dafür, dass Schreiboperationen bei einem Fehler automatisch wiederholt werden. Der Parameter `w=majority` sorgt dafür, dass die Schreiboperationen erst dann als erfolgreich gelten, wenn sie auf der Mehrheit der Replikate durchgeführt wurden.

- `sed` Command:
  - `sed -i 's/#security:/security:\n authorization: enabled/g' /etc/mongod.conf` - Dieser Befehl fügt die Zeile `authorization: enabled` unter der Zeile `security:` in der Datei `mongod.conf` hinzu. Dies aktiviert die Authentifizierung für MongoDB.
  - `sed -i 's/127.0.0.1/0.0.0.0/g' /etc/mongod.conf` - Dieser Befehl ersetzt alle Vorkommen von `127.0.0.1` durch `0.0.0.0` in der Datei `mongod.conf`. Dies ermöglicht den Zugriff auf MongoDB von anderen IP-Adressen als nur `localhost`. Dies ist wichtig, wenn MongoDB auf einem Server ausgeführt wird, auf den man von einem anderen Computer aus zugreifen möchte.

- **mongod.conf Screenshot:**

	![Screenshot von MongoDB Shell](../../../x-res/m/01/mongod-conf.png)

## B. Erste Schritte GUI

- Einzufügendes Dokument: ![JSON eines MongoDB Dokument](../../../x-res/m/01/json.png)

- Eingefügtes Dokument: ![Eingefügtes JSON Dokument in MongoDB Compass](../../../x-res/m/01/document.png)

- Export Datei: [json](./Name.Prename.json)

- Erklärung Datentypen: Bei einem Datum besteht das Risiko, dass der Wert versehentlich als String gespeichert/intepretiert wird und so zu Fehler führen kann, wenn Applikationen sich darauf verlassen, dass der richtige Datentyp verwendet wird und z.B. kein Parsing vornehmen.

## C. Erste Schritte Shell

- MongoDB-Shell in Compass:

	![MongoDB-Shell in Compass](../../../x-res/m/01/mongosh.png)

- MongoDB-Shell auf AWS Linux-Server:

	![MongoDB-Shell auf AWS Linux-Server](../../../x-res/m/01/aws.png)

- Erklärung Befehle 1-5:
	1. `show dbs;`: Zeigt alle Datenbanken des Clusters an.
	2. `show databases;`: Zeigt alle Datenbanken des Clusters an.
	3. `use Kampus;`: Verwendet die Datenbank 'Kampus'.
	4. `show collections;`: Zeigt alle Collections der Datenbank 'Kampus'.
	5. `show tables;`: Zeigt alle Tables der Datenbank 'Kampus'.

- Collections vs. Tables:

	Tables are used in relational databases, while collections are used in NoSQL databases like MongoDB. Tables have a fixed schema, while collections are schema-less and can store documents with different structures. This allows for more flexibility in data storage and retrieval in NoSQL databases.

## D. Rechte und Rollen

- Screenshot mit falscher Authentifizierungsquelle: ![Screenshot mit falscher Authentifizierungsquelle](../../../x-res/m/01/failed-auth.png)
- [Script](./create-users.sh)
- Screenshot Benutzer 1: ![Screenshot Benutzer 1](../../../x-res/m/01/user1.png)
- Screenshot Benutzer 1 Read: ![Screenshot Benutzer 1](../../../x-res/m/01/user1-r.png)
- Screenshot Benutzer 2: ![Screenshot Benutzer 2](../../../x-res/m/01/user2.png)
- Screenshot Benutzer 2 Read Write: ![Screenshot Benutzer 2](../../../x-res/m/01/user2-rw.png)
