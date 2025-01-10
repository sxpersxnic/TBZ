# ePortfolio - 07-05-2024

## CMD Skript
``cmd
@echo off
REM Gruppen erstellen
net localgroup GL /add
net localgroup ADMINISTRATOREN /add
net localgroup RECHNUNGSWESEN /add
net localgroup VERWALTUNG /add
net localgroup VERKAUF /add
net localgroup SEKRETARIAT /add
net localgroup RECHTSANWAELTE /add
 
REM Benutzer erstellen und Gruppen zuweisen
net user geH pass1234 /add /fullname:"Heinz Gerber" /comment:"Geschäftsleitung"
net localgroup GL geH /add
net localgroup RECHTSANWAELTE geH /add
 
net user geD pass1234 /add /fullname:"Heidi Gerber" /comment:"Geschäftsleitungssekretärin"
net localgroup GL geD /add
net localgroup RECHNUNGSWESEN geD /add
net localgroup VERWALTUNG geD /add
 
net user beC pass1234 /add /fullname:"Claire Bertelsmann" /comment:"Windows-System-Administration"
net localgroup ADMINISTRATOREN beC /add
net localgroup VERWALTUNG beC /add
 
net user daJ pass1234 /add /fullname:"Joel D'Alfonso" /comment:"Verkauf"
net localgroup VERKAUF daJ /add
 
net user meD pass1234 /add /fullname:"Dénise Merten" /comment:"Rechnungswesen"
net localgroup RECHNUNGSWESEN meD /add
net localgroup SEKRETARIAT meD /add
 
net user weL pass1234 /add /fullname:"Lara Weber" /comment:"Sekretariat"
net localgroup SEKRETARIAT weL /add
 
net user guS pass1234 /add /fullname:"Susi Gut" /comment:"Sekretariat"
net localgroup SEKRETARIAT guS /add
 
net user meM pass1234 /add /fullname:"Martin Meisterhans" /comment:"Rechtsanwalt"
net localgroup RECHTSANWAELTE meM /add
 
net user zoE pass1234 /add /fullname:"Elise Zopfi" /comment:"Rechtsanwältin"
net localgroup RECHTSANWAELTE zoE /add
 
net user stK pass1234 /add /fullname:"Konrad Strub" /comment:"Notar/Rechtsanwalt"
net localgroup RECHTSANWAELTE stK /add
 
REM Verzeichnisse erstellen und Berechtigungen setzen
mkdir C:\GESCHAEFTSLEITUNG
mkdir C:\ARCHIV
mkdir C:\ALLGEMEIN
mkdir C:\RECHNUNGSWESEN
mkdir C:\VERWALTUNG
mkdir C:\VERKAUF
mkdir C:\SEKRETARIAT
mkdir C:\RECHTSANWAELTE
 
REM Berechtigungen setzen
icacls C:\GESCHAEFTSLEITUNG /grant GL:(OI)(CI)F
icacls C:\GESCHAEFTSLEITUNG /deny *S-1-1-0:(OI)(CI)(IO)F
 
icacls C:\ARCHIV /grant GL:(OI)(CI)R /grant ADMINISTRATOREN:(OI)(CI)F /grant *S-1-1-0:(OI)(CI)R /grant VERWALTUNG:(OI)(CI)F
 
icacls C:\ALLGEMEIN /grant GL:(OI)(CI)F /grant ADMINISTRATOREN:(OI)(CI)F /grant *S-1-1-0:(OI)(CI)R
 
icacls C:\RECHNUNGSWESEN /grant GL:(OI)(CI)R /grant RECHNUNGSWESEN:(OI)(CI)F /grant VERWALTUNG:(OI)(CI)R
 
icacls C:\VERWALTUNG /grant GL:(OI)(CI)R /grant VERWALTUNG:(OI)(CI)F /grant RECHNUNGSWESEN:(OI)(CI)R
 
icacls C:\VERKAUF /grant GL:(OI)(CI)R /grant VERKAUF:(OI)(CI)F
 
icacls C:\SEKRETARIAT /grant GL:(OI)(CI)R /grant SEKRETARIAT:(OI)(CI)F /grant RECHTSANWAELTE:(OI)(CI)R
 
icacls C:\RECHTSANWAELTE /grant GL:(OI)(CI)R /grant RECHTSANWAELTE:(OI)(CI)F /grant SEKRETARIAT:(OI)(CI)R
 
echo Skript abgeschlossen.
``