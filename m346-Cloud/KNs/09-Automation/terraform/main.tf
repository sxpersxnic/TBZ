terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 4.16"
    }
  }
  required_version = ">= 1.2.0"
}

provider "aws" {
  region = "us-east-1"
}

# Security group resource
resource "aws_security_group" "db_server_sg" {
  name        = "db-server-security-group"
  description = "Security group for the database server"

  ingress {
    from_port   = 3306
    to_port     = 3306
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

# EC2 instance resource
resource "aws_instance" "db_server" {
  ami           = "ami-04b4f1a9cf54c11d0"
  instance_type = "t2.micro"

  user_data = file("../cloud-init/cloud-init-db.yml")

  vpc_security_group_ids = [aws_security_group.db_server_sg.id]

  tags = {
    Name  = "Databaseserver"
    Using = "Terraform"
  }
}
