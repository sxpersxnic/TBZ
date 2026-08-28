const bcrypt = require('bcryptjs');
const escapeHtml = require('escape-html');
const db = require('./fw/db');

async function handleLogin(req) {
    // Only process credentials on POST; GET just shows the form.
    if (req.method !== 'POST') {
        return { html: getHtml(req), user: { username: '', userid: 0 } };
    }

    const username = req.body.username || '';
    const password = req.body.password || '';

    if (!username || !password) {
        return { html: getHtml(req, 'Please enter username and password.'), user: { username: '', userid: 0 } };
    }

    const result = await validateLogin(username, password);

    if (result.valid) {
        return { html: '', user: { username: result.username, userid: result.userId, roleid: result.roleid } };
    }
    return { html: getHtml(req, result.msg), user: { username: '', userid: 0 } };
}

function startUserSession(req, res, user) {
    req.session.userid   = user.userid;
    req.session.username = user.username;
    req.session.roleid   = user.roleid || 0;
    res.redirect('/');
}

async function validateLogin(username, password) {
    const result = { valid: false, msg: '', userId: 0, username: '', roleid: 0 };

    try {
        const rows = await db.executeStatement(
            `SELECT u.id, u.username, u.password, COALESCE(r.id, 0) roleid
             FROM users u
             LEFT JOIN permissions p ON u.id = p.userid
             LEFT JOIN roles r ON p.roleID = r.id
             WHERE u.username = ?`,
            [username]
        );

        if (rows.length === 0) {
            result.msg = 'Username does not exist';
            return result;
        }

        const row = rows[0];
        const storedHash = row.password;
        let passwordOk = false;

        if (storedHash.startsWith('$2b$') || storedHash.startsWith('$2a$')) {
            passwordOk = await bcrypt.compare(password, storedHash);
        } else {
            // Legacy plaintext — compare then migrate to bcrypt automatically
            passwordOk = (password === storedHash);
            if (passwordOk) {
                const newHash = await bcrypt.hash(password, 12);
                await db.executeStatement('UPDATE users SET password = ? WHERE id = ?', [newHash, row.id]);
            }
        }

        if (passwordOk) {
            result.valid    = true;
            result.userId   = row.id;
            result.username = row.username;
            result.roleid   = row.roleid;
        } else {
            result.msg = 'Incorrect password';
        }
    } catch (err) {
        console.error('Login error:', err);
        result.msg = 'Login error — please try again.';
    }

    return result;
}

function getHtml(req, errorMsg) {
    const csrf = `<input type="hidden" name="_csrf" value="${req?.session?.csrfToken || ''}">`;
    const error = errorMsg ? `<p class="info info-error">${escapeHtml(errorMsg)}</p>` : '';
    return `
    <h2>Login</h2>
    ${error}
    <form id="form" method="post" action="/login">
        ${csrf}
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" class="form-control size-medium" name="username" id="username" autocomplete="username">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control size-medium" name="password" id="password" autocomplete="current-password">
        </div>
        <div class="form-group">
            <label for="submit"></label>
            <input id="submit" type="submit" class="btn size-auto" value="Login">
        </div>
    </form>`;
}

module.exports = { handleLogin, startUserSession };
