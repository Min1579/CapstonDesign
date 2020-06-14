import React, {useState, useEffect} from 'react';
import "./SearchUsers.css"
import axios from "axios";
import SearchUserCard from "./SearchUserCard";

const SearchUsers = ({usernameOrNameOrEmail}) => {
    const [users, setUsers] = useState([]);

    useEffect(() => {
        console.log("user search " + usernameOrNameOrEmail)
        axios.get(`http://localhost:8080/user/search/${usernameOrNameOrEmail}`)
            .then((res) => {
                setUsers(res.data);
                console.log(`received : ${users}`);
            })
            .catch(err => console.log(err));
    },[]);

    return users.map((user) =>
        (<SearchUserCard user={user}/>)
    )
};

export default SearchUsers;