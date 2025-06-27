# Prometheus 02

## A. Custom Metrics

- [API](./metrics)

### Getting Started

```sh
cd ./metrics
go mod tidy # Ensure all dependencies are resolved
go run main.go # Start the application
curl http://localhost:8080/metrics # Access the metrics endpoint
```

## B. Environment Configuration

![Grafana Dashboard with Custom Metrics](/m165-NoSQL/x-resources/p/02/g-dashboard.png)