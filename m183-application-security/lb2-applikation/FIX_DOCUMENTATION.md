# Todo List Node.js – Vulnerability Fix Documentation

**Application**: TBZ m183 Todo List  
**Fixes Applied**: 2026-06-26  
**Branch**: m183

This document describes what was changed to remediate each of the 21 vulnerabilities listed in `VULNERABILITY_REPORT.md`.

---

## Fix Summary Table

| # | Vulnerability | Severity | File(s) Changed | Status |
|---|---|---|---|---|
| 1 | SQL Injection – Login | CRITICAL | `login.js`, `fw/db.js` | Fixed |
| 2 | Plain-text Password Storage | CRITICAL | `login.js`, `package.json` | Fixed |
| 3 | Credentials via GET Request | HIGH | `login.js` | Fixed |
| 4 | Hardcoded Session Secret | HIGH | `app.js` | Fixed |
| 5 | Weak Cookie-Based Authentication | HIGH | `app.js`, `login.js` | Fixed |
| 6 | SQL Injection – Edit Task | CRITICAL | `edit.js`, `fw/db.js` | Fixed |
| 7 | XSS – Edit Task Attributes | HIGH | `edit.js` | Fixed |
| 8 | SQL Injection – Save Task | CRITICAL | `savetask.js` | Fixed |
| 9 | SQL Injection – Search V2 | CRITICAL | `search/v2/index.js` | Fixed |
| 10 | SQL Injection – Tasklist | CRITICAL | `user/tasklist.js` | Fixed |
| 11 | XSS – Tasklist Output | HIGH | `user/tasklist.js` | Fixed |
| 12 | SQL Injection – Header | CRITICAL | `fw/header.js` | Fixed |
| 13 | XSS – Index Username | HIGH | `index.js` | Fixed |
| 14 | XSS – Profile Route | HIGH | `app.js` | Fixed (route removed) |
| 15 | XSS – Background Search JS Context | HIGH | `user/backgroundsearch.js` | Fixed |
| 16 | SSRF | CRITICAL | `search.js` | Fixed |
| 17 | Exposed Passwords in Admin Panel | CRITICAL | `admin/users.js` | Fixed |
| 18 | Missing Access Control on Admin Panel | HIGH | `app.js` | Fixed |
| 19 | XSS – Admin Panel Output | HIGH | `admin/users.js` | Fixed |
| 20 | Hardcoded Database Credentials | HIGH | `config.js`, `.env.example`, `.gitignore` | Fixed |
| 21 | Missing CSRF Protection | MEDIUM | `app.js`, all forms | Fixed |

---

## Detailed Fixes

### Fix 1 — SQL Injection in Login (`login.js:37`)

**Root cause**: The SQL query concatenated the username directly into the query string.

**Before**:
```javascript
const sql = `SELECT id, username, password FROM users WHERE username='` + username + `'`;
const [results] = await conn.query(sql);
```

**After** (`login.js`):
```javascript
const rows = await db.executeStatement(
    `SELECT u.id, u.username, u.password, COALESCE(r.id, 0) roleid
     FROM users u
     LEFT JOIN permissions p ON u.id = p.userid
     LEFT JOIN roles r ON p.roleID = r.id
     WHERE u.username = ?`,
    [username]
);
```

The `executeStatement` helper in `fw/db.js` always calls `conn.execute(statement, params)`, which uses MySQL prepared statements. The `?` placeholder is resolved by the driver, never concatenated.

---

### Fix 2 — Plain-text Password Storage (`login.js:48`, `package.json`)

**Root cause**: Passwords were stored and compared as plain text. Any database read would expose all credentials.

**Before**:
```javascript
if (password == db_password) { ... }
```

**After** (`login.js`):
```javascript
const bcrypt = require('bcryptjs');

// Comparison at login:
if (storedHash.startsWith('$2b$') || storedHash.startsWith('$2a$')) {
    passwordOk = await bcrypt.compare(password, storedHash);
} else {
    // Legacy plain-text — compare once, then migrate hash automatically
    passwordOk = (password === storedHash);
    if (passwordOk) {
        const newHash = await bcrypt.hash(password, 12);
        await db.executeStatement('UPDATE users SET password = ? WHERE id = ?', [newHash, row.id]);
    }
}
```

