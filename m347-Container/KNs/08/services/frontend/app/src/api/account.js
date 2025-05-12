const holdingsUrl = process.env.REACT_APP_ACCOUNT_HOLDINGS;
const friendsUrl = process.env.REACT_APP_ACCOUNT_FRIENDS;

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
            alert("Failed to call the URL. Is the api up and running?");
        });
}

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
            alert("Failed to call the URL. Is the api up and running?");
        });
}


export default {
    holdings: holdingsApiRequest,
    friends: friendsApiRequest
};