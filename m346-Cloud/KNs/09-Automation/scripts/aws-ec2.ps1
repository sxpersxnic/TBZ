$InstanceNameTag = "webserver"
$LaunchTemplateId = "lt-0da41b3d69b36b110"
$LogFile = "..\logs\aws-automation.log"
$PublicDnsAddress = "ec2-3-218-44-99.compute-1.amazonaws.com"

Write-Host "==== AWS CLI Automation Script startet ====" -ForegroundColor Green
Remove-Item $LogFile -ErrorAction SilentlyContinue
New-Item $LogFile
Start-Transcript -Path $LogFile -Append

Write-Host "==== Step 1: Checking for Instance ID (webserver) ====" -ForegroundColor Green
$DescribeInstancesOutput = aws ec2 describe-instances --query "Reservations[].Instances[?Tags[?Key=='Name' && Value=='$InstanceNameTag']].[InstanceId]" --output text
$InstanceId = $DescribeInstancesOutput.Trim()

if (-Not $InstanceId) {
    Write-Host "ERROR: No instance with Tag-Name '$InstanceNameTag' found." -ForegroundColor Red
    Stop-Transcript
    Exit 1
}

Write-Host "Found Instance ID: $InstanceId" -ForegroundColor Green

Write-Host "==== Step 2: Stopping Instance (webserver) ====" -ForegroundColor Green
Write-Host "Trying to stop instance with ID: $InstanceId..."
aws ec2 stop-instances --instance-ids $InstanceId | Write-Host
Write-Host "Instance was stopped." -ForegroundColor Blue
Write-Host "Waiting 60 seconds for instance to shut down..." -ForegroundColor Yellow

Start-Sleep -Seconds 60

Write-Host "==== Step 3: Start of instance (webserver) ====" -ForegroundColor Green
Write-Host "Starting instance with ID: $InstanceId..."
aws ec2 start-instances --instance-ids $InstanceId | Write-Host
Write-Host "Waiting 30 seconds for instance to start up..." -ForegroundColor Yellow
Start-Sleep -Seconds 30
Write-Host "Instance was started." -ForegroundColor Blue

Write-Host "==== Step 4: Creating new instance with Launch Template (databaseserver) ====" -ForegroundColor Green
Write-Host "Creating new instance with Launch Template ID: $LaunchTemplateId" -ForegroundColor Yellow

aws ec2 run-instances `
  --launch-template LaunchTemplateId=$LaunchTemplateId | Write-Host

Write-Host "New instance launched successfully." -ForegroundColor Blue

Write-Host "Waiting 300 seconds for databaseserver to initialize..." -ForegroundColor Yellow

Start-Sleep -Seconds 300

Write-Host "==== Step 5: Testing connection on Port 3306 (databaseserver) ====" -ForegroundColor Green
$PublicIpAddress = aws ec2 describe-instances --query "Reservations[].Instances[?Tags[?Key=='Name' && Value=='databaseserver'] && State.Name=='running'].[PublicIpAddress]" --output text
Write-Host "Found Public IP Address: $PublicIpAddress" -ForegroundColor Blue

Write-Host "Using: $PublicDnsAddress"
ping $PublicDnsAddress

if ($LASTEXITCODE -ne 0) {
    Write-Host "ERROR: Could not ping $PublicDnsAddress" -ForegroundColor Red
    Stop-Transcript
    Exit 1
}

Write-Host "telnet $PublicDnsAddress 3306"
telnet $PublicDnsAddress 3306

Write-Host "==== Done ====" -ForegroundColor Green
Stop-Transcript
