![TBZ-Logo](../x_gitressources/tbz_logo.png)

# HTML-Grundlagen (H1)

TBZ Informatik Modul 293 - Webauftritt erstellen und veröffentlichen

[TOC]

## Lernziele

- Ich kann die Geschichte und Entwicklung des Internets erzählen und den Erfinder von HTML nennen.
- Ich kann den Doctype und den Zusammenhang zwischen HTML und XML erklären.
- Ich kann das Grundgerüst für eine Webseite erstellen.
- Ich kann eine Webseite mit einfachen HTML-Elementen erweitern.
- Ich kann die Tags für Überschriften, Paragraphen, Listen und Tabellen nennen und anwenden.
- Ich kann den Link-Tag anwenden und seine Attribute erläutern.
- Ich kann den Unterschied zwischen absoluten und relativen URLs erklären.
- Ich kann den Tag für Bilder korrekt anwenden.



## Geschichte

- Ende der sechziger Jahre: Arpanet (Advanced Research Projects Agency Network) wurde im Auftrag der US-Luftwaffe von einer kleinen Forschergruppe unter der Leitung des MIT (Massachusetts Institute of Technology) entwickelt, mit dem Ziel ein dezentrales Netzwerk zwischen verschiedenen US-Universitäten zu erschaffen.
- 1989: CERN erstellt Projekt zur Findung einer Lösung von Austausch von Forschungsergebnissen.
- 1990: Es wurde beschlossen, das Internet der Öffentlichkeit zugänglich zu machen und für kommerzielle Zwecke zu nutzen.
- 1992: CERN entwickelt erste Version von HTML (Hypertext Markup Language).
- 1993: Rasanter Wachstumsschub mit dem ersten grafikfähigen Browser "Mosaic"
- 1995: HTML 2. Einführung von Formularen. W3C übernimmt die Entwicklung von HTML.
- 1997: HTML 3.2. HTML 3 war nie erschienen, weil die Spezifikation veraltet war und der Netscape Browser schon mehr konnte. Einführung von Tabellen, Textfluss, Bilder und Applets
- 1997: HTML 4. Einführung von Stylesheets, Skripten und Frames.
- 1999: HTML 4.01. Diese Version war lange Standard, auch weil sich XHTML nie durchgesetzt hatte. Da HTML 4.01 vieles nicht konnte, wurde mit Alternativen gearbeitet wie Adobe Flash und kurze Zeit auch Microsoft Silverlight. Die Alternativen waren kompilierter Code, der als Objekt im Browser ausgeführt werden konnten, solange ein Browser-Plugin installiert war.
- 2000 - 2006: Entwicklung von XHTML. Hat sich nie durchgesetzt und wurde zugunsten von HTML 5 aufgegeben.
- 2014: HTML 5. Überarbeitung des DOM und Einführung von vielen neuen Tags
- 2016: HTML 5.1
- 2017: HTML 5.2

Quellen:

- <https://www.polaris-systems.de/tutorials-beitrag/entstehung-und-zweck-von-html.html>
- <https://de.wikipedia.org/wiki/Hypertext_Markup_Language>



**Beispiel**

![MS-1999](x_gitressources/microsoft_1999.png)

*Abb: Microsoft Webseite aus dem Jahr 1999*

Sie können das Web Archive verwenden, um alte Webseiten anzuschauen: <https://web.archive.org/>



## Web 1.0 - Web 3.0

Im Jahr 2020 kam immer mehr der Begriff des Web 3.0 auf. Folgend eine Übersicht was die verschiedenen Versionen für eine Bedeutung haben.

