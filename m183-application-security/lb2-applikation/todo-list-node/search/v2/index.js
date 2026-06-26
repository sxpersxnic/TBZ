const db = require('../../fw/db');
const escapeHtml = require('escape-html');

async function search(req) {
    if (!req.session?.userid) {
        return 'Unauthorised';
    }

    if (req.query.userid === undefined || req.query.terms === undefined) {
        return 'Not enough information to search';
    }

    // Always search within the logged-in user's tasks — ignore the userid query param
    const userid = req.session.userid;
    const terms  = req.query.terms;

    try {
        const rows = await db.executeStatement(
            "SELECT ID, title, state FROM tasks WHERE userID = ? AND title LIKE ?",
            [userid, `%${terms}%`]
        );

        if (rows.length === 0) return '';

        return rows.map(r => `${escapeHtml(r.title)} (${escapeHtml(r.state)})<br />`).join('');
    } catch (err) {
        console.error('Search v2 error:', err);
        return 'Search error';
    }
}

module.exports = { search };
