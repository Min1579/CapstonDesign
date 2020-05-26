import React from 'react';
import "./VideoMain.css"
import Video from "./Video";
import VideoDescription from "./VideoDescription";
import PlayList from "./PlayList";
import VideoComments from "./VideoComments";
import {Col, Row} from "react-bootstrap";

const VideoMain = ({currentUser}) => {
    return (
        <div className="video-main-box">
            <Row className="row">
                <Col className="col" md={8}>
                    <Video />
                    <br/>
                    <VideoDescription />
                    <br/>
                    <VideoComments />
                </Col>
                <Col className="col" md={4}>
                    <PlayList/>
                </Col>
            </Row>
        </div>
    );
};

export default VideoMain;