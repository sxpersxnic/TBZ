# Prometheus 01

## A. Installation

### Prometheus Node-Exporter Metrics

![Prometheus Node-Exporter Metrics](/m165-NoSQL/x-resources/p/01/p-metrics.png)

### Prometheus Dashboard

![Prometheus Dashboard](/m165-NoSQL/x-resources/p/01/p-dashboard.png)

### Grafana Dashboard

- Username: `admin`
- Password: `admin`

![Grafana Dashboard](/m165-NoSQL/x-resources/p/01/g-dashboard.png)

### Grafana Metrics for Prometheus

![Grafana Metrics for Prometheus](/m165-NoSQL/x-resources/p/01/g-metrics.png)

## B. Explanation Cloud-Init

### 1. What are Scrapes in Prometheus?

**Definition:**

A _scrape_ in Prometheus refers to the act of collecting metrics data by periodically querying endpoints (usually `/metrics`) exposed by applications or services. Prometheus works on a **pull model**, where it actively scrapes metrics from configured targets.

**Example:**

```yml
scrape_configs:
  - job_name: prometheus
    static_configs:
      - targets: ['localhost:9090']
  - job_name: node
    static_configs:
      - targets: ['localhost:9100']
```

**Explanation:**

- `job_name`: Scrapes metrics from Prometheus itself.

- `targets: ['localhost:9090']`: The endpoint where Prometheus exposes its own metrics.

- `job_name: node`: Scrapes metrics from the **Node Exporter**, a tool that exposes hardware and OS metrics.

- `targets: ['localhost:9100']`: The endpoint where the Node Exporter exposes its metrics.

### 2. What are Rules in Prometheus?

**Definition:**

Rules in Prometheus define either:

- **Recording Rules**: Create new time series based on queries.
- **Alerting Rules**: Trigger alerts based on thresholds or conditions.

They are declared in external **YAML** files (e.g. `rules.yml`) and referenced via the `rule_files` config.

**Example:**

**Recording Rules:**

```yml
- record: node_memory_MemFree_percent
  expr: 100 - (100 * node_memory_MemFree_bytes / node_memory_MemTotal_bytes)
```

- This rule calculates free memory as a percentage.

**Alerting Rules:**

```yml
- alert: InstanceDown
  expr: up == 0
  for: 1m
  labels:
    serverity: critical
  annotations:
    summary: "Instance {{ $labels.instance }} down"
```

- This rule triggers if an instance is **down for more than 1 minute** (i.e., `up == 0`).

### 3. Steps to Push Custom Data into Prometheus

Prometheus uses **pull-based architecture** - meaning _you expose_ the data via a `/metrics` endpoint HTTP endpoint, and Prometheus scrapes it.


**Step-by-step:**

1. **Instrument an application** using a Prometheus client library.

2. **Expose metrics at `/metrics`**:

- E.g., a simple HTTP server, which exposes metrics.

3. **Configure Prometheus**: The app's endpoint needs to be added to the `scrape_configs` in the Prometheus configuration file.

4. **Reload Prometheus** to apply changes.

### 4. Variables in Scrapes & Rules

**In Scrapes:**

- `job_name`: Logical identifier of the target group.

- targets: List of target URLs (host:port).

- `metrics_path` (optional): Path from where metrics are pulled, default `/metrics`.

These point to **live endpoints** like:

- `localhost:9090` (Prometheus itself)

- `localhost:9100` (Node Exporter)

- Custom apps at e.g., `127.0.0.1:8080/metrics`

**In Rules:**

- `${labels.instance}`: The host:port of the instance.

- `${labels.job}`: The job name from scrape config.

These are auto-populated by Prometheus when it evaluates expressions like `up == 0`.

### 5. How Does Prometheus Know If a System is Up?

Prometheus uses the `up` metric:

```txt
up{job="node", instance="localhost:9100"} 1
```

- `up == 1`: Target is reachable and responding.

- `up == 0`: Target is unreachable or returns an error.

**Example Alert Rule:**

```yml
- alert: InstanceDown
  expr: up == 0
  for: 1m
```

**Meaning:**

- If Prometheus can't scrape a target for 1 minute (`up == 0` for 1m), it fires an **alert**.

- These alerts are shown in the Alerts UI and can be forwarded via **Alertmanager**.
