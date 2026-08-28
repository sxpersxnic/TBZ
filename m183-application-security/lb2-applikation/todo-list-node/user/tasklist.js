const escapeHtml = require('escape-html');
const db = require('../fw/db');

async function getHtml(req) {
    let html = `
    <section id="list">
        <a href="edit">Create Task</a>
        <table>
            <tr>
                <th>ID</th>
                <th>Description</th>
                <th>State</th>
                <th></th>
            </tr>
    `;

    try {
        const rows = await db.executeStatement(
            'SELECT ID, title, state FROM tasks WHERE UserID = ?',
            [req.session.userid]
        );

        for (const row of rows) {
            html += `
            <tr>
                <td>${escapeHtml(String(row.ID))}</td>
                <td class="wide">${escapeHtml(row.title)}</td>
                <td>${escapeHtml(ucfirst(row.state))}</td>
                <td>
                    <a href="edit?id=${escapeHtml(String(row.ID))}">edit</a> |
                    <a href="delete?id=${escapeHtml(String(row.ID))}">delete</a>
                </td>
            </tr>`;
        }
    } catch (err) {
        console.error('Tasklist error:', err);
        html += '<tr><td colspan="4">Error loading tasks</td></tr>';
    }

    html += `
        </table>
    </section>`;

    return html;
}

function ucfirst(string) {
    if (!string) return '';
    return string.charAt(0).toUpperCase() + string.slice(1);
}

module.exports = { html: getHtml };
