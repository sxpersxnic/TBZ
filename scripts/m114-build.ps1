# Script serves to initialize the working directory of TBZ Module 114

# Set the working directory to the root of the module
Set-Location C:\Users\sxpersxnic\home\workspaces\TBZ\m114-Encoding-Compression-Encryption

# Initialize directories
New-Item -ItemType Directory -Name "A-Daten-codieren" -Force
New-Item -ItemType Directory -Name "B-Daten-komprimieren" -Force
New-Item -ItemType Directory -Name "C-Grundlagen-Kryptografie" -Force
New-Item -ItemType Directory -Name "D-Gesicherte-Datenübertragung" -Force

# Initialize README.md for each directory
New-Item -ItemType File -Name "README.md" -Path ".\" -Force
New-Item -ItemType File -Name "README.md" -Path ".\A-Daten-codieren" -Force
New-Item -ItemType File -Name "README.md" -Path ".\B-Daten-komprimieren" -Force
New-Item -ItemType File -Name "README.md" -Path ".\C-Grundlagen-Kryptografie" -Force
New-Item -ItemType File -Name "README.md" -Path ".\D-Gesicherte-Datenübertragung" -Force

# Initialize the README.md files
Set-Content -Path ".\README.md" -Value "# Module 114 - Encoding, Compression, Encryption"
Set-Content -Path ".\A-Daten-codieren\README.md" -Value "# A - Daten codieren (12 Lektionen)"
Set-Content -Path ".\B-Daten-komprimieren\README.md" -Value "# B - Daten komprimieren (8 Lektionen)"
Set-Content -Path ".\C-Grundlagen-Kryptografie\README.md" -Value "# C - Grundlagen Verschlüsselungsverfahren (Kryptografie) (6 Lektionen)"
Set-Content -Path ".\D-Gesicherte-Datenübertragung\README.md" -Value "# D - Gesicherte Datenübertragung (6 Lektionen)"

# Add subdirectories of each directory
New-Item -ItemType Directory -Name "1-Zahlensysteme-numerische-Codes" -Path ".\A-Daten-codieren" -Force
New-Item -ItemType Directory -Name "2-Alphanumerische-Codes-ASCII-Unicode" -Path ".\A-Daten-codieren" -Force
New-Item -ItemType Directory -Name "3-Zusammengesetzte-Codierung-Barcodes" -Path ".\A-Daten-codieren" -Force
New-Item -ItemType Directory -Name "4-Bildcodierung" -Path ".\A-Daten-codieren" -Force

New-Item -ItemType Directory -Name "1-Verlustlose-Komprimierung" -Path ".\B-Daten-komprimieren" -Force
New-Item -ItemType Directory -Name "2-Verlustbehaftete-Komprimierung" -Path ".\B-Daten-komprimieren" -Force

New-Item -ItemType Directory -Name "1-Symmetrische-Verschlüsselung" -Path ".\C-Grundlagen-Kryptografie" -Force
New-Item -ItemType Directory -Name "2-Asymmetrische-Verschlüsselung" -Path ".\C-Grundlagen-Kryptografie" -Force
New-Item -ItemType Directory -Name "3-Digital-signieren" -Path ".\C-Grundlagen-Kryptografie" -Force

New-Item -ItemType Directory -Name "1-Public-Key-Infrastruktur" -Path ".\D-Gesicherte-Datenübertragung" -Force
New-Item -ItemType Directory -Name "2-Internet-und-Zertifikate" -Path ".\D-Gesicherte-Datenübertragung" -Force
New-Item -ItemType Directory -Name "3-PGP-und-OpenPGP" -Path ".\D-Gesicherte-Datenübertragung" -Force
New-Item -ItemType Directory -Name "4-Sichere-Emails" -Path ".\D-Gesicherte-Datenübertragung" -Force

# Initialize the README.md files for the subdirectories
Set-Content -Path ".\A-Daten-codieren\1-Zahlensysteme-numerische-Codes\README.md" -Value "# 1 - Zahlensysteme, numerische Codes"
Set-Content -Path ".\A-Daten-codieren\2-Alphanumerische-Codes-ASCII-Unicode\README.md" -Value "# 2 - Alphanumerische Codes, ASCII, Unicode"
Set-Content -Path ".\A-Daten-codieren\3-Zusammengesetzte-Codierung-Barcodes\README.md" -Value "# 3 - Zusammengesetzte Codierung, Barcodes"
Set-Content -Path ".\A-Daten-codieren\4-Bildcodierung\README.md" -Value "# 4 - Bildcodierung"

Set-Content -Path ".\B-Daten-komprimieren\1-Verlustlose-Komprimierung\README.md" -Value "# 1 - Verlustlose Komprimierung"
Set-Content -Path ".\B-Daten-komprimieren\2-Verlustbehaftete-Komprimierung\README.md" -Value "# 2 - Verlustbehaftete Komprimierung"

Set-Content -Path ".\C-Grundlagen-Kryptografie\1-Symmetrische-Verschlüsselung\README.md" -Value "# 1 - Symmetrische Verschlüsselung"
Set-Content -Path ".\C-Grundlagen-Kryptografie\2-Asymmetrische-Verschlüsselung\README.md" -Value "# 2 - Asymmetrische Verschlüsselung"
Set-Content -Path ".\C-Grundlagen-Kryptografie\3-Digital-signieren\README.md" -Value "# 3 - Digital signieren"

Set-Content -Path ".\D-Gesicherte-Datenübertragung\1-Public-Key-Infrastruktur\README.md" -Value "# 1 - Public Key Infrastruktur"
Set-Content -Path ".\D-Gesicherte-Datenübertragung\2-Internet-und-Zertifikate\README.md" -Value "# 2 - Internet und Zertifikate"
Set-Content -Path ".\D-Gesicherte-Datenübertragung\3-PGP-und-OpenPGP\README.md" -Value "# 3 - PGP und OpenPGP"
Set-Content -Path ".\D-Gesicherte-Datenübertragung\4-Sichere-Emails\README.md" -Value "# 4 - Sichere Emails"