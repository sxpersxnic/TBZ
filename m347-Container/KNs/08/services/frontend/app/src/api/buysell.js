const buyUrl = process.env.REACT_APP_BUYSELL_BUY;
const sellUrl = process.env.REACT_APP_BUYSELL_SELL;

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