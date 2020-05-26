import React from 'react';
import "./MyPageProfileSetting.css"

const MyPageProfileSetting = () => {
    return (
        <div className="mypage-main">
            <h3>프로필 설정</h3>
            <div className="mypage-profile-setting">
                <div className="profile-element">
                    <label class="label" htmlFor="id"><strong>아이디</strong></label>
                    <input class="input" id="id" type="text"/>
                    <div className="msg">사용가능</div>
                </div>
                <div className="profile-element">
                    <label class="label" htmlFor="name"><strong>닉네임</strong></label>
                    <input class="input" id="name" type="text"/>
                    <div className="msg">사용가능</div>
                </div>
                <div className="profile-element">
                    <label class="label" htmlFor="email"><strong>이메일</strong></label>
                    <input class="input" id="email" type="eamil"/>
                    <div className="msg">사용가능</div>
                </div>
            </div>
        </div>
    );
};

export default MyPageProfileSetting;