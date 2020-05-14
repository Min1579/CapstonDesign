import React from 'react';
import CarouselMain from "./CarouselMain";
import VideoList from "./VideoList";
import {Container} from "react-bootstrap";

const Main = () => {
    return (
        <div>
            <Container>
                <CarouselMain/>
                <VideoList />
            </Container>
        </div>
    );
};

export default Main;