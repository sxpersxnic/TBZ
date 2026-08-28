const escapeHtml = require('escape-html');
const db = require('./fw/db');

async function getHtml(req) {
    let title = '';
    let state = '';
    let taskId = '';
    const options = ['Open', 'In Progress', 'Done'];

    if (req.query.id !== undefined) {
        taskId = req.query.id;
        try {
            const rows = await db.executeStatement(
                'SELECT ID, title, state FROM tasks WHERE ID = ?',
                [taskId]
            );
            if (rows.length > 0) {
                title = rows[0].title;
                state = rows[0].state;
            } else {
                taskId = '';
            }
        } catch (err) {
            console.error('Edit query error:', err);
            taskId = '';
        }
    }

    const csrf = `<input type="hidden" name="_csrf" value="${req?.session?.csrfToken || ''}">`;

    let html = taskId ? '<h1>Edit Task</h1>' : '<h1>Create Task</h1>';
    html += `
    <form id="form" method="post" action="savetask">
        ${csrf}
        <input type="hidden" name="id" value="${escapeHtml(taskId)}">
        <div class="form-group">
            <label for="title">Description</label>
            <input type="text" class="form-control size-medium" name="title" id="title" value="${escapeHtml(title)}">
        </div>
        <div class="form-group">
            <label for="state">State</label>
            <select name="state" id="state" class="size-auto">`;

    for (const opt of options) {
        const selected = state === opt.toLowerCase() ? 'selected' : '';
        html += `<option value="${opt.toLowerCase()}" ${selected}>${escapeHtml(opt)}</option>`;
    }

    html += `
            </select>
        </div>
        <div class="form-group">
            <label for="submit"></label>
            <input id="submit" type="submit" class="btn size-auto" value="Submit">
        </div>
    </form>
    <script>
        $(document).ready(function () {
            $('#form').validate({
                rules: { title: { required: true } },
                messages: { title: 'Please enter a description.' },
                submitHandler: function (form) { form.submit(); }
            });
        });
    </script>`;

    return html;
}

module.exports = { html: getHtml };