`bcryptjs` was added as an explicit dependency in `package.json`. The migration path means existing plain-text records are upgraded to bcrypt on first successful login without requiring a manual migration script.

---

### Fix 3 — Credentials Transmitted via GET Request (`login.js`)

**Root cause**: The login form used `GET`, putting username and password in the URL — visible in logs, browser history, and referrer headers.

**Before** (`login.js`):
```javascript
if (typeof req.query.username !== 'undefined' && ...) {
    let result = await validateLogin(req.query.username, req.query.password);
```

**After**: The login function now only processes credentials when `req.method === 'POST'`:
```javascript
if (req.method !== 'POST') {
    return { html: getHtml(req), user: { username: '', userid: 0 } };
}
const username = req.body.username || '';
const password = req.body.password || '';
```

In `app.js`, `GET /login` only renders the form; `POST /login` handles credentials. The form in `getHtml()` uses `method="post"`.

---

### Fix 4 — Hardcoded Session Secret (`app.js:20`)

**Root cause**: `secret: 'secret'` allowed any attacker who read the source to forge valid session cookies.

**Before**:
```javascript
app.use(session({ secret: 'secret', resave: true, saveUninitialized: true }));
```

**After** (`app.js`):
```javascript
const SESSION_SECRET = process.env.SESSION_SECRET || crypto.randomBytes(32).toString('hex');
app.use(session({
    secret: SESSION_SECRET,
    resave: false,
    saveUninitialized: false,
    cookie: { httpOnly: true, sameSite: 'lax' }
}));
```

- `httpOnly: true` prevents client-side JS from reading the session cookie.
- `sameSite: 'lax'` provides CSRF mitigation for cross-site navigations.
- `saveUninitialized: false` avoids creating sessions for unauthenticated visitors.
- In production, `SESSION_SECRET` must be set via the environment (see `.env.example`).

---

### Fix 5 — Weak Cookie-Based Authentication (`app.js`)

**Root cause**: `activeUserSession` only checked for the presence of a `username` cookie. Attackers could set `document.cookie = "username=admin"` to bypass authentication entirely.

**Before**:
```javascript
function activeUserSession(req) {
    return req.cookies?.username !== undefined && req.cookies.username !== '';
}
```

**After** (`app.js`):
```javascript
function requireAuth(req, res, next) {
    if (req.session?.userid) return next();
    res.redirect('/login');
}
```

All protected routes use `requireAuth` as Express middleware. The session is managed server-side by `express-session`; the client only holds a signed, `httpOnly` session cookie that cannot be forged or read by JavaScript.

---

### Fix 6 — SQL Injection in Edit Task (`edit.js:16`)

**Root cause**: `taskId` from `req.query.id` was directly interpolated into the SQL string.

**Before**:
```javascript
let [result, fields] = await conn.query('select ID, title, state from tasks where ID = ' + taskId);
```

**After** (`edit.js`):
```javascript
const rows = await db.executeStatement(
    'SELECT ID, title, state FROM tasks WHERE ID = ?',
    [taskId]
);
```

---

### Fix 7 — XSS in Edit Task HTML Attributes (`edit.js:29,32`)

**Root cause**: `taskId` and `title` were interpolated into HTML attribute values without escaping, allowing attribute-breaking XSS payloads.

**Before**:
```javascript
html += `<input type="hidden" name="id" value="` + taskId + `" />`;
html += `<input ... value="` + title + `">`;
```

**After** (`edit.js`):
```javascript
const escapeHtml = require('escape-html');
// ...
html += `<input type="hidden" name="id" value="${escapeHtml(taskId)}">`;
html += `<input ... value="${escapeHtml(title)}">`;
```

`escape-html` converts `"`, `<`, `>`, `&`, `'` to their HTML entity equivalents, preventing any injected content from escaping the attribute context.

---

### Fix 8 — SQL Injection in Save Task (`savetask.js:10,22,24`)

