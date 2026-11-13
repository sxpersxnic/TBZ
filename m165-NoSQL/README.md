# M165 – NoSQL-Datenbanken einsetzen

> **NoSQL databases** · Year 2 (2024/25) · Spring semester (FS) · Done

Competence proofs (KN) on document, key-value, graph and time-series databases: MongoDB, Redis, Neo4j and Prometheus.

> NoSQL databases (AKA "not only SQL") store data differently than relational tables. NoSQL databases come in a variety of types based on their data model. The main types are document, key-value, wide-column, and graph. They provide flexible schemas and scale easily with large amounts of big data and high user loads.
>
> — [MongoDB](https://www.mongodb.com/resources/basics/databases/nosql-explained)

## Contents

### MongoDB

| KN | Topics |
| --- | --- |
| [KN-M-01](./KNs/MongoDB/01/) | Installation, first steps with GUI and shell |
| [KN-M-02](./KNs/MongoDB/02/) | Data modelling: conceptual, logical, physical |
| [KN-M-03](./KNs/MongoDB/03/) | Inserting, deleting and querying data |
| [KN-M-04](./KNs/MongoDB/04/) | Aggregation, join aggregation, sub-documents and arrays |
| [KN-M-05](./KNs/MongoDB/05/) | Administration: rights and roles, backup and restore, scaling |
| [KN-M-06](./KNs/MongoDB/06/) | JSON schemas and validation |
| [KN-M-07](./KNs/MongoDB/07/) | [API](./KNs/MongoDB/07/api/) on top of MongoDB |

### Redis

| KN | Topics |
| --- | --- |
| [KN-R-01](./KNs/Redis/01/) | Redis basics |

### Neo4j

| KN | Topics |
| --- | --- |
| [KN-N-01](./KNs/Neo4j/01/) | Installation, logical model |
| [KN-N-02](./KNs/Neo4j/02/) | Adding, querying and deleting data |
| [KN-N-03](./KNs/Neo4j/03/) | Neo4j 03 |

### Prometheus

| KN | Topics |
| --- | --- |
| [KN-P-01](./KNs/Prometheus/01/) | Installation, cloud-init setup |
| [KN-P-02](./KNs/Prometheus/02/) | Custom metrics, environment configuration |

### Notes

- [Graph databases](./Graph-Databases/)

## Key concepts

### ACID (SQL) vs. BASE (NoSQL)

ACID (Atomicity, Consistency, Isolation, Durability) is a set of properties that guarantee reliable processing of database transactions. BASE (Basically Available, Soft state, Eventually consistent) is an alternative to ACID that allows for more flexibility and scalability in distributed systems.

### CAP theorem

The CAP theorem states that a distributed data store can only provide two of the following three guarantees at the same time: consistency, availability, and partition tolerance.

---

[← Back to overview](../README.md)
