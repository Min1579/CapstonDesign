import React,{useState, useEffect} from 'react';
import axios from "axios";
import "./SidebarUserList.css"

const SidebarUserList = () => {
    const [users, setUsers] = useState([]);

    useEffect(() => {
        axios.get("http://localhost:8080/user/streaming",null)
            .then((res) => {
                setUsers(res.data);
            })
            .catch(err => console.log(err));
    },[]);

    return (
        <div id="sidebar-user-list">
            <h6>Recommended</h6>

            {users.map(user => (
                <div className="user-in-list">
                    <img src={user.picture} alt=""/>
                    <span><b>{user.name}({user.username})</b></span>
                </div>
            ))}

        </div>
    );
};

export default SidebarUserList;