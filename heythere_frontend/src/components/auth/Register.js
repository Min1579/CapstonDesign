import React, {useReducer, useState} from 'react';
import axios from "axios";
import {Button, Form, Modal} from "react-bootstrap";

const registerUserReducer = (registerUserState, action) => {
    return {
        ...registerUserState,
        [action.name]: action.value
    }
};

const Register = () => {
    const [registerState, dispatch] = useReducer(registerUserReducer, {
        username: '',
        name: '',
        email: '',
        password: ''
    })
    const { username, name, email, password } = registerState

    const handleSubmit = () => {
        axios
            .post('http://localhost:8080/user/register', registerState)
            .then(res => {
                console.log(res.data)
            })
            .catch(err => {
                console.log(err)
            })
        setShow(false)
    }

    const handleClose = () => {
        setShow(false)
    }

    const onChange = e => {
        dispatch(e.target)
    }

    const [show, setShow] = useState(false)
    const handleShow = () => setShow(true)
    return (
        <div>
            <Button variant='light' onClick={handleShow}>
                Register
            </Button>

            <Modal show={show} onHide={handleClose} animation={false}>
                <Modal.Header closeButton>
                    <Modal.Title>Register</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group controlId='formBasicUsername'>
                            <Form.Label>Username</Form.Label>
                            <Form.Control
                                type='text'
                                name='username'
                                value={username}
                                onChange={onChange}
                                placeholder='Enter email'
                            />
                        </Form.Group>
                        <Form.Group controlId='formBasicName'>
                            <Form.Label>Name</Form.Label>
                            <Form.Control
                                type='text'
                                name='name'
                                value={name}
                                onChange={onChange}
                                placeholder='Enter Name'
                            />
                        </Form.Group>
                        <Form.Group controlId='formBasicEmail'>
                            <Form.Label>Email</Form.Label>
                            <Form.Control
                                type='email'
                                name='email'
                                value={email}
                                onChange={onChange}
                                placeholder='Enter Email'
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

export default Register;