const axios = require('axios');

const ALLOWED_PROVIDERS = ['/search/v2/'];

async function getHtml(req) {
    if (!req.body.provider || !req.body.terms || !req.body.userid) {
        return 'Not enough information provided';
    }

    const provider = req.body.provider;
    const terms    = req.body.terms;
    const userid   = req.session?.userid;

    if (!userid) {
        return 'Unauthorised';
    }

    if (!ALLOWED_PROVIDERS.includes(provider)) {
        return 'Invalid search provider';
    }

    // Build URL from whitelisted path only — never from user-supplied input
    const url = `http://localhost:3000${provider}?userid=${encodeURIComponent(userid)}&terms=${encodeURIComponent(terms)}`;

    try {
        const response = await axios.get(url);
        return response.data;
    } catch {
        return 'No results found!';
    }
}

module.exports = { html: getHtml };
