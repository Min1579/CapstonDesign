import React, {useState, useEffect} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Table, Image} from "react-bootstrap";
import axios from "axios";

const RecommendList = () => {
    const [users, setUsers] = useState([]);

    useEffect(() => {
        axios.get("http://localhost:8080/main/user/on",null)
            .then((res)=>{
                console.log(res.data);
                setUsers(res.data);
            }).catch((err)=>{
                console.log(err)
        })
    },[]);

    return (
        <div>
            <Table striped hover variant="dark" size="sm">
                <thead>
                    <td colSpan="3">ON</td>
                </thead>
                {
                    users.map(user => {
                        return (
                            <div>
                                <tr>
                                    <td><Image src={user.picture} roundedCircle/></td>
                                    <td>{user.name}</td>
                                    <td>접속중</td>
                                </tr>
                            </div>
                        )
                    })
                }
            </Table>
        </div>
    )
};

export default RecommendList;