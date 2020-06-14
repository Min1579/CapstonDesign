import React, {useState, useReducer} from 'react';
import {Button, Modal, Form} from "react-bootstrap";
import axios from "axios";
import "./Modal.css";
const loginReducer = (state, action) => {
    return {
        ...state,
        [action.name]: action.value,
    }
}

const UserLoginModal = ({setAuthUser}) => {
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

    const setUser = (user) => {
        setAuthUser(user);
    };

    const onSubmitHandler = (e) => {
        e.preventDefault();
        axios.post("http://localhost:8080/api/auth/signin",  loginState)
            .then((res) => {
                const {userId, username, name, email, picture,  accessToken} = res.data;

                setUser({userId, username, name, email, picture, accessToken, admin:false, validation:true});
                console.log({userId, username, name, email, picture, accessToken, admin:false, validation:true})
            })
            .catch((err) => {
                console.log(err);
            });
        handleClose();
    };

    return  (
        <>
            <button className="button" onClick={handleShow}>
                Login
            </button>

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
                        <Button type="submit" variant="primary" onClick={onSubmitHandler}>
                            Submit
                        </Button>
                    </Modal.Footer>
                </Form>
            </Modal>

        </>
    );
};

export default UserLoginModal;