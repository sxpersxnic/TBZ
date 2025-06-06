# MongoDB 05: Administration of MongoDB

## A. Rights and Roles

- **Screenshot of error at connection with wrong authentication source:**

	![Authentication Error](/m165-NoSQL/x-resources/m/05/auth-error.png)

- [User Creation Script](create-user.js)

- ****

## B. Backup and Restore

## C. Scaling

### Difference: Replication vs. Partitioning

| Replication | Partitioning (Sharding) |
|-------------|-------------------------|
| Duplicates Data | Distributes Data horizontally to multiple Servers |
| Provides High Availability | Provides Scalability |
| Primary and Secondary Structure | Shard key-based Data Distribution |
| Easy Restoration | Complex Routing and Balancing |

**Illustration:**

```plaintext
REPLICATION:

+---------+     +----------+
| PRIMARY | --> | SECONDARY|
+---------+     +----------+
                     |
                 +----------+
                 | SECONDARY|
                 +----------+

SHARDING:

+--------+    +--------+    +--------+
| Shard1 |    | Shard2 |    | Shard3 |
+--------+    +--------+    +--------+
     \           |             /
      \         Router        /
       \__________+__________/
                  |
              Application
```

### Recommendation for the Company

**Initial situation:**

Our application stores large amounts of event logs and user interactions (>10 million records/month). So far, everything runs on a single MongoDB server without replication or partitioning.

**Recommendation:**

**Sharding + Replication:**

- **Sharding:** For horizontal scaling of the amount of data.
- **Replication:** For high availability, Backups and Readoptimization.

**Advantages:**

- **High Availability:** Data is available even if one server fails.
- **Neatless Scaling:** New shards can be added as needed.
- **Performance:** Read and write operations can be distributed across multiple servers.

However, if the application is only read-heavy and has few write accesses, replication alone may be sufficient. In this case, a read-replica could improve latency without shard complexity.
