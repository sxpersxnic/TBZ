import { useState } from 'react'
import './App.css'
import { Button } from './components/button'
import { Header } from './components/header'

function App() {
  const [count, setCount] = useState(0)
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [user, setUser] = useState(null);
  const createAccount = () => setUser({ name: 'Jane Doe' });
  const logIn = () => setIsLoggedIn(true);
  const logOut = () => setIsLoggedIn(false);
  return (
    <>
      <Header
        onLogin={() => logIn()}
        onLogout={() => logOut()}
        onCreateAccount={() => createAccount()}
      />
      <div className="card">
        <p>{isLoggedIn ? "Is logged in" : "Is not logged in"}</p>
        {user && <p>Username: {user.name}</p>}
      </div>
      <div className="card">
        <Button primary label={`count is ${count}`} onClick={() => setCount((count) => count + 1)} />
      </div>
    </>
  )
}

export default App
