# 1 - Zahlensysteme, numerische Codes

## Zahlensysteme

- **Binärsystem**
  - Basis: 2
  - Zeichen: 0, 1
  - *Use case: Mikroprozessoren*
- **Oktalsystem**
  - Basis: 8
  - Zeichen: 0, 1, 2, 3, 4, 5, 6, 7, 8
  - *Use case: Permissions on UNIX/LINUX*
- **Hexadezimalsystem**
  - Basis: 16
  - Zeichen: 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, A, B, C, D, E, F
  - *Use case: MAC-Addresses, IPv6, Hex-Color-Codes*
- **Dezimalsystem**
  - Basis: 10
  - Zeichen: 0, 1, 2, 3, 4, 5, 6, 7, 8, 9

### Beispiele

|Dezimal|Binär|Oktal|Hex|
|-------|-----|-----|---|
|1|0000 0001|1|1|
|8|0000 1000|10|8|
|16|0001 0000|20|10|
|32|0010 0000|40|20|
|64|0100 0000|100|40|
|128|1000 0000|200|80|
|256|0001 0000 0000|400|100|
|512|0010 0000 0000|1000|200|
|1024|0100 0000 0000|2000|400|

## Aufgaben

- Ergänzen Sie die folgende BIN-DEC-HEX-Zahlentabelle:

  |BIN(MSB)|BIN|BIN|BIN(LSB)|DEC|HEX|
  |--------|---|---|--------|---|---|
  |0|0|0|0|0|0|
  |0|0|0|1|1|1|
  |0|0|1|0|2|2|
  |0|0|1|1|3|3|
  |0|1|0|0|4|4|
  |0|1|0|1|5|5|
  |0|1|1|0|6|6|
  |0|1|1|1|7|7|
  |1|0|0|0|8|8|
  |1|0|0|1|9|9|
  |1|0|1|0|10|A|
  |1|0|1|1|11|B|
  |1|1|0|0|12|C|
  |1|1|0|1|13|D|
  |1|1|1|0|14|E|
  |1|1|1|1|15|F|

- Wandeln Sie die folgende Dezimalzahl ohne Taschenrechner in die entsprechende Binärzahl um:

  - **Dezimal:** 911
  - **Binär:** 0011 1000 1111

- Wandeln Sie die folgende Binärzahl ohne Taschenrechner in die entsprechende Dezimalzahl um:
  - **Binär:** 1011 0110
  - **Dezimal:** 182

- Wandeln Sie die folgende Binärzahl ohne Taschenrechner in die entsprechende Hexadezimalzahl um:

  - **Binär:** 1110 0010 1010 0101
  - *Dezimal:* 58'021
  - **Hex:** E2A5

- Der Addierer einer *ALU* erhält für Zahl-A: 1101 1001 und für Zahl-B: 0111 0101. Was wird man als Resultat erhalten? Erklären Sie!

Nutzt man die selbe Technik, wie für das schriftliche Addieren, ist es kinderleicht. Da das nächste *Bit*, das doppelte des betrachteten *Bit* ist, kann man es sich so merken: Sind beide Bits von Zahl-A und Zahl-B 1 oder 0, schreibt man 0 und rechnet beim nächsten *Bit* mit einem *Bit* an der Stelle mehr. Ist nur ein *Bit* der Zahl-A oder Zahl-B auf 1, schreibt man 1.

|    |    |    |
|----|----|----|
|0000|1101|1001|
|0000|0111|0101|
|**0001**|**0100**|**1110**|

- Sie analysieren mit den **Network-Sniffer Wireshark** Ihren **Network-Traffic**. Sie haben in *OSI-Layer 3* unter anderem folgende Bitkombination herausgelesen: **1100 0000 . 1010 1000 . 0100 1100 . 1101 0011**
Was bedeutet dies? Beantworten Sie diese Frage möglichst umfassend.

*In Dezimal:* 192.168.76.211

Ich vermute es handelt sich hierbei um eine private *IPv4-Adresse*. Dies besteht aus *4 Bytes*. Für private Adressen, werden gemäss **RFC 1918** 172er, 192er und 10er Netwerke genutzt. In diesem Beispiel haben wir leider keine Subnetzmaske oder CIDR Notierung, deshalb können wir nicht genau sagen, wie gross das Netz ist. Geht man jedoch von der üblichen Netz grösse aus, ist die Subnetmaske *255.255.255.0* oder */24*. Somit ist unsere *Host-Addresse* 211.

- Nun wurde Ihre Neugier geweckt und Sie untersuchen erneut Ihr **Netzwerk** mit Wireshark, diesmal aber den *OSI-Layer 2* und finden unter anderem folgende Bitkombination: **1011 1110 - 1000 0011 - 1000 0101 - 1101 0101 - 1110 0100 - 1111 1110** Was bedeutet dies? Beantworten Sie diese Frage möglichst umfassend.

  - *Dezimal:* 190-131-133-213-228-254
  - *Hex:* BE-83-85-D5-E4-FE

Vermutlich handelt es sich hier um eine **MAC-Adresse**. Dies vermute ich aufgrund folgender Beobachtungen:

    - 48 Bit (Länge einer MAC-Adresse)
    - MAC-Adressen werden auf dem OSI-Layer 2 verwendet.
    - Auch den Hersteller kann man von der MAC-Adresse ableiten. In diesem Fall handelt es sich womöglich um Microsoft, jedoch konnte ich nur die Range BC:83:85:00:00:00 - BC:83:85:00:00:00 finden.
