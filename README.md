# [![Logo TBZ](./x-res/logo.svg)](https://tbz.ch)

Notes, exercises and projects from my apprenticeship as *Informatiker/in EFZ Applikationsentwicklung* at the [TBZ](https://tbz.ch).

> [!IMPORTANT]
> This repository is a **personal project** created for learning and school-related exercises.
> It is **not an official repository of the [TBZ](https://tbz.ch)**, nor is it maintained, endorsed, or affiliated with the school in any way.
>
> All code, notes, and materials here represent my own work.

## Contents

- [Modules](#modules)
  - [1st year – 2023/24](#1st-year--202324)
  - [2nd year – 2024/25](#2nd-year--202425)
  - [3rd year – 2025/26](#3rd-year--202526)
  - [4th year – 2026/27](#4th-year--202627)
- [Repository conventions](#repository-conventions)
- [Links](#links)
- [License](#license)

## Modules

Semester: **HS** = autumn semester, **FS** = spring semester. Modules without a folder were completed without notes in this repository.

### 1st year – 2023/24

| Semester | Module | Title | Folder | Status |
| --- | --- | --- | --- | --- |
| HS | **231** | Datenschutz und Datensicherheit anwenden | – | Done |
| HS | **431** | Aufträge im eigenen Berufsumfeld selbstständig durchführen | – | Done |
| HS | **162** | Daten analysieren und modellieren | – | Done |
| HS | **319** | Applikationen entwerfen und implementieren | – | Done |
| FS | **122** | Abläufe mit einer Scriptsprache automatisieren | [m122](./m122-Bash/) | Done |
| FS | **164** | Datenbanken erstellen und Daten einfügen | – | Done |
| FS | **293** | Webauftritt erstellen und veröffentlichen | [m293](./m293-Html/) | Done |
| FS | **117** | Informatik- und Netzinfrastruktur für ein kleines Unternehmen realisieren | [m117](./m117-Networks/) | Done |

### 2nd year – 2024/25

| Semester | Module | Title | Folder | Status |
| --- | --- | --- | --- | --- |
| HS | **426** | Software mit agilen Methoden entwickeln | [m426](./m426-Scrum/) | Done |
| HS | **322** | Benutzerschnittstellen entwerfen und implementieren | – | Done |
| HS | **346** | Cloud-Lösungen konzipieren und realisieren | [m346](./m346-Cloud/) | Done |
| HS | **320** | Objektorientiert programmieren | [m320](./m320-OOP/) | Done |
| FS | **114** | Codierungs-, Kompressions- und Verschlüsselungsverfahren einsetzen | [m114](./m114-Encoding-Compression-Encryption/) | Done |
| FS | **254** | Geschäftsprozesse im eigenen Berufsumfeld beschreiben | [m254](./m254-Business-Processes/) | Done |
| FS | **165** | NoSQL-Datenbanken einsetzen | [m165](./m165-NoSQL/) | Done |
| FS | **347** | Dienst mit Container anwenden | [m347](./m347-Container/) | Done |

### 3rd year – 2025/26

| Semester | Module | Title | Folder | Status |
| --- | --- | --- | --- | --- |
| HS | **306** | Kleinprojekte im eigenen Berufsumfeld abwickeln | [m306](./m306-Projects/) | Done |
| HS | **450** | Applikationen testen | [m450](./m450-Application-Testing/) | Done |
| FS | **183** | Applikationssicherheit implementieren | [m183](./m183-application-security/) | Done |
| FS | **323** | Funktional programmieren | [m323](./m323-Functional-Programming/) | Done |

### 4th year – 2026/27

| Semester | Module | Title | Folder | Status |
| --- | --- | --- | --- | --- |
| HS | **324** | DevOps-Prozesse mit Tools unterstützen | [m324](./m324-DevOps/) | In progress |
| HS | **321** | Verteilte Systeme programmieren | [m321](./m321-distributed-systems/) | In progress |
| FS | **241** | Innovative ICT-Lösungen initialisieren | – | Planned |
| FS | **245** | Innovative ICT-Lösungen umsetzen | – | Planned |

## Repository conventions

- **One folder per module**, named `m<number>-<Topic>`.
- **Every module has a `README.md`** with the same structure:
  1. Title: `# M<number> – <official module title>`
  2. Summary line: topic · school year · semester · status
  3. **Contents**: a table linking every section, exercise or competence proof (KN)
  4. Optional module-specific sections (key concepts, grading, schedule)
  5. Link back to this overview
- **Dated work** goes into `DD-MM-YYYY/` folders; learning journals into `Journal/DD-MM-YYYY.md`.
- **Structure and headings are in English**; course content may stay in German.
- **Private links and local config** use the `*.local*` suffix and are ignored by Git.
- **Markdown formatting** is checked with [markdownlint](https://github.com/DavidAnson/markdownlint-cli2) ([config](./.markdownlint-cli2.jsonc)):

  ```sh
  npx markdownlint-cli2@0.20.0 --fix
  ```

## Links

- [TBZ course material (GitLab)](https://gitlab.com/ch-tbz-it/Stud)
- [Modulbaukasten – Informatiker/in EFZ Applikationsentwicklung](https://www.modulbaukasten.ch/?d=Informatiker%2Fin-EFZ-Applikationsentwicklung-(ab-2021))

## License

This repository is licensed under the [MIT](./LICENSE) license.
