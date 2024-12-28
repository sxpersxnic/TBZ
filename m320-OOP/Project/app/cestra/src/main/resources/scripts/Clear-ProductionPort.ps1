Set-Alias PORTNUMBER 161

Set-Alias PID netstat -ano | findstr :$PORTNUMBER 
taskkill /PID $PID /F

