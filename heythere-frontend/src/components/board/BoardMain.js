import React from 'react';
import BoardLayout from "./BoardLayout";
import "./BoardMain.css"

const BoardMain = ({currentUser}) => {
    return (
        <div className="board-main">
            <BoardLayout />
        </div>
    );
};

export default BoardMain;