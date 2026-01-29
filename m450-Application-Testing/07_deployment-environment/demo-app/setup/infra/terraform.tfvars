
aws_region         = "eu-central-1"
cluster_name       = "demo-item-app-cluster"
desired_node_count = 2
min_node_count     = 1
max_node_count     = 3

tags = {
  Environment = "staging"
  ManagedBy   = "terraform"
}