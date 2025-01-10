# CheatSheet 

## DNS 
- DNS -> **D**omain **N**ame **S**ystem

- Converts Domain names to IP-Adresses: example.com -> x.x.x.x
- DNS1 and DNS2 -> Primary and Secondary DNS-Server

## Ports

### Protocols
- HTTP: 80
- HTTPS: 443

### Databases
- MySQL: 3306
- PostgreSQL: 5432
- MongoDB: 27017

### Develoment ports
- 8080
- 8081
- 9001

## IP-Adress 
*(Classful addressing is not used anymore -> Class A, B C)*

## MAC-Adress

- MAC -> *M*edia *A*ccess *C*ontrol Adress
- Unique identifier assigned to network interfaces
- Expressed as six groups of two hexadeximal digits, separated by colons or dashes *Example: 00:1A:2B:3C:4D:5E*

## Subnetting
    - Subnetting is the process of dividing a network into smaller sub-networks.
    - It helps in optimizing network performance, improving security and efficiently utilizing IP-Adresses.
    - Subnet masks are used to define the network portion and the host portion of an IP adress:
      - 255.0.0.0 -> /8 (Class A)
      - 255.255.0.0 -> /16 (Class B)
      - 255.255.255.0 -> /24 (Class C)

## OSI Model

### Layers

- **Layer 1 - Physical Layer**: Deals with the *physical* connections between devices.
- **Layer 2 - Data Link Layer**: Responseible for node-to-node communication, error detection and *MAC* adressing.
- **Layer 3 - Network Layer**: Handles routing and forwarding of data packages.
- **Layer 4 - Transport Layer**: Provides end-to-end communication between *applications*.
- **Layer 5 - Session Layer**: Establishes, manages and terminates connections between applications.
- **Layer 6 - Presentation Layer**:
- Translates data between the application layer and teh network format.
- **Layer 7 - Application Layer**: Provides interfaces between applications and the network.
- *(Layer 8 - User Layer: Is a joke)*: Although not officially part of the OSI model, it humorously refers to end-users who interact with the network.

## TCP/IP Model

### Layers
- **Layer 1 - Application Layer**
    This layer combines the OSI model's top three layers (Application, Presentation, and Session).
Handles high-level protocols, interface with software applications, and provides network services to end-users.
Examples of protocols: HTTP, HTTPS, FTP, SMTP.
- **Layer 2 - Transport Layer**
- **Layer 3 - Network Layer**
- **Layer 4 - Network Access Layer**


## ISO vs TCP

![ISO-Model-vs.-TCP-Model](./assets/OSI-vs-TCP.webp)

## VLAN 

- VLAN -> **V**irtual **L**ocal **A**rea **N**etwork

## NAT

- NAT -> **N**etwork **A**ddress **T**ranslation

## Protocols

### HTTP
### HTTPS
### FTP
### SFTP
### SMTP
### TCP/UDP
#### TCP
#### UDP

### Fileextensions

#### Visual
##### PNG
##### JPG
##### JPEG
##### MP4
##### GIF
#### Audio
##### WAV
##### MPEG
##### MP3