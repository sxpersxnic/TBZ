import React, { useState, useEffect } from 'react';
import account from './api/account';
import buysell from './api/buysell';
import './Crypto.css';

function Crypto() {
  const [crypto, setCrypto] = useState(0);
  const [amount, setAmount] = useState(0);

  var loadData = () => {
    {
      let result = account.holdings(localStorage.getItem("userid"))
      .then(data => {
        setCrypto(data)
      });
    }
  };

  useEffect(() => {
    loadData();
  }, []);

  const onClickBuy = (target) => {
    buysell.buy(localStorage.getItem("userid"), amount)
      .then(data => {
        setAmount(0);
        loadData();
      });
    
  }

  const onClickSell = (target) => {
    buysell.sell(localStorage.getItem("userid"), amount)
    .then(data => {
      setAmount(0);
      loadData();
    });
  }

  return (
    <section className="crypto">
      <span className='crypto-label'>Your Crypto Amount: </span>
      <span className='crypto-amount'>{crypto}</span>
      <span className='crypto-name'> tbzCoin</span>
      <div className='crypto-buysell'>
        <div className="form-group">
          <label htmlFor="amount">Amount: </label>
          <input type="number" id="amount" name="amount" className="form-control size-small" value={amount} onChange={(event) => setAmount(event.target.value)}/>
        </div>
        <div className="form-group">
          <label htmlFor="nothing"></label>
          <button onClick={onClickBuy}>Buy</button>
          <button onClick={onClickSell}>Sell</button>
        </div>
      </div>
    </section>
  );
}

export default Crypto;
