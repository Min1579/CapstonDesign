import React, {useState, useEffect} from 'react';
import "./Home.css"
import axios from "axios";
import VideoCard from "../components/home/VideoCard";

const Home = () => {
    const [videos, setVideos] = useState([]);
    useEffect(() => {
        axios.get("http://localhost:8080/video")
            .then((res) => {
                setVideos(res.data);
            })
            .catch()
    }, []);

    return (
        <div className="video-player-cards-container">
            {videos.map(video => (
                <VideoCard video={video}/>
            ))}
        </div>
    );
};

export default Home;