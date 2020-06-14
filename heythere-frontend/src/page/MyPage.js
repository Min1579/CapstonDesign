import React from 'react';
import "./MyPage.css"
import ProfileImgCard from "../components/card/ProfileImgCard";
import UserInfoUploadCard from "../components/card/UserInfoUploadCard";
import PasswordModifyCard from "../components/card/PasswordModifyCard";

const MyPage = ({currentUser}) => {
    return (
        <div className="mypage-flex-container">
            <div className="mypage-card-list">
                <ProfileImgCard currentUser={currentUser}/>
                <UserInfoUploadCard currnetUser={currentUser}/>
                <PasswordModifyCard currentUser={currentUser}/>
            </div>

        </div>
    );
};

export default MyPage;