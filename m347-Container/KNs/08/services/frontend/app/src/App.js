import logo from './logo.svg';
import './App.css';
import Crypto from './Crypto';
import Friends from './Friends';



function App() {
  //console.log(process);
  //console.log(process.env);

  localStorage.setItem("userid", process.env.REACT_APP_USER_LOGGED_IN);

  
  return (
    <>
    <main>
      <header>
        <div>Frontend for the Kubernetes Microservices Example</div>
        <nav>
            <ul>
                <li><a href="/">Home</a></li>
            </ul>
        </nav>
      </header>
      <Crypto />
      <Friends />
    </main>
    <footer>
      &copy;TBZ
      &nbsp;&nbsp;&nbsp;&nbsp;
  </footer>
  </>
  );
}

export default App;
