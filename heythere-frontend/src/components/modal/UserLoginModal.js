import React, {useState, useReducer} from 'react';
import {Button, Modal, Form} from "react-bootstrap";
import "./ModalShared.css";
import axios from "axios";

const loginReducer = (state, action) => {
    return {
        ...state,
        [action.name]: action.value,
    }
}

const UserLoginModal = ({setCurrentUser}) => {
    const [loginState, dispatch] = useReducer(loginReducer, {
        usernameOrEmail: '',
        password: ''
    });
    const {usernameOrEmail, password} = loginState;

    const [show, setShow] = useState(false);

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);
    const onInputChange = e => {
        e.preventDefault();
        dispatch(e.target);
    };

    const handleSendUserInfo = (e) => {
        e.preventDefault();
        axios.post("http://localhost:8080/api/auth/signin",  loginState)
            .then((res) => {
                console.log(res.data)
                const currentUser = {};
                currentUser.userId = res.data.userId;
                currentUser.username = res.data.username;
                currentUser.name = res.data.name;
                currentUser.picture = res.data.picture;
                currentUser.email = res.data.email;
                currentUser.accessToken = res.data.accessToken;
                currentUser.admin = false;
                currentUser.validation = true;
                setCurrentUser(currentUser);
                console.log(currentUser)
            })
            .catch((err) => {
                console.log(err);
            });
        handleClose();
    };

    return  (
        <>
            <Button className="button" onClick={handleShow}>
                로그인
            </Button>

            <Modal show={show} onHide={handleClose}>
                <Form>
                    <Modal.Header closeButton>
                        <Modal.Title>로그인</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Form.Group controlId="formBasicEmail">
                            <Form.Label>Email address Or Username</Form.Label>
                            <Form.Control type="text" name="usernameOrEmail"
                                          value={usernameOrEmail}
                                          placeholder="Enter email or username"
                                          onChange={onInputChange}/>
                            <Form.Text className="text-muted">
                                We'll never share your email with anyone else.
                            </Form.Text>
                        </Form.Group>

                        <Form.Group controlId="formBasicPassword">
                            <Form.Label>Password</Form.Label>
                            <Form.Control type="password" name="password"
                                          value={password}
                                          placeholder="Password"
                                          onChange={onInputChange} />
                        </Form.Group>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="secondary" onClick={handleClose}>
                            Close
                        </Button>
                        <Button type="submit" variant="primary" onClick={handleSendUserInfo}>
                            Submit
                        </Button>
                    </Modal.Footer>
                </Form>
            </Modal>

        </>
    );
};

export default UserLoginModal;