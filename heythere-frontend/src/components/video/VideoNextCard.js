import React, {useState, useEffect} from 'react';
import {useHistory, Redirect} from "react-router-dom";
import Video from "../../page/Video";

const VideoNextCard = ({video}) => {
    const history = useHistory();

    const videoId = video.id;
    const redirectHandler = (e) => {
        history.push(`/video/${videoId}`);
        window.location.reload();
    };
    return (
        <>
            <div className="next-video">
                <div className="next-video-thumbnail">
                    <img src={video.thumbnailUrl} alt=""/>
                </div>
                <div className="next-video-info">
                    <button onClick={redirectHandler}>{video.title}</button>
                    <div>{video.description}</div>
                    <div>조회수{video.view} 2020.01.01</div>
                </div>
            </div>
        </>
    );
};

export default VideoNextCard;