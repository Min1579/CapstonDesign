import React from 'react';
import Grid from "@material-ui/core/Grid"
import VideoCard from "./VideoCard";

const VideoMain = () => {
    return (
        <Grid
            container
            direction="row"
            justify="flex-start"
            alignItems="flex-start">

            <VideoCard />
            <VideoCard />
            <VideoCard />
            <VideoCard />
            <VideoCard />
            <VideoCard />
            <VideoCard />
            <VideoCard />

        </Grid>
    );
};

export default VideoMain;