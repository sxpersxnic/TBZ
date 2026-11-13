# M183 – Applikationssicherheit implementieren

> **Application security** · Year 3 (2025/26) · Spring semester (FS) · Done

Finding, exploiting and fixing common web vulnerabilities in a deliberately insecure application, plus a standalone SSRF demonstration.

## Contents

| Section | Description |
| --- | --- |
| [LB2 application](./lb2-applikation/) | Deliberately insecure todo-list app (Node.js / PHP) used for the LB2 assessment |
| [SSRF demo](./ssrf-demo/) | Server-side request forgery demonstration with Docker |

### LB2 documents

| Document | Description |
| --- | --- |
| [Vulnerability report](./lb2-applikation/VULNERABILITY_REPORT.md) | Assessment of the Node.js application |
| [Fix documentation](./lb2-applikation/FIX_DOCUMENTATION.md) | Fixes applied per vulnerability, changed files |
| [Exploit scripts](./lb2-applikation/exploit-scripts/) | Proof-of-concept scripts ([index](./lb2-applikation/exploit-scripts/INDEX.md)) |
| [Test report (EN)](./lb2-applikation/exchange/TEST_REPORT.md) | Findings from the peer penetration test |
| [Testbericht (DE)](./lb2-applikation/exchange/TESTBERICHT.md) | Penetration test report, LB2 phase 2 |

> [!WARNING]
> The LB2 application is intentionally vulnerable. Run it only locally and never expose it to a network.

## Resources

- [Course material (TBZ GitLab)](https://gitlab.com/ch-tbz-it/Stud/m183/m183)

---

[← Back to overview](../README.md)
