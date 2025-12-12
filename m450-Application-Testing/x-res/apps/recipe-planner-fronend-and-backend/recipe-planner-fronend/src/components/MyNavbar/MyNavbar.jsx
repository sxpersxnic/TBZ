import React from 'react';
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from 'react-bootstrap/Navbar';


const MyNavbar=()=>{
    return (
        <>
        <Navbar bg="dark" data-bs-theme="dark">
            <Container>
                <Navbar.Brand href="/">Recipe Planner</Navbar.Brand>
                <Nav className="me-auto">
                    <Nav.Link href="/">Browse</Nav.Link>
                    <Nav.Link href="planer">Planer</Nav.Link>
                    <Nav.Link href="new-menues">Add Recipes</Nav.Link>
                </Nav>
            </Container>
        </Navbar>
        </>
    )

}

export default MyNavbar;