import React, {useState, useEffect} from 'react';
import axios from 'axios';
import {Navbar, Nav, NavDropdown, Form, FormControl, Button, Modal} from 'react-bootstrap';

const Navigation = () => {

    const [show, setShow] = useState(false);

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);



    return (
        <div>
            <Navbar bg="dark" variant="dark">
                <Navbar.Brand href="#home">조만간 트위치</Navbar.Brand>
                <Nav className="mr-auto">
                    <Nav.Link href="#home">찾기</Nav.Link>
                    <Nav.Link href="#features">탐색</Nav.Link>
                    <NavDropdown title="Dropdown" id="collasible-nav-dropdown">
                        <NavDropdown.Item onClick={handleShow}>로그인</NavDropdown.Item>
                        <NavDropdown.Item onClick={handleShow}>회원가입</NavDropdown.Item>
                        <NavDropdown.Item href="#action/3.2">로그아웃</NavDropdown.Item>
                        <NavDropdown.Divider />
                        <NavDropdown.Item href="#action/3.4">마이페이지</NavDropdown.Item>
                    </NavDropdown>
                </Nav>
                <Form inline>
                    <FormControl type="text" placeholder="Search" className="mr-sm-2" />
                    <Button variant="outline-info">검색</Button>
                </Form>
            </Navbar>

            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Modal heading</Modal.Title>
                </Modal.Header>
                <Modal.Body>Woohoo, you're reading this text in a modal!</Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Close
                    </Button>
                    <Button variant="primary" onClick={handleClose}>
                        Save Changes
                    </Button>
                </Modal.Footer>
            </Modal>
        </div>
    );
};

export default Navigation;
