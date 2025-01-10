Set-Alias PORTNUMBER 8080

Set-Alias PID netstat -ano | findstr :$PORTNUMBER 
taskkill /PID $PID /F

