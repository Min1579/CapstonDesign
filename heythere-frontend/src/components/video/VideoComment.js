import React, {useState, useEffect} from 'react';
import "./VideoComment.css"
import axios from "axios";

const VideoComment = ({videoId, currentUser}) => {
    const [comments, setComments] = useState([]);

    useEffect(() => {
        axios.get(`http://localhost:8080/v/${videoId}`)
            .then((res) => {
                console.log(currentUser);
                console.log(res.data);
                setComments(res.data);
            })
            .catch(err => console.log(err));
    }, []);

    const [commentInput, setCommentInput] = useState("");
    const commentInputHandler = (e) => {
        console.log(e.target.value);
        setCommentInput(e.target.value);
    };
    const onCommentSubmitHandler = async (e) => {
        e.preventDefault();
        await axios.post(`http://localhost:8080/v/${videoId}/u/${currentUser.userId}`,
            {"comment" : commentInput },
            {
                headers: {
                    "Authorization": `Bearer ${currentUser.accessToken}`
                }
            })
            .then((res) => {
                console.log(res.data);
                setComments(res.data);
            })
            .catch(err => console.log(err))
    };

    return (
        <div id="video-comment-container">
            <div id="video-comment-input">
                <img id="video-comment-profile" src={currentUser.picture} alt=""/>
                <input id="comment-input" type="text" placeholder="공개 댓글 추가" onKeyUp={commentInputHandler}/>
                <button id="comment-button" onClick={onCommentSubmitHandler}>답글</button>
            </div>
            <br/>

            <div id="video-comment-list">
                {(comments.map(({userId, commentId, name, picture, comment, good, bad}) =>
                    <div id="video-comment">
                        <div>
                            <img id="video-comment-profile" src={picture} alt=""/>
                        </div>
                        <div id="video-comments">
                            <div>{name}</div>
                            <div>{comment}</div>
                            <div id="video-comment-info">
                                <div id="good"/> {good} <div id="bad"/> {bad} </div>
                            <div>댓글 보기</div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default VideoComment;