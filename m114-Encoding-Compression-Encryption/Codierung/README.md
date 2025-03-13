# Aufgaben Codierung

1. Ergänzen Sie die Tabelle. Studieren Sie anschliessend Ihre fertig ausgefüllte Tabelle, insbesondere die Kolonnen mit den Binärwerten. Was stellen Sie fest?

|DEC|HEX|BIN (8)|BIN (4)|BIN (2)|BIN (1)|
|---|---|-------|-------|-------|-------|
|0|0|0|0|0|0|
|1|1|0|0|0|1|
|2|2|0|0|1|0|
|3|3|0|0|1|1|
|4|4|0|1|0|0|
|5|5|0|1|0|1|
|6|6|0|1|1|0|
|7|7|0|1|1|1|
|8|8|1|0|0|0|
|9|9|1|0|0|1|
|10|A|1|0|1|0|
|11|B|1|0|1|1|
|12|C|1|1|0|0|
|13|D|1|1|0|1|
|14|E|1|1|1|0|
|15|F|1|1|1|1|

**Schlussfolgerung:** Das Dezimalsystem beruht auf Basis 10, das Hexadezimalsystem auf Basis 16 und das Binärsystem auf Basis 2.

2. Wandeln Sie die folgende Dezimalzahl ohne Taschenrechner in die entsprechende Binärzahl um:

- **Dezimal:** 911
- **Binär:** 0011 1000 1111

3. Wandeln Sie die folgende Binärzahl ohne Taschenrechner in die entsprechende Dezimalzahl um:

- **Binär:** 1011 0110
- **Dezimal:** 182

4. Wandeln Sie die folgende Binärzahl ohne Taschenrechner in die entsprechende Hexadezimalzahl um:

- **Binär:** 1110 0010 1010 0101
- *Dezimal:* 58'021
- **Hex:** E2A5

5. Was ergibt die Addition der beiden binären Zahlen **1101 1001** und **0111 0101**? Beachten Sie, dass für das Resultat ebenfalls nur zur Verfügung stehen.

Nutzt man die selbe Technik, wie für das schriftliche Addieren, ist es kinderleicht. Da das nächste *Bit*, das doppelte des betrachteten *Bit* ist, kann man es sich so merken: Sind beide Bits von Zahl-A und Zahl-B 1 oder 0, schreibt man 0 und rechnet beim nächsten *Bit* mit einem *Bit* an der Stelle mehr. Ist nur ein *Bit* der Zahl-A oder Zahl-B auf 1, schreibt man 1.

|    |    |    |
|----|----|----|
|0000|1101|1001|
|0000|0111|0101|
|**0001**|**0100**|**1110**|

6. Was könnten die beiden folgenden binären Wert für eine Bedeutung haben?

**a.** 11000000.10101000.01001100.11010011

**b.** 10111110-10000011-10000101-11010101-11100100-11111110

- a. *In Dezimal:* 192.168.76.211

  Ich vermute es handelt sich hierbei um eine private *IPv4-Adresse*. Dies besteht aus *4 Bytes*. Für private Adressen, werden gemäss **RFC 1918** 172er, 192er und 10er Netwerke genutzt. In diesem Beispiel haben wir leider keine Subnetzmaske oder CIDR Notierung, deshalb können wir nicht genau sagen, wie gross das Netz ist. Geht man jedoch von der üblichen Netz grösse aus, ist die Subnetmaske *255.255.255.0* oder */24*. Somit ist unsere *Host-Addresse* 211.

- b.
  - *Dezimal:* 190-131-133-213-228-254
  - *Hex:* BE-83-85-D5-E4-FE

  Vermutlich handelt es sich hier um eine **MAC-Adresse**. Dies vermute ich aufgrund folgender Beobachtungen:

  - 48 Bit (Länge einer MAC-Adresse)
  - MAC-Adressen werden auf dem OSI-Layer 2 verwendet.
  - Auch den Hersteller kann man von der MAC-Adresse ableiten. In diesem Fall handelt es sich womöglich um Microsoft, jedoch konnte ich nur die Range BC:83:85:00:00:00 - BC:83:85:00:00:00 finden.
  
7. Was könnte die folgende in einem Bash-Script entdeckte
Zeile für eine Bedeutung haben? **chmod 751 CreateWeeklyReport**

Das Oktale Zahlensystem wird unter Linux für Berechtigungen verwendet.

- 7 -> 111 (Owner: Lesen, Schreiben, Ausführen)
- 5 -> 101 (Group: Lesen, Ausführen)
- 1 -> 001 (Others: Nur Ausführen)

8. Dimensionieren Sie für den Matterhorn-Express, wo insgesamt 107 Gondeln die Touristen von Zermatt auf den Trockenen-Steg befördern, die Codebreite des Binärcodes für die Kabinenzählung.

- Dec: 107 = Bin: 0110 1011
- Es werden *7* Bits gebraucht um die Zahl 107 darzustellen. Jedoch werden Zahlen im Binärsystem als volle Bytes dargestellt.

9. *Zusatzaufgabe:* Sie untersuchen einen Arbeitsspeicher mit 12-Bit-Adress- bzw. 16-Bit-Datenbus.
Welche Speicherkapazität in *kiB* besitzt dieser? *(Hinweis: 1kiB = 1024B)*

**Antwort:**

10. *Zusatzaufgabe:* Zwei Geräte sind mit einer seriellen Leitung und zusätzlichem Taktsignal verbunden. Das Taktsignal beträgt **1MHz**.

- a. Wie viele Bytes können damit pro Sekunde übertragen werden?

**Antwort:**

- b. Wie viele Bytes pro Sekunde könnten übertragen werden, wenn die Verbindung der beiden Geräte nicht seriell, sondern **8 Bit-parallel** wäre?

**Antwort:**

11. 