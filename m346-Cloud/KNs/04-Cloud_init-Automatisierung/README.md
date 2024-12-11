# KN04 06.12.2024 #

## A) Cloud-init ##

**Dokumenterte YAML-Datei:** [File](./Cloud-Init/Cloud-init.yml)

## B) SSH-Key und Cloud-init ##

- [Config-File](./Cloud-Init/aws_2.yml)
  
- Screenshot "Key pair assigned at launch":
  
    ![Key pair assigned at launch](/m346-Cloud/Images/KN04/KEY-ASSIGNED-AT-LAUNCH.png)
  
- Screenshot SSh-Cmd mit erstem Schlüssel
  
    ![Screenshot SSh-Cmd mit erstem Schlüssel](/m346-Cloud/Images/KN04/SSH-CMD-KEY-1.png)
  
- Screenshot SSh-Cmd mit zweitem Schlüssel
  
    ![Screenshot SSh-Cmd mit zweitem Schlüssel](/m346-Cloud/Images/KN04/SSH-CMD-KEY-2.png)
  
- Screenshot Cloud-Init-Log
  
    ![Screenshot Cloud-Init-Log](/m346-Cloud/Images/KN04/CLOUD-INIT-LOG.png)

## C) Template ##

[YML Template](./Cloud-Init/template.yml)

## D) Installation automatisieren ##

### Webserver

**cloud-init-web.yml:** [Here](/m346-Cloud/KNs/04-Cloud_init-Automatisierung/Cloud-Init/cloud-init-web.yml)

### DB Server

**cloud-init-db.yml:** [Here](/m346-Cloud/KNs/04-Cloud_init-Automatisierung/Cloud-Init/cloud-init-db.yml)
