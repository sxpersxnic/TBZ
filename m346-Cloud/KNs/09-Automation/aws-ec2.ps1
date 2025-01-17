# AWS CLI Automation Script
# Dokumentiert die Schritte zur Verwaltung von EC2-Instanzen mit AWS CLI.

# Variablen
$InstanceNameTag = "WebServer-KN09"  # Name-Tag der bestehenden Instanz
$LaunchTemplateId = "lt-067af6ea39b913777"  # Launch Template ID für neue Instanz
$LogFile = ".\aws-automation.log"  # Pfad zur Log-Datei

# Log starten
New-Item $LogFile
Start-Transcript -Path $LogFile -Append
Write-Host "==== AWS CLI Automation Script gestartet ====" -ForegroundColor Green

# 1. Finde die Instance ID basierend auf dem Name-Tag
Write-Host "==== Schritt 1: Ermitteln der Instance ID (WebServer-KN09) ====" -ForegroundColor Green
$DescribeInstancesOutput = aws ec2 describe-instances --query "Reservations[].Instances[?Tags[?Key=='Name' && Value=='$InstanceNameTag']].[InstanceId]" --output text
$InstanceId = $DescribeInstancesOutput.Trim()

if (-Not $InstanceId) {
    Write-Host "FEHLER: Keine Instanz mit dem Tag-Namen '$InstanceNameTag' gefunden." -ForegroundColor Red
    Stop-Transcript
    Exit 1
}

Write-Host "Gefundene Instance ID: $InstanceId" -ForegroundColor Green

# 2. Stoppen der Instanz
Write-Host "==== Schritt 2: Stoppen der Instanz (WebServer-KN09) ====" -ForegroundColor Green
Write-Host "Versuche, die Instanz mit der ID $InstanceId zu stoppen..."
aws ec2 stop-instances --instance-ids $InstanceId | Write-Host
Write-Host "Instanz wurde gestoppt. Überprüfen Sie den Status mit folgendem Befehl:" -ForegroundColor Blue
Write-Host "aws ec2 describe-instances --instance-ids $InstanceId"

# Warte, um sicherzustellen, dass die Aktion abgeschlossen ist
Start-Sleep -Seconds 60

# 3. Starten der Instanz
Write-Host "==== Schritt 3: Starten der Instanz (WebServer-KN09) ====" -ForegroundColor Green
Write-Host "Starte die Instanz mit der ID $InstanceId..."
aws ec2 start-instances --instance-ids $InstanceId | Write-Host
Write-Host "Instanz wurde gestartet. Ueberpruefe Sie den Status mit folgendem Befehl:" -ForegroundColor Blue
Write-Host "aws ec2 describe-instances --instance-ids $InstanceId"

# Warte kurz, um sicherzustellen, dass die Aktion abgeschlossen ist
Start-Sleep -Seconds 60

# 4. Erstellen einer neuen Instanz mit Launch Template
Write-Host "==== Schritt 4: Erstellen einer neuen Instanz mit Launch Template (DatenbankServer) ====" -ForegroundColor Green
Write-Host "Erstelle eine neue Instanz mit Launch Template ID: $LaunchTemplateId" -ForegroundColor Yellow

aws ec2 run-instances `
  --launch-template LaunchTemplateId=$LaunchTemplateId | Write-Host

Write-Host "Neue Instanz wurde erfolgreich erstellt. Überprüfen Sie die Details der neuen Instanz." -ForegroundColor Blue

# 5. Telnet-Test vorbereiten
Write-Host "==== Schritt 5: Teste den Zugriff auf Port 3306 (DatenbankServer) ====" -ForegroundColor Green

$PublicIpAddress = aws ec2 describe-instances --query "Reservations[].Instances[?Tags[?Key=='Name' && Value=='DatabaseServer'] && State.Name=='running'].[PublicIpAddress]" --output text
Write-Host "Found Public IP Address: $PublicIpAddress" -ForegroundColor Blue
Write-Host "telnet $PublicIpAddress 3306"
telnet $PublicIpAddress 3306

Write-Host "==== Automatisierung abgeschlossen ====" -ForegroundColor Green

# Log stoppen
Stop-Transcript
