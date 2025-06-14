const sendReceiveUrl = process.env.REACT_APP_SENDRECEIVE_URL;
const sendUrl = sendReceiveUrl + "/send";

/**
 * @param {number} receiverId 
 * @param {number} senderId 
 * @param {number} amount 
 */
function sendApiRequest(receiverId, senderId, amount) {
    const data = {
        id: senderId,
        receiverId: receiverId,
        amount: amount
    }
    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    };
    return fetch(sendUrl, requestOptions)
        .then(response => response.json())
        .catch(error => {
            alert("Failed to call the URL. Is the api up and running?");
        });
}

export default {
    send: sendApiRequest
};