import React, {useReducer, useEffect} from 'react';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css'
import {
    Image,
    Container,
    Form
} from 'react-bootstrap'

const Mypage = ({currentUser}) => {
    const {userId, username, name, email, picture, accessToken} = currentUser;

    function getPictureUrl(picture) {
        if(picture === null) return "http://placehold.it/180x180"
        return picture;
    }
    return (
        <div>
            <br/>
            <Container>
                <Form>
                    <Image src={getPictureUrl(picture)} />
                    <Form.Group controlId="formBasicUsername">
                        <Form.Label>Profile Picture</Form.Label>
                        <Form.Control type="file" name={picture} />
                    </Form.Group>
                    <Form.Group controlId="formBasicUsername">
                        <Form.Label>Username</Form.Label>
                        <Form.Control type="text" name={username} value={username} placeholder="Username" />
                    </Form.Group>
                    <Form.Group controlId="formBasicName">
                        <Form.Label>Name</Form.Label>
                        <Form.Control type="text" name={name} value={name} placeholder="Name" />
                    </Form.Group>
                    <Form.Group controlId="formBasicEmail">
                        <Form.Label>Email</Form.Label>
                        <Form.Control type="email" name={email} value={email} placeholder="Email" />
                    </Form.Group>
                    <Form.Group controlId="formBasicPassword">
                        <Form.Label>Password</Form.Label>
<<<<<<< HEAD
                        <Form.Control type="password"  placeholder="Password" />
=======
                        <Form.Control type="passoword"  placeholder="Passowrd" />
>>>>>>> 47a44e93fe15c8e22906975ff0312d73e5578fcb
                    </Form.Group>

                </Form>
            </Container>
        </div>
    );
};

export default Mypage;