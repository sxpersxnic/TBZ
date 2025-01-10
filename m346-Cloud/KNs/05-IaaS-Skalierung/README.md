# KN05 13.12.2024 #

## A) Terms and private IP ##

### Term explanation ###

|Term|Explanation|
|----|-----------|
|VPCs|Stands for **V**irtual **P**rivate **C**loud. A VPC is logically isolated from the rest of networks in the AWS Cloud. Grants access to control over virtual network environment.
|Subnet|A subnet is a range of IP addresses in your VPC. A subnet mask, such as 255.0.0.0 or /24, defines how many hosts can connect to this network. Subnets allow you to create custom networks that are not directly connected to the internet, enabling you to set custom rules and policies, such as firewall settings.
|How many subnets are already predefined?| There is one predefined subnet with the name: '-' |
|Do the IP ranges of the subnet cover the entire IP range of the VPC?|Yes the entire range of the VPC (172.31.0.0/16) is covered (Min: 172.31.0.0/20, Max: 172.31.80.0/20) but by multiple subnets, a single subnet does not cover the entire range of the VPC.|
|Public IP address| The public IP is reachable from the internet |
|Static IP address|A static IP address is an IP address that does not change. Unlike dynamic IP addresses assigned by DHCP, static IP addresses remain constant, making them useful for servers that need a consistent address for communication.|

### Screenshot Subnet-List ###

  ![Subnet-List in AWS](/m346-Cloud/Images/KN05/SUBNETS.png)

### Private IPs ###

#### Apache ####

- 172.31.0.10/16
  
#### Mariadb ####

- 172.31.0.20/16

## B) Creation of objects and instances ##

### Securitygroup ###

**Screenshot Securitygroups list:**

  ![Securitygroups](/m346-Cloud/Images/KN05/SECURITYGROUPS.png)

**Inbound rules of MariaDB Server:**

  ![Inbound rules of MariaDB Server](/m346-Cloud/Images/KN05/INBOUND-MARIADB.png)

**Inbound rules of Apache Server:**

  ![Inbound rules of Apache Server](/m346-Cloud/Images/KN05/INBOUND-APACHE.png)

### Public, static IP ###

  ![Elastic IPs](/m346-Cloud/Images/KN05/IP-APACHE.png)

### Create instances ###

  ![EC2 Instances](/m346-Cloud/Images/KN05/INSTANCES.png)
