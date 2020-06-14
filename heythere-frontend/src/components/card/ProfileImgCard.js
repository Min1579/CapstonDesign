import React, {useState, useEffect} from 'react';
import "./ProfileImgCard.css"
import axios from "axios";

const ProfileImgCard = ({currentUser}) => {

    const [img, setImg] = useState({
        preview: "",
        raw: ""
    });

    useEffect(() => {
        axios.get(`http://localhost:8080/user/mypage/img/${currentUser.userId}`,
            null,
            {
                headers: {
                    "Authorization": `Bearer ${currentUser.accessToken}`
                }
            })
            .then((res) => {
                setImg({
                    preview: res.data.picture,
                    raw: ""
                })
            })
            .catch(err => console.log(err));
    }, []);


    const imgUploadHandler = (e) => {
        setImg({
            preview: URL.createObjectURL(e.target.files[0]),
            raw: e.target.files[0],
        });
    };

    const submitHandler = async (e) => {
        console.log(img);

        const form = new FormData();
        form.append("picture", img.raw);

        await axios.put(`http://localhost:8080/user/mypage/upload/img/${currentUser.userId}`,
            form,
            {
                headers: {
                    "Content-Type": "multipart/form-data",
                    "Authorization": `Bearer ${currentUser.accessToken}`
                }
            }
        )
            .then((res) => {
                console.log("precess success!");
            })
            .catch(err => console.log(err));
    };

    return (
        <div className="profile-img-card img-align">
            <img className="img" src={img.preview} alt=""/>
            <label htmlFor="file">Upload</label>
            <input id="file" type="file" onChange={imgUploadHandler}/>
            <button className="button" onClick={submitHandler}>프로필 사진 변경하기</button>
        </div>
    );
};

export default ProfileImgCard;