const accountUrl = process.env.REACT_APP_ACCOUNT_URL;
const holdingsUrl = accountUrl + "/Account/Cryptos/?userid=<userId>";
const friendsUrl = accountUrl + "/Account/Friends/?userid=<userId>";

/**
 * @param {number} uid 
 */
function holdingsApiRequest(uid) {

    var theUrl = holdingsUrl.replace("<userId>", uid);
    const requestOptions = {
        method: 'GET'//,'POST'
        //headers: { 'Content-Type': 'application/json' },
        //body: JSON.stringify()
    };
    return fetch(theUrl, requestOptions)
        .then(response => response.json())
        .catch(error => {
            alert("Failed to call the Account URL. Is the api up and running?");
        });
}

/**
 * @param {number} uid 
 */
function friendsApiRequest(uid) {

    var theUrl = friendsUrl.replace("<userId>", uid);
    const requestOptions = {
        method: 'GET'//, 'POST'
        //headers: { 'Content-Type': 'application/json' },
        //body: JSON.stringify()
    };
    return fetch(theUrl, requestOptions)
        .then(response => response.json())
        .catch(error => {
            alert("Failed to call the Account URL. Is the api up and running?");
        });
}


export default {
    holdings: holdingsApiRequest,
    friends: friendsApiRequest
};