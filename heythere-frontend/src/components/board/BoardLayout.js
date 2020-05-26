import React from 'react';
import "./BoardLayout.css"
import Posts from "./Posts";
import {Link} from "react-router-dom";

const BoardLayout = () => {
    return (
        <div className="board-layout">
            <Posts />
        </div>
    );
};

export default BoardLayout;