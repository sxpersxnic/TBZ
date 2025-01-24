# KN09 24.01.2025 #

## A) Automatisierung mit CLI ##

![Screenshot der Details der Instanz, die Sie stoppen und starten](/m346-Cloud/Images/KN09/CLI.png)

- Script: [Here](./scripts/aws-ec2.ps1)
- Log: [Here](./logs/aws-automation.log)

![Screenshot der Details der neu-erstellten Instanz](/m346-Cloud/Images/KN09/NEW-INSTANCE.png)

![Screenshot des Befehls telnet ihre-IP 3306](/m346-Cloud/Images/KN09/TELNET.png)

- KN05 Automation:
    - [script](./scripts/kn05-automation.ps1)
    - [log](./logs/kn05-automation.log)

## B) Terraform ##

- Terraform: [Here](./terraform)
- Terraform config: [Here](./terraform/main.tf)
- Terraform telnet: ![Here](/m346-Cloud/Images/KN09/TERRAFORM-TELNET.png)
- Commands:

    - ```sh
        terraform init # Initialises the directory
      ```

    - ```sh
        terraform fmt # Formats configuration
      ```

    - ```sh
        terraform validate # Validates configuration
      ```

    - ```sh
        terraform apply # Creates infrastructure
      ```

## C) Beliebige Erweiterungen (optional) ##
