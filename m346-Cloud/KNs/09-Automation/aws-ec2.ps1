$InstanceNameTag = "WebServer-KN09"  
$LaunchTemplateId = "lt-067af6ea39b913777"
$LogFile = ".\aws-automation.log"

New-Item $LogFile
Start-Transcript -Path $LogFile -Append
Write-Host "==== AWS CLI Automation Script startet ====" -ForegroundColor Green

Write-Host "==== Step 1: Checking for Instance ID (WebServer-KN09) ====" -ForegroundColor Green
$DescribeInstancesOutput = aws ec2 describe-instances --query "Reservations[].Instances[?Tags[?Key=='Name' && Value=='$InstanceNameTag']].[InstanceId]" --output text
$InstanceId = $DescribeInstancesOutput.Trim()

if (-Not $InstanceId) {
    Write-Host "ERROR: No instance with Tag-Name '$InstanceNameTag' found." -ForegroundColor Red
    Stop-Transcript
    Exit 1
}

Write-Host "Found Instance ID: $InstanceId" -ForegroundColor Green

Write-Host "==== Step 2: Stopping Instance (WebServer-KN09) ====" -ForegroundColor Green
Write-Host "Trying to stop instance with ID: $InstanceId..."
aws ec2 stop-instances --instance-ids $InstanceId | Write-Host
Write-Host "Instance was stopped. Check status with following command:" -ForegroundColor Blue
Write-Host "aws ec2 describe-instances --instance-ids $InstanceId"

Start-Sleep -Seconds 60

Write-Host "==== Step 3: Start of instance (WebServer-KN09) ====" -ForegroundColor Green
Write-Host "Starting instance with ID: $InstanceId..."
aws ec2 start-instances --instance-ids $InstanceId | Write-Host
Write-Host "Instance was started. Check status with following command:" -ForegroundColor Blue
Write-Host "aws ec2 describe-instances --instance-ids $InstanceId"

Start-Sleep -Seconds 60

Write-Host "==== Step 4: Creating new instance with Launch Template (DatabaseServer) ====" -ForegroundColor Green
Write-Host "Creating new instance with Launch Template ID: $LaunchTemplateId" -ForegroundColor Yellow

aws ec2 run-instances `
  --launch-template LaunchTemplateId=$LaunchTemplateId | Write-Host

Write-Host "New instance launched successfully. Check details of the new instance." -ForegroundColor Blue

Write-Host "==== Step 5: Testing connection on Port 3306 (DatabaseServer) ====" -ForegroundColor Green

$PublicIpAddress = aws ec2 describe-instances --query "Reservations[].Instances[?Tags[?Key=='Name' && Value=='DatabaseServer'] && State.Name=='running'].[PublicIpAddress]" --output text
Write-Host "Found Public IP Address: $PublicIpAddress" -ForegroundColor Blue
Write-Host "telnet $PublicIpAddress 3306"
telnet $PublicIpAddress 3306

Write-Host "==== Done ====" -ForegroundColor Green

Stop-Transcript
