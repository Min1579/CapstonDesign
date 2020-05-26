import React from 'react';
import "./Video.css"

const Video = () => {
    return (
        <div className="video-player-box">
            <video src="http://localhost:8080/video/stream/1" width="100%"
                   controls loop muted autoPlay></video>
        </div>
    );
};

export default Video;