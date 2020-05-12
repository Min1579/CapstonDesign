import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Navbar, Nav, Form, FormControl, Button, Row, Col, Table, Image, Figure, DropdownButton, Dropdown} from "react-bootstrap";
import {Link, Route} from "react-router-dom";
import RecommendList from "./components/RecommendList";
import VideoList from "./components/VideoList";

const App = () => {
    return (
        <div>
            <div>
                <Navbar bg="dark" variant="dark">
                    <Navbar.Brand href="#home">HeyThere</Navbar.Brand>
                    <Nav className="mr-auto">
                        <Nav.Link href="#home">탐색</Nav.Link>
                        <Nav.Link href="#features">Features</Nav.Link>
                        <Nav.Link href="#pricing">Pricing</Nav.Link>
                    </Nav>
                    <Form inline alignCenter>
                        <FormControl type="text" placeholder="Search" className="mr-sm-2" />
                        <Button variant="outline-info">Search</Button>
                    </Form>

                    <DropdownButton
                        alignRight
                        title="Dropdown right"
                        id="dropdown-menu-align-right"
                        variant="outline-info"
                    >
                        <Dropdown.Item eventKey="1">Action</Dropdown.Item>
                        <Dropdown.Item eventKey="2">Another action</Dropdown.Item>
                        <Dropdown.Item eventKey="3">Something else here</Dropdown.Item>
                        <Dropdown.Divider />
                        <Dropdown.Item eventKey="4">Separated link</Dropdown.Item>
                    </DropdownButton>
                </Navbar>
                <Row>
                    <Col sm={2}>
                        <RecommendList/>
                    </Col>
                    <Col sm={10} fluid>
                        <VideoList/>
                    </Col>
                </Row>
            </div>
        </div>
    );
};

export default App;
