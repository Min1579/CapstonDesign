import React from 'react';
import "./UserInfoUploadCard.css"

const UserInfoUploadCard = () => {
    return (
        <div className="profile-img-card">
            <h3>유저정보 수정</h3>
            <form className="input-form">
                <input className="input" id="username" type="text" placeholder="ID"/>
                <input className="button" type="submit" value="변경"/>
            </form>
            <div>message</div>
            <form className="input-form">
                <input className="input" id="name" type="text" placeholder="NAME"/>
                <input className="button" type="submit" value="변경"/>
            </form>
            <div>message</div>
            <form className="input-form">
                <input className="input" id="email" type="password" placeholder="EMAIL"/>
                <input className="button" type="submit" value="변경"/>

            </form>
            <div>message</div>
        </div>
    );
};

export default UserInfoUploadCard;