import React, { useState, useReducer } from 'react'
import 'bootstrap/dist/css/bootstrap.min.css'
import {
    Navbar,
    Nav,
    Form,
    Button,
    FormControl,
    DropdownButton,
    Dropdown,
    Row,
    Col, CardGroup,
} from 'react-bootstrap'
import {Link, Route} from 'react-router-dom';
import Login from './components/auth/Login';
import Register from './components/auth/Register'
import Mypage from './components/auth/Mypage'
import Main from './components/main/Main'
import RecommendList from "./components/RecommendList";
import SearchList from "./components/search/SearchList";


const App = () => {
    const [currentUser, setCurrentUser] = useState(null);
    const [valid, setValid] = useState(false);
    const [input, setInput] = useState("");

    const onSearchHandler = (e) => {
        const input = e.target.value;
        console.log(input)
        setInput(input);
    };

    {/*  Auth */}
    const setAuthVerifiedUser = (user) => {
        console.log(`in App.js : user -> ${user}`);
        setCurrentUser(user);
        setValid(true);
    }


    return (
        <div>
            <div>
                <Navbar bg='dark' variant='dark'>
                    <Navbar.Brand><Link to="/">HeyThere</Link></Navbar.Brand>
                    <Nav className='mr-auto'>
                        <Nav.Link href='#home'>탐색</Nav.Link>
                        <Nav.Link href='#'>###</Nav.Link>
                        <Nav.Link href='#'>###</Nav.Link>
                    </Nav>
                    <Form inline alignCenter>
                        <FormControl type='text' placeholder='Search' className='mr-sm-2' onChange={onSearchHandler}/>
                        <Button variant="outline-info"><Link to="/search">Search</Link></Button>
                    </Form>

                    <DropdownButton
                        alignRight
                        title=''
                        id='dropdown-menu-align-right'
                        variant='outline-info'
                    >
                        {valid === false ? (
                            <div>
                                <Dropdown.Item eventKey='1'>
                                    <Login setCurrentUser={setAuthVerifiedUser}/>
                                </Dropdown.Item>
                                <Dropdown.Item eventKey='2'>
                                    <Register />
                                </Dropdown.Item>
                            </div>
                        ) : (
                            <div>
                                <Dropdown.Item eventKey='1'><Link to="/mypage">Mypage</Link></Dropdown.Item>
                                <Dropdown.Item
                                    eventKey='2'
                                    onClick={() => {
                                        setCurrentUser(null);
                                        console.log(`logout successful!`)
                                    }}
                                >
                                    Logout
                                </Dropdown.Item>
                                <Dropdown.Item eventKey='3'><Link to="/">방송하기</Link></Dropdown.Item>
                                <Dropdown.Item eventKey='4'><Link to="/">동영상업로드</Link></Dropdown.Item>
                            </div>
                        )}
                        <Dropdown.Divider />
                    </DropdownButton>
                </Navbar>
            </div>
            <div>
                <Row>
                    <Col sm={2}>
                        <RecommendList />
                    </Col>


                    <Col sm={10} fluid>



                        <Route path="/"
                               exact
                               render={() =>
                                    <Main />} />

                        <Route path="/search"
                               exact
                               render={() =>
                                   <CardGroup>
                                       <SearchList input={input} />
                                   </CardGroup>
                               } />

                        <Route path="/mypage"
                               exact
                               render={() =>
                                   <Mypage currentUser={currentUser} />}
                        />
                    </Col>
                </Row>
            </div>
        </div>
    )
}

export default App