| Version      | Beschreibung                                                 |
| ------------ | ------------------------------------------------------------ |
| Web&nbsp;1.0 | Web 1.0 wurde geprägt in den Anfangsjahren in denen Webseiten hauptsächlich server-seitigen Inhalt zur Verfügung stellte. |
| Web&nbsp;2.0 | Der Begriff Web 2.0 wird verwendet um unsere heutigen mordernen Web Applikationen zu beschreiben. Web 2.0 erlaubt nicht nur die Darstellung von Inhalten, sondern erlaubt den Benutzern eine Interaktion auf den Websiten. Es beschreibt die Sozialen Netzwerke, aber auch die hoch-zentralisierten Seiten und Kooperationen, die solche Applikationen zur Verfügung stellen. |
| Web&nbsp;3.0 | Der Begriff Web 3.0 ist neu und kommt immer mehr im Zusammenhang mit dezentralen Inhalten, die nicht mehr nur einer Kooperation gehören, sondern einer Gemscheinschaft und diese kann auch bestimmen wie sich die Inhalte weiterentwickeln werden. Die Dezentralisierung wird erreicht durch die Blockchain-Technologie und Cryptos, die SmartContracts zulassen. **Web 3.0 ist im Anfangsstadium**. |

[Einen Artikel zu diesem Thema lesen...](https://www.gemini.com/cryptopedia/web-3-0-definition-open-internet-decentralized)



## Dokumentenaufbau

HTML-Dokumente sind strukturierte Dokumente. Das bedeutet, daß ein Dokument  nicht nur eine Folge von Zeichen ist, sondern eine strukturierte  Zusammenstellung verschiedener Elemente, die gemeinsam ein Dokument ausmachen. Der aktuelle HTML-Standard ist [hier sehr umfangreich beschrieben](https://html.spec.whatwg.org/multipage/).

**Beispiel**

~~~html
<!DOCTYPE html>
<html lang="en">
    <head></head>
    <body></body>
</html>
~~~

### Doctype

Im Code-Beispiel steht an erster Stelle der *DOCTYPE*. Diese Code-Zeile steht so für sich, **ist kein Tag** und wird darum auch nicht geschlossen. Es handelt sich hier um eine Information für den Browser und erklärt, wie der Inhalt des Dokuments dargestellt werden soll.

Der kurze *Doctype* aus dem Beispiel oben, steht für HTML 5. Frühere *Doctypes* für die Version HTML 4.01 waren viel länger und vielseitiger. Beispiele finden Sie auf der [W3C-Seite](https://www.w3.org/QA/2002/04/valid-dtd-list.html).

**Browserinkompatibilitäten** waren früher verbreitet und die Darstellung von Seiten konnte auf allen Browsern unterschiedlich sein. Es war wichtig den korrekte *Doctype* zu verwenden. Mit der Einführung von HTML 5 wurde dies stark verbessert und die alten *Doctypes* verschwanden glücklicherweise schnell.

[Mehr über den Doctype lernen...](https://www.w3schools.com/tags/tag_doctype.asp)

### Html-Tag, XML und Attribute

Jede HTML-Seite ist ein XML-Dokument, wobei die Liste der gültigen XML-Tags fest definiert sind. Das root-Element von HTML ist der Tag `<html>`, jeder weitere Tag ist also ein Child-Element. Ein HTML-Tag kann - genau wie XML-Tags - Attribute enthalten. Im *html*-Tag im Beispiel oben sehen sie, dass die Sprache dieses Documents via dem Attribut lang="en" als Englisch definiert wurde.

- Der Name des Attributes (oder auch Schlüssel) ist "lang"
- Der Wert des Attributes ist "en".

Seit der Einführung von Stylesheets - speziell CSS3 und HTML5 - werden immer weniger Eigenschaften in Attributen in HTML-Tags ausgedrückt, sondern in CSS-Dateien ausgelagert.

[Mehr über den HTML-Tag lernen...](https://www.w3schools.com/tags/tag_html.asp)

### Head-Tag

Der Head-Tag enthält Meta-Informationen (Titel, Meta-Tags) oder Referenzen auf zusätzliche Dateien wie Skripts oder Stylesheets. Die Inhalte dieses Tags sind nicht sichtbar.

[Mehr über den Head-Tag lernen...](https://www.w3schools.com/tags/tag_head.asp)

### Body-Tag

Der Body-Tag enthält die sichtbaren Inhalte des HTML-Dokuments, wobei auch hier die Liste der erlaubten HTML-Tags limitiert und spezifiziert ist. Nicht erlaubte Tags werden ignoriert.

[Mehr über den Body-Tag lernen...](https://www.w3schools.com/tags/tag_body.asp)

### Schreibweisen und Verschachtelung

HTML-Elemente haben normalerweise einen öffnenden und einen schliessenden Tag: `<p>Dies ist Text</p>`. Der Forward-Slash kennzeichnet dabei den schliessenden Tag.

Tags lassen sich aber auch beim Öffnen gleichzeitig wieder schliessen, falls der Tag kein Inhalt enthalten wird oder kann: *&lt;p /&gt;*. Der Forward-Slash wird dabei am Ende hinzugefügt. Die Browser können einige Elemente auch ohne den schliessenden Tag interpretieren und korrekt darstellen (z.B. *link*-, *br*-, *p*-Tags), aber sie sollten sich angewöhnen alle Tags zu schliessen.

Ebenso wichtig ist die korrekte Verschachtelung von Tags. Eine gültige Tag-Kombination wäre *&lt;p&gt;&lt;span&gt;Dies ist Text&lt;/span&gt;&lt;/p&gt;*. Hier wird der innere span-Tag zuerst geschlossen und erst anschliessend der p-Tag.
Ungültig wäre die Verschachtelung *&lt;p&gt;&lt;span&gt;Dies ist Text&lt;/p&gt;&lt;/span&gt;*. Jeder Browser könnte diese Kombination unterschiedlich interpretieren und darstellen. Gross- und Kleinschreibung der Texts ist nicht relevant und wird als gleichwertig gesehen. Üblicherweise werden die Tags klein geschrieben.

[Mehr über HTML-Elemente lernen...](https://www.w3schools.com/html/html_elements.asp)



## Überschriften

Überschriften sind Titel oder Untertitel, die in einem HTML-Dokument eingesetzt werden können. Es gibt **sechs vordefinierte Titelstufen** von `h1` bis `h6`, wobei der `h1`-Tag die grösste Stufe darstellt und nur einmal pro Seite verwendet werden sollte. Titel werden standardmässig in fetter Schrift und als Block-Element dargestellt.

Natürlich können Sie mit CSS auch normalen Text so formatieren, dass er gleich aussieht wie ein Titel, aber die **Semantik** des HTML geht dadurch verloren. Stellen sie sich einen Web-Crawler vor (z.B. Google), welcher Seiten durchsucht und Informationen zur Darstellung sucht. Wenn nicht die korrekten Tags verwendet werden, weiss dieser Crawler nicht was die gefundenen Inhalte bedeuten oder wie die Ergebnisse dargestellt werden müssen.

[Mehr über den Titel-Tag lernen...](https://www.w3schools.com/tags/tag_hn.asp)

**Beispiel**

![H1-H6](x_gitressources/titel1-6.png)

~~~html
<!DOCTYPE html>
<html>
    <head></head>
    <body>
        <h1>Dies ist das Hauptthema</h1>
        <p>
            Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Maecenas porttitor congue massa. Fusce posuere, magna sed pulvinar ultricies, purus lectus malesuada libero, sit amet commodo magna eros quis urna.
            <br />
            Nunc viverra imperdiet enim. Fusce est. Vivamus a tellus.
        </p>
        <h2>Hier kommt das Unterthema 1</h2>
        <p>
            Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Proin pharetra nonummy pede. Mauris et orci.
            <br />
            Aenean nec lorem. In porttitor. Donec laoreet nonummy augue.
            <br />
            Suspendisse dui purus, scelerisque at, vulputate vitae, pretium mattis, nunc. Mauris eget neque at sem venenatis eleifend. Ut nonummy.
        </p>
        <h2>Hier kommt das Unterthema 2</h2>
        <p>
            Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Proin pharetra nonummy pede. Mauris et orci.
            <br />
            Aenean nec lorem. In porttitor. Donec laoreet nonummy augue.
            <br />
            Suspendisse dui purus, scelerisque at, vulputate vitae, pretium mattis, nunc. Mauris eget neque at sem venenatis eleifend. Ut nonummy.
        </p>
    </body>
</html>
~~~

[Beispiel mit CodePen testen](https://codepen.io/nuy/pen/NWaQeWM)



## Paragraphen und Zeilenumbrüche

In HTML wird Text - wie in Word-Dokumenten - in Paragraphen verpackt, die Abstände zueinander haben und wird mit einem *p*-Tag repräsentiert. Der Text zwischen dem öffnenden und schliessenden Tag ist der Inhalt, welcher im Dokument angezeigt wird. Zwischen zwei Paragraphen fügt der Browser einen vordefinierten Abstand ein, welcher mit CSS übersteuert werden kann. CSS wird später behandelt.

Ein einfacher Zeilenumbruch - ohne Abstand zwischen den Texten - wird mit dem *br*-Tag erstellt. Ein *br*-Tag hat nie Inhalt und wird gleich wieder geschlossen *&lt;br /&gt;*.

[Mehr über den Paragraph-Tag lernen...](https://www.w3schools.com/tags/tag_p.asp)

**Beispiel**

~~~html
<!DOCTYPE html>
<html>
    <head></head>
    <body>
        <p>
            Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Maecenas porttitor congue massa. Fusce posuere, magna sed pulvinar ultricies, purus lectus malesuada libero, sit amet commodo magna eros quis urna.
            <br />
            Nunc viverra imperdiet enim. Fusce est. Vivamus a tellus.
        </p>
        <p>
            Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Proin pharetra nonummy pede. Mauris et orci.
            <br />
            Aenean nec lorem. In porttitor. Donec laoreet nonummy augue.
            <br />
            Suspendisse dui purus, scelerisque at, vulputate vitae, pretium mattis, nunc. Mauris eget neque at sem venenatis eleifend. Ut nonummy.
        </p>
    </body>
</html>
~~~

[Beispiel mit CodePen testen](https://codepen.io/nuy/pen/JjrQepj)



## Listen

Es existieren zwei Arten von Listen, eine **unsortierte** (*ul*-Tag) und eine **sortierte** (*ol*-Tag).
Beide Listen-Tags benötigen Listen-Einträge, die mit dem *li*-Tag dargestellt werden. Andere Tags sind ungültig für die Listen-Tags. Innerhalb des *li*-Tags, können aber wieder viele verschiedene Tags verschachtelt werden. Über Attribute, können die Listen noch zusätzlich konfiguriert werden. 

[Mehr über Listen lernen...](https://www.w3schools.com/html/html_lists.asp)

**Beispiel**

![lists](x_gitressources/list.png)

~~~html
<!DOCTYPE html>
<html>
    <head></head>
    <body>
        <h1>Eine unsortierte Liste, z.B. Einkaufsliste</h1>
        <ul>
            <li>Pasta</li>
            <li>Brot</li>
            <li>Orangensaft</li>
        </ul>

        <h1> Eine sortierte Liste, z.B. Rang</h1>
        <ol start="10">
            <li>Severin</li>
            <li>Anna</li>
            <li>Anke</li>
        </ol>
    </body>
</html>
~~~

[Beispiel mit Codepen testen](https://codepen.io/nuy/pen/ExwqGaY)



## Tabellen

Tabellen haben sich über die Zeit entwickelt. Früher gab es weniger Tags, die man verwenden konnte, z.B. gab es keine Footer. Moderne Tabellen, haben eine bessere Unterteilung und Struktur.

Früher (HTML 4.01) wurden Tabellen auch verwendet, um das Layout von HTML-Seiten zu gestalten. Dies war notwendig, weil HTML und CSS nicht weit genug entwickelt waren. Sie sollten es aber unbedingt **vermeiden Tabellen als Design-Elemente** zu verwenden.

Eine einfache Tabelle, verwendet nur die Tags 

- *table*
- *tr*: Eine Zeile der Tabelle
- *th*: Eine Zelle einer Kopfzeile
- *td*: Eine Zelle einer Tabelle

Komplexere Tabellen verwenden zusätzliche Tags, die eine bessere Struktur zulassen. Sie können mit den zusätzlichen Tags z.B. den Inhalt einer Tabelle scrollen lassen, während die Kopfzeile stehen bleibt. Die zusätzlichen Tags werden vom Browser sowieso hinzugefügt, einfach implizit. Im folgenden Beispiel sehen sie auf den ersten Blick keinen Unterschied, aber die Semantik der Tags ist unterschiedlich. So fällt ihnen auch das Styling später leichter.

[Mehr über Tabellen lernen...](https://www.w3schools.com/html/html_tables.asp)

**Beispiel**

![table](x_gitressources/table.png)

~~~html
<!DOCTYPE html>
<html>
    <head></head>
    <body>
        <h1>Eine einfache Tabelle</h1>
        <table>
              <tr>
                <th>Student Name</th>
                <th>Module 1</th>
                <th>Module 2</th>
              </tr>
              <tr>
                <td>Fabian</td>
                <td>4.5</td>
                <td>4</td>
              </tr>
              <tr>
                <td>Sarah</td>
                <td>5.5</td>
                <td>5</td>
              </tr>
              <tr>
                <td>Durchschnitt</td>
                <td>5</td>
                <td>4.5</td>
              </tr>
            </tbody>
          </table>

        <h1>Eine erweiterte Tabelle</h1>
        <table>
            <thead>
                <tr>
                    <th>Student Name</th>
                    <th>Module 1</th>
                    <th>Module 2</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Fabian</td>
                    <td>4.5</td>
                    <td>4</td>
                </tr>
                <tr>
                    <td>Sarah</td>
                    <td>5.5</td>
                    <td>5</td>
                </tr>
            </tbody>
            <tfoot>
                <tr>
                    <td>Durchschnitt</td>
                    <td>5</td>
                    <td>4.5</td>
                </tr>
            </tfoot>
          </tbody>
        </table>
    </body>
</html>
~~~

[Beispiel mit CodePen testen](https://codepen.io/nuy/pen/jOGgXEv)



## Hyperlinks

Hyperlinks - oder kurz Links - werden verwendet, um zwischen Seiten zu wechseln. Sie können dabei auf einen Inhalt der gleichen Seite springen oder auf einen Inhalt einer anderen Seite wechseln. Der dazu verwendete *a*-Tag muss **zwingend das Attribute *href*** enthalten, welches die URL enthält auf die verlinkt wird. Ein **optionales Attribute *target*** dient zur Steuerung, ob die neue Seite im gleichen oder in einem neuen Fenster aufgerufen wird.

[Mehr über den Link-Tag lernen...](https://www.w3schools.com/html/html_links.asp)

### Relative und absolute verlinkung

Links werden **absolut** oder **relativ** gesetzt. Absolute Links benötigen eine vollständige, gültige URL mit Protokoll, Domain und Pfad auf die Seite. Relative URLs haben eine Pfadangabe relativ zu dem aktuellen Dokument.

Eine absolute URL hat die folgenden Elemente, die in einem späteren Kapitel noch im Detail erläutert werden.

![url](x_gitressources/url-syntax.png)

[Mehr über Hyperlinks lernen...](https://developer.mozilla.org/en-US/docs/Learn/Common_questions/What_is_a_URL)

| Beispiel | Erklärung |
| ------------------------- | ------------------------------------------------------ |
| https://gitlab.com/ch-tbz-it/Stud/m293/tree/main/D1_BasicStyles | Absolute URL auf eine Seite in Gitlab. |
| seite1.html | relative URL, die auf eine Datei zeigt, die im gleichen Ordner liegt wie das aufgerufene Dokument |
| ./seite2.html | Genau gleich wie das vorherige Beispiel mit dem Unterschied, dass explizit angegeben wurde, dass sich die Datei im gleichen Ordner befindet via "./". |
| ../seite3.html | relative URL, die auf eine Datei zeigt, die ein Ordner höher liegt als die aufgerufene Datei. |
| ../weitere/seite4.html | relative URL, die auf eine Datei zeigt, die in einem anderen Ordner ("weitere") liegt, auf der gleichen Ebene wie die aufgerufene Seite. |

Dateistruktur zu den Beispielen oben:

- wwwroot
  - aktuelle
    - seite1.html
    - seite2.html
  - weitere
    - seite4.html
  - seite3.html

Links können unterschiedliche Farben haben. Die Browser unterscheiden per Default die Darstellung zwischen einem besuchten und einem unbesuchtem Link.

**Beispiel**

![links](./x_gitressources/links.png)

~~~html
<!DOCTYPE html>
<html>
    <head></head>
    <body>
        <h1>Try Me Seiten</h1>
        <a href="TryMe_lists.html">Lists</a><br />
        <a href="./TryMe_tables.html">Tables</a><br />
        <a href="TryMe_paragraphs.html">Paragraphen</a><br />

        <h1>Externe Seiten</h1>
        <a href="https://tbz.ch" target="_blank">TBZ Seite</a><br />
        <a href="https://portal.office.com" target="_blank">O365 Login</a><br />
    </body>
</html>

~~~

[Beispiel mit CodePen testen](https://codepen.io/nuy/pen/JjrgwdY)



## Bilder

Eine HTML-Seite kann verschiedene Bild-Formate enthalten. Die Limitierung ist nicht HTML, sondern was der Browser darstellen kann. Die typischen Bild-Formate sind jpg, jpeg, gif, png, svg.

Der *img*-Tag wird zur Darstellung von Bildern verwendet. Ein *img*-Tag benötigt **zwingend das Attribute *src***, welches die Quelle des Bildes angibt. Der Aufbau des Pfades folgt dabei dem gleichen Schema wie unter Hyperlink beschrieben (absolute oder relative URLs).

Bilder können - wie in Word - fliessend im Text platziert werden. Dies geschieht mit CSS-Anweisungen und ist ein Thema in einem anderen Kapitel.

[Mehr über den Img-Tag lernen...](https://www.w3schools.com/tags/tag_img.asp)

**Beispiel**

![images](./x_gitressources/images.png)

~~~html
<!DOCTYPE html>
<html>
    <head></head>
    <body>
        Das folgende Bild ist mit einer <strong>absoluten URL</strong> eingebunden: <br />
        <img src="https://ch-tbz-it.gitlab.io/Stud/m293assets/H1_BasicElements/logo-hat.png" />
        <br />
		<br />
		Das folgende Bild ist mit einer <strong>relative URL</strong> eingebunden. <br />
        <img src="../logo-hat.png" />
    </body>
</html>
~~~

[Beispiel mit CodePen testen...](https://codepen.io/nuy/pen/qBPzQmW)



## Checkpoints

- [ ] Ich kann die Geschichte und Entwicklung des Internets erzählen und den Erfinder von HTML nennen.
- [ ] Ich kann den Doctype und den Zusammenhang zwischen HTML und XML erklären.
- [ ] Ich kann eine Webseite mit einfachen HTML-Elementen erweitern.
- [ ] Ich kann die Tags für Überschriften, Paragraphen, Listen und Tabellen nennen und anwenden.
- [ ] Ich kann die Tags für Listen und Tabellen nennen und anwenden.
- [ ] Ich kann den Link-Tag anwenden und seine Attribute erläutern.
- [ ] Ich kann den Unterschied zwischen absoluten und relativen URLs erklären.
- [ ] Ich kann den Tag für Bilder korrekt anwenden.



## Ressourcen

1. https://de.wikipedia.org/wiki/Hypertext_Markup_Language (Wikipedia)
2. https://html.spec.whatwg.org/multipage/ (W3C HTML Standard)
3. https://www.w3schools.com/html/html_basic.asp (W3Schools)



**Informatik Modul 293 - Webauftritt erstellen und veröffentlichen**