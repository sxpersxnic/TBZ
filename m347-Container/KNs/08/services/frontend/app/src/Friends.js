import React, { useState, useEffect } from 'react';
import account from './api/account';
import sendreceive from './api/sendreceive';
import './Friends.css';


function Friends() {
  const [friends, setFriends] = useState([]);
  const [amount, setAmount] = useState(0);
  const [friendid, setFriendId] = useState("");
  useEffect(() => {
    let result = account.friends(localStorage.getItem("userid"))
    .then(data => {
      if (data == undefined) data = [];
      setFriends(data)
    });
  }, []);

  const onClick = ({ target }) => setFriendId(target.id); //console.log(target); //setFriendId("hallo");
  const onClickSend = () => {
    sendreceive.send(friendid, localStorage.getItem("userid"), amount)
      .then(data => {
        setAmount(0);
        setFriendId("");
      });
    
  }

  return (
    <section className="friends">
      <h1>Friends</h1>
      <div className='friends-list'>
        {friends.map(item => (
            <div key={item.id}>
              <span>{item.name}</span>
              <span className='link' id={item.id} onClick={onClick}>Send Crypto</span>
            </div>
          ))}
      </div>
      <div className="form-group">
        <label htmlFor="friendid">Friend Id: </label>
        <input type="text" id="friendid" name="friendid" className="form-control size-medium" readOnly disabled value={friendid} />
      </div>
      <div className="form-group">
        <label htmlFor="amount">Amount: </label>
        <input type="number" id="amount" name="amount" className="form-control size-small" value={amount} onChange={(event) => setAmount(event.target.value)} />
      </div>
      <div className="form-group">
        <label htmlFor="nothing"></label>
        <button onClick={() => onClickSend(12)}>Send</button>
      </div>
      
      
    </section>
  );
}

export default Friends;

