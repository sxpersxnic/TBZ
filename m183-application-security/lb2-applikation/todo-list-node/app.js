const crypto = require('node:crypto');
const express = require('express');
const session = require('express-session');
const path = require('node:path');

const header         = require('./fw/header');
const footer         = require('./fw/footer');
const login          = require('./login');
const index          = require('./index');
const adminUser      = require('./admin/users');
const editTask       = require('./edit');
const saveTask       = require('./savetask');
const search         = require('./search');
const searchProvider = require('./search/v2/index');

const app  = express();
const PORT = 3000;

// ── Session ────────────────────────────────────────────────────────────────
// SESSION_SECRET must be set in production via environment variable.
const SESSION_SECRET = process.env.SESSION_SECRET || crypto.randomBytes(32).toString('hex');

app.use(session({
    secret: SESSION_SECRET,
    resave: false,
    saveUninitialized: false,
    cookie: {
        httpOnly: true,   // JS cannot read the session cookie
        sameSite: 'lax',  // CSRF mitigation
    }
}));

// ── Body parsers ───────────────────────────────────────────────────────────
app.use(express.urlencoded({ extended: true }));
app.use(express.json());
app.use(express.static(path.join(__dirname, 'public')));

// ── CSRF token ─────────────────────────────────────────────────────────────
// Generate a per-session token; verify it on every state-changing request.
app.use((req, _res, next) => {
    if (!req.session.csrfToken) {
        req.session.csrfToken = crypto.randomBytes(32).toString('hex');
    }
    next();
});

function csrfCheck(req, res, next) {
    if (['POST', 'PUT', 'PATCH', 'DELETE'].includes(req.method)) {
        const token = req.body?._csrf || req.headers['x-csrf-token'];
        if (!token || token !== req.session.csrfToken) {
            return res.status(403).send('Forbidden: invalid or missing CSRF token');
        }
    }
    next();
}

// ── Auth helpers ───────────────────────────────────────────────────────────
function requireAuth(req, res, next) {
    if (req.session?.userid) return next();
    res.redirect('/login');
}

function requireAdmin(req, res, next) {
    if (req.session?.userid && req.session.roleid === 1) return next();
    res.status(403).send('Forbidden');
}

// ── Routes ─────────────────────────────────────────────────────────────────

app.get('/', requireAuth, async (req, res) => {
    res.send(await wrapContent(await index.html(req), req));
});

app.get('/edit', requireAuth, async (req, res) => {
    res.send(await wrapContent(await editTask.html(req), req));
});

app.post('/savetask', requireAuth, csrfCheck, async (req, res) => {
    res.send(await wrapContent(await saveTask.html(req), req));
});

app.get('/admin/users', requireAuth, requireAdmin, async (req, res) => {
    res.send(await wrapContent(await adminUser.html(), req));
});

// Login: GET shows form, POST processes credentials
app.get('/login', async (req, res) => {
    if (req.session?.userid) return res.redirect('/');
    const content = await login.handleLogin(req);
    res.send(await wrapContent(content.html, req));
});

app.post('/login', csrfCheck, async (req, res) => {
    const content = await login.handleLogin(req);
    if (content.user.userid !== 0) {
        login.startUserSession(req, res, content.user);
    } else {
        res.send(await wrapContent(content.html, req));
    }
});

app.get('/logout', (req, res) => {
    req.session.destroy();
    res.redirect('/login');
});

// Search — auth required; CSRF checked via csrfCheck
app.post('/search', requireAuth, csrfCheck, async (req, res) => {
    res.send(await search.html(req));
});

app.get('/search/v2/', requireAuth, async (req, res) => {
    res.send(await searchProvider.search(req));
});

// ── Start ──────────────────────────────────────────────────────────────────
app.listen(PORT, () => {
    console.log(`Server running on http://localhost:${PORT}`);
});

// ── Helpers ────────────────────────────────────────────────────────────────
async function wrapContent(content, req) {
    return (await header(req)) + content + footer;
}
