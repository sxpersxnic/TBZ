# ePortfolio - 06-28-2024

## Teil 2 PCs der Anwaltskanzlei Arista GmbH

- *PLANUNG*

- *IMPLEMENTATION*

- *TESTEN*

### Vorgaben

#### Hostnames

- geH
- geD
- beC
- daJ
- meD
- weL
- guS
- meM
- zoE
- stK

#### Gruppen

- **GL**
Geschäftsleitung: Führt den Betrieb, übernimmt aber auch Rechtsfälle, hat eine Sekretärin zur Seite.

- **ADMINISTRATOREN**
Sys-Admins

- **JEDER**
Gruppe, die alle lokal eingetragenen Windows-Benutzer vereint.

- **RECHNUNGSWESEN**
Rechnungswesen: Regelt die finanziellen Aspekte wie Rechnungsstellung an die Kunden, Lohnauszahlung and die Mitarbeitenden etc.

- **SEKRETARIAT**
Sekretariat: Unterstützt das juristische Personal.

- **R_ANWALT** & **NOTAR**
Rechtsanwält:in: Sind die juristisch ausgebildeten Mitarbeiter:innen, die die Mandate betreuen. Rechtsanwält:innen und Notar:innen werden durch das Sekretariat unterstützt.

- **VERWALTUNG**
Verwaltung: Verwaltet und koordiniert die Dateiablage, insbesondere das Dateiarchiv, erledit die Personalabteilung wie Lohnbuchhaltung etc.

- **VRKF_ACQSTN**
Verkauf, Acquisition: Sorgt für neue Kundschaft bzw. Mandate, Werbung.

### Benutzer und Gruppen erstellen unter Windows 10 (CMD)

#### Erstellen von Users

```cmd
net user geH /add
net user geD /add
net user beC /add
net user daJ /add
net user meD /add
net user weL /add
net user guS /add
net user meM /add
net user zoE /add
net user stK /add
``` 

#### Erstellen von Benutzergruppen

```cmd
net localgroup "GL Geschäftsleitung" /add
net localgroup "ADMINISTRATOREN Sys-Admins" /add
net localgroup "JEDER" /add
net localgroup "RECHNUNGSWESEN Rechnungswesen" /add
net localgroup "SEKRETARIAT Sekretariat" /add
net localgroup "R_ANWALT & NOTAR Rechtsanwält:in" /add
net localgroup "VERWALTUNG Verwaltung" /add
net localgroup "VRKF_ACQSTN Verkauf, Acquisition" /add
```

#### Benutzer zu Gruppen hinzufügen

```cmd
net localgroup "GL Geschäftsleitung" geH /add
net localgroup "ADMINISTRATOREN Sys-Admins" geD /add
net localgroup "JEDER" geH /add
net localgroup "JEDER" geD /add
net localgroup "RECHNUNGSWESEN Rechnungswesen" beC /add
net localgroup "SEKRETARIAT Sekretariat" daJ /add
net localgroup "R_ANWALT & NOTAR Rechtsanwält:in" meD /add
net localgroup "VERWALTUNG Verwaltung" weL /add
net localgroup "VRKF_ACQSTN Verkauf, Acquisition" guS /add
```

### Berechtigungen verwalten unter Windows 10 (CMD)

#### Verwaltungskonsole öffnen

```WIN + R``` > lusrmgr.msc 

- Benutzer zu Gruppen hinzufügen
- Rechte und Berechtigungen vergeben

#### Ordnerberechtigungen setzen

1. Im Beispiel werden für den Ordner xy Berechtigungen gesetzt.

2. Mit Rechtsklick auf Ordner xy > Eigenschaften/Properties

3. Sicherheit > Bearbeiten

4. Benutzer/Gruppen hinzufügen und Rechte/Berechtigungen verwalten.
