import React,{useState} from 'react';
import "./Navbar.css"
import UserLoginModal from "../components/modal/UserLoginModal";
import UserRegisterModal from "../components/modal/UserRegisterModal";
import {Link} from 'react-router-dom';


const Navbar = ({currentUser, setAuthCurrentUser, setSearch}) => {

    const {userId, username, name, email, picture, admin, accessToken, validation} = currentUser;
    const [usernameOrNameEmail, setUsernameOrNameOrEamil] = useState();

    const searchBarKeyUpHandler = e => {
        setUsernameOrNameOrEamil(e.target.name);
    };

    const setAuthUser = (user) => {
        console.log('navbar', user);
        setAuthCurrentUser(user);
    }

    return (
        <div className="navbar">
            <div className="navbar-brand">Hey There!</div>
            <div className="navbar-search">
                <input type="text" className="navbar-search__input" onKeyUp={searchBarKeyUpHandler}/>
                <button type="submit" className="navbar-serach__button"/>
            </div>
            <div className="navbar-button">
                {
                    validation === true ?
                        <>
                            <Link exact to="/mypage">마이페이지</Link>
                            <Link exact to="/video-upload">비디오 업로드</Link>
                        </>
                        :
                        <>
                            <UserLoginModal setAuthUser={setAuthUser}/>
                            <UserRegisterModal/>
                        </>
                }
            </div>
        </div>
    );
};

export default Navbar;