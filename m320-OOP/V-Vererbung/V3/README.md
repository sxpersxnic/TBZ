# V3 Interfaces anwenden

## Beschreibung

Verwendung eines Interfaces um den Code flexibler zu gestalten. Polymorphismus mittels Interface demonstrieren.

## Idee, Beispiel

Wir wollen ein Portfolio von Wertpapieren (Aktien) simulieren. Dabei soll das Portfolio entsprechende Börsen aufrufen können, um die Preise der Aktien zu aktualisieren.
Unsere Portfolio Klasse soll es aber ermöglichen, dass wir verschiedene Börsen als Parameter übergeben können. Somit verwendet die Portfolio Klasse ein Interface Stock Exchange.
Hinter dem StockExchange können sich verschiedene Börsen *verstecken*: Zürich, London und NY z.Bsp.

## Schwierigkeit

Das Konzept des Interfaces verstehen und anwenden. Dabei kann auch Polymorphismus demonstriert werden.

## Vorgehen

Erstellen Sie eine Klasse Portfolio, welche eine Sammlung von Aktien besitzt. Um den aktuellen Wert einer Aktie zu bestimmen, wird eine entsprechende Börse aufgerufen.
Dabei soll es möglich sein, dass das Portfolio verschiedene Börsen aufrufen kann. Somit ist die Börse als Interface im Portfolio definiert.

### TASK a):

Implementieren Sie die Struktur Portfolio - Liste von Aktien - Interface Stock Exchange mit zwei konkreten Implementationen (Börsenplätze).
Setzen Sie die Preise für zwei Aktien in den jeweiligen Börsen fest. (z.Bsp. Microsoft hat 100$ in NY, 120 CHF in Zürich).
Ihre Testklasse (main-Klasse) ruft das Portfolio auf und übergibt eine Börse als Parameter.
Die jeweiligen Werte für die beiden Aktien werden in der Konsole ausgegeben.

### TASK b):

Implementieren Sie die Struktur Portfolio - Liste von Aktien - Interface StockExchange mit zwei konkreten Implementationen (Börsenplätze).
Setzen Sie die Preise für vier Aktien in den jeweiligen Börsen fest. (z.Bsp. Microsoft hat 100$ in NY, 120 CHF in Zürich).
Ihre Testklasse (main-Klasse) ruft das Portfolio auf und übergibt eine Börse als Parameter.
Die jeweiligen Werte für die beiden Aktien werden in der Konsole ausgegeben.

### TASK c):

Implementieren Sie die Struktur Portfolio *Liste von Aktien* Interface StockExchange mit zwei konkreten Implementationen (Börsenplätze).
Setzen Sie die Preise für vier Aktien in den jeweiligen Börsen fest. (z.Bsp. Microsoft hat 100$ in NY, 120 CHF in Zürich). Erstellen Sie ein TUI, die es dem Benutzer ermöglicht, nach dem Wert des Portfolios zu fragen. Dabei kann der Benutzer New York als Börse auswählen oder Zürich.