**Root cause**: `title`, `state`, and `taskId` were concatenated directly into INSERT and UPDATE statements.

**Before**:
```javascript
let stmt = db.executeStatement("insert into tasks (title, state, userID) values ('" + title + "', '" + state + "', '" + userid + "')");
```

**After** (`savetask.js`):
```javascript
await db.executeStatement(
    'INSERT INTO tasks (title, state, userID) VALUES (?, ?, ?)',
    [title, state, userid]
);
// and for updates:
await db.executeStatement(
    'UPDATE tasks SET title = ?, state = ? WHERE ID = ? AND userID = ?',
    [title, state, id, userid]
);
```

The `userid` is always taken from `req.session.userid` (server-authoritative), never from user input. The UPDATE also checks `userID = ?` so users cannot overwrite each other's tasks.

---

### Fix 9 — SQL Injection in Search V2 (`search/v2/index.js:12`)

**Root cause**: `userid` and `terms` from query parameters were concatenated into the LIKE query.

**Before**:
```javascript
let stmt = await db.executeStatement("select ... where userID = " + userid + " and title like '%" + terms + "%'");
```

**After** (`search/v2/index.js`):
```javascript
// userid is always taken from the server-side session
const userid = req.session.userid;
const rows = await db.executeStatement(
    "SELECT ID, title, state FROM tasks WHERE userID = ? AND title LIKE ?",
    [userid, `%${terms}%`]
);
```

The userid parameter in the query string is now ignored; the session value is used instead, which also prevents users from searching other users' tasks.

---

### Fix 10 — SQL Injection in Tasklist (`user/tasklist.js:17`)

**Root cause**: `req.cookies.userid` was directly interpolated into the query.

**Before**:
```javascript
let [result, fields] = await conn.query('select ... from tasks where UserID = ' + req.cookies.userid);
```

**After** (`user/tasklist.js`):
```javascript
const rows = await db.executeStatement(
    'SELECT ID, title, state FROM tasks WHERE UserID = ?',
    [req.session.userid]
);
```

---

### Fix 11 — XSS in Tasklist Output (`user/tasklist.js:23,26`)

**Root cause**: `row.ID`, `row.title`, and `row.state` were injected directly into HTML.

**Before**:
```javascript
html += `<td>` + row.ID + `</td>`;
html += `<td class="wide">` + row.title + `</td>`;
```

**After** (`user/tasklist.js`):
```javascript
const escapeHtml = require('escape-html');
// ...
html += `<td>${escapeHtml(String(row.ID))}</td>`;
html += `<td class="wide">${escapeHtml(row.title)}</td>`;
html += `<td>${escapeHtml(ucfirst(row.state))}</td>`;
// href attributes also escaped:
html += `<a href="edit?id=${escapeHtml(String(row.ID))}">edit</a>`;
```

---

### Fix 12 — SQL Injection in Header (`fw/header.js:23`)

**Root cause**: `id` from `req.cookies.userid` was interpolated into the role-lookup query.

**Before**:
```javascript
let stmt = await db.executeStatement("select ... where userid = " + id);
```

**After** (`fw/header.js`):

The DB query was removed entirely. The user's `roleid` is now stored in the session at login time:

```javascript
// login.js — at login, roleid is fetched once and stored in session
req.session.roleid = row.roleid;

// fw/header.js — reads from session, no DB call
if (req.session.roleid === 1) {
    content += `<li><a href="/admin/users">User List</a></li>`;
}
```

This removes a network round-trip on every page load and eliminates the injection surface entirely.

---

### Fix 13 — XSS in Index Username Output (`index.js:6`)

**Root cause**: `req.cookies.username` was interpolated into the welcome message without escaping.

**Before**:
```javascript
return `<h2>Welcome, ` + req.cookies.username + `!</h2>` + ...;
```

**After** (`index.js`):
```javascript
const escapeHtml = require('escape-html');
const username = escapeHtml(req.session?.username || '');
return `<h2>Welcome, ${username}!</h2>${taskListHtml}<hr>${bgSearch.html(req)}`;
```

