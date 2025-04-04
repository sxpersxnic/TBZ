# Kompression

1. **Huffman-Algorithmus:** Wir beschäftigen uns hier mit sogenannten Baumstrukturen. Kennen sie noch andere Gebiete in der IT, wo Baumstrukturen zur Anwendung kommen? Beim Huffman handelt es sich sogar um eine spezielle Baumstruktur, nämlich einem sogenannten binären Baum. Was unterscheidet einen binären Baum von einem nicht binären Baum?

    - **Weitere Anwendungen der Baumstruktur:**
        - Dateisysteme
        - Suchalgorithmen
    - **Unterschied binären Baum vs. nicht binären Baum:**
        Eine *Node* in einem binären Baum hat **maximal** zwei *children/leafs*. Hingegen ein nicht binärer Baum kein **maximum** an *children/leafs* hat.

2. **Huffman-Algorithmus** In dieser Aufgabe arbeiten sie zu zweit: Jeder denkt sich ein Wort mit ca. 15 Buchstaben aus und erstellt dazu den Huffman-Code inkl. Codetabelle und das entsprechend komprimierte Wort in Binärdarstellung. Tauschen sie ihre Codes und Codetabellen gegenseitig aus und vergewissern sie sich, dass ihr Partner ihr gewähltes Wort richtig dekomprimieren kann. Sie haben die Aufgabe dann vollständig gelöst, wenn sie einen korrekten binären Baum vorweisen können, die Codes herausgelesen und tabellarisch notiert haben und das komprimierte Wort in Huffman-Binärcode nicht fehlt.

    - **Wort:** Dampfschiff
    - **Huffman-Code:**
        - D  A  M  P  F  S  C  H  I  F  F
        - 01 02 03 04 05 06 07 08 09 10 11
    - **Binärdarstellung:**

3. **RLC**

4. **RLC:** Sie erhalten diesen RL-Code: 010100011110010010010010010010010010010110010110010010010010010010010010001 Folgendes ist ihnen dazu bekannt: Es handelt sich um eine quadratische Schwarz-Weiss-Rastergrafik mit einer Kantenlänge von 8 Pixel. Es wird links oben mit der Farbe Weiss begonnen. Eine Farbe kann sich nicht mehr als siebenmal wiederholen. Zeichnen sie die Grafik auf. Was stellt sie dar?

    - **White:** '-'
    - **Black:** 'o'

    | Bits| Decs | Val. |
    |-----|------|------|
    | 010 | 2 | -- |
    | 100 | 4 | oooo |
    | 011 | 3 | --- |
    | 110 | 6 | oooooo |
    | 010 | 2 | -- |
    | 010 | 2 | oo |
    | 010 | 2 | -- |
    | 010 | 2 | oo |
    | 010 | 2 | -- |
    | 010 | 2 | oo |
    | 010 | 2 | -- |
    | 010 | 2 | oo |
    | 010 | 2 | -- |
    | 110 | 6 | oooooo |
    | 010 | 2 | -- |
    | 110 | 6 | oooooo |
    | 010 | 2 | -- |
    | 010 | 2 | oo |
    | 010 | 2 | -- |
    | 010 | 2 | oo |
    | 010 | 2 | -- |
    | 010 | 2 | oo |
    | 010 | 2 | -- |
    | 010 | 2 | oo |
    | 001 | 1 | - |

    **Result:**

    |0|1|2|3|4|5|6|7|8|
    |-|-|-|-|-|-|-|-|-|
    |**1**|-|-|o|o|o|o|-|-|
    |**2**|-|o|o|o|o|o|o|-|
    |**3**|-|o|o|-|-|o|o|-|
    |**4**|-|o|o|-|-|o|o|-|
    |**5**|-|o|o|o|o|o|o|-|
    |**6**|-|o|o|o|o|o|o|-|
    |**7**|-|o|o|-|-|o|o|-|
    |**8**|-|o|o|-|-|o|o|-|

