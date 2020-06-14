import React, {useState, useEffect} from 'react';
import "./VideoInfo.css"
import axios from "axios";

const VideoInfo = ({videoId, title, description, view, good, bad}) => {
    const [goodUpdate, setGoodUpdate] = useState(0);
    const [badUpdate, setBadUpdate] = useState(0);

    useEffect(() => {
        setBadUpdate(bad);
        setGoodUpdate(good);
    },[]);

    const onGoodButtonClickHandler = () => {
        axios.get(`http://localhost:8080/video/good/${videoId}`, null)
            .then((res) => {
                setGoodUpdate(res.data.good);
                console.log(goodUpdate);
            })
            .catch(err => console.log(err));
    };

    const onBadButtonClickHandler = () => {
        axios.get(`http://localhost:8080/video/bad/${videoId}`, null)
            .then((res) => {
                setBadUpdate(res.data.bad);
                console.log(badUpdate);
            })
            .catch(err => console.log(err))
    }

    return (
        <div id="video-info-container">
            <h3>{title}</h3>
            <p>{description}</p>
            <div id="video-sub-info">
                <div id="sub-info">조회수 {view}회 2020.01.01</div>
                <div id="icon-set">
                    <div id="good" onClick={onGoodButtonClickHandler}/>
                    <div>{goodUpdate}</div>
                    <div id="bad" onClick={onBadButtonClickHandler}/>
                    <div>{badUpdate}</div>
                    <div id="share"/>
                    <div>share</div>
                </div>

            </div>
            <hr/>
        </div>
    );
};

export default VideoInfo;