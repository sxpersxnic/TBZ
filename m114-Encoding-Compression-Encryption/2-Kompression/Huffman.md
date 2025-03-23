# Huffman-Codierung

Bei der **Huffman-Codierung** handelt es sich um ein **verlustfreies** Kompressionsverfahren, das besonders bei symbolbasierten Daten genutzt wird. Basierend auf der Idee, **häufigere Zeichen kürzer** und **seltenere Zeichen länger** zu codieren, spart diese Codierung, Speicherplatz.

## Beispiel "HEDGEHOG"

1. Buchstabenhäufigkeit:
  |Char|Count|
  |----|-----|
  |D|1|
  |O|1|
  |H|2|
  |E|2|
  |G|2|

2. Binary-Tree
    1. Node für jedes Zeichen (key) und Häufigkeit (value).
    ```css
    (D, 1)  (O, 1) (H, 2) (E, 2) (G, 2)
    ```
    2. Nodes mit geringsten Häufigkeit verbinden zu neuer Node mit der Summe der Child-Nodes
    ```css
        (DO,2)
        /   \
    (D, 1)  (O, 1) (H, 2) (E, 2) (G, 2)
    ```
    3. Wiederholen, bis nur noch eine Node übrig ist (root).
    ```css
                   (DOHEG|8)
                     /   \
              (DOH|4)     (EG|4)
             / \            / \
        (DO|2) (H|2)    (E|2) (G|2)
         /  \
    (D|1)  (O|1)
    ```

3. Codes zuweisen
  ```css
               0 (DOHEG|8) 1
                  /     \
            (DOH|4)     (EG|4)
            / \             / \
         (DO|2) (H|2)   (E|2)  (G|2)
        / \ 
    (D|1)  (O|1)
 
  D = 000
  O = 001
  H = 01
  E = 10
  G = 11

  # Koordinaten
  D(000) O(001) H(01) E(10) G(11)
  = 000 0001 01 10 11
  = 0000001011011
  ```

