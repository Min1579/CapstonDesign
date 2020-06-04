import React, {useState, useEffect} from 'react';
import {Form} from "react-bootstrap";
import "./SideBarNav.css";
import axios from "axios";
import FindUser from "../components/sidebar/FIndUser";
import {Link} from "react-router-dom";

const SideBarNav = ({currentUser}) => {
    const [users, setUsers] = useState([{
        id: 0,
        picture: "",
        name: ""
    }]);

    const [usernameOrNameOrEmail, setUsernameOrNameOrEmail] = useState('');

    const onSearchChangeHandler = e => {
        setUsernameOrNameOrEmail(e.target.value);
    };

    return (
        <div className="side">
            <div className="side__form">
                <Form.Control size="md" type="text" placeholder="Search..." onChange={onSearchChangeHandler} />
            </div>
            <div className="side__recommends">
                {
                    usernameOrNameOrEmail.length > 0 ?
                        <FindUser currentUser={currentUser} usernameOrNameOrEmail={usernameOrNameOrEmail}/>
                        : ''
                }

                <div>Recommendation</div>
                <ul className="side__recommends__lists">
                    <li className="list_element">
                    </li>

                    <br/>
                    <li className="list_element">

                    </li>
                    <br/>
                    <li className="list_element">

                    </li>
                </ul>
            </div>
            <div className="footer">
                Copy Right By Min <br/>
                Made by <br/>
                Seungmin Han <br/>
                Jungyoou Huh <br/>
                Moonhyun Lee <br/>
            </div>


        </div>
    );
};

export default SideBarNav;