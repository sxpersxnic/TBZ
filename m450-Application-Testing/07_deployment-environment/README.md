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

## Exercise 3
