import React, {useEffect, useState} from 'react';
import "./Video.css"
import VideoPlayer from "../components/video/VideoPlayer";
import VideoInfo from "../components/video/VideoInfo";
import VideoAuthor from "../components/video/VideoAuthor";
import VideoComment from "../components/video/VideoComment";
import VideoNext from "../components/video/VideoNext";
import axios from "axios";

const Video = ({videoId, currentUser}) => {
    const [video, setVideo] = useState({});

    const {id, fileName, title, description, view, videoUrl, thumbnailUrl, username, picture, good, bad} = video;

    useEffect(() => {
        console.log(`match param ${videoId}`);
        console.log(`http://localhost:8080/video/${videoId}`)
        axios.get(`http://localhost:8080/video/${videoId}`,null)
            .then((res) => {
                setVideo(res.data);
                console.log(video);
            })
            .catch((err) => {
                console.log(err);
            })
    },[]);

    return (
        <div id="video-container">
            <div id="video-container__main-content">
                <VideoPlayer videoUrl={videoUrl} />
                <VideoInfo videoId={videoId} title={title} description={description} view={view} good={good} bad={bad} />
                <VideoAuthor username={username} picture={picture} />
                <VideoComment videoId={videoId} currentUser={currentUser} />
            </div>
            <div id="video-container__more-video">
                <VideoNext videoId={videoId} username={username} />
            </div>
        </div>
    );
};

export default Video;