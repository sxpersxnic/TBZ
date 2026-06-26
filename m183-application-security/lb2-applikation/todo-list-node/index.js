const escapeHtml = require('escape-html');
const tasklist = require('./user/tasklist');
const bgSearch = require('./user/backgroundsearch');

async function getHtml(req) {
    const taskListHtml = await tasklist.html(req);
    const username = escapeHtml(req.session?.username || '');
    return `<h2>Welcome, ${username}!</h2>${taskListHtml}<hr>${bgSearch.html(req)}`;
}

module.exports = { html: getHtml };
