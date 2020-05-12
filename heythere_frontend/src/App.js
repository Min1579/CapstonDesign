import React, { useState, useReducer } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Navbar, Nav, Form, FormControl, Button, Row, Col, Table, Image, Figure, DropdownButton, Dropdown, Modal} from "react-bootstrap";
import {Link, Route} from "react-router-dom";
import RecommendList from "./components/RecommendList";
import VideoList from "./components/VideoList";
import axios from "axios";





const loginUserReducer = (loginUserState, action) => {
    return {
        ...loginUserState,
        [action.name] : action.value,
    }
}

function Login() {
    const [loginUserState, dispatch] = useReducer(loginUserReducer, {
        usernameOrEmail:'',
        password:'',
    });


    const {usernameOrEmail, password} = loginUserState;

    const handleSubmit = () => {
        axios.post("http://172.30.1.28:8080/api/auth/signin", loginUserState
            ).then((res) => {
                console.log(res.data);
            }).catch((err) => {
            console.log(err);
        });
        setShow(false);
    }

    const onChange = e => {
        dispatch(e.target);
    }

    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    return (
        <div>
            <Button variant="light" onClick={handleShow}>
                Login
            </Button>

            <Modal show={show} onHide={handleClose} animation={false}>
                <Modal.Header closeButton>
                    <Modal.Title>Modal heading</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group controlId="formBasicUsername">
                            <Form.Label>Username</Form.Label>
                            <Form.Control type="text" name="usernameOrEmail" value={usernameOrEmail} onChange={onChange} placeholder="Enter email" />
                        </Form.Group>
                        <Form.Group controlId="formBasicPassword">
                            <Form.Label>Password</Form.Label>
                            <Form.Control type="password" name="password" value={password} onChange={onChange}  placeholder="Password" />
                        </Form.Group>

                        <Form.Text className="text-muted">
                            We'll never share your email with anyone else.
                        </Form.Text>
                    </Form>

                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Close
                    </Button>
                    <Button variant="primary" onClick={handleSubmit}>
                        Save Changes
                    </Button>
                </Modal.Footer>
            </Modal>
        </div>
    );
}

const registerUserReducer = (registerUserState, action) => {
    return {
        ...registerUserState,
        [action.name]: action.value,
    }
}

function Register() {
    const [registerState, dispatch] = useReducer(registerUserReducer, {
        username:'',
        name :'',
        email:'',
        password:''
    });
    const {username, name, email, password} = registerState;

    const handleSubmit = () => {
        axios.post("http://172.30.1.28:8080/user/register", registerState)
            .then((res) => {
                console.log(res.data);
            }).catch((err) => {
            console.log(err);
        });
        setShow(false);
    }

    const handleClose = () => {
        setShow(false);
    }

    const onChange = e => {
        dispatch(e.target);
    }
    const [show, setShow] = useState(false);
    const handleShow = () => setShow(true);
    return (
        <div>
            <Button variant="light" onClick={handleShow}>
                Register
            </Button>

            <Modal show={show} onHide={handleClose} animation={false}>
                <Modal.Header closeButton>
                    <Modal.Title>Register</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group controlId="formBasicUsername">
                            <Form.Label>Username</Form.Label>
                            <Form.Control type="text" name="username" value={username} onChange={onChange} placeholder="Enter email" />
                        </Form.Group>
                        <Form.Group controlId="formBasicName">
                            <Form.Label>Name</Form.Label>
                            <Form.Control type="text" name="name" value={name} onChange={onChange}  placeholder="Enter Name" />
                        </Form.Group>
                        <Form.Group controlId="formBasicEmail">
                            <Form.Label>Email</Form.Label>
                            <Form.Control type="email" name="email" value={email} onChange={onChange}  placeholder="Enter Email" />
                        </Form.Group>
                        <Form.Group controlId="formBasicPassword">
                            <Form.Label>Password</Form.Label>
                            <Form.Control type="password" name="password" value={password} onChange={onChange}  placeholder="Password" />
                        </Form.Group>

                        <Form.Text className="text-muted">
                            We'll never share your email with anyone else.
                        </Form.Text>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Close
                    </Button>
                    <Button variant="primary" onClick={handleSubmit}>
                        Save Changes
                    </Button>
                </Modal.Footer>
            </Modal>
        </div>
    );
}

const App = () => {

    return (
        <div>
            <div>
                <Navbar bg="dark" variant="dark">
                    <Navbar.Brand href="#home">HeyThere</Navbar.Brand>
                    <Nav className="mr-auto">
                        <Nav.Link href="#home">탐색</Nav.Link>
                        <Nav.Link href="#">###</Nav.Link>
                        <Nav.Link href="#">###</Nav.Link>
                    </Nav>
                    <Form inline alignCenter>
                        <FormControl type="text" placeholder="Search" className="mr-sm-2" />
                        <Button variant="outline-info">Search</Button>
                    </Form>

                    <DropdownButton
                        alignRight
                        title=""
                        id="dropdown-menu-align-right"
                        variant="outline-info"
                    >
                        <Dropdown.Item eventKey="1" ><Login /></Dropdown.Item>
                        <Dropdown.Item eventKey="2"><Register /></Dropdown.Item>
                        <Dropdown.Item eventKey="3">MyPage</Dropdown.Item>
                        <Dropdown.Divider />
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
