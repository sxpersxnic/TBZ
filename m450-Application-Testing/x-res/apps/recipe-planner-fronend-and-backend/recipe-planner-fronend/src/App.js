import Recipe from "./components/Recipe/Recipe";
import MyNavbar from "./components/MyNavbar/MyNavbar";
import {findAllByDisplayValue} from "@testing-library/react";
import Browse from "./components/Browse/Browse";
import {Route, Routes} from "react-router-dom";
import Planer from "./components/Planer/Planer";
import AddRecipe from "./components/AddRecipe/AddRecipe";
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';


function App() {
  return (
      <>


      <MyNavbar/>
      <div>
        <Routes>
          <Route path="/" element={<Browse/>} />
          <Route path="/planer" element={<Planer/>} />
          <Route path="/new-menues" element={<AddRecipe/>} />
        </Routes>
      </div>
      </>
  );
}

export default App;
