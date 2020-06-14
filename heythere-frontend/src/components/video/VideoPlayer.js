import React,{useState, useEffect} from 'react';
import "./VidepPlayer.css"
import axios from "axios";

const VideoPlayer = ({videoUrl}) => {

    return (
        <div id="video-player-container">
            <video id="video" controls autoplay
                src={videoUrl} />
        </div>
    );
};

export default VideoPlayer;