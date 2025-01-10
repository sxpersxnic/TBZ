# PowerShell script to display a toast notification
param($message)
Import-Module BurntToast
New-BurntToastNotification -Text "Test Script", $message
