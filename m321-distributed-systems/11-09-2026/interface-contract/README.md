# Schnittstellenbeschreibung – Bibliotheksverwaltung

## Inhaltsverzeichnis

* [1. Ausgangslage](#1-ausgangslage)
* [2. Wahl des Schnittstellendefinitionsformats](#2-wahl-des-schnittstellendefinitionsformats)
* [3. Umsetzung mit OpenAPI](#3-umsetzung-mit-openapi)
* [4. Validierung der Schnittstellendefinition](#4-validierung-der-schnittstellendefinition)
* [5. Umsetzung mit GraphQL](#5-umsetzung-mit-graphql)
* [6. Vergleich der beiden Formate](#6-vergleich-der-beiden-formate)
* [7. Persönliche Beurteilung](#7-persönliche-beurteilung)
* [8. Fazit](#8-fazit)
* [9. Challenge – Codegenerierung](#9-challenge--codegenerierung)

---

# 1. Ausgangslage

Im Rahmen dieser Aufgabe soll eine Schnittstelle für eine Bibliotheksverwaltung beschrieben werden.

Die API ermöglicht die Verwaltung von **Büchern** und **Autoren**. Dabei besteht zwischen Büchern und Autoren eine Beziehung: Ein Buch besitzt einen oder mehrere Autoren und ein Autor kann mehrere Bücher geschrieben haben.

Die API soll folgende Funktionen anbieten:

### Bücher

* Alle Bücher abrufen
* Ein einzelnes Buch anhand seiner ID abrufen
* Ein neues Buch erstellen
* Ein bestehendes Buch aktualisieren
* Ein Buch löschen

### Autoren

* Alle Autoren abrufen
* Einen einzelnen Autor anhand seiner ID abrufen
* Einen neuen Autor erstellen
* Einen bestehenden Autor aktualisieren

Ein Buch besitzt mindestens folgende Informationen:

* ID
* Titel
* Beschreibung
* Veröffentlichungsjahr
* Autoren

Ein Autor besitzt mindestens folgende Informationen:

* ID
* Name
* Geburtsdatum
* geschriebene Bücher

---

# 2. Wahl des Schnittstellendefinitionsformats

Für die erste Umsetzung wurde **OpenAPI** gewählt.

OpenAPI eignet sich besonders zur Beschreibung von REST-Schnittstellen. Es können unter anderem HTTP-Methoden, Endpunkte, Parameter, Request-Bodies, Responses und Datenmodelle definiert werden.

Für diese Aufgabe ist OpenAPI geeignet, weil sich die Anforderungen direkt auf REST-Endpunkte abbilden lassen.

Beispielsweise wird das Abrufen aller Bücher durch folgenden Endpunkt beschrieben:

```http
GET /books
```

Das Abrufen eines bestimmten Buches erfolgt über:

```http
GET /books/{bookId}
```

Die OpenAPI-Schnittstellendefinition befindet sich in:

```text
openapi.yaml
```

---

# 3. Umsetzung mit OpenAPI

Die Schnittstelle wurde mit **OpenAPI 3.1** beschrieben.

Die wichtigsten Endpunkte sind:

| Funktion              | HTTP-Methode | Endpoint              |
| --------------------- | ------------ | --------------------- |
| Alle Bücher abrufen   | `GET`        | `/books`              |
| Buch nach ID abrufen  | `GET`        | `/books/{bookId}`     |
| Buch erstellen        | `POST`       | `/books`              |
| Buch aktualisieren    | `PUT`        | `/books/{bookId}`     |
| Buch löschen          | `DELETE`     | `/books/{bookId}`     |
| Alle Autoren abrufen  | `GET`        | `/authors`            |
| Autor nach ID abrufen | `GET`        | `/authors/{authorId}` |
| Autor erstellen       | `POST`       | `/authors`            |
| Autor aktualisieren   | `PUT`        | `/authors/{authorId}` |

## 3.1 Datenmodell

Das Datenmodell enthält die beiden Haupttypen `Book` und `Author`.

### Book

```text
Book
├── id
├── title
├── description
├── publicationYear
└── authors[]
```

### Author

```text
Author
├── id
├── name
├── birthDate
└── books[]
```

Die Beziehung zwischen Büchern und Autoren ist eine **Many-to-Many-Beziehung**.

Ein Buch kann mehrere Autoren besitzen:

```text
Book 1 ──── Author 1
       ├─── Author 2
       └─── Author 3
```

Ein Autor kann gleichzeitig mehrere Bücher geschrieben haben:

```text
Author 1 ──── Book 1
         ├─── Book 2
         └─── Book 3
```

Bei der Erstellung eines Buches werden deshalb die IDs der Autoren angegeben.

Beispiel:

```json
{
  "title": "Der Herr der Ringe",
  "description": "Ein Fantasy-Roman über Mittelerde.",
  "publicationYear": 1954,
  "authors": [1]
}
```

---

# 4. Validierung der Schnittstellendefinition

Die OpenAPI-Datei wurde auf ihre syntaktische Korrektheit überprüft.

Dafür kann beispielsweise der **Swagger Editor** verwendet werden:

https://editor.swagger.io/

Die Datei `openapi.yaml` wird in den Editor eingefügt. Der Editor überprüft anschliessend, ob die OpenAPI-Definition dem erwarteten Format entspricht.

Zusätzlich kann die Definition über die Kommandozeile validiert werden:

```bash
npx swagger-cli validate openapi.yaml
```

Wenn die Datei korrekt ist, wird sie als gültig erkannt.

Die Validierung ist wichtig, weil eine syntaktisch fehlerhafte Schnittstellenbeschreibung später beispielsweise nicht zuverlässig für Dokumentation oder Codegenerierung verwendet werden kann.

---

# 5. Umsetzung mit GraphQL

Nach der Umsetzung mit OpenAPI wurde die Schnittstelle in **GraphQL** übertragen.

Die GraphQL-Schnittstellendefinition befindet sich in:

```text
schema.graphql
```

Im Gegensatz zu REST werden bei GraphQL nicht für jede Operation unterschiedliche HTTP-Endpunkte definiert.

Stattdessen wird zwischen **Queries** und **Mutations** unterschieden.

## 5.1 Queries

Für das Abrufen von Daten werden Queries verwendet:

```graphql
type Query {
  books: [Book!]!
  book(id: ID!): Book
  authors: [Author!]!
  author(id: ID!): Author
}
```

Damit können alle Bücher oder Autoren sowie einzelne Bücher oder Autoren anhand ihrer ID abgerufen werden.

Beispiel:

```graphql
query {
  book(id: "1") {
    id
    title
    publicationYear
    authors {
      id
      name
    }
  }
}
```

Ein wichtiger Unterschied zu REST ist, dass der Client selbst auswählen kann, welche Felder zurückgegeben werden sollen.

---

## 5.2 Mutations

Für Änderungen an den Daten werden Mutations verwendet:

```graphql
type Mutation {
  createBook(input: CreateBookInput!): Book!
  updateBook(id: ID!, input: UpdateBookInput!): Book!
  deleteBook(id: ID!): Boolean!

  createAuthor(input: CreateAuthorInput!): Author!
  updateAuthor(id: ID!, input: UpdateAuthorInput!): Author!
}
```

Damit werden alle geforderten Änderungen an Büchern und Autoren abgedeckt.

Beispielsweise kann ein Buch so erstellt werden:

```graphql
mutation {
  createBook(
    input: {
      title: "Der Herr der Ringe"
      description: "Ein Fantasy-Roman über Mittelerde."
      publicationYear: 1954
      authorIds: ["1"]
    }
  ) {
    id
    title
    authors {
      name
    }
  }
}
```

Die Antwort kann ebenfalls vom Client bestimmt werden.

---

# 6. Vergleich der beiden Formate

OpenAPI und GraphQL können dieselben fachlichen Anforderungen abbilden. Die beiden Formate verfolgen jedoch unterschiedliche Ansätze.

| Bereich          | OpenAPI / REST                       | GraphQL                            |
| ---------------- | ------------------------------------ | ---------------------------------- |
| Grundprinzip     | Ressourcen und HTTP-Endpunkte        | Daten und deren Beziehungen        |
| Abrufen          | HTTP `GET`                           | `Query`                            |
| Erstellen        | HTTP `POST`                          | `Mutation`                         |
| Aktualisieren    | HTTP `PUT`                           | `Mutation`                         |
| Löschen          | HTTP `DELETE`                        | `Mutation`                         |
| Endpunkte        | Mehrere Endpunkte                    | Normalerweise ein GraphQL-Endpunkt |
| Schema           | OpenAPI Schema                       | GraphQL Schema                     |
| Datenfelder      | Vom Server definiert                 | Vom Client auswählbar              |
| Beziehungen      | Über Responses oder weitere Requests | Direkt verschachtelbar             |
| HTTP-Statuscodes | Zentrale Bedeutung                   | Weniger zentral                    |
| Caching          | Sehr gut mit HTTP umsetzbar          | Komplexer                          |
| Overfetching     | Kann auftreten                       | Wird reduziert                     |
| Underfetching    | Kann auftreten                       | Wird reduziert                     |
| Einstieg         | Einfacher                            | Etwas komplexer                    |
| Tooling          | Sehr umfangreich                     | Umfangreich                        |

## 6.1 Unterschied bei Datenabfragen

Bei REST könnte beispielsweise folgendes verwendet werden:

```http
GET /books/1
```

Der Server entscheidet dabei, welche Informationen über das Buch zurückgegeben werden.

Bei GraphQL kann der Client exakt angeben, welche Informationen benötigt werden:

```graphql
query {
  book(id: "1") {
    title
    authors {
      name
    }
  }
}
```

Wenn nur der Titel benötigt wird, können die Autoren weggelassen werden:

```graphql
query {
  book(id: "1") {
    title
  }
}
```

Dadurch muss der Server nicht unnötige Daten übertragen.

---

# 7. Persönliche Beurteilung

Für diese konkrete Aufgabe finde ich **GraphQL besser geeignet**.

Der Hauptgrund ist die Beziehung zwischen Büchern und Autoren. Die Daten sind miteinander verbunden und können mit GraphQL direkt verschachtelt abgefragt werden.

Beispielsweise:

```graphql
query {
  author(id: "1") {
    name
    books {
      title
      authors {
        name
      }
    }
  }
}
```

Damit können Informationen über einen Autor und seine Bücher innerhalb einer Anfrage abgerufen werden.

Bei einer klassischen REST-Schnittstelle müsste man je nach API-Design mehrere Endpunkte verwenden oder besonders verschachtelte Responses definieren.

GraphQL bietet ausserdem den Vorteil, dass der Client selbst bestimmen kann, welche Felder er benötigt. Dadurch können Overfetching und Underfetching reduziert werden.

## Nachteile von GraphQL

GraphQL ist allerdings nicht grundsätzlich besser.

Die Implementierung ist komplexer als bei einer einfachen REST-API. Auch HTTP-Caching ist bei REST einfacher, da REST direkt auf den bestehenden HTTP-Mechanismen aufbaut.

Für einfache CRUD-Schnittstellen würde ich deshalb eher REST mit OpenAPI verwenden.

Für komplexere Datenmodelle mit vielen Beziehungen würde ich GraphQL in Betracht ziehen.

---

# 8. Fazit

Durch die Umsetzung in beiden Formaten wurde deutlich, dass OpenAPI und GraphQL unterschiedliche Aspekte einer Schnittstelle in den Mittelpunkt stellen.

**OpenAPI / REST** beschreibt hauptsächlich:

```text
HTTP
│
├── Methoden
├── Endpunkte
├── Parameter
├── Requests
├── Responses
└── Datenmodelle
```

**GraphQL** beschreibt hauptsächlich:

```text
Datenmodell
│
├── Types
├── Fields
├── Relationships
├── Queries
└── Mutations
```

OpenAPI passt besonders gut zu klassischen REST-Anwendungen und ist einfach mit bestehenden HTTP-Mechanismen und Tools kombinierbar.

GraphQL eignet sich besonders für Anwendungen, bei denen Clients flexibel Daten aus verschiedenen miteinander verbundenen Ressourcen abrufen müssen.

Für die vorliegende Bibliotheksverwaltung würde ich **GraphQL bevorzugen**, weil die Beziehung zwischen Büchern und Autoren einen wesentlichen Teil des Datenmodells darstellt.

---

# 9. Challenge – Codegenerierung

Als optionale Erweiterung können aus den Schnittstellendefinitionen automatisch Grundgerüste für Software erzeugt werden.

Für OpenAPI stehen beispielsweise Codegeneratoren zur Verfügung, die anhand der `openapi.yaml` verschiedene Bestandteile einer Anwendung generieren können.

Ein bekanntes Werkzeug ist **OpenAPI Generator**.

Beispielsweise kann daraus ein TypeScript-Client erzeugt werden:

```bash
openapi-generator-cli generate \
  -i openapi.yaml \
  -g typescript-fetch \
  -o generated/client
```

Dadurch müssen die API-Interfaces auf der Client-Seite nicht vollständig von Hand geschrieben werden.

Auch für GraphQL existieren Codegeneratoren. Ein verbreitetes Werkzeug ist beispielsweise **GraphQL Code Generator**.

Damit können anhand des GraphQL-Schemas und der Queries automatisch TypeScript-Typen und weitere Client-Artefakte erzeugt werden.

## Erkenntnisse

Die Codegenerierung hat den Vorteil, dass sich Fehler bei der manuellen Erstellung von Interfaces reduzieren lassen. Änderungen am Schema können ausserdem als zentrale Quelle für die Generierung verwendet werden.

Der Nachteil ist, dass generierter Code nicht immer exakt der gewünschten Architektur entspricht. Deshalb sollte generierter Code überprüft und nicht blind übernommen werden.

Für grössere Projekte halte ich Codegenerierung besonders bei OpenAPI und GraphQL für sinnvoll, da dadurch die Schnittstellendefinition als **Single Source of Truth** verwendet werden kann.

---

# Verwendete Dateien

```text
.
├── README.md
├── openapi.yaml
└── schema.graphql
```

* `README.md` – Dokumentation und Vergleich
* `openapi.yaml` – OpenAPI-Schnittstellendefinition
* `schema.graphql` – GraphQL-Schnittstellendefinition
