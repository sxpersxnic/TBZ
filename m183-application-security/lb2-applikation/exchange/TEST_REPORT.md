# Test Report – `todo-list-node`

The application is, on the whole, well hardened: all DB access uses
parameterised prepared statements, output is HTML-escaped, a strict CSP is in
place, authentication is server-side session based, CSRF synchronizer tokens
guard every state-changing route, and object access is scoped to the session
user (no IDOR). The findings below are the **residual, exploitable issues** that
remain.

| # | Finding | Severity | Status |
|---|---------|----------|--------|
| 1 | Rate-limit bypass via spoofed `X-Forwarded-For` (misconfigured `trust proxy`) | **Medium** | Exploitable in shipped config |
| 2 | Username enumeration on `/register` | Low | Exploitable |
| 3 | Session cookie transmitted over cleartext HTTP (no `Secure`, HSTS off) | Low–Medium | Exploitable on the network |
| 4 | Committed / placeholder secrets (`SESSION_SECRET`, DB password) | Low | Config weakness |
| 5 | Stack-trace disclosure when `NODE_ENV` is not `production` | Info | Conditional |

---

## 1. Rate-limit bypass via spoofed `X-Forwarded-For` — **Medium**

**Files:** [app.js:25](app.js#L25), [app.js:95-109](app.js#L95-L109)
**OWASP:** A07 (Identification & Authentication Failures) / A05 (Misconfiguration)

### What

The app enables reverse-proxy trust:

```js
app.set('trust proxy', 1);          // app.js:25
```

and then rate-limits by client IP:

```js
const loginLimiter = rateLimit({ windowMs: 15*60*1000, max: 10, ... });   // app.js:95
const registerLimiter = rateLimit({ windowMs: 60*60*1000, max: 5, ... }); // app.js:103
```

`express-rate-limit` keys buckets on `req.ip`. With `trust proxy` enabled,
Express derives `req.ip` from the client-supplied **`X-Forwarded-For`** header
instead of the real socket address.

**But there is no reverse proxy in the deployment.** `docker/compose.node.yaml`
publishes the Node container's port directly (`ports: ["80:3000"]`) and there is
no nginx/traefik/caddy service anywhere in `docker/`. Because nothing strips or
sets `X-Forwarded-For`, the value is fully attacker-controlled.

### Impact

An attacker sends a different `X-Forwarded-For` value on every request and gets
a **fresh rate-limit bucket each time**, completely defeating the brute-force
protection on `/login` (nominally 10 / 15 min) and the abuse protection on
`/register` (nominally 5 / hour). Since there is no per-account lockout either,
password brute-forcing and mass account creation become unlimited.

### Proof of concept

```bash
# Each request presents a new source IP → never rate-limited.
for i in $(seq 1 1000); do
  curl -s -o /dev/null \
    -H "X-Forwarded-For: 10.0.$((RANDOM%255)).$((RANDOM%255))" \
    -X POST http://localhost/login \
    --data "username=admin1&password=guess$i&_csrf=<token>"
done
# All 1000 attempts are processed; the "Too many login attempts" limit never fires.
```

(A CSRF token + matching `sid` cookie from a prior `GET /login` is needed; both
are freely obtainable by an anonymous client and do not blunt the bypass.)

### Fix

Match `trust proxy` to the **actual** hop count in front of the app:

- If the app is exposed directly (current Docker setup): `app.set('trust proxy', false);`
- If a real proxy is added later: set it to the exact number of trusted proxies,
  or a specific subnet — never leave it trusting a hop that does not exist.

Consider adding an account-based throttle (e.g. lock/slow after N failures per
username) so protection does not depend solely on a spoofable IP.

---

## 2. Username enumeration on `/register` — **Low**

**File:** [register.js:43-46](register.js#L43-L46)
**OWASP:** A07

The login path was deliberately hardened against enumeration (generic message +
dummy-hash timing equalisation). Registration undoes part of that: it returns a
**distinct** message when a username already exists.

```js
const existing = await db.query('SELECT ID FROM users WHERE username = ? LIMIT 1', [username]);
if (existing.length > 0) {
    return { success: false, msg: 'This username is already taken.' };
}
```

### Impact

An attacker can probe `/register` to learn which usernames exist (e.g. confirm
`admin1` exists) — useful reconnaissance for the brute-force in Finding&nbsp;1.
Impact is inherent to any "unique username" feature, but it is worsened here
because the rate limit meant to slow probing is itself bypassable (Finding&nbsp;1).

### Fix

This one is largely unavoidable for a self-service unique-username flow; mitigate
by (a) fixing Finding&nbsp;1 so probing is actually rate-limited, and
(b) optionally moving to email-based registration where the "already registered"
signal is delivered out-of-band instead of in the HTTP response.

---

## 3. Session cookie over cleartext HTTP — **Low–Medium**

**Files:** [app.js:61-73](app.js#L61-L73), `docker/compose.node.yaml` (`COOKIE_SECURE=false`)
**OWASP:** A02 (Cryptographic Failures) / A05

The session cookie is `HttpOnly` + `SameSite=Strict` (good), but in the shipped
configuration the `Secure` flag is off and HSTS is disabled:

```js
secure: process.env.COOKIE_SECURE === 'true',   // = false in compose
...
hsts: false,                                     // helmet, app.js:46
```

The app is served over plain HTTP (`80:3000`). Any attacker able to observe the
network (same LAN, malicious Wi-Fi, on-path device) can read the `sid` cookie in
transit and hijack the authenticated session.

This is documented as an intentional teaching trade-off, but it is a real,
exploitable weakness whenever the app is reachable beyond `localhost`.

### Fix

Terminate TLS in front of the app and set `COOKIE_SECURE=true`, re-enable
`helmet` HSTS, and redirect HTTP→HTTPS. Do not run the app on an untrusted
network over plain HTTP.

---

## 4. Committed / placeholder secrets — **Low**

**Files:** [config.js:6-9](config.js#L6-L9), `docker/compose.node.yaml`

The code correctly reads secrets from the environment, but the committed defaults
and the compose file ship real, publicly-known values:

- `SESSION_SECRET=change-me-to-a-long-random-value` (compose)
- `DB_PASSWORD=ChangeMe.App.Pw1` (compose) and the same as a hard-coded default
  in [config.js:8](config.js#L8); it also matches the `todoapp` DB user created
  in `docker/db/m183_lb2.sql`.

### Impact

If any environment is deployed without overriding these, the session-signing
secret and DB password are known to anyone who has seen the repo. A known
`SESSION_SECRET` lets an attacker forge validly-signed session cookies (auth
impact is limited because session data is server-side, but it removes a defence
layer). The DB user is at least least-privileged (DML on one schema only), which
caps the blast radius.

### Fix

Do not commit real secret values, even placeholders that look deployable.
Provide a `.env.example` with obviously-fake values and require the real ones to
be injected at deploy time; fail fast (refuse to boot) if `SESSION_SECRET` is
unset in production rather than falling back.

---

## 5. Stack-trace disclosure outside `production` — **Info**

**File:** [app.js](app.js) (multiple async route handlers)

Several async handlers (e.g. `GET /`, `GET /admin/users`, `GET /edit`,
`POST /savetask`, `POST /search`) have no local `try/catch`. A DB error there
propagates to Express's default error handler, which returns a full stack trace
when `NODE_ENV !== 'production'`.

The shipped `docker/compose.node.yaml` sets `NODE_ENV=production`, so this is
**mitigated in the intended setup**; the risk applies only if the app is started
outside that environment (e.g. `npm start` locally). Add a central Express error
handler that logs server-side and returns a generic message regardless of
`NODE_ENV` to remove the dependency on that variable.

---

## Items explicitly checked and found NOT vulnerable

- **SQL injection** – all queries parameterised via the pool ([fw/db.js](fw/db.js)); the
  search LIKE term is bound, not concatenated ([search/v2/index.js:20-23](search/v2/index.js#L20-L23)).
- **XSS** – every user-influenced value is passed through `escapeHtml`
  ([fw/security.js:8](fw/security.js#L8)); CSP forbids inline/3rd-party scripts ([app.js:30-47](app.js#L30-L47)).
- **IDOR / broken object-level auth** – task read/edit/update/delete are all
  scoped to `req.session.userId` ([edit.js:20-23](edit.js#L20-L23), [savetask.js:37-40](savetask.js#L37-L40),
  [delete.js:14-17](delete.js#L14-L17)).
- **CSRF** – synchronizer token enforced with constant-time compare on every
  POST route ([fw/security.js:42-59](fw/security.js#L42-L59)).
- **Privilege escalation** – admin gate reads the role from the server-side
  session, not the client ([app.js:86-91](app.js#L86-L91)).
- **Session fixation** – session regenerated on successful login ([app.js:142-152](app.js#L142-L152)).
- **Password storage** – bcrypt cost 12, dummy-hash timing equalisation on login
  ([login.js:28-31](login.js#L28-L31)).
