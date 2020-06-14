import React, {useState, useEffect} from 'react';
import {Link, Redirect} from "react-router-dom";

const VideoNextCard = ({video}) => {
    return (
        <>
            <div className="next-video">
                <div className="next-video-thumbnail">
                    <img src={video.thumbnailUrl} alt=""/>
                </div>
                <div className="next-video-info">
                    <div><Link to={`/video/${video.id}`}> {video.title}</Link></div>
                    <div>{video.description}</div>
                    <div>조회수{video.view} 2020.01.01</div>
                </div>
            </div>
        </>
    );
};

export default VideoNextCard;