import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Navbar, Nav, Form, FormControl, Button, Row, Col, Table, Image, Figure} from "react-bootstrap";
import {Link, Route} from "react-router-dom";

const RecommendList = () => {
    return (
        <div>
            <Table striped hover variant="dark" size="sm">
                <thead>
                <td colSpan="3">Recommend</td>
                </thead>
                <tr>
                    <td><Image src="http://placehold.it/30x30" roundedCircle/></td>
                    <td>aaaa</td>
                    <td>asdfasdf</td>
                </tr>
                <tr>
                    <td><Image src="http://placehold.it/30x30"  roundedCircle/></td>
                    <td>aaaa</td>
                    <td>asdfasdf</td>
                </tr>
                <tr>
                    <td><Image src="http://placehold.it/30x30" roundedCircle/></td>
                    <td>aaaa</td>
                    <td>asdfasdf</td>
                </tr>
                <tr>
                    <td><Image src="http://placehold.it/30x30" roundedCircle/></td>
                    <td>aaaa</td>
                    <td>asdfasdf</td>
                </tr>
                <tr>
                    <td><Image src="http://placehold.it/30x30" roundedCircle/></td>
                    <td>aaaa</td>
                    <td>asdfasdf</td>
                </tr>
            </Table>
        </div>
    );
};

export default RecommendList;