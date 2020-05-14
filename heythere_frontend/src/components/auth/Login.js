import React, {useReducer, useState} from "react";
import axios from "axios";
import {Button, Form, Modal} from "react-bootstrap";

const currentUser = {};

const loginUserReducer = (loginUserState, action) => {
    return {
        ...loginUserState,
        [action.name]: action.value
    }
}

const Login = ({setCurrentUser}) => {


    const [loginUserState, dispatch] = useReducer(loginUserReducer, {
        usernameOrEmail: '',
        password: ''
    });

    const { usernameOrEmail, password } = loginUserState;

    const handleSubmit = () => {
        axios
            .post('http://localhost:8080/api/auth/signin', loginUserState)
            .then(res => {
                console.log(res.data)
                currentUser.userId = res.data.userId;
                currentUser.username = res.data.username;
                currentUser.name = res.data.name;
                currentUser.picture = res.data.picture;
                currentUser.email = res.data.email;
                currentUser.accessToken = res.data.accessToken;

                setCurrentUser(currentUser);
                console.log(currentUser)
            })
            .catch(err => {
                console.log(err)
            })

        setShow(false)
    }

    const onChange = e => {
        dispatch(e.target)
    }

    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    return (
        <div>
            <Button variant='light' onClick={handleShow}>
                Login
            </Button>

            <Modal show={show} onHide={handleClose} animation={false}>
                <Modal.Header closeButton>
                    <Modal.Title>Modal heading</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group controlId='formBasicUsername'>
                            <Form.Label>Username</Form.Label>
                            <Form.Control
                                type='text'
                                name='usernameOrEmail'
                                value={usernameOrEmail}
                                onChange={onChange}
                                placeholder='Enter email'
                            />
                        </Form.Group>
                        <Form.Group controlId='formBasicPassword'>
                            <Form.Label>Password</Form.Label>
                            <Form.Control
                                type='password'
                                name='password'
                                value={password}
                                onChange={onChange}
                                placeholder='Password'
                            />
                        </Form.Group>

                        <Form.Text className='text-muted'>
                            We'll never share your email with anyone else.
                        </Form.Text>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant='secondary' onClick={handleClose}>
                        Close
                    </Button>
                    <Button variant='primary' onClick={handleSubmit}>
                        Save Changes
                    </Button>
                </Modal.Footer>
            </Modal>
        </div>
    )
};

export default Login;