---

### Fix 14 — XSS in Profile Route (`app.js:95`)

**Root cause**: `req.session.username` was interpolated without escaping into the response.

**Before**:
```javascript
app.get('/profile', (req, res) => {
    res.send(`Welcome, ${req.session.username}! <a href="/logout">Logout</a>`);
});
```

**After**: The `/profile` route was removed. It served no application purpose; the welcome message is rendered in `index.js` with proper escaping (see Fix 13).

---

### Fix 15 — XSS in Background Search JavaScript Context (`user/backgroundsearch.js:34`)

**Root cause**: `req.cookies.userid` was interpolated directly into a `<script>` block, allowing an attacker to break out of the JavaScript string literal.

**Before**:
```javascript
userid = ` + req.cookies.userid + `;
// Payload: cookie value "1; alert('XSS'); //" → userid = 1; alert('XSS'); // ;
```

**After** (`user/backgroundsearch.js`):
```javascript
const userid = JSON.stringify(req.session?.userid ?? null);
// In the template:
var userid = ${userid};  // JSON.stringify produces a quoted, escaped numeric literal
```

`JSON.stringify` on a number produces e.g. `1` (no quotes needed, no injection possible). If somehow a non-number ended up in the session, `JSON.stringify` would still produce a safe quoted string. The CSRF token is also passed the same way.

---

### Fix 16 — SSRF (`search.js:15`)

**Root cause**: The `provider` body parameter was directly appended to `http://localhost:3000`, allowing an attacker to supply arbitrary paths or full URLs.

**Before**:
```javascript
let theUrl = 'http://localhost:3000' + provider + '?userid=' + userid + '&terms=' + terms;
```

**After** (`search.js`):
```javascript
const ALLOWED_PROVIDERS = ['/search/v2/'];
// ...
if (!ALLOWED_PROVIDERS.includes(provider)) {
    return 'Invalid search provider';
}
// Build URL from whitelisted path only; userid comes from session:
const url = `http://localhost:3000${provider}?userid=${encodeURIComponent(userid)}&terms=${encodeURIComponent(terms)}`;
```

Only exact matches against `ALLOWED_PROVIDERS` are accepted. Any other path — including `/../`, absolute URLs, or `file://` — is rejected before the HTTP request is made.

---

### Fix 17 — Exposed Passwords in Admin Panel (`admin/users.js:19`)

**Root cause**: The query joined the `users` table and included the `password` column, then embedded it in a hidden input in the HTML.

**Before**:
```javascript
html += `... <input type='hidden' name='password' value='` + record.password + `' />`;
```

**After** (`admin/users.js`):
```javascript
// Query does NOT select the password column at all
const rows = await db.executeStatement(
    `SELECT u.ID, u.username, r.title
     FROM users u
     INNER JOIN permissions p ON u.ID = p.userID
     INNER JOIN roles r ON p.roleID = r.ID
     ORDER BY u.username`
);
// Table only renders ID, username, role — no password field
```

---

### Fix 18 — Missing Access Control on Admin Panel (`app.js:51-58`)

**Root cause**: The `/admin/users` route only checked `activeUserSession` (was the user logged in?), not whether they were an admin.

**Before**:
```javascript
app.get('/admin/users', async (req, res) => {
    if (activeUserSession(req)) {  // any logged-in user could access this
        let html = await wrapContent(await adminUser.html, req);
        res.send(html);
    }
});
```

**After** (`app.js`):
```javascript
function requireAdmin(req, res, next) {
    if (req.session?.userid && req.session.roleid === 1) return next();
    res.status(403).send('Forbidden');
}

app.get('/admin/users', requireAuth, requireAdmin, async (req, res) => {
    res.send(await wrapContent(await adminUser.html(), req));
});
```

`roleid` is stored in the session at login from the DB (see Fix 12). Non-admin users receive HTTP 403.

---

### Fix 19 — XSS in Admin Panel Output (`admin/users.js:19`)

**Root cause**: `record.ID`, `record.username`, and `record.title` (role name) were interpolated without escaping.

**Before**:
```javascript
html += `<tr><td>` + record.ID + `</td><td>` + record.username + `</td><td>` + record.title + `</td>...`;
```

**After** (`admin/users.js`):
```javascript
const escapeHtml = require('escape-html');
// ...
html += `<tr>
    <td>${escapeHtml(String(row.ID))}</td>
    <td>${escapeHtml(row.username)}</td>
    <td>${escapeHtml(row.title)}</td>
</tr>`;
```

---

### Fix 20 — Hardcoded Database Credentials (`config.js`)

**Root cause**: DB credentials were hardcoded in `config.js`, making them visible in source control and any codebase backup.

**Before**:
```javascript
module.exports = {
    host: 'm183-lb2-db',
    user: 'root',
    password: 'Some.Real.Secr3t',
    database: 'm183_lb2'
};
```

**After** (`config.js`):
```javascript
if (!process.env.DB_PASS) {
    throw new Error('DB_PASS environment variable is required.');
}
module.exports = {
    host:     process.env.DB_HOST || 'm183-lb2-db',
    user:     process.env.DB_USER || 'root',
    password: process.env.DB_PASS,
    database: process.env.DB_NAME || 'm183_lb2'
};
```

- The application now refuses to start without `DB_PASS`, preventing accidental deployment with no password.
- `.env.example` was created with placeholder values to guide setup.
- `.gitignore` was created to prevent `.env` from ever being committed.

---

### Fix 21 — Missing CSRF Protection (all POST endpoints)

**Root cause**: No CSRF token was included in forms or verified on state-changing requests. An attacker could host a hidden form on another site that would submit actions on behalf of an authenticated user.

**After** (`app.js`):
```javascript
// Middleware: generate a per-session token
app.use((req, _res, next) => {
    if (!req.session.csrfToken) {
        req.session.csrfToken = crypto.randomBytes(32).toString('hex');
    }
    next();
});

// Middleware: verify token on all mutating requests
function csrfCheck(req, res, next) {
    if (['POST', 'PUT', 'PATCH', 'DELETE'].includes(req.method)) {
        const token = req.body?._csrf || req.headers['x-csrf-token'];
        if (!token || token !== req.session.csrfToken) {
            return res.status(403).send('Forbidden: invalid or missing CSRF token');
        }
    }
    next();
}
```

Every POST route (`/login`, `/savetask`, `/search`) uses `csrfCheck`. Every form template includes:
```html
<input type="hidden" name="_csrf" value="${req.session.csrfToken}">
```

The AJAX search in `backgroundsearch.js` reads the token via `JSON.stringify(req.session.csrfToken)` and sends it as `_csrf` in the POST body.

---

## Files Changed

| File | Change |
|---|---|
| `login.js` | Parameterized query, bcrypt, POST-only, CSRF token in form |
| `app.js` | Session setup, CSRF middleware, `requireAuth`/`requireAdmin`, all routes protected |
| `config.js` | Env-var credentials, hard-fail if `DB_PASS` missing |
| `edit.js` | Parameterized query, `escapeHtml` on all outputs, CSRF token in form |
| `savetask.js` | Parameterized queries, userid from session, ownership check on update |
| `search.js` | Provider whitelist, userid from session, `encodeURIComponent` on URL params |
| `search/v2/index.js` | Parameterized query, userid from session only, `escapeHtml` on output |
| `user/tasklist.js` | Parameterized query, `escapeHtml` on all outputs |
| `user/backgroundsearch.js` | `JSON.stringify` for JS context, CSRF token passed to AJAX |
| `fw/header.js` | DB query removed; role read from session |
| `fw/db.js` | `executeStatement` wrapper enforces parameterized queries |
| `index.js` | `escapeHtml` on username, reads from session |
| `admin/users.js` | Password column removed from query, `escapeHtml` on all outputs |
| `package.json` | Added `bcryptjs`, `escape-html`; removed unused `cookie-parser` |
| `.env.example` | Created — template for required environment variables |
| `.gitignore` | Created — excludes `.env` and `node_modules/` from git |
