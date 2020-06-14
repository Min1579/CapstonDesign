import React from 'react';
import {Link} from "react-router-dom"
import "./VideoCard.css"

const VideoCard = ({video}) => {
    const videoURL = `/video/${video.id}`;
    return (
        <div className="video-player-card">
            <video id="video-player" src={video.videoUrl} controls autoplay muted
            poster={video.thumbnailUrl}/>

            <div className="video-info">
                <div className="video-img-box">
                    <img src={video.picture} alt=""/>
                </div>

                <div className="video-info-box">
                    <div id="video-title">
                        <Link to={videoURL}>{video.title}</Link></div>
                    <div id="name">{video.username}</div>
                    <div id="sub-info">조회수 {video.view}회 2020.01.01</div>
                </div>
            </div>
        </div>
    );
};

export default VideoCard;