## Huffman-Speed

    |Char|Häufigkeit|
    |----|----------|
    |E|2|
    |S|2|
    |U|2|
    |B|1|
    |R|1|
    |U|1|
    |F|1|
    |C|1|
    |H|1|
    |L|1|

    - (B,1) + (R,1) -> (BR,2)
    - (F,2) + (C,1) -> (FC,2)
    - (H,1) + (L,1) -> (HL,2)
    - (BR,2) + (FC,2) -> (BRFC,4)
    - (HL,2) + (E,2) -> (HLE,4)
    - (S,2) + (U,2) -> (SU,4)
    - (HLE,4) + (SU,4) -> (HLESU,8)
    - (BRFC,4) + (HLESU,8) -> (BRFCHLESU,12)

    ```css
            (BRFCHLESU,12)
            /              \
    (BRFC,4)         (HLESU,8)
    /       \        /         \
    (BR,2)   (FC,2)  (HLE,4)    (SU,4)
    /   \     /   \   /   \      /   \
    B(1) R(1) F(1) C(1) H(1) L(1) S(2) U(2)
                    /   \
                    E(2)   
    ```

    ```css
            12
            / \ 
            /   \ 
            /     \ 
        /       \ 
        /         \
        4           8
        / \         / \
    /   \       /   \
    2     2     4     4
    /  \  /  \   /  \ / \
    1    1 1   1 2  2  2  2
                        / \
                        1   1

            12
        /   \
        /     \
        4       8
            /  \
            4    4
            / \  / \
            2  2 2   2
                    / \
                1   1
        12
        /
    4               8
    /     \         /     \  
    2       2       4       4
    / \     /   \   /   \   /   \
    1   1   1   1   2   2   2   2
        

    2  4   2   2   2 
    | / \ / \ / \ / \
    E U S B R F C H L 
    2 2 2 1 1 1 1 1 1
    ```

    Erstellen sie jetzt den Huffman-Baum für das 12 Buchstaben lange Wort BERUFSSCHULE.

5. **LZW-Verfahren**

6. **BWT Burrows-Wheeler-Transformation**
    - a. Erstellen Sie die BWT-Transformation für das Wort ANANAS und überprüfen Sie mit der Rücktransformation ihr Resultat.

    ANANAS
    SANANA
    ASANAN
    NASANA
    ANASAN
    NANASA

    1. Rotation

        |**A**|N|A|N|A|S|
        |-----|-|-|-|-|-|
        |S|**A**|N|A|N|A|
        |A|S|**A**|N|A|N|
        |N|A|S|**A**|N|A|
        |A|N|A|S|**A**|N|
        |N|A|N|A|S|**A**|

    2. Alphabetisch sortieren

        |**A**|N|A|N|A|S|
        |-----|-|-|-|-|-|
        |A|N|A|S|**A**|N|
        |A|S|**A**|N|A|N|
        |N|A|N|A|S|**A**|
        |N|A|S|**A**|N|A|
        |S|**A**|N|A|N|A|

    3. Letzte Spalte und Position des Originals werden übermittelt

        |A|N|A|N|A|**S**|
        |-|-|-|-|-|-----|
        |A|N|A|S|A|**N**|
        |A|S|A|N|A|**N**|
        |N|A|N|A|S|**A**|
        |N|A|S|A|N|**A**|
        |S|A|N|A|N|**A**|

    **return: SNNAAA1 | S2N3A1**

    - b. Sie erhalten den Code **IICRTGH6** in der Burrows-Wheeler-Transformation. Welches Wort verbirgt sich dahinter?

    |C|X|X|X|X|X|I|
    |G|R|I|C|X|X|I|
    |H|X|X|X|X|X|C|
    |I|C|X|X|X|X|R|
    |I|G|R|I|C|H|T|
    |R|I|C|X|X|X|G| <-
    |T|I|G|R|I|C|H|

    | | | | | | |     |
    |-|-|-|-|-|-|-----|
    |C|H|T|I|G|R|**I**|
    |G|R|I|C|H|T|**I**|
    |H|T|I|G|R|I|**C**|
    |I|C|H|T|I|G|**R**|
    |I|G|R|I|C|H|**T**|
    |**R**|**I**|**C**|**H**|**T**|**I**|**G**|
    |T|I|G|R|I|C|**H**|

    **Antwort: RICHTIG**
