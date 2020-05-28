import React,{useState} from 'react';
import "./MyPageProfileImg.css"

const MyPageProfileImg = ({currentUser}) => {
    const [picture, setPicture] = useState(null);

    return (
        <div className="mypage-main">
            <h3>프로필 사진</h3>
            <div className="mypage-main__upload-profile">
                <div className="upload-profile-img__execute">
                    <img className="upload-profile__img" src="http://placehold.it/100x100" alt=""/>
                </div>
                <div className="upload-profile__execute">
                    <label className="button profile-button" htmlFor="profile">프로필 사진 추가</label>
                    <input id="profile" type="file" className="button"/>
                    <p>10MB 이내의 JEPG,PNG,GIF 형식이어야 됩니다. </p>
                </div>

            </div>

        </div>
    );
};

export default MyPageProfileImg;