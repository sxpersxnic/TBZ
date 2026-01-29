# Deployment Environment

## Environment Types

- Development: Local setup for developers to build and test features quickly.
- Testing: Isolated environment for running automated tests and quality assurance.
- Staging: Pre-production environment that closely mirrors production for final testing.
- Production: Live environment where the application is accessible to end-users.

## Exercise 1

### Tool for Environment

**Tools**:

- Docker Compose
- Kubernetes (K8s)
- Terraform
- Vagrant

|Environment|Tool|Reason|
|-----------|----|------|
|**Development**|Docker Compose / (if needed) Vagrant|Compose allows simple, fast and reproducable environments on a laptop: all services (DB, Cache, API, Frontend ...) in a single `docker-compose.yml`. Very easy to set up and fast iteration. **Vagrant** becomes useful when a VM with a specific OS/Network is needed (e.g. to mimic conditions of production locally).|
|**Testing**		|Docker Compose (isolated multi container stacks), for test data if applicable |Isolated, easy to spin up test environment with the same configuration as in dev, allows integrations tests without complex infrastructure.|
|**Staging**		|Kubernetes or Terraform (depending on scenario)|Wenn the production depends on container orchestration: Ideally, use Kubernetes clusters for staging to test behavior under realistic conditions (scaling, load balancing, error cases). If the staging environment also contains infrastructure components (networks, VMs, DB servers, load balancers), Terraform is useful for provisioning infrastructure consistently and reproducibly|
|**Production**	|Kubernetes + Terraform (optionally with other IaC / config tools)|Kubernetes offers scalability, reliability, and service orchestraion - requirements that are virtually mandatory for production systems if high loads are expected. Terraform is perfect for automatically building and managing the underlying infrastructure (clusters, networks, VMs, cloud resources) and ensures consisteny between staging and production.|

## Exercise 2

### What Solution I would choose

I choose **Terraform + Kubernetes**, with focus on the automated setup of a **staging- or production-environment**.

### Example Use-Case: Deployment of a Microservice Application

Assuming, I have a small microservice application (e.g. Web-Frontend + API + PostgreSQL + Redis). I want to setup an environment in the cloud or a VPS-Cluster: Load-Balancer, VMs or Container-Cluster, Network, DB-Instance, etc.

**Approach**:

1. **Terraform for Infrastructure Provisioning**:
	- Network (VPC / Subnets), Security Groups / Firewalls
	- VMs or Container Cluster (e.g. managed k8s)
	- Load-Balancer / Ingress / Storage / Database Service
2. Creating **Kubernetes Cluster** directly with Terraform (managed or self-managed).
3. Deployment of the Microservices into the k8s Cluster (via Kubernetes manifests or Helm charts).
4. Everything is defined as code - versioned, reproducible, with `terraform plan/apply`.

**What I can realistically do in a lesson (45min)**:

- Base terraform script that creates a VM or a Kubernetes cluster
- Kubernetes manfest/Helm for my microservices
- Deployment with `terraform apply` and a simple smoke test (is the API reachable? DB connected?)

**Expected Challenges**:

- Correctly configuring the Network / Security (Ports, Firewalls, Load-Balancer)
- Managing secrets / credentials (DB passwords, API keys etc.)
- Kubernetes complexity (resources, yaml, deployments, services)
- State management in terraform, if applicable remote state

**When & Why**:

- When I have multiple environments (Staging, Prod evtl. QA) - consistent, reproducible infrastructure
- When an application consists of multiple components (DB, Cache, API, Load-Balancer, external services etc.)
- When scalability, reliability, maintainability and infrastructure availability over time is important

For simple personal or small projects with few components, that setup might be overkill. For _serious_ deployments (e.g. webservice, SaaS, microservices, Cloud-Native apps) it is a solid foundation.

## Exercise 3 (Optional Challenge)

- [Setup](./demo-app/setup)
