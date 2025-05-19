# Kubernetes

This directory contains all configuration files to deploy the services to **Kubernetes**.

## Namespace

> In Kubernetes, _namespaces_ provide a mechanism for isolating groups of resources within a single cluster. Names of resources need to be unique within a namespace, but not across namespaces. Namespace-based scoping is applicable only for namespaced [objects](https://kubernetes.io/docs/concepts/overview/working-with-objects/#kubernetes-objects) _(e.g. Deployments, Services, etc.)_ and not for cluster-wide objects _(e.g. StorageClass, Nodes, PersistentVolumes, etc.)_.
>
> \- [Kubernetes Docs](https://kubernetes.io/docs/concepts/overview/working-with-objects/namespaces/)

### Namespace configuration file

- [namespace.yml](./namespace.yml)

---

## Deployments

> A Deployment manages a set of Pods to run an application workload, usually one that doesn't maintain state.
>
> \- [Kubernetes Docs](https://kubernetes.io/docs/concepts/workloads/controllers/deployment/)

### Deployment configuration files

- [Frontend](./deployments/deployment-frontend.yml)
- [Account](./deployments/deployment-account.yml)
- [BuySell](./deployments/deployment-buysell.yml)
- [SendReceive](./deployments/deployment-sendreceive.yml)

---

## Services

> In Kubernetes, a Service is a method for exposing a network application that is running as one or more [Pods](https://kubernetes.io/docs/concepts/workloads/pods/) in your cluster.
>
> \- [Kubernetes Docs](https://kubernetes.io/docs/concepts/services-networking/service/)

### Service configuration files

To keep it simple, the configuration for each service, is in the service's [**deployment configuration file**](#deployment-configuration-files).

---

## ConfigMaps

> A ConfigMap is an API object used to store non-confidential data in key-value pairs. [Pods](https://kubernetes.io/docs/concepts/workloads/pods/) can consume ConfigMaps as environment variables, command-line arguments, or as configuration files in a [volume](https://kubernetes.io/docs/concepts/storage/volumes/).
>
> \- [Kubernetes Docs](https://kubernetes.io/docs/concepts/configuration/configmap/)

### ConfigMap configuration files

- [Frontend](./config/frontend-env.yml)
- [Account](./config/account-env.yml)
- [BuySell](./config/buysell-env.yml)
- [SendReceive](./config/sendreceive-env.yml)

---

## Secrets

> A Secret is an object that contains a small amount of sensitive data such as a password, a token, or a key. Such information might otherwise be put in a [Pod](https://kubernetes.io/docs/concepts/workloads/pods/) specification or in a [container image](https://kubernetes.io/docs/reference/glossary/?all=true#term-image). Using a Secret means that you don't need to include confidential data in your application code.
>
> \- [Kubernetes Docs](https://kubernetes.io/docs/concepts/configuration/secret/)

### Secret configuration file

> [!WARNING]
>
> I just added this in case it would be useful, it will probably be removed in further commits.

- [MariaDB](./secrets/mariadb-secret.yml)
