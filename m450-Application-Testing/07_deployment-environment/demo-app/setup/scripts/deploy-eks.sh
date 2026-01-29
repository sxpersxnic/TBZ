#!/usr/bin/env bash
set -euo pipefail

# Config
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
BASE_DIR="$(dirname "$SCRIPT_DIR")"
TF_DIR="$BASE_DIR/infra"
K8S_DIR="$BASE_DIR/k8s"
SLEEP_AFTER_APPLY=120  # Seconds to wait after terraform apply

echo "[+] Switching to Terraform Directory"
cd "$TF_DIR"

echo "[+] Terraform init"
terraform init

echo "[+] Terraform apply"
terraform apply -auto-approve

echo "[+] Waiting $SLEEP_AFTER_APPLY Seconds for Cluster initialization..."
sleep $SLEEP_AFTER_APPLY

# Cluster Infos from Terraform Output
REGION=$(terraform output -raw region 2>/dev/null || echo "eu-central-1")
CLUSTER_NAME=$(terraform output -raw cluster_name)

echo "[+] Configuring kubectl for EKS cluster"
aws eks --region "$REGION" update-kubeconfig --name "$CLUSTER_NAME"

echo "[+] Waiting for nodes to be ready..."
sleep 10
kubectl get nodes

echo "[+] Installing NGINX Ingress Controller"
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.9.4/deploy/static/provider/aws/deploy.yaml

echo "[+] Waiting for NGINX Ingress Controller to be ready..."
kubectl wait --namespace ingress-nginx \
  --for=condition=ready pod \
  --selector=app.kubernetes.io/component=controller \
  --timeout=120s

echo "[+] Deploying Kubernetes manifests"

# Create namespace
kubectl apply -f "$K8S_DIR/namespace.yaml"

# Apply secret first (required by StatefulSet and Deployment)
kubectl apply -f "$K8S_DIR/postgres/secret.yaml"

# Apply PostgreSQL StatefulSet and Service
kubectl apply -f "$K8S_DIR/postgres/service.yaml"
kubectl apply -f "$K8S_DIR/postgres/deployment.yaml"

echo "[+] Waiting for PostgreSQL to be ready..."
kubectl wait --namespace demo-app --for=condition=ready pod -l app=demo-postgres --timeout=120s

# Apply Redis
kubectl apply -f "$K8S_DIR/redis/"
echo "[+] Waiting for Redis to be ready..."
kubectl wait --namespace demo-app --for=condition=ready pod -l app=demo-redis --timeout=60s

kubectl apply -f "$K8S_DIR/backend/"
kubectl apply -f "$K8S_DIR/frontend/"
kubectl apply -f "$K8S_DIR/ingress.yaml"

echo "[√] Cluster and applications deployed successfully"