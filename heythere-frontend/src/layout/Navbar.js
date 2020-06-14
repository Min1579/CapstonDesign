import React,{useState} from 'react';
import "./Navbar.css"
import UserLoginModal from "../components/modal/UserLoginModal";
import UserRegisterModal from "../components/modal/UserRegisterModal";
import {Link, useHistory} from 'react-router-dom';


const Navbar = ({currentUser, setAuthCurrentUser, setSearch}) => {
    const history = useHistory();

    const {userId, username, name, email, picture, admin, accessToken, validation} = currentUser;
    const [usernameOrNameEmail, setUsernameOrNameOrEamil] = useState('');

    const searchBarKeyUpHandler = e => setUsernameOrNameOrEamil(e.target.value);

    const setAuthUser = (user) => {
        console.log('navbar', user);
        setAuthCurrentUser(user);
    }

    const redirectToMyPage = () => {
        history.push(`/mypage/${userId}`);
        window.location.reload();
    };

    const redirectToVideoUpload = () => {
        history.push('/video-upload');
        window.location.reload();
    };
    return (
        <div className="navbar">
            <div className="navbar-brand"> <Link id="direct-to-home" exact to="/">Hey There!</Link></div>
            <div className="navbar-search">
                <input type="text" className="navbar-search__input" onKeyUp={searchBarKeyUpHandler}/>
                <Link to={`/search/${usernameOrNameEmail}`} type="submit" className="navbar-serach__button"></Link>
            </div>
            <div className="navbar-button">
                {
                    validation === true ?
                        <>
                            <button className="button" onClick={redirectToMyPage}>마이페이지</button>
                            <button className="button" onClick={redirectToVideoUpload}>비디오 업로</button>
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