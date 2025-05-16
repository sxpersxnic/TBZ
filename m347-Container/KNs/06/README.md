# KN06: Kubernetes I

> **Overview:**
>	- [A - Installation](#a---installation)
>	- [B - Understanding of Clusters](#b---understanding-of-clusters)

## A - Installation

### Nodes

![List of master and connected nodes](../../x-resources/06/nodes.png)

## B - Understanding of Clusters

### `microk8s` vs. `microk8s kubectl`

While `microk8s` manages the MicroK8s-Cluster, `microk8s kubectl` is used for interaction with the Kubernetes-API-Server.

### `microk8s kubectl get nodes`

![CLI of Node 2](../../x-resources/06/node-2.png)

### Explanation

The first lines of `microk8s status` indicate wther MicroK8s is running and wether it is a **High Availability (HA)** Cluster. *HA:yes* means that multiple Master-Nodes work in the Cluster, to guarantee reliability. It also shows us which nodes are on *standby* and which are *master* nodes.

![High-Availability](../../x-resources/06/high-availability.png)

### Node Removal

![Node 3 leave Screenshot](../../x-resources/06/node-3-leave.png)

![Remove Node Screenshot](../../x-resources/06/master-remove-node-3.png)

### Worker Node

![Worker Node Screenshot](../../x-resources/06/worker-node.png)

#### Explanation

The Node was added as *Worker*. This means it is **not** part of the etcd-Cluster and **does not take any control** (no scheduling, no API-Server etc.). This gets visible in `microk8s status`, because only the **Master** get listed as HA-Control-Plane-Nodes.

### `microk8s kubectl get nodes` - Master vs. Worker

![Master](../../x-resources/06/master.png)
![Worker](../../x-resources/06/worker.png)

The Worker node is not a node of the master at *172.31.0.10* while the other nodes are still master nodes.
