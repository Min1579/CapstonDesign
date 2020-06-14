import React,{useState} from 'react';
import "./VideoUpload.css"
import {Link} from "react-router-dom";
import axios from "axios";

const VideoUpload = ({currentUser}) => {

    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const [video, setVideo] = useState({preview: "", raw: ""});
    const [thumbnail, setThumbnail] = useState({preview: "", raw: ""});

    const [videoUrl, setVideoUrl] = useState("");


    const videoUploadHandler = (e) => {
        console.log(`video : ${e.target.files[0]}`);

        setVideo({
            preview: URL.createObjectURL(e.target.files[0]),
            raw: e.target.files[0],
        });
    };

    const thumbnailUploadHandler = (e) => {
        console.log(`thumbnail : ${e.target.files[0]}`);
        setThumbnail({
            preview: URL.createObjectURL(e.target.files[0]),
            raw: e.target.files[0],
        });
    };

    const titleUploadHandler = (e) => {
        setTitle(e.target.value);
    };

    const descriptionUploadHandler = (e) => {
        setDescription(e.target.value);
    };

    const onClickHandler = async () => {
        if (video === null || thumbnail == null || title.length === 0 || description.length === 0) {
            console.log("error");
            return;
        }

        const videoForm = new FormData();

        videoForm.append("video", video.raw);
        videoForm.append("thumbnail", thumbnail.raw);
        videoForm.append("title", title);
        videoForm.append("description", description);

        await axios.post(`http://localhost:8080/video/upload/${currentUser.userId}`,
            videoForm,
            {
                headers : {
                    "Content-Type": "multipart/form-data",
                    "Authorization": `Bearer ${currentUser.accessToken}`
                }
            })
            .then((res) => {
                console.log(`video  id : ${res.data}`);
                setVideoUrl(`/video/${res.data}`);
            })
            .catch(err => console.log(err))
    };

    return (
        <div className="video-upload-form">
            <div className="media-files">
                <div className="media-file-info">
                    <label htmlFor="thumbnail">Thumbnail</label>
                    <input id="thumbnail" className="display-none" type="file" onChange={thumbnailUploadHandler}/>
                    <img id="thumbnail-preview" src={thumbnail.preview} alt=""/>
                </div>
                <div className="media-file-info">
                    <label htmlFor="video">Video</label>
                    <input id="video" className="display-none" type="file" onChange={videoUploadHandler}/>
                    <img id="video-preview" src={video.preview} alt=""/>
                </div>
            </div>
            <br/>
            <input id="title" type="text" placeholder="TITLE" onKeyUp={titleUploadHandler}/>
            <textarea id="description"  placeholder="VIDEO DESCRIPTION" onKeyUp={descriptionUploadHandler}/>
            <br/>
            <button onClick={onClickHandler}>save</button>
            <Link className="video-save-button" to={videoUrl} >save</Link>
        </div>
    );
};

export default VideoUpload;