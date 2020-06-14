import React, {useState, useEffect} from 'react';
import "./Community.css"
import {Link, Route, BrowserRouter as Router} from "react-router-dom";
import CommunityHome from "../components/community/CommunityHome";
import CommunityVideo from "../components/community/CommunityVideo";
import CommunityStreaming from "../components/community/CommunityStreaming";
import CommunityInformation from "../components/community/CommunityInfomation";
import axios from "axios";

const Community = ({ownerId, currentUser}) => {
    const [owner ,setOwner] = useState({
        name: "",
        picture: ""
    });

    useEffect(() => {
        axios.get(`http://localhost:8080/c/${ownerId}`)
            .then((res) => {
                setOwner(res.data);
                console.log(owner);
            })
            .catch(err => console.log(err));
    },[]);


    return (
        <div id="community-container">
            <Router>
                <div id="community-navbar">
                    <div id="community-navbar__main">
                        <div id="community-owner-info">
                            <div id="img-box"><img src={owner.picture} alt=""/></div>
                            <div><h5>{owner.name}</h5> 구독자 11명</div>
                        </div>
                        <div id="button-box">
                            <button>Subscribe</button>
                        </div>
                    </div>
                    <div id="community-navbar__list">
                        <Link className="community-link" to={`/community/${ownerId}/home`}>Home</Link>
                        <Link className="community-link" to={`/community/${ownerId}/video`}>Video</Link>
                        <Link className="community-link" to={`/community/${ownerId}/streaming`}>Streaming</Link>
                        <Link className="community-link" to={`/community/${ownerId}/info`}>Information</Link>
                        <hr/>
                    </div>
                </div>

                <div id="sub-router-controller">
                    <Route path={"/community/:ownerId/home"} exact render={({match}) =>
                        <CommunityHome ownerId={match.params.ownerId} currentUser={currentUser}/>}
                    />
                    <Route path={"/community/:ownerId/video"} exact render={({match}) =>
                        <CommunityVideo ownerId={match.params.ownerId} currentUser={currentUser}/>}
                    />
                    <Route path={"/community/:ownerId/streaming"} exact render={({match}) =>
                        <CommunityStreaming ownerId={match.params.ownerId} currentUser={currentUser}/>}
                    />
                    <Route path={"/community/:ownerId/info"} exact render={({match}) =>
                        <CommunityInformation ownerId={match.params.ownerId} currentUser={currentUser}/>}
                    />
                </div>
            </Router>
        </div>
    );
};

export default Community;