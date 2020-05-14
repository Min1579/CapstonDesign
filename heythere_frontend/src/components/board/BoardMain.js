import React, {useEffect} from 'react';
import axios from 'axios';

const BoardMain = ({boardId}) => {

    useEffect(() => {
        axios.get(`http://localhost:8080/board/main/${boardId}`,null)
            .then((res) => {
                console.log(res.data);
            })
            .catch((err) => {
                console.log(err);
            })
    }, []);

    return (
        <div>
            <h3>this is {boardId} boards!</h3>
        </div>
    );
};

export default BoardMain;