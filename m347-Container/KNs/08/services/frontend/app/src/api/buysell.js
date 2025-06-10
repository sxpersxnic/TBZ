const buySellUrl = process.env.REACT_APP_BUYSELL_URL;
const buyUrl = buySellUrl + "/buy";
const sellUrl = buySellUrl + "/sell";

/**
 * @param {number} uid
 * @param {number} amount
 */
function buyApiRequest(uid, amount) {
    const data = {
        id: uid,
        amount: amount
    }
    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    };
    return fetch(buyUrl, requestOptions)
        .then(response => response.json())
        .catch(error => {
            alert("Failed to call the URL. Is the api up and running?");
        });
}

/**
 * @param {number} uid
 * @param {number} amount
 */
function sellApiRequest(uid, amount) {
    const data = {
        id: uid,
        amount: amount
    }
    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    };
    return fetch(sellUrl, requestOptions)
        .then(response => response.json())
        .catch(error => {
            alert("Failed to call the URL. Is the api up and running?");
        });
}


export default {
    buy: buyApiRequest,
    sell: sellApiRequest
};