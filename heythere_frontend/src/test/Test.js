import React, {useState} from 'react';
import axios from 'axios';

const Test = () => {
    const [message, setMessage] = useState("");

    const ClickHandler = () => {
        const getMesFromServer = axios.get("http://localhost:8080/user/test", {
            headers : {
                Authorization : "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNTg3ODE3MDY0LCJleHAiOjE1ODc4MjA2NjR9.xx0jHcawCgcs1ebaeK13VZ5NRBh5mvKmWMnm9ku74FgwoU0koyzsAhl29Q_QK5WsPrbm_17HQGKvKdDTCeeOUA"}
        }, null).then(res => setMessage(res.data)
        ).catch(err => console.log(err));
    }



    return (
        <div>
            {message}
            <button onClick={ClickHandler}>getDate</button>
        </div>
    );
};

export default Test;
