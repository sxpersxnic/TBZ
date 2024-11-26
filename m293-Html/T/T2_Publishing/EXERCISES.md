# T2 Übungen



### Übung 1: Webhosting erstellen und Webseite manuell publizieren

Erstellen sie ein Webhosting auf [bplaced.net](https://www.bplaced.net/) oder anderen **Gratis-**Diensten. Bplaced.net bietet mit "FreeStyle" ein Gratishosting an und wurde aus diesem Grund hier aufgeführt.

Sie finden auf der [bplaced.net Hilfe-Seite](https://wiki.bplaced.net/ftp-zugaenge) Informationen wie sie die FTP Einstellungen finden und anwenden.

Installieren sie eines der genannten FTP-Tools (z. B. FileZilla) und laden ihre bisherige Web-Seite auf bplaced.net hoch.

### Übung 2: Publishing automatisieren

Automatisieren sie den Publishing-Vorgang mit einem der genannten (oder anderen) Tools und erstellen sie sie eine Batch/Bash/Exe-Datei mit der sie ihre Seite "per Knopfdruck" publizieren können.

Ihr Programm muss dabei einen Konfigurationsteil zu Beginn haben, der die Einstellungen (Server, Port, Username, Password, lokaler Pfad, remote Pfad) enthält und anschliessend das Skript, welches die Dateien aus dem lokalen Pfad via SFTP oder FTPS auf den Webserver hochlädt.

- <https://www.lautenbacher.io/allgemein/ftp-skripte-in-verbindung-mit-batch-skripten-unter-windows/>

Wenn Sie wollen, können sie auch ein Java-, Python oder C#-Projekt erstellen (wahrscheinlich aber mit ein wenig Mehraufwand).

### Optionale Übung 3: Publishing via Gitlab/Github

Diese Variante ist ungleich aufwändiger als Übung 2 und dient als weiterführender Inhalt, den sie optional gerne lösen dürfen.

[Heroku](https://www.heroku.com/) bietet eine Cloudumgebung an auf welcher es möglich ist Applikationen in verschiedenen Programmiersprachen zu publizieren. Sie bieten eine Gratisversion an und wird aus diesem Grund hier verwendet.

Publizieren sie via Gitlab/Github CI/CD Tools die Seite auf Heroku als App. Sie finden entsprechende Anleitungen hier:

- [Heroku Anleitung zu CLI (Command Line Interface)](https://devcenter.heroku.com/articles/heroku-cli)
- [Gitlab Anleitung zu Heroku](https://about.gitlab.com/blog/2021/03/22/we-are-building-a-better-heroku/)

**ACHTUNG**: Auf Heroku kann man nur dynamische Applikationen publizieren. Bei reinen HTML-Seiten wird ein Fehler geworfen. Der Trick dabei ist, dass man suggeriert, dass es sich um eine dynamische Seite handelt. Eine entsprechende Anleitung [finden sie hier](https://medium.com/@agavitalis/how-to-deploy-a-simple-static-html-website-on-heroku-492697238e48).