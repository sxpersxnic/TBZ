output "cluster_endpoint" {
  description = "EKS cluster endpoint"
  value       = module.eks.cluster_endpoint
}

output "cluster_name" {
  description = "Name of EKS cluster"
  value       = module.eks.cluster_name
}

output "cluster_security_group_id" {
  description = "Security group attached to control plane"
  value       = module.eks.cluster_security_group_id
}

output "kubeconfig_certificate_authority_data" {
  description = "CA data for kubeconfig"
  value       = module.eks.cluster_certificate_authority_data
}

output "region" {
  value = var.aws_region
}