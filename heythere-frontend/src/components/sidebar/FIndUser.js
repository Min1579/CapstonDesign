import React,{useState, useEffect} from 'react';
import axios from "axios";
import {Link} from "react-router-dom";

const FIndUser = ({currentUser,usernameOrNameOrEmail}) => {
    const [users, setUsers] = useState([]);
    useEffect(() => {
        axios.post(`http://localhost:8080/user/search/${usernameOrNameOrEmail}`,null,
            {
                headers : {
                    "Authorization" : `Bearer ${currentUser.accessToken}`,
                }
            })
            .then((res) => {
                console.log(res.data);
                setUsers(res.data);
            })
            .catch((err) => {
                console.log(err);
            })

    },[]);

    return users.map((user) => {
        return (
            <div>
                <ul>
                    <li><Link>사진  {user.name}</Link> </li>
                </ul>
            </div>
        )
    })
};

export default FIndUser;