import React from 'react';
import "./Main.css";
import {Route, Switch} from 'react-router-dom';
import Mypage from "../page/MyPage";
import Video from "../page/Video";
import VideoUpload from "../page/VideoUpload";
import BoardMain from "../page/BoardMain";
import Home from "../page/Home";
import Community from "../page/Community";
import SearchUsers from "../components/searchUser/SearchUsers";

const Main = ({currentUser}) => {
    return (
        <div className="main">
            <Switch>
                <Route path={`/search/:usernameOrNameOrEmail`} exact={true} render={({match}) =>
                    <SearchUsers usernameOrNameOrEmail={match.params.usernameOrNameOrEmail} />}
                />
                <Route path="/mypage" exact={true} render={() =>
                    <Mypage currentUser={currentUser}/>}
                />
                <Route path="/video/:videoId" exact={true} render={({match}) =>
                    <Video videoId={match.params.videoId} currentUser={currentUser} />}
                />
                <Route path="/video-upload" exact={true} render={() =>
                    <VideoUpload currentUser={currentUser}/>}
                />
                <Route path="/board" exact={true} render={() =>
                    <BoardMain currentUser={currentUser}/>}
                />
                <Route path="/community/:ownerId" exact={true} render={({match}) =>
                    <Community ownerId={match.params.ownerId} currentUser={currentUser}/>}
                />
                <Route path="/" exact={true} render={() => <Home/>} />

            </Switch>

        </div>
    );
};

export default Main;