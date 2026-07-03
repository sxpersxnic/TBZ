# Testbericht – Penetrationstest (LB2 Phase 2)

| | |
|---|---|
| **Getestetes Projekt** | `fremd_projekt` – TBZ m183 Todo-List-Applikation + `ssrf-demo` |
| **Testdatum** | 2026-06-26 |
| **Testart** | White-Box (Code-Review) + abgeleitete dynamische Testfälle |
| **Getestete Stacks** | Node.js (`todo-list-node`), PHP (`todo-list-php`), Erweiterung (`ssrf-demo`) |
| **Referenz** | OWASP Top 10 (2021) |

---

## 1. Management Summary

Die **Node.js-Variante** wurde solide und sorgfältig gehärtet: SQL-Injection, die
gefälschte Cookie-Authentifizierung, das Passwort-Leak im Admin-Panel und die
meisten XSS-Stellen sind sauber behoben (Details in Kap. 6 „Positiv"). Die
mitgelieferte `FIX_DOCUMENTATION.md` ist nachvollziehbar und überwiegend korrekt.

Es bestehen jedoch **mehrere reale Restschwachstellen**, davon zwei mit hoher
Kritikalität:

* eine **IDOR-Lücke**, über die jeder eingeloggte Nutzer fremde Tasks lesen kann,
* **Passwörter, die im Auslieferungszustand im Klartext** in der Datenbank liegen,
* sowie eine **komplett ungehärtete PHP-Variante**, die im Repo mitgeliefert wird
  und produktiv katastrophal wäre.

Dazu kommen mehrere mittlere/niedrige Befunde (kein Least-Privilege-DB-User,
keine Security-Header, kein Brute-Force-Schutz, User-Enumeration, veraltete
Dependencies) und funktionale Defekte (Suche und Delete sind nicht funktionsfähig).

### Befundübersicht

| ID | Befund | Severity | OWASP |
|----|--------|----------|-------|
| F-01 | IDOR: fremde Tasks über `/edit?id=` lesbar | **Hoch** | A01 |
| F-02 | Passwörter im Klartext in DB-Seed (+ Plaintext-Login-Fallback) | **Hoch** | A02 |
| F-03 | PHP-Variante vollständig ungehärtet & verwundbar | **Hoch** *(bedingt)* | A01–A07 |
| F-04 | Applikation verbindet als DB-`root` (kein Least Privilege) | Mittel | A05 |
| F-05 | Keine Security-Header / kein CSP (helmet fehlt) | Mittel | A05 |
| F-06 | Kein Brute-Force-Schutz / Rate-Limiting am Login | Mittel | A07 |
| F-07 | Veraltete Dependencies mit bekannten CVEs | Mittel | A06 |
| F-08 | User-Enumeration am Login (unterschiedliche Fehlermeldungen) | Niedrig–Mittel | A07 |
| F-09 | Keine Session-Regeneration beim Login (Session Fixation) | Niedrig–Mittel | A07 |
| F-10 | Logout via GET ohne CSRF-Token | Niedrig | A01 |
| F-11 | Session-Cookie ohne `Secure`-Flag / keine HTTPS-Option | Niedrig | A05 |
| F-12 | jQuery 3.4.0 vom CDN ohne SRI (bekannte XSS-CVEs) | Niedrig | A06/A08 |
| F-13 | Kein Body-Size-Limit (DoS-Fläche) | Niedrig | A05 |
| I-01 | Suche funktional defekt (interner Aufruf ohne Session) | Info/Funktion | – |
| I-02 | Delete-Link zeigt auf nicht existierende Route | Info/Funktion | – |
| I-03 | `ssrf-demo` bewusst verwundbar + Bypässe im „safe"-Endpoint | Info | A10 |

---

## 2. Scope, Vorgehen & Einschränkungen

* **Vorgehen:** Systematisches Code-Review entlang der OWASP Top 10 sowie der
  21 Punkte aus der `FIX_DOCUMENTATION.md` der Entwickler. Jeder behauptete Fix
  wurde gegen den tatsächlichen Code geprüft, zusätzlich wurde nach neu
  eingeführten Lücken und übersehenen Stellen gesucht.
* **Einschränkung:** Ein vollständiger dynamischer Lauf war in der Testumgebung
  nicht möglich, da kein Docker-Daemon (und damit keine MariaDB) verfügbar war.
  Die Befunde sind daher quellcode-basiert belegt (Datei:Zeile); die angegebenen
  Reproduktionsschritte sind auf einer laufenden Instanz auszuführen und
  verifizierbar.
* Schwerpunkt liegt auf der **Node.js-Variante**, da diese laut Doku der
  bearbeitete Stack ist. PHP und `ssrf-demo` wurden ergänzend bewertet.

---

## 3. Detailbefunde (Node.js)

### F-01 — IDOR: fremde Tasks über `/edit?id=` lesbar  · Severity: HOCH · OWASP A01
**Ort:** [`todo-list-node/edit.js:13-16`](lb2-applikation/todo-list-node/edit.js#L13)

Die Edit-Ansicht lädt einen Task **nur anhand der ID**, ohne den Besitzer zu prüfen:

```javascript
const rows = await db.executeStatement(
    'SELECT ID, title, state FROM tasks WHERE ID = ?',   // <-- kein userID-Filter
    [taskId]
);
```

Im Gegensatz dazu wurde der Ownership-Check in `savetask.js` (Update) korrekt
ergänzt – im Edit-View aber vergessen. Ein eingeloggter Nutzer kann dadurch durch
Hochzählen der `id` **Titel und Status beliebiger fremder Tasks auslesen**
(Vertraulichkeitsverletzung, Broken Object Level Authorization).

**Reproduktion:**
1. Als `user1` einloggen.
2. `http://localhost/edit?id=1` aufrufen (Task eines anderen Users, z. B. admin1).
3. Der fremde Task-Titel erscheint im vorausgefüllten Formularfeld.

**Empfehlung:** Query um `AND userID = ?` mit der Session-User-ID ergänzen
(analog zu `savetask.js`); bei keinem Treffer „nicht gefunden" zurückgeben.

---

### F-02 — Passwörter im Klartext in der DB · Severity: HOCH · OWASP A02
**Ort:** [`docker/db/m183_lb2.sql:133-134`](lb2-applikation/docker/db/m183_lb2.sql#L133), [`todo-list-node/login.js:57-63`](lb2-applikation/todo-list-node/login.js#L57)

Der Datenbank-Seed speichert die Passwörter weiterhin im **Klartext**:

```sql
insert into users (ID, username, password) values (1, 'admin1', 'Awesome.Pass34');
insert into users (ID, username, password) values (2, 'user1', 'Amazing.Pass23');
```

Der Login enthält einen „Legacy"-Zweig, der Klartext akzeptiert und erst beim
**ersten erfolgreichen Login** auf bcrypt migriert. Folge:

* Im Auslieferungszustand liegen **alle** Seed-Passwörter im Klartext in der DB.
* Konten, die sich nie einloggen (z. B. `user1`), bleiben **dauerhaft Klartext**.
* Jeder mit Lesezugriff auf die DB (siehe auch F-04: App läuft als `root`) sieht
  die Passwörter.

**Empfehlung:** Seed bereits mit bcrypt-Hashes ausliefern; den
Klartext-Fallback nach erfolgter Migration entfernen.

---

### F-04 — Applikation verbindet als DB-`root` · Severity: MITTEL · OWASP A05
**Ort:** [`todo-list-node/config.js:7`](lb2-applikation/todo-list-node/config.js#L7), [`todo-list-node/.env.example:4`](lb2-applikation/todo-list-node/.env.example#L4)

```javascript
user: process.env.DB_USER || 'root',
```

Es wird kein dedizierter Applikations-User mit minimalen Rechten verwendet; per
Default (und laut `.env.example`) verbindet die App als `root`. Damit hätte eine
künftige SQL-Lücke (oder ein Fehler) sofort vollen Zugriff auf alle Datenbanken,
inkl. `DROP`, `GRANT` und User-Verwaltung – die „Blast-Radius"-Begrenzung fehlt.

**Empfehlung:** Dedizierten DB-User mit ausschliesslich `SELECT/INSERT/UPDATE/
DELETE` auf `m183_lb2` anlegen und in `config`/`.env` verwenden.

---

### F-05 — Keine Security-Header / kein CSP · Severity: MITTEL · OWASP A05
**Ort:** [`todo-list-node/app.js`](lb2-applikation/todo-list-node/app.js) (kein `helmet`)

Es werden keinerlei Sicherheits-Header gesetzt. Es fehlen u. a.:
* **Content-Security-Policy** – keine Defense-in-Depth gegen XSS (besonders
  relevant, da Inline-Scripts und CDN-jQuery genutzt werden, s. F-12).
* **X-Frame-Options / frame-ancestors** – **Clickjacking** möglich.
* **X-Content-Type-Options: nosniff**, **Referrer-Policy**, **HSTS**.

**Empfehlung:** `helmet` einbinden und eine restriktive CSP konfigurieren.

---

### F-06 — Kein Brute-Force-Schutz · Severity: MITTEL · OWASP A07
**Ort:** [`todo-list-node/app.js:93`](lb2-applikation/todo-list-node/app.js#L93) (`POST /login`)

Der Login besitzt kein Rate-Limiting und keine Account-Lockout-Logik. Passwörter
können unbegrenzt durchprobiert werden. Mit bcrypt ist Online-Bruteforce zwar
verlangsamt, ein Schutz fehlt aber vollständig (auch DoS-Fläche, da jede
Anfrage einen bcrypt-Vergleich auslöst).

**Empfehlung:** `express-rate-limit` o. ä. am Login (und allen schreibenden
Endpunkten), optional temporäre Kontosperre / Logging.

---

### F-07 — Veraltete Dependencies mit bekannten CVEs · Severity: MITTEL · OWASP A06
**Ort:** [`todo-list-node/package.json`](lb2-applikation/todo-list-node/package.json)

`express@^4.17.1`, `mysql2@^2.3.0`, `express-session@^1.17.2`, `axios@^1.7.2` sind
veraltet und enthalten bekannte Schwachstellen (u. a. `body-parser` DoS,
`cookie`-OOB, axios SSRF/Prototype-Pollution-Advisories). Ein `npm audit` schlägt
hier an.

**Empfehlung:** Dependencies aktualisieren (`npm audit fix`), `axios` entfernen,
falls die interne Suche refaktoriert wird (s. I-01).

---

### F-08 — User-Enumeration am Login · Severity: NIEDRIG–MITTEL · OWASP A07
**Ort:** [`todo-list-node/login.js:47,72`](lb2-applikation/todo-list-node/login.js#L47)

Der Login liefert unterschiedliche Meldungen: `"Username does not exist"` vs.
`"Incorrect password"`. Ein Angreifer kann damit gültige Benutzernamen
ermitteln. Zusätzlich unterscheidet sich die Antwortzeit (DB-Treffer + bcrypt
nur bei existierendem User), was Timing-Enumeration ermöglicht.

**Empfehlung:** Generische Meldung „Invalid username or password"; Dummy-bcrypt-
Vergleich auch bei unbekanntem User, um Timing anzugleichen.

---

### F-09 — Keine Session-Regeneration beim Login · Severity: NIEDRIG–MITTEL · OWASP A07
**Ort:** [`todo-list-node/login.js:26-31`](lb2-applikation/todo-list-node/login.js#L26)

`startUserSession` setzt die User-Daten in die **bestehende** Session, ohne
`req.session.regenerate()`. Eine vor dem Login (z. B. durch den CSRF-Mittelware-
Schritt) angelegte Session-ID bleibt nach dem Login gültig → **Session-Fixation**.

**Empfehlung:** Session beim erfolgreichen Login regenerieren, dann erst
User-Daten setzen.

---

### F-10 — Logout via GET ohne CSRF · Severity: NIEDRIG · OWASP A01
**Ort:** [`todo-list-node/app.js:102`](lb2-applikation/todo-list-node/app.js#L102)

`GET /logout` ist nicht CSRF-geschützt; ein Angreifer kann per `<img src=
".../logout">` einen Opfer-Logout auslösen (geringe Auswirkung, aber inkonsistent
zum sonst sauberen CSRF-Konzept).

**Empfehlung:** Logout als CSRF-geschützten POST umsetzen.

---

### F-11 — Session-Cookie ohne `Secure` / keine HTTPS-Option · Severity: NIEDRIG · OWASP A05
**Ort:** [`todo-list-node/app.js:27-30`](lb2-applikation/todo-list-node/app.js#L27)

`httpOnly` und `sameSite:'lax'` sind gesetzt (gut), aber `secure` fehlt komplett
und ist nicht konfigurierbar. Über HTTPS würde das Session-Cookie im Klartext
übertragen.

**Empfehlung:** `secure`-Flag (per Env steuerbar) ergänzen; ggf. `sameSite:
'strict'`.

---

### F-12 — jQuery 3.4.0 vom CDN ohne SRI · Severity: NIEDRIG · OWASP A06/A08
**Ort:** [`todo-list-node/fw/header.js:9-10`](lb2-applikation/todo-list-node/fw/header.js#L9)

jQuery **3.4.0** ist eingebunden – `< 3.5.0` ist von XSS-CVEs betroffen
(CVE-2020-11022/11023). Zusätzlich fehlt **Subresource Integrity (SRI)**: bei
einer CDN-Kompromittierung könnte beliebiger Code nachgeladen werden
(Supply-Chain).

**Empfehlung:** Auf aktuelle jQuery-Version aktualisieren, SRI-Hash setzen oder
lokal hosten.

---

### F-13 — Kein Body-Size-Limit · Severity: NIEDRIG · OWASP A05
**Ort:** [`todo-list-node/app.js:34-35`](lb2-applikation/todo-list-node/app.js#L34)

`express.urlencoded({extended:true})` / `express.json()` ohne `limit`. Grosse
Payloads vergrössern die DoS-Fläche.

**Empfehlung:** `limit` (z. B. `'64kb'`) setzen, `extended:false` genügt hier.

---

## 4. PHP-Variante

### F-03 — PHP-Stack vollständig ungehärtet · Severity: HOCH (bedingt) · OWASP A01–A07
**Ort:** [`todo-list-php/`](lb2-applikation/todo-list-php/)

Die **PHP-Variante wurde nicht angefasst** und enthält weiterhin sämtliche
Original-Schwachstellen. Sie wird über `docker/compose.php.yaml` mitgeliefert –
wer diesen Stack startet, betreibt eine voll verwundbare App:

* **SQL-Injection** trotz `prepare()`, weil der Username direkt in den String
  interpoliert wird: `prepare("SELECT ... WHERE username='$username'")`
  ([`login.php:18`](lb2-applikation/todo-list-php/login.php#L18)); ebenso
  `tasks where UserID = $userid` aus dem Cookie
  ([`user/tasklist.php:16`](lb2-applikation/todo-list-php/user/tasklist.php#L16)).
* **Klartext-Passwortvergleich** `$password == $db_password`
  ([`login.php:31`](lb2-applikation/todo-list-php/login.php#L31)).
* **Gefälschte Cookie-Authentifizierung** (`$_COOKIE['username']`/`userid`) –
  trivial fälschbar.
* **Login per GET**, Passwortfeld `type="text"`, **XSS** (ungescapte `echo`),
  **IDOR**, **DB-`root` mit hartkodiertem Klartext-Passwort**
  ([`config.php`](lb2-applikation/todo-list-php/config.php)).

**Empfehlung:** Falls PHP nicht der Abgabe-Stack ist, den gesamten
`todo-list-php`-Ordner und `compose.php.yaml` **aus der Abgabe entfernen**.
Andernfalls dieselben Fixes wie in Node nachziehen. *(Severity „bedingt", da nur
relevant, wenn der PHP-Stack Teil des Deployments/der Abgabe ist.)*

---

## 5. Erweiterung `ssrf-demo`

### I-03 — Bewusst verwundbare SSRF-Demo · Info · OWASP A10
**Ort:** [`ssrf-demo/`](ssrf-demo/)

Die Erweiterung ist eine **didaktische SSRF-Demo** mit absichtlich verwundbarem
`/api/fetch`-Endpunkt und einem `internal-secret-service`, der Klartext-Secrets
ausliefert. Das ist **gewollt** und damit kein Befund gegen die App. Anmerkungen:

* Es ist eine **Demonstration**, keine Härtung/kein Schutz der Todo-App selbst –
  inhaltlich passt sie nur lose zu „eigene Sicherheits-Erweiterung im Sinne von
  CIA". (Hinweis für die Bewertung, nicht sicherheitskritisch.)
* Sie darf **niemals zusammen mit der Todo-App erreichbar deployt** werden.
* Der „sichere" Endpunkt `/api/fetch-safe` filtert nur per Regex auf Hostnamen
  und ist umgehbar: **IPv6-Loopback `http://[::1]:3002`**, **dezimale/oktale/hex
  IP-Notation** (`http://2130706433`), externe Domains die auf interne IPs
  auflösen (**DNS-Rebinding**), und Redirects (zwar `maxRedirects:0`, aber die
  Hostname-Allowlist-Logik selbst greift zu kurz). Für eine reine Demo ok,
  als „Mitigation-Vorbild" aber unvollständig.

---

## 6. Positiv hervorzuheben (saubere Fixes in Node.js)

Damit der Bericht fair bleibt – das wurde gut gelöst:

* **SQL-Injection** durchgängig per Prepared Statements behoben (login, edit,
  savetask, search/v2, tasklist, admin, header). ✔
* **Echte server-seitige Sessions** statt der fälschbaren `username`/`userid`-
  Cookies; `requireAuth`/`requireAdmin`-Middleware. ✔
* **XSS-Ausgaben** konsequent mit `escape-html` escaped (tasklist, admin, index,
  Edit-Attribute, Fehlermeldungen). ✔
* **Admin-Panel**: Passwort-Spalte aus Query/Ausgabe entfernt **und**
  Rollenprüfung (`requireAdmin`) ergänzt. ✔
* **CSRF-Token-Mechanismus** pro Session inkl. Prüfung auf POST. ✔
* **bcrypt** für Passwörter (inkl. Auto-Migration). ✔
* **SSRF (provider)** per exakter Whitelist mitigiert; `userid` kommt aus der
  Session, nicht mehr aus dem Request. ✔
* **DB-Credentials** aus Env, `.gitignore`/`.env.example` vorhanden. ✔
* Die `FIX_DOCUMENTATION.md` ist nachvollziehbar und überwiegend ehrlich.

---

## 7. Funktionale Defekte (Nebenbeobachtung)

* **I-01 – Suche funktioniert nicht:** `search.js` ruft per `axios` intern
  `http://localhost:3000/search/v2/` auf. Dieser interne Request trägt **kein
  Session-Cookie**, die Route `/search/v2/` ist aber `requireAuth`-geschützt →
  Redirect auf `/login` → die Suche liefert nie Ergebnisse. Empfehlung: die
  Suchfunktion direkt in-process aufrufen (kein HTTP-Umweg), das beseitigt auch
  die letzte SSRF-Restfläche und die axios-Abhängigkeit.
* **I-02 – Delete tot:** `tasklist.js` rendert `delete?id=…`, es gibt aber **keine
  `/delete`-Route** in `app.js`. Bei Implementierung zwingend als CSRF-
  geschützten POST mit Ownership-Check (`AND userID = ?`).

---

## 8. Empfohlene Priorisierung der Fixes

1. **F-01 IDOR** (schnell, hoher Impact) – `AND userID = ?` in `edit.js`.
2. **F-02 Klartext-Passwörter** – Seed hashen, Fallback entfernen.
3. **F-03 PHP** – ungenutzten Stack entfernen oder härten.
4. **F-04/F-05/F-06** – Least-Privilege-DB-User, `helmet`+CSP, Rate-Limiting.
5. **F-07–F-13** – Dependencies, Enumeration, Session-Fixation, Cookie-Flags, SRI.
6. Funktionale Defekte I-01/I-02 beheben.
