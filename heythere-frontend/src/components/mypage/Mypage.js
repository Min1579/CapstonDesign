import React, {useState, useReducer, useEffect} from 'react';
import {Button, Col, Form, Container} from "react-bootstrap";
import axios from "axios";
import "../user/shared.css"
import MyPageProfileImg from "./MyPageProfileImg";
import MyPageProfileSetting from "./MyPageProfileSetting";

const Mypage = ({currentUser}) => {

    return (
        <div className="mypage-box">
            <MyPageProfileImg />
            <br/><br/>
            <MyPageProfileSetting />
        </div>
    );
};

export default Mypage;