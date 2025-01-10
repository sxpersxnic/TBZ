# M122 - Aufgabe

2023-02 MUH

## Dateien und Verzeichnisse anlegen

Als Lehrer habe ich den Bedarf, für alle Lernenden einer Klasse 
dieselben Dateien zur Verfügung zu stellen. Sie können sich
auch vorstellen, Sie müssen für eine Anzahl ihrer Kollegen oder
Mitarbeiter einen Satz von gleichen Dateien bereitstellen.

In diesem Beispiel gibt mehrere Klassen. Jede Klasse bekommt ein Verzeichnis.

In diesem Klassenverzeichnis wird für jede Person ein weiteres
Verzeichnis angelegt. Das heisst, dass alle Lernenden ein eigenes Verzeichnis bekommen. Und in diesem Lernenden-Verzeichnis sollen
dann die gleichen Dateien zur Verfügung gestellt werden.

### Endresultat

Es soll nachher eine solche Struktur entstehen:

	gen/M122-AP22b
		Amherd
			Datei-1.txt
			Datei-2.docx
			Datei-3.pdf				
		Baume
			Datei-1.txt
			Datei-2.docx
			Datei-3.pdf
		Keller
			Datei-1.txt
			Datei-2.docx
			Datei-3.pdf

		  usw.
		  
	gen/M122-AP22c
		Arslan
			Datei-1.txt
			Datei-2.docx
			Datei-3.pdf				
		Buehler
			Datei-1.txt
			Datei-2.docx
			Datei-3.pdf
		Camenisch
			Datei-1.txt
			Datei-2.docx
			Datei-3.pdf

		  usw.
		
### Vorgehen

		
Sie erstellen 2 Skripts:


#### Teil 1 "erstelleVorlagen.sh"
 
für die Erstellung des Templateverzeichnis wo nachher mind. 3 Dateien drin stehen und mind. 3 Klassen-Dateien mit einer Liste von mind. 12 Schüler:innen-Nachnamen 

- Machen Sie ein erstes Skript mit dem Namen `erstelleVorlagen.sh` 
  und machen Sie das Skript ausführbar (executable) 
  mit `chmod +x erstelleVorlagen.sh`
- Die erste Zeile im Skript enthält `#!/bin/bash`
- Entwickeln Sie nun Ihren Code der folgendes macht:
	- Erzeuge Directory "_templates"
	- Erstellen Sie in diesem Directory mindestens 3 Dateien 
	  (z.B. datei-1.txt, datei-2.pdf, datei-3.doc)
	- Erzeuge Directory "_schulklassen"
	- Erstellen Sie in diesem  Directory mindestens 2 Schulklassen-Dateien 
	  z.B. M122-AP22b.txt, M122-AP22c.txt, M122-AP22d.txt
	- Lassen Sie in jede Schulklassen-Datei mind. 12 Schüler-Namen reinschreiben

![aufgabeA_erstelleVorlagen.jpg](x_ressources/aufgabeA_erstelleVorlagen.jpg)


#### Teil 2 "verteileDateien.sh" 

Das Skript aus Teil 1 ist die Vorgabe für dieses Skript Teil 2. 
Es geht nun um die Verarbeitung der Klassen-Dateien und die
Verteilung der Dateien.

- Machen Sie ein Skript mit dem Namen `verteileDateien.sh` und 
  machen Sie das Skript ausführbar (executable) mit `chmod +x verteileDateien.sh`
- Die erste Zeile im Skript enthält `#!/bin/bash`
- Entwickeln Sie nun Ihren Code der folgendes macht:
	- Lesen Sie alle Dateien aus "_schulklassen" und erstellen Sie für jede
	  angetroffene Datei ein Directory mit dem Namen der Datei, jedoch ohne 
	  die Datei-Endung `.txt` 
	  (es wird also für jede Klasse ein Directory erstellt).
	- Lesen Sie für jede Datei (=Klasse) die Inhalte (=Schülernamen) 
	  aus und erstellen Sie für jede Zeile ein Directory 
	  (=pro Schülername ein Directory)
	- Kopieren Sie alles was in `_templates` steht zu diesem neu 
	  erstellen Schüler-Directory
		
![aufgabeA_verteileDateien.jpg](x_ressources/aufgabeA_verteileDateien.jpg)

<hr>


**Allgemeines:**

Sie sollten grundsätzlich selber versuchen, Ihre eigene 
Lösung zu finden. Sie können sich gerne auch mit jemandem 
zusammenzutun um zusammen zu forschen, nachzudenken und um
die ersten Schritte gemeinsam zu machen. Aber jede:r Lernende
gibt die eigene Lösung ab. 

Sie haben in den letzten Wochen **bash** gelernt. Die Idee
ist, dass nun mit dieser Skriptsprache die Lösung erarbeiten.

Wenn Sie gefitzt sind, werden Sie vielleicht auch eine 
einfachere und schnellere Lösung in **bash** finden.


**_beachten Sie:_**

**Bash** hat nicht die gleichen Möglichkeiten wie PowerShell. 
Manches ist einfachen und manches ist nicht so einfach zu 
machen. Jede Skript-Programmiersprache hat ihr eigenen Vor- und Nachteile. 

Und was auch nicht zu vernachlässigen ist, ist dass **bash** auf
jedem Linux-System ohne etwas zu installieren schon verfügbar ist.
Sie können immer davon ausgehen, dass Sie Ihr Linux-Bash-Skript
sofort auf einem fremden System verwenden. Das ist in der 
Praxis ein **Riesen-Vorteil**!



### Mögliche Strategie und Lösung in PowerShell 

Damit Sie einmal einen Einblick bekommen, ist hier in der 
Folge die gleiche Aufgabe in **PowerShell** programmiert
worden. Da es in PowerShell andere Möglichkeiten gibt,
können Sie nicht alles gleich machen. Aber die Strategie,
was zu machen ist, können Sie vielleicht, zumindest teilweise,
übernehmen.

- Hilfestellung für Teil 1

Mögliche Lösung: [in Powershell](loesung-in-powershell/prepareTemplate.ps1)


- Hilfestellung für Teil 2


1.) Das Skript muss zuerst den Dateinamen 
(vor dem Punkt) lesen um damit das Grundverzeichnis 
(das der Klasse) anzulegen. Diesen verwendeten Namen sollte
gespeichert werde, weil er vermutlich wieder verwendet 
werden wird.

2.) Dann muss das Skript die Datei öffnen und alle Namenseinträge 
herauslesen. 

3.) Die Namen sind in einer Liste notiert. Die können gleich
als "array" eingelesen und nachher so verwendet werden. 

Es gibt Möglichkeiten, die oben beiden Schritte in einem
einzigen Befehl zu machen um gleichzeitig den Inhalt einer
Datei direkt einer Programm-Variablen zuzuweisen und um den 
nächsten Schritt gleich damit zu machen.

4.) Mit jedem (Namens-)Eintrag muss man erstens ein 
Verzeichnis erstellen und zweitens die Dateien aus dem 
"Template"-Verzeichnis hinein zu kopieren.

- [./loesung-in-powershell/einstieg-prepareFiles-01.ps1](./loesung-in-powershell/einstieg-prepareFiles-01.ps1)
- [./loesung-in-powershell/einstieg-prepareFiles-02.ps1](./loesung-in-powershell/einstieg-prepareFiles-02.ps1)
- [./loesung-in-powershell/einstieg-prepareFiles-03.ps1](./loesung-in-powershell/einstieg-prepareFiles-03.ps1)
- [./loesung-in-powershell/einstieg-prepareFiles-04.ps1](./loesung-in-powershell/einstieg-prepareFiles-04.ps1)
- [./loesung-in-powershell/einstieg-prepareFiles-05.ps1](./loesung-in-powershell/einstieg-prepareFiles-05.ps1)
- [./loesung-in-powershell/einstieg-prepareFiles-06.ps1](./loesung-in-powershell/einstieg-prepareFiles-06.ps1)
- [./loesung-in-powershell/einstieg-prepareFiles-07.ps1](./loesung-in-powershell/einstieg-prepareFiles-07.ps1)


Das fertige Skript: [./loesung-in-powershell/prepareFiles.ps1](./loesung-in-powershell/prepareFiles.ps1)


<hr>

## Bewertung

| Stufe | Punkte |Beschreibung | 
|-------|--------|------------ |
|     1 |  1 | "Skript erstelleVorlagen.sh:" Erstellung Namensdateien (mind. 2)             |
|     2 |  1 | "Skript erstelleVorlagen.sh:" Erstellung der Dateien im Template-Verzeichnis |
|     - |  - |                                                                              |
|     3 |  1 | "Skript verteileDateien.sh:" Klassenverzeichnis wird angelegt                |
|     4 |  1 | "Skript verteileDateien.sh:" Lernendenverzeichnisse werden angelegt          |
|     5 |  1 | "Skript verteileDateien.sh:" Lernendenverzeichnisse haben die Dateien drin   |
|     6 |  1 | "Skript verteileDateien.sh:" Mehrere Klassen- und Lernenden-Verzeichnisse    |
|       | **6** | **Total** |
|       |       |           |
|       |**1/2** | Halbierung der Punkte, wenn der gleiche Code schon mal (bei einem Kollegen) gesehen wurde |
|       |   |   |

