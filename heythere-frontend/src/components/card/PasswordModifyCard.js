import React from 'react';
import "./ProfileImgCard.css"

const PasswordModifyCard = () => {
    return (
        <div className="profile-img-card">
            <h3>비밀번호 수정</h3>
            <form>
                <input className="input password-input"  type="password" placeholder="현재 비밀번호"/>
            </form>
            <div>message</div>
            <form className="input-form">
                <input className="input password-input" type="password" placeholder="새로운 비밀번호"/>
            </form>
            <form className="input-form">
                <input className="input password-input"  type="password" placeholder="비밀번호 다시입력"/>
            </form>
            <div>message</div>
            <button className="button">프로필 사진 변경하기</button>
        </div>
    );
};

export default PasswordModifyCard;