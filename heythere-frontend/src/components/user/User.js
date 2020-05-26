import React from 'react';
import {Dropdown} from "react-bootstrap";
import "./User.css";
import {Link, Route} from "react-router-dom";

const User = ({currentUser}) => {
    return (
        <div>
            <Dropdown>
                <Dropdown.Toggle  className="user-icon"  id="dropdown-basic">
                    {currentUser.name}
                </Dropdown.Toggle>

                <Dropdown.Menu>
                    <Link class="menu-link" to="/mypage" exact>마이페이지</Link>
                    <Link class="menu-link" to="/video-upload">동영상 업로드</Link>
                    <Link class="menu-link" to="/on-live">방송하기</Link>
                </Dropdown.Menu>
            </Dropdown>


        </div>
    );
};

export default User;