$LogFile = "..\logs\kn05-automation.log"

$WebCloudInit = "file:..\cloud-init\cloud-init-web.yml"
$DbCloudInit = "file:..\cloud-init\cloud-init-db.yml"
$ImageId = "ami-04b4f1a9cf54c11d0"

Remove-Item $LogFile -ErrorAction SilentlyContinue
New-Item $LogFile
Start-Transcript -Path $LogFile -Append
Write-Host "==== AWS CLI KN05 Automation Script startet ====" -ForegroundColor Green

#TODO: <-- Script -->

Write-Host "==== Creating VPC ====" -ForegroundColor Green
$VpcCidrBlock = "172.0.0.0/16"
$Vpc = aws ec2 create-vpc --cidr-block $VpcCidrBlock --query 'Vpc.VpcId' --output text
Write-Host "VPC created with ID: $Vpc" -ForegroundColor Green

Write-Host "==== Creating Subnet ====" -ForegroundColor Green
$SubnetCidrBlock = "172.0.0.0/24"
$Subnet = aws ec2 create-subnet --vpc-id $Vpc --cidr-block $SubnetCidrBlock --query 'Subnet.SubnetId' --output text
Write-Host "Subnet created with ID: $Subnet" -ForegroundColor Green

Write-Host "==== Creating Elastic-IP ====" -ForegroundColor Green
ElasticIp = aws ec2 allocate-address --domain vpc --query "PublicIp" --output text
Write-Host "Elastic IP created: $ElasticIp" -ForegroundColor Green

Write-Host "==== Creating Webserver Security-group ====" -ForegroundColor Green
Write-Host "Allowing:" -ForegroundColor Blue
Write-Host "  - SSH: Port 22 | 0.0.0.0" -ForegroundColor Blue
Write-Host "  - HTTP: Port 80 | 0.0.0.0" -ForegroundColor Blue
Write-Host "  - HTTPS: Port 443 | 0.0.0.0" -ForegroundColor Blue
WebSecurityGroup = aws ec2 create-security-group --group-name WebserverSecurityGroup --description "Webserver" --vpc-id $Vpc --query "GroupId" --output text
aws ec2 authorize-security-group-ingress --group-id $WebSecurityGroup --protocol tcp --port 22 --cidr 0.0.0.0/0
aws ec2 authorize-security-group-ingress --group-id $WebSecurityGroup --protocol tcp --port 80 --cidr 0.0.0.0/0
aws ec2 authorize-security-group-ingress --group-id $WebSecurityGroup --protocol tcp --port 443 --cidr 0.0.0.0/0
Write-Host "Webserver Security-group created with ID: $WebSecurityGroup" -ForegroundColor Green

Write-Host "==== Creating Databaseserver Security-group ====" -ForegroundColor Green
Write-Host "Allowing:" -ForegroundColor Blue
Write-Host "  - SSH: Port 22 | 0.0.0.0" -ForegroundColor Blue
Write-Host "  - MySQL: Port 3306 | $ElasticIp" -ForegroundColor Blue
DbSecurityGroup = aws ec2 create-security-group --group-name DatabaseserverSecurityGroup --description "Databaseserver" --vpc-id $Vpc --query "GroupId" --output text
aws ec2 authorize-security-group-ingress --group-id $WebSecurityGroup --protocol tcp --port 22 --cidr 0.0.0.0/0
aws ec2 authorize-security-group-ingress --group-id $WebSecurityGroup --protocol mysql --port 3306 --cidr $ElasticIp
Write-Host "Webserver Security-group created with ID: $DbSecurityGroup" -ForegroundColor Green

#TODO: <-- Create Network interface for Webserver with private IP 172.0.0.10 and Elastic IP -->
#TODO: <-- Create Network interface for Databaseserver with private IP 172.0.0.20 and no public IP -->
Write-Host "==== Launching Databaseserver ====" -ForegroundColor Green
#TODO: <-- Assign Network interface to Databaseserver -->
Databaseserver = aws ec2 run-instances --image-id ami-0c55b159cbfafe1f0 --instance-type t2.micro --key-name m346 --security-group-ids $DbSecurityGroup --subnet-id $Subnet --user-data $DbCloudInit --query "Instances[0].InstanceId" --output text
Write-Host "Databaseserver launched with ID: $Databaseserver" -ForegroundColor Green

Write-Host "==== Launching Webserver ====" -ForegroundColor Green
#TODO: <-- Assign Network interface to Webserver -->
Webserver = aws ec2 run-instances --image-id ami-0c55b159cbfafe1f0 --instance-type t2.micro --key-name m346 --security-group-ids $WebSecurityGroup --subnet-id $Subnet --user-data $WebCloudInit --query "Instances[0].InstanceId" --output text
Write-Host "Webserver launched with ID: $Webserver" -ForegroundColor Green

# Write-Host "==== Assigning Elastic IP to Webserver ====" -ForegroundColor Green
# aws ec2 associate-address --instance-id $Webserver --allocation-id $ElasticIp
# Write-Host "Elastic IP assigned to Webserver" -ForegroundColor Green

Write-Host "==== Rebooting Webserver ====" -ForegroundColor Green
aws ec2 reboot-instances --instance-ids $Webserver
Start-Sleep -Seconds 60
Write-Host "Webserver rebooted" -ForegroundColor Green

Write-Host "==== Ping servers ====" -ForegroundColor Green
DatabaseserverIp = aws ec2 describe-instances --instance-ids $Databaseserver --query "Reservations[0].Instances[0].PublicIpAddress" --output text
WebserverIp = aws ec2 describe-instances --instance-ids $Webserver --query "Reservations[0].Instances[0].PublicIpAddress" --output text
Write-Host "Pinging Databaseserver: $DatabaseserverIp" -ForegroundColor Blue
ping $DatabaseserverIp
Write-Host "Pinging Webserver: $WebserverIp" -ForegroundColor Blue
ping $WebserverIp

Write-Host "==== Done ====" -ForegroundColor Green

Stop-Transcript