import React, {useState, useReducer} from 'react';
import {Button, Modal, Form, Col} from "react-bootstrap";
import "./ModalShared.css";
import axios from "axios";

const registerReducer = ((state, action) => {
    return {
        ...state,
        [action.name]: action.value,
    };
});

const UserRegisterModal = () => {
    const [show, setShow] = useState(false);

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const [state,dispatch] = useReducer(registerReducer, {
        username: '',
        name: '',
        email: '',
        password: ''
    });
    const {username, name, email, password} = state;
    const onInputChange = e => {
        e.preventDefault();
        dispatch(e.target);
    };

    const handleRegisterInfo = (e) => {
        e.preventDefault();
        axios.post("http://localhost:8080/user/register", state)
            .then((res) => {
                console.log(res);
            })
            .catch((err) => {
                console.log(err);
            });

        handleClose();
    }
    return (
        <div>
            <Button className="button" onClick={handleShow}>
                회원가입
            </Button>

            <Modal show={show} onHide={handleClose}>
                <Form>
                    <Modal.Header closeButton>
                        <Modal.Title>Modal heading</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formGridUsername">
                                <Form.Label>Username</Form.Label>
                                <Form.Control type="text" placeholder="Enter Username"
                                              name="username"
                                              value={username}
                                              onChange={onInputChange} />
                            </Form.Group>

                            <Form.Group as={Col} controlId="formGridPassword">
                                <Form.Label>Name</Form.Label>
                                <Form.Control type="text" placeholder="Name"
                                              name="name"
                                              value={name}
                                              onChange={onInputChange}/>
                            </Form.Group>
                        </Form.Row>

                        <Form.Group controlId="formGridEmail">
                            <Form.Label>Email</Form.Label>
                            <Form.Control type="email" placeholder="Email"
                                          name="email"
                                          value={email}
                                          onChange={onInputChange}/>
                        </Form.Group>

                        <Form.Group controlId="formGridAddress2">
                            <Form.Label>Password</Form.Label>
                            <Form.Control type="password" placeholder="Password"
                                          name="password"
                                          value={password}
                                          onChange={onInputChange}/>
                        </Form.Group>


                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="secondary" onClick={handleClose}>
                            Close
                        </Button>
                        <Button variant="primary" onClick={handleRegisterInfo}>
                            Submit
                        </Button>
                    </Modal.Footer>
                </Form>
            </Modal>
        </div>
    );
};

export default UserRegisterModal;