# AWS CLI Automation Script
# Dokumentiert die Schritte zur Verwaltung von EC2-Instanzen mit AWS CLI.

# Variablen
$InstanceNameTag = "WebServer"  # Name-Tag der bestehenden Instanz
$AMI_ID = "<AMI_ID>"                        # Amazon Machine Image ID für die neue Instanz
$KeyName = "aws"                     # Schlüsselname für SSH
$SecurityGroupId = "<SECURITY_GROUP_ID>"    # Sicherheitsgruppen-ID
$CloudInitPath = ".\cloud-init.yml"         # Pfad zur Cloud-Init-Datei

# Überprüfen, ob die Cloud-Init-Datei existiert
if (-Not (Test-Path $CloudInitPath)) {
    Write-Output "FEHLER: Die Cloud-Init-Datei ($CloudInitPath) wurde nicht gefunden. Bitte sicherstellen, dass die Datei existiert."
    Exit 1
}

# 1. Finde die Instance ID basierend auf dem Name-Tag
Write-Output "==== Schritt 1: Ermitteln der Instance ID ===="
$DescribeInstancesOutput = aws ec2 describe-instances --query "Reservations[].Instances[?Tags[?Key=='Name' && Value=='$InstanceNameTag']].[InstanceId]" --output text
$InstanceId = $DescribeInstancesOutput.Trim()

if (-Not $InstanceId) {
    Write-Output "FEHLER: Keine Instanz mit dem Tag-Namen '$InstanceNameTag' gefunden."
    Exit 1
}

Write-Output "Gefundene Instance ID: $InstanceId"

# 2. Stoppen der Instanz
Write-Output "==== Schritt 2: Stoppen der Instanz ===="
Write-Output "Versuche, die Instanz mit der ID $InstanceId zu stoppen..."
aws ec2 stop-instances --instance-ids $InstanceId | Write-Output
Write-Output "Instanz wurde gestoppt. Überprüfen Sie den Status mit folgendem Befehl:"
Write-Output "aws ec2 describe-instances --instance-ids $InstanceId"

# Warte, um sicherzustellen, dass die Aktion abgeschlossen ist
Start-Sleep -Seconds 10

# 3. Starten der Instanz
Write-Output "==== Schritt 3: Starten der Instanz ===="
Write-Output "Starte die Instanz mit der ID $InstanceId..."
aws ec2 start-instances --instance-ids $InstanceId | Write-Output
Write-Output "Instanz wurde gestartet. Überprüfen Sie den Status mit folgendem Befehl:"
Write-Output "aws ec2 describe-instances --instance-ids $InstanceId"

# Warte kurz, um sicherzustellen, dass die Aktion abgeschlossen ist
Start-Sleep -Seconds 10

# 4. Erstellen einer neuen Instanz mit Cloud-Init
Write-Output "==== Schritt 4: Erstellen einer neuen Instanz ===="
Write-Output "Erstelle eine neue Instanz mit folgenden Eigenschaften:"
Write-Output "AMI ID: $AMI_ID, Instanztyp: t2.micro, Sicherheitsgruppe: $SecurityGroupId, Schlüsselname: $KeyName"

aws ec2 run-instances `
  --image-id $AMI_ID `
  --count 1 `
  --instance-type t2.micro `
  --key-name $KeyName `
  --security-group-ids $SecurityGroupId `
  --user-data file://$CloudInitPath | Write-Output

Write-Output "Neue Instanz wurde erfolgreich erstellt. Überprüfen Sie die Details der neuen Instanz."

# 5. Telnet-Test vorbereiten
Write-Output "==== Schritt 5: Teste den Zugriff auf WebServer ===="
Write-Output "Führe folgenden Befehl aus, um den Telnet-Zugriff zu überprüfen:"
Write-Output "telnet <INSTANCE_PUBLIC_IP>"

Write-Output "==== Automatisierung abgeschlossen ===="