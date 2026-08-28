const escapeHtml = require('escape-html');
const db = require('../fw/db');

async function getHtml() {
    try {
        const rows = await db.executeStatement(
            `SELECT u.ID, u.username, r.title
             FROM users u
             INNER JOIN permissions p ON u.ID = p.userID
             INNER JOIN roles r ON p.roleID = r.ID
             ORDER BY u.username`
        );

        let html = `
    <h2>User List</h2>
    <table>
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Role</th>
        </tr>`;

        for (const row of rows) {
            html += `<tr>
            <td>${escapeHtml(String(row.ID))}</td>
            <td>${escapeHtml(row.username)}</td>
            <td>${escapeHtml(row.title)}</td>
        </tr>`;
        }

        html += `\n    </table>`;
        return html;
    } catch (err) {
        console.error('Admin users error:', err);
        return '<p>Error loading user list</p>';
    }
}

module.exports = { html: getHtml };
