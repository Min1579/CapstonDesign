import React, {useState, useEffect} from 'react';
import "./Community.css"
import {Route, useHistory} from "react-router-dom";
import CommunityHome from "../components/community/CommunityHome";
import CommunityVideo from "../components/community/CommunityVideo";
import CommunityStreaming from "../components/community/CommunityStreaming";
import CommunityInformation from "../components/community/CommunityInfomation";
import axios from "axios";

const Community = ({ownerId, currentUser}) => {
    const history = useHistory();

    const [owner, setOwner] = useState({
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
    }, []);

    const redirectHandler = () => {
        history.push(`/community/${ownerId}/home`);
        window.location.reload();
    }


    return (
        <div id="community-container">
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
                    <button className="community-link" onClick={redirectHandler}>Home</button>
                    <button lassName="community-link" to={`/community/${ownerId}/video`}>Video</button>
                    <button className="community-link" to={`/community/${ownerId}/streaming`}>Streaming</button>
                    <button className="community-link" to={`/community/${ownerId}/info`}>Information</button>
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
        </div>
    );
};

export default Community;