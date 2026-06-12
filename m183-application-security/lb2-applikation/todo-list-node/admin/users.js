const db = require('../fw/db');

async function getHtml() {
    let conn = await db.connectDB();
    let html = '';
    let [result,fields] = await conn.query("SELECT users.ID, users.username, users.password, roles.title FROM users inner join permissions on users.ID = permissions.userID inner join roles on permissions.roleID = roles.ID order by username");

    html += `
    <h2>User List</h2>

    <table>
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Role</th>
        </tr>`;

    result.map(function (record) {
        html += `<tr><td>`+record.ID+`</td><td>`+record.username+`</td><td>`+record.title+`</td><input type='hidden' name='password' value='`+record.password+`' /></tr>`;
    });

    html += `
    </table>`;

    return html;
}

module.exports = { html: getHtml() };