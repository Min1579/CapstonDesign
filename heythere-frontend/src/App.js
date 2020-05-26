import React, {useState} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css'
import {Navbar,Row, Col} from "react-bootstrap";
import {Route, Link} from "react-router-dom";
import SideBarNav from "./layout/SideBarNav";
import MainContent from "./layout/MainContent";
import UserLoginModal from "./components/modal/UserLoginModal";
import UserRegisterModal from "./components/modal/UserRegisterModal";
import User from "./components/user/User";
import Mypage from "./components/user/Mypage";
import OnLive from "./components/user/OnLive";
import VideoUpload from "./components/user/VideoUpload";
import BoardMain from "./components/board/BoardMain";
import VideoMain from "./components/video/VideoMain";

const App = () => {
    const defaultAuthInfo = {
        userId: -1,
        username: "",
        name: "",
        email: "",
        accessToken: "",
        admin: false,
        validation: false,
    };

    const [currentUser, setCurrentUser] = useState(defaultAuthInfo);

    const setAuthUser = (user) => {
        setCurrentUser(user);
        console.log(currentUser);
    };


    const logoutHandler = (e) => {
        e.preventDefault();
        setCurrentUser(defaultAuthInfo);
    };


    return (
        <div className="main-body">
            <Navbar className="main-navbar" variant="dark">
                <Navbar.Brand href="#home">
                    <Link class="main-link" to="/">
                        <img
                            src="http://placehold.it/30x30"
                            className="d-inline-block align-top"
                        />{' '}
                        Hey There!
                    </Link>
                </Navbar.Brand>
                <Navbar.Collapse className="justify-content-end">
                    {currentUser.validation ?

                        (
                            <>
                                <User currentUser={currentUser} />
                                <button className="button" onClick={logoutHandler}>로그아웃</button>
                            </>
                        )
                                :
                        (
                            <>
                                <UserLoginModal className="modal-button" setCurrentUser={setAuthUser} />
                                <UserRegisterModal className="modal-button" />
                            </>
                        )
                    }

                </Navbar.Collapse>
                </Navbar>
            <Row className="row">
                <Col className="col" md={2}>
                    <SideBarNav currentUser={currentUser} />
                </Col>
                <Col className="col main" md={10}>
                    <Route path="/" exact component={MainContent}/>
                    <Route path="/mypage" exact render={()=> <Mypage currentUser={currentUser} />}/>
                    <Route path="/video-upload" exact render={()=> <VideoUpload currentUser={currentUser}/>}/>
                    <Route path="/on-live" exact render={()=> <OnLive currentUser={currentUser}/>}/>
                    <Route path="/board" exact render={() => <BoardMain currentUser={currentUser} />}/>
                    <Route path="/video" exact render={() => <VideoMain currentUser={currentUser} />}/>
                </Col>
            </Row>
        </div>
    );
};

export default App;