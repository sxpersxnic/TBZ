# LB3

## Symmetrische Verschlüsselungsverfahren (ROT, Vigenere, XOR, AES)

- **ROT:**
  - **Formel:** *E(x)* = (*x* + *k*) mod 26 (*x* = num. Wert eines Buchstabens & *k* der Verschiebungswert)
  - **Entschlüsselung:** *D(x)* = (*x* - *k*) mod 26
  - **Ablauf:**
    1. Schlüssel *k* wählen (z.B. *k* = 13 bei ROT-13).
    2. Jeden Buchstaben *x* im Klartext durch den Buchstaben ersetzen, der (*x* + *k*) mod 26 entspricht.
    3. Chiffretext entsteht aus den verschobenen Buchstaben.
  - **Kryptoanalyse:**
    - **Brute-Force:** Es gibt nur 25 mögliche Werte für *k*.
    - **Häufigkeitsanalyse:** Typische Buchstaben (z.B. "E") erscheinen häufiger.
- **Vigenère:**
  - **Formel (_ = i-te):** *E(x_i)* = *(x_i + k_i)* mod 26 (wobei *x_i* der i-te Buchstabe des Klartexts und *k_i* der entsprechende Buchstabe des wiederholten Schlüsselworts ist)
  - **Ablauf:**
    1. Schlüsselwort wählen (z.B. "AFFE", wobei A = 0, F = 5, ...).
    2. Schlüsselwort so oft wiederholen, dass es den Klartext abdeckt.
    3. Für jeden Buchstaben *x_i*: *x_i* -> *(x_i + k_i)* mod 26.
  - **Kryptoanalyse:**
    - **Kasiski-Methode:** Suche nach wiederkehrenden Sequenzen im Chiffretext, um die Schlüsselperiodenlänge L zu schätzen.
    - **Friedman-Test:** Berechne den Index of Coincidence *(IC): IC = Σ [fᵢ(fᵢ – 1)]⁄[N(N – 1)]*   (*f_i* = Häufigkeit des i-ten Buchstabens, *N* = Gesamtlänge)
    - Spaltenweise Häufigkeitsanalyse: Jede "Spalte" (alle Zeichen, die mit demselben *k_i* verschlüsselt wurden) wird separat analysiert,  um *k_i* zu rekonstruieren.
  - **Illustration:** ![Vigenére](https://raw.githubusercontent.com/sxperlinx/TBZ/main/m114-Encoding-Compression-Encryption/x-resources/LB/LB3/vigenere.jpg)
- **XOR (Stromchiffre):**
  - **Formel:** *c* = *m* XOR *k*
  - **Entschlüsselung:** *m* = *c* XOR *k*
  - **Ablauf:**
    1. Klartext *m* in Binär umwandeln (z.B. in einer 16-Bit-Darstellung, führende Nullen beachten).
    2. Schlüssel *k* in Binär (z.B. 10001101) konvertieren.
    3. Wiederhole *k*, falls der Klartext länger ist als der Schlüssel.
    4. Bitweise XOR-Operation von *m* und *k* ergibt den Chiffretext *c*.
  - **Kryptoanalyse:**
    - **Known-Plaintext-Angriff:** Falls Teile von *m* bekannt sind, kann *k* = *c* XOR *m* berechnet werden.
    - **Brute-Force:** Bei kurzen Schlüsseln lassen sich alle möglichen *k* testen.

---

## Asymmetrische Verschlüsselungsverfahren (Diffie Hellman Schlüsseltausch, RSA)

- **Diffie Hellman Schlüsseltausch:**
  1. Öffentlich wählen: Grosse Primzahl *p* & Basis (Generator) *g*.
  2. Alice wählt Secret *a* und Bob Secret *b*
  3. Shared keys werden berechnet (^ = Potenz, mod = Modulus) -> *A* = *g*^*a* mod *p* und *B* = *g*^*b* mod *p*.
  4. Shared keys (*A*, *B*) werden ausgetauscht.
  5. Gemeinsamer *Session key* wird generiert -> Alice: *B*^*a* mod *p* und Bob: *A*^*b* mod *p*.

  - **Illustration:** ![Vigenére](https://raw.githubusercontent.com/sxperlinx/TBZ/main/m114-Encoding-Compression-Encryption/x-resources/LB/LB3/diffie-hellman.jpg)

- **RSA:**
  - **Ablauf und Formeln:**
    1. Wähle zwei grosse Primzahlen *p* und *q*.
    2. Berechne: *n* = *p* * *q*
    3. Berechne: *φ(n)* = *(p – 1)(q – 1)*
    4. Wähle einen öffentlichen Exponenten *e*, so dass gcd(e, φ(n)) = 1.
    5. Bestimme den privaten Exponenten *d* mit: e * d ≡ 1 mod *φ(n)*
    6. Verschlüsselung: *c* = *m^e* mod *n*
    7. Entschlüsselung: *m* = *c^d* mod *n* (Hierbei ist *m* die Nachricht, *c* der Chiffretext.)

---

## Vor- und Nachteile SYM - ASYM: Schlüsselanzahl, sicherer Kanal für Schlüsseltausch, Verarbeitungszeit

- **SYM:**
  - **Vorteile:**
  - **Nachteile:**
    - **Schlüsselanzahl:**
      - **Formel:** *S = n(n - 1) / 2* (bei *n* Teilnehmern)
      - **Beispiel:** Für *n* = 1000 -> *S = 1000 * 999 / 2 = **499500*** Schlüssel
    - **Brute-Force-Angriff:**
      - Für ROT: Es gibt 25 mögliche Schlüssel (*k* = 1 bis 25).
      - Für Vigenère: Komplexität ca. 26^L (*L* = Schlüssellänge).
- **ASYM:**
  - **Vorteile:**
    - P-Key für Verschlüsselung und S-Key für Entschlüsselung.
  - **Nachteile:**
    - Bei verfälschtem public key können Dritte mitlesen.

---

- **Kryptoanalyse (Histogramm, Brute-Force):**

- **Hybride Verfahren (RSA/AES):**

- **Digitale Signatur, Hashwert, Hashverfahren:**
  - **Digitale Signatur:**
    - Erzeugung: *s* = *H(m)^d* mod *n*
    - Überprüfung: *m'* = *s^e* mod *n*, vergleiche *m'* mit *H(m)* (Dabei ist *H* eine Hashfunktion, *m* die Nachricht, *d* der private Schlüssel und *e* der öffentliche Schlüssel.)
  - **Hashfunktionen:**
    - Allgemeine Funktion: *H: {0,1}\* -> {0,1}^n*
    - Eigenschaften:
      - Irreversibilität: Es ist praktisch unmöglich, *m* aus *H(m)* zu rekonstruieren.
      - Kollisionsresistenz: Es sollte sehr unwahrscheinlich sein, zwei unterschiedliche *m* und *m'* mit *H(m) = H(m')* zu finden.

- **PKI, X509, Web-of-Trust, Zertifikate, Zertifizierungsstellen, S-MIME vs. OpenPGP:**

- **Sichere Webseite; HTTP vs. HTTPS (TLS/SSL, Zertifikatsüberprüfung):**

- **Unterschiedliche Strenge der CA's bei Vergabe von Zertifikaten:**
