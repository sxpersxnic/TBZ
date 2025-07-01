# KN09

## A. Automatisierung mit CLI

![Screenshot der Details der Instanz, die Sie stoppen und starten](../../x-res/09/CLI.png)

- [Script](./scripts/aws-ec2.ps1)
- [Log](./logs/aws-automation.log)

![Screenshot der Details der neu-erstellten Instanz](../../x-res/09/NEW-INSTANCE.png)

![Screenshot des Befehls telnet ihre-IP 3306](../../x-res/09/TELNET.png)

- **KN05 Automation:**
  - [Script](./scripts/kn05-automation.ps1)
  - [Log](./logs/kn05-automation.log)

## B. Terraform

- [Terraform](./terraform)
- [Terraform Config](./terraform/main.tf)
- Terraform telnet:
  ![Telnet Command](../../x-res/09/TERRAFORM-TELNET.png)
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
