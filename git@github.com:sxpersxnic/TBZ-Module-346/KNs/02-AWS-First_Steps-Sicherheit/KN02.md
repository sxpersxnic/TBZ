# KN02 22.11.2024

## A) Umgang mit AWS Kurs (20%)

*-*

## B) Instanz erstellen (40%)

### AWS EC2 Instanz

![Screenshot von AWS EC2 Instanzen](/Images/KN02/AWS_KN02_EC2.png)

### Details

- Konfiguration:
  - Name: sxpersxnic_m346
  - OS Image: Ubuntu 24.04
  - Key-Pair: **Zwei** neue Key pairs: *sxnic*, *sxnic2*. **Ersten** Key auswählen.

- **Diskgrösse:** 8 GiB
- **Betriebssystem:** Ubuntu Linux 24.04
- **Grösse des RAM:** 957Mi
- **Anzahl (v)CPUs:** 1

### Abgabe

## C) Zugriff mit SSH-Key (40%)

### Command

```sh
ssh -i "C:\Users\sxpersxnic\.ssh\sxnic1.pem" ubuntu@ec2-23-21-28-114.compute-1.amazonaws.com
```

### Screenshot Key-1

![Screenshot mit dem ssh-Befehl und des Resultats unter Verwendung des ersten Schlüssels](/Images/KN02/AWS_KN02_EC2_KEY1.png)

### Screenshot Key-2

![Screenshot mit dem ssh-Befehl und des Resultats unter Verwendung des zweiten Schlüssels](/Images/KN02/AWS_KN02_EC2_KEY2.png)

### Screenshot Instance-Details

![Screenshot der Instanz-Detail (oder Liste), so dass der verwendete Schlüssel sichtbar ist.](/Images/KN02/AWS_KN02_EC2_KEY_USED.png)