import React, {useState, useReducer, useEffect} from 'react';
import {Button, Col, Form, Container} from "react-bootstrap";
import axios from "axios";
import "./shared.css"

const mypageReducer = ((state, action) => {
    return {
        ...state,
        [action.name]: action.value,
    };
});

const Mypage = ({currentUser}) => {

    const [state,dispatch] = useReducer(mypageReducer, {
        username: '',
        name: '',
        email: '',
        password: '',
        picture: ''
    });
    const {username, name, email, password, picture} = state;
    const onInputChange = e => {
        e.preventDefault();
        dispatch(e.target);
    };

    const [user, setUser] = useState({});

    useEffect(() => {
        axios.get(`http://localhost:8080/user/mypage/${currentUser.userId}`,
            null,
            {
                headers : {
                    "Authorization": `Bearer ${currentUser.accessToken}`
                }
            }
        ).then((res) => {
            console.log(res.data);
            setUser(res.data);
        }).catch((err) => {
                console.log(err);
        });
    },[]);

    return (
        <div className="mypage-box">
            <Container class="box-container">
                <h1>Mypage</h1>
                <Form controlid="formGridPicture">
                    <Form.Group>
                        <Form.Label>Email</Form.Label>
                        <Form.Control type="email" placeholder={user.email}
                                      name="email"
                                      value={email}
                                      onChange={onInputChange}/>
                    </Form.Group>

                    <Form.Row>
                        <Form.Group as={Col} controlid="formGridUsername">
                            <Form.Label>Username</Form.Label>
                            <Form.Control type="text" placeholder={user.username}
                                          name="username"
                                          value={username}
                                          onChange={onInputChange} />
                        </Form.Group>

                        <Form.Group as={Col} controlid="formGridPassword">
                            <Form.Label>Name</Form.Label>
                            <Form.Control type="text" placeholder={user.name}
                                          name="name"
                                          value={name}
                                          onChange={onInputChange}/>
                        </Form.Group>
                    </Form.Row>

                    <Form.Group controlid="formGridPassword">
                        <Form.Label>Password</Form.Label>
                        <Form.Control type="password" placeholder="password"
                                      name="password"
                                      value={password}
                                      onChange={onInputChange}/>
                    </Form.Group>

                    <Button type="submit" value="submit" />
                </Form>
            </Container>
        </div>
    );
};

export default Mypage;