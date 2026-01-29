variable "aws_region" {
  description = "AWS region to deploy into"
  type        = string
  default     = "eu-central-1"
}

variable "cluster_name" {
  description = "Name of the EKS cluster"
  type        = string
  default     = "demo-item-app-cluster"
}

variable "desired_node_count" {
  description = "Initial number of nodes in managed node group"
  type        = number
  default     = 2
}

variable "min_node_count" {
  description = "Min number of nodes"
  type        = number
  default     = 1
}

variable "max_node_count" {
  description = "Max number of nodes"
  type        = number
  default     = 3
}

variable "tags" {
  description = "Tags to apply to resources"
  type        = map(string)
  default     = {}
}