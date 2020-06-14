import React,{useState, useEffect} from 'react';
import VideoNextCard from "./VideoNextCard";
import "./VideoNext.css"

import axios from "axios";

const VideoNext = ({videoId, username}) => {
    const [videos, setVideos] = useState([]);

    useEffect(() => {
        axios.get(`http://localhost:8080/video/retrieve/${videoId}`)
            .then((res) => {
                setVideos(res.data);
            })
            .catch(err => console.log(err));
    },[]);

    return (
        <div id="next-videos-container">
            <h3>{username}의 다른영상</h3>
            {(videos.map((video)=>
                <VideoNextCard video={video}/>
            ))}
        </div>
    );
};

export default VideoNext;