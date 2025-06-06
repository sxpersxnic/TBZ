# Neo4j 02

## A. Adding Data

- [Script](create.cypher.txt)

## B. Querying Data

- [Script](query.cypher.txt)

**Explanation:**

- `MATCH (n)`: Finds all nodes in the graph.
- `OPTIONAL MATCH (n)-[r]->(m)`: Tries to find relationships from node `n`. If no relationships exist, `r` and `m` will be returned as `null`.
- `RETURN n, r, m`: Returns the nodes and relationships found.

> [!NOTE]
>
> The `OPTIONAL MATCH`

## C. Deleting Data

- [Script](delete.cypher.txt)

## D. Updating Data

- [Script](update.cypher.txt)

## E. Additional Clauses

- [Script: Unwind](unwind.cypher.txt)
- [Script: Merge](merge.cypher.txt)
