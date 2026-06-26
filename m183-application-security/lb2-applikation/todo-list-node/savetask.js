const db = require('./fw/db');

async function getHtml(req) {
    const { title, state, id } = req.body;

    if (title === undefined || state === undefined) {
        return "<span class='info info-error'>No update was made</span>";
    }

    // userid comes from the server-side session — never from user input
    const userid = req.session.userid;

    try {
        if (!id || id.length === 0) {
            await db.executeStatement(
                'INSERT INTO tasks (title, state, userID) VALUES (?, ?, ?)',
                [title, state, userid]
            );
        } else {
            // Verify the task belongs to this user before updating
            const existing = await db.executeStatement(
                'SELECT ID FROM tasks WHERE ID = ? AND userID = ?',
                [id, userid]
            );
            if (existing.length === 0) {
                return "<span class='info info-error'>Task not found</span>";
            }
            await db.executeStatement(
                'UPDATE tasks SET title = ?, state = ? WHERE ID = ? AND userID = ?',
                [title, state, id, userid]
            );
        }
        return "<span class='info info-success'>Update successfull</span>";
    } catch (err) {
        console.error('Savetask error:', err);
        return "<span class='info info-error'>An error occurred</span>";
    }
}

module.exports = { html: getHtml };
