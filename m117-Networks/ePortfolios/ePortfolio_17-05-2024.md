# ePortfolio_17_05_2024.md

## Ethernetverkabelung Aufgaben

### Netzwerktopologien

1. **Welche Topologie entspricht dem historischen Yellowcable (Koaxialkabel) und welche der aktuellen Ethernetverkabelung?**

    Die historische Yellowcable (Koaxialkabel) entspricht der **Bus-Topologie**. Die aktuelle Ethernetverkabelung entspricht hauptsächlich der **Stern-Topologie**.

2. **Was unterscheidet die Baumtopologie von der Meshtopologie?**

    Die **Baumtopologie** ist eine Hierarchie von Stern-Topologien, die mit einem zentralen "Stamm" verbunden sind. Sie hat einen einzelnen Punkt des Versagens (den zentralen Knotenpunkt). Die **Meshtopologie** hingegen hat mehrere Pfade zwischen jedem Knotenpunkt, was Redundanz und Fehlertoleranz bietet.

3. **Was ist der Nachteile einer Stern- oder Baumstruktur?**

    Der Hauptnachteil einer Stern- oder Baumstruktur ist, dass sie einen einzelnen Punkt des Versagens haben. Wenn der zentrale Knotenpunkt (der "Stern" in der Stern-Topologie oder der "Stamm" in der Baumtopologie) ausfällt, wird das gesamte Netzwerk beeinträchtigt.

4. **Welche Netzwerktopologie treffen sie in aktuellen LANs an?**

    In aktuellen LANs trifft man hauptsächlich die **Stern-Topologie** an. Sie wird wegen ihrer Einfachheit und Zuverlässigkeit bevorzugt.

### Kommunikation

5. **Was ist der Vorteil der modernen Ethernetverkabelung gegenüber dem historischen Yellow-Cable?**

    Die moderne Ethernetverkabelung bietet höhere Datenübertragungsraten, bessere Skalierbarkeit, einfacheres Kabelmanagement und verbesserte Fehlertoleranz im Vergleich zum historischen Yellow-Cable.

6. **Sie möchten zwei PC’s direkt mit einem Ethernetkabel verbinden. Was muss dabei beachtet werden?**

    Um zwei PCs direkt mit einem Ethernetkabel zu verbinden, benötigen Sie ein **Crossover-Kabel**. Dieses Kabel hat die Senden- und Empfangsadern an einem Ende des Kabels vertauscht, sodass die Sendenader eines PCs mit der Empfangsader des anderen PCs verbunden ist und umgekehrt.

### Regelung der Kommunikation

7. **Was bedeutet die Abkürzung CSMA/CD und was regelt sie?**

    CSMA/CD steht für **Carrier Sense Multiple Access with Collision Detection**. Es ist ein Netzwerkprotokoll, das regelt, wie Netzwerkgeräte kommunizieren, um Kollisionen zu vermeiden. Es ermöglicht mehreren Geräten, dasselbe Übertragungsmedium zu teilen, indem es sicherstellt, dass nur ein Gerät zu einem bestimmten Zeitpunkt sendet.

8. **Was versteht man unter Kollisionen und warum muss jedes Gerät die Leitung ständig auf solche abhören?**

    Eine **Kollision** tritt auf, wenn zwei oder mehr Geräte gleichzeitig auf demselben Netzwerkkanal senden. Jedes Gerät muss die Leitung ständig auf Kollisionen abhören, um sicherzustellen, dass es nicht gleichzeitig mit einem anderen Gerät sendet. Wenn eine Kollision erkannt wird, stoppt das Gerät das Senden und versucht es nach einer zufälligen Verzögerung erneut.

9. **Warum muss bei einem Rückzug infolge Kollisionsentdeckung ein Zufallszeit abgewartet werden, bis ein erneuter Sendeversuch gestartet werden kann?**

    Die Zufallszeit wird verwendet, um zu verhindern, dass die Geräte gleichzeitig erneut zu senden versuchen (was zu einer weiteren Kollision führen würde). Durch das Warten einer zufälligen Zeit wird sichergestellt, dass jedes Gerät zu einem etwas anderen Zeitpunkt erneut zu senden versucht, wodurch die Wahrscheinlichkeit einer erneuten Kollision verringert wird.

### Materialwahl für das Übertragungsmedium

10. **Worin liegen die Unterschiede von Draht, Litze oder Glas (Vor-, Nachteile)?**

    **Draht** (massiver Draht) hat eine hohe Zugfestigkeit und ist gut für lange, gerade Strecken geeignet, kann aber bei häufiger Biegung brechen. **Litze** (mehrere dünne Drähte) ist flexibler und widersteht dem Brechen bei häufiger Biegung, hat aber eine geringere Zugfestigkeit. **Glas** (wie in Glasfaserkabeln verwendet) ist immun gegen elektromagnetische Interferenzen und ermöglicht sehr hohe Datenübertragungsraten über große Entfernungen, ist aber teurer und weniger robust als Draht oder Litze.

11. **Wo setzt man welches Material ein?**

    Massiver Draht wird oft in fest verlegten Netzwerkkabeln verwendet. Litze wird in Patchkabeln und anderen Anwendungen verwendet, wo Flexibilität erforderlich ist. Glasfaser wird in Anwendungen verwendet, die hohe Datenübertragungsraten über große Entfernungen erfordern, wie z.B. in Backbone-Netzwerken.

12. **Welches Medium hat am meisten Potential bezüglich der Datenübertragungsrate?**

    **Glasfaser** hat das größte Potenzial hinsichtlich der Datenübertragungsrate. Es kann Daten über große Entfernungen mit Geschwindigkeiten von bis zu mehreren Terabits pro Sekunde übertragen.

### Störeinflüsse und Abwehrmassnahmen

13. **Nennen sie ein paar potentielle elektromagnetische oder elektrostatische Störungsquellen.**

    Potenzielle Quellen für elektromagnetische oder elektrostatische Störungen können Motoren, Generatoren, Leuchtstofflampen, Funkgeräte, Mobiltelefone und andere elektronische Geräte sein.

14. **Warum ist der Lichtleiter (Glasfaser) von elektromagnetischen und elektrostatischen Störeinflüssen kaum betroffen?**

    Glasfaser ist kaum von elektromagnetischen und elektrostatischen Störeinflüssen betroffen, weil sie Licht anstelle von elektrischen Signalen zur Datenübertragung verwendet. Lichtsignale sind immun gegen elektromagnetische und elektrostatische Störungen.

15. **Was sind die Nachteile von Glasfasern?**

    Nachteile von Glasfasern sind ihre höheren Kosten, ihre Empfindlichkeit gegenüber physischen Schäden und die Notwendigkeit spezieller Ausrüstung und Fachkenntnisse für die Installation und Wartung.

### Kabelaufbau

16. **Aus wie vielen Adern besteht ein modernes Ethernetkabel?**

    Ein modernes Ethernetkabel besteht in der Regel aus acht Adern, die zu vier verdrillten Paaren zusammengefasst sind.

17. **Beschreiben sie den Kabelaufbau eines S-FTP-Kabels.**

    Ein S-FTP-Kabel (Screened Foiled Twisted Pair) hat vier verdrillte Paare von Kupferdrähten. Jedes Paar ist mit einer Aluminiumfolie abgeschirmt (Foiled), und das gesamte Kabel ist mit einem metallischen Geflecht oder einer Folie abgeschirmt (Screened).

18. **Was unterscheidet die Abschirmungsbezeichnung Shielded von Screened und Foiled?**

    "Shielded" ist ein allgemeiner Begriff, der jede Art von Abschirmung bezeichnet, die dazu dient, elektromagnetische Interferenzen zu reduzieren. "Screened" und "Foiled" sind spezifischere Begriffe, die auf die Art der Abschirmung hinweisen - "Screened" bezieht sich auf eine metallische Abschirmung um das gesamte Kabel, während "Foiled" sich auf eine Folienabschirmung um jedes verdrillte Paar bezieht.

19. **Sie müssen ein Ethernetkabel durch eine Maschinenhalle führen. Welchen Kabeltyp wählen sie?**

    In einer Maschinenhalle, wo es wahrscheinlich viele elektromagnetische Störquellen gibt, wäre ein **geschirmtes Kabel** (z.B. ein S-FTP-Kabel) eine gute Wahl, um die Datenübertragung vor Interferenzen zu schützen.

20. **Zwecks elektronischer Schussauswertung möchten sie auf einem Schiessplatz den Schützenstand mit den Zieltafeln verbinden. Die Distanz beträgt 300 Meter. Welchen Kabeltyp wählen sie?**

    Für eine Distanz von 300 Metern wäre ein **Glasfaserkabel** die beste Wahl. Glasfaserkabel können Daten über große Entfernungen mit sehr hohen Geschwindigkeiten übertragen, weit über das, was mit Kupferkabeln möglich ist. Zudem sind sie immun gegen elektromagnetische Interferenzen, was in einer Umgebung mit vielen elektronischen Geräten von Vorteil sein kann.
