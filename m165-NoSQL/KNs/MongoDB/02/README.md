# MongoDB 02: Datenmodellierung

## A. Konzeptionelles Datenmodell

### Mermaid Markdown Diagramm

```mermaid
---
title: "Konzeptionelles Datenmodell"
---

erDiagram
	BAND {
		string name
		string genre
		string country
		int fans
		date formationDate
	}

	ALBUM {
		string title
		date releaseDate
		int sales
	}

	SONG {
		string title
		int duration
		string lyrics
	}

	MEMBER {
		string name
		string instrument
		int age
	}

	CONCERT {
		string location
		date date
		int visitors
		float ticketPrice
	}

	BAND ||--o{ ALBUM : produces
	BAND ||--o{ CONCERT : performs
	ALBUM ||--o{ SONG : contains
	MEMBER ||--o{ BAND : "is member of"
	SONG }o--o{ CONCERT : "performed in"

```

### Bild des Diagramms

![Konzeptionelles Datenmodell](/m165-NoSQL/x-resources/m/02/conceptual-data-model.png)

### Erklärung der Entitäten

- **BAND**
  - **Beschreibung:** Repräsentiert eine Musikgruppe.
  - **Attribute:**
- **ALBUM**
  - **Beschreibung:** Veröffentlichung einer Band.
  - **Attribute:**
- **SONG**
  - **Beschreibung:** Repräsentiert eine Musikgruppe.
  - **Attribute:**
- **MEMBER**
  - **Beschreibung:** Repräsentiert eine Musikgruppe.
  - **Attribute:**
- **CONCERT**
  - **Beschreibung:** Repräsentiert eine Musikgruppe.
  - **Attribute:**

...

## B. Logisches Modell für MongoDB

### Bild des logischen Modells

![Logisches Modell](https://raw.githubusercontent.com/andreaskeller/m165-NoSQL/main/KNs/MongoDB/02/logical_model.png)

### Original Datei

- [Here](./model.drawio)

### Erklärung des Modells

...

## C. Anwendung des Schemas in MongoDB

