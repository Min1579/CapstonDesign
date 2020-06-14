import React from 'react';
import "./VideoAuthor.css"

const VideoAuthor = ({username, picture}) => {
    return (
        <div id="video-author-container">
            <div id="user-profile">
                <img src={picture} alt=""/>
            </div>
            <div id="video-author-info">
                <h5>{username}</h5>
                <div>구독자 11명</div>
                <div>fun cool sexy
                    explain? Not Sexy</div>
            </div>
            <div>
                <button id="sub-btn">Subscribe</button>
            </div>
        </div>
    );
};

export default VideoAuthor;