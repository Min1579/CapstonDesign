import React, {useState} from 'react';
import "./shared.css"
import "./VideoUpload.css";
import axios from "axios";
import {Form, Col, Button} from "react-bootstrap";

const VideoUpload = ({currentUser}) => {
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const [video, setVideo] = useState(null);
    const [thumbnail, setThumbnail] = useState(null);

    const onTitleChangeHandler = (e) => {
        setTitle(e.target.value);
    };

    const onDescriptionChangeHandler = (e) => {
        setDescription(e.target.value);
    };

    const onVideoChangeHandler = (e) => {
        setVideo(e.target.files[0]);
    };

    const onThumbnailChangeHandler = (e) => {
        setThumbnail(e.target.files[0]);
    };

    const onSubmit = (e) => {
        e.preventDefault();

        const formData = new FormData();
        formData.append("title", title);
        formData.append("description", description);
        formData.append("video", video);
        formData.append("thumbnail", thumbnail);

        console.log(`current id : ${currentUser.userId}`);
        axios.post(`http://localhost:8080/video/upload/${currentUser.userId}`,
            formData,
            {
                headers : {
                     'Content-Type': 'multipart/form-data',
                     "Authorization" : `Bearer ${currentUser.accessToken}`,
                }
            })
            .then((res) => {
                console.log(res.data);
            })
            .catch((err) => {
                console.log(err);
            });
    };

    return (
        <div className="video-upload-box">
            <h1>video upload</h1>
            <div>
                <Form>
                    <Form.Row>
                        <Form.Group className="video" as={Col} controlId="formGridVideo">
                            <Form.Label className="form-title">Video</Form.Label>
                            <Form.Control className="form-control-multipart" type="file" onChange={onVideoChangeHandler}  />
                        </Form.Group>

                        <Form.Group className="thumbnail" as={Col} controlId="formGridThumbnail">
                            <Form.Label className="form-title">Thumbnail</Form.Label>
                            <Form.Control className="form-control-multipart" type="file" onChange={onThumbnailChangeHandler} />
                        </Form.Group>
                    </Form.Row>

                    <Form.Group controlId="formGridTitle">
                        <Form.Label>Title</Form.Label>
                        <Form.Control  placeholder="title" onChange={onTitleChangeHandler} />
                    </Form.Group>

                    <Form.Group controlId="formGridDescription">
                        <Form.Label>Description</Form.Label>
                        <Form.Control placeholder="description" onChange={onDescriptionChangeHandler} />
                    </Form.Group>

                    <Button className="button" type="submit" onClick={onSubmit}>
                        Submit
                    </Button>
                </Form>
            </div>
        </div>
    );
};

export default VideoUpload;