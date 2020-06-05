import React, {useState, useEffect} from 'react';
import "./MainContent.css";
import axios from "axios";

const MainContent = () => {
    const [videos, setVideos] = useState([]);

    useEffect(() => {
        axios.get("http://localhost:8080/video", null)
            .then((res) => {
                console.log(res.data);
                setVideos(res.data);
            })
            .catch((err) => {
                console.log(err);
            })
    },[]);

    return videos.map((video) => {
        const {id, title, description, view, thumbnailUrl, videoUrl, username, picture} = video;

        return (
            <div className="box">
                <div className="card">
                    <video className="card-video" src={videoUrl}
                           controls muted autoplay
                           poster={thumbnailUrl}
                    />
                    <span className="card-body">
                    <img className="card-body__picture" src={picture === null ?
                        "http://placehold.it/30x30" : picture} alt=""/>

                    <ul className="content-body_info">
                        <li className="title">{title}</li>
                        <li className="info">{username} &nbsp;&nbsp; 조회수 {view}</li>
                        <li className="date">2XXX-XX-XX XX:XX</li>
                    </ul>
                </span>
                </div>
            </div>
        )
    })
};

export default MainContent;