const db = require('./fw/db');

async function getHtml(req) {
    let html = '';
    let taskId = '';

    // see if the id exists in the database
    if (req.body.id !== undefined && req.body.id.length !== 0) {
        taskId = req.body.id;
        let stmt = await db.executeStatement('select ID, title, state from tasks where ID = ' + taskId);
        if (stmt.length === 0) {
            taskId = '';
        }
    }

    if (req.body.title !== undefined && req.body.state !== undefined){
        let state = req.body.state;
        let title = req.body.title;
        let userid = req.cookies.userid;

        if (taskId === ''){
            stmt = db.executeStatement("insert into tasks (title, state, userID) values ('"+title+"', '"+state+"', '"+userid+"')");
        } else {
            stmt = db.executeStatement("update tasks set title = '"+title+"', state = '"+state+"' where ID = "+taskId);
        }

        html += "<span class='info info-success'>Update successfull</span>";
    } else {
        html += "<span class='info info-error'>No update was made</span>";
    }

    return html;
}

module.exports = { html: getHtml }