# Graph Databases

## What is a Graph Database?

Eine Graphdatenbank ist ein spezieller NoSQL-Datenbanktyp, der Daten als Knoten (Nodes) und Beziehungen (Edges) speichert. Jeder Knoten kann Eigenschaften besitzen, ebenso wie jede Beziehung. Die Datenstruktur ähnelt einem Netzwerk oder Diagramm.

Statt Tabellen verwendet man also eine vernetzte Struktur, in der Beziehungen zwischen Objekten im Mittelpunkt stehen – nicht nur die Objekte selbst. Dadurch lassen sich hochgradig vernetzte Informationen besonders effizient speichern und abfragen.

## Use cases

Graphdatenbanken sind besonders sinnvoll bei:

- Soziale Netzwerke (Freundschaftsbeziehungen)
- Empfehlungsdienste (z. B. „Person A hat Produkt B gekauft, du auch?“)
- Betrugserkennung (Verdächtige Transaktionen über viele Konten)
- Wissensdatenbanken & Ontologien (z. B. Wikipedia-Verlinkungen)
- Routenplanung & Netzwerkanalyse (z. B. Verkehr, Telekommunikation)

## Example

```ascii
[Alice] ---liest---> [Der Hobbit]
   |                      ^
  kennt                  |
   v                      |
 [Bob] ---liest---> [1984]
            |
         empfiehlt
            v
      [Der Hobbit]
```

## Graph Database vs. Relational Database

| Merkmal | Graphdatenbank | Relationale Datenbank |
|---------|----------------|-----------------------|
| Struktur | KNoten + Kanten | Tabellen |
| Fokus | Beziehungen | Entitäten |
| Abfragen | Traversierung entlang Kanten | Joins zwischen Tabellen |
| Komplexe Beziehungen | Sehr effizient | Performanceproblem bei JOINs |

## Performance RW

### Read & Write

- **Schreiben:** Etwas langsamer als einfache Key-Value-Stores, aber schneller als relationale DBs bei Beziehungsdaten.
- **Lesen:** Sehr schnell, da keine Joins erforderlich sind. Traversierung entlang der Kanten ist sehr performant.

### Advantages

- Optimiert für vernetzte Daten
- Keine teuren JOINs nötig
- Flexibles Schema
- Ideal für Beziehungs-Intensive Anwendungsfälle

### Disadvantages

- Nicht ideal für tabellarische oder flache Daten
- Komplexere Einstiegshürde
- Weniger standardisierte Query-Languages (z.B. Cypher)

## Subtypes of Graph Databases

- Property Graph Model (z.B. Neo4j): Knoten und Kanten haben Eigenschaften.
- RDF / Triple Store (z.B. GraphDB, Apache Jena): Daten werden in Tripel "Subjekt-Prädikat-Objekt" gespeichert.
- Hypergraphen: Eine Kante kann mehrere Knoten verbinden (seltener).

## Famous Graph Databases

|Produkt|Beschreibung|
|-------|-----------|
|Neo4j|Marktführer für Property-Graphen|
|ArangoDB|Multi-Modell, unterstützt auch Graph|
|Amazon Neptune|Cloudbasierte Graphdatenbank von AWS|
|OrientDB|Open Source mit mehreren Modi|
|JanusGraph|Hochskalierbar, für Big Data geeignet|
|Dgraph|Native Graphdatenbank, skalierbar|
