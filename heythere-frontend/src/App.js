import React, {useState,useEffect} from 'react';
import './App.css'
import 'bootstrap/dist/css/bootstrap.min.css';
import Navbar from "./layout/Navbar";
import Sidebar from "./layout/Sidebar";
import Main from "./layout/Main";

const defalutser = {
    userId: -1,
    username: '',
    name: '',
    email: '',
    accessToken: '',
    admin:false,
    validation: false,
};

const App = () => {
    const [currentUser, setCurrentUser] = useState(defalutser);
    const [search, setSearch] = useState("");

    const setAuthCurrentUser = (user) => {
        console.log(`App ${user.validation}`);
        setCurrentUser(user);
    }

    return (
        <div>
            <Navbar currentUser={currentUser} setAuthCurrentUser={setAuthCurrentUser} />
            <div id="layout">
                <Sidebar currentUser={currentUser} />
                <Main currentUser={currentUser}  setSearch={setSearch} />
            </div>

        </div>
    );
};

export default App;