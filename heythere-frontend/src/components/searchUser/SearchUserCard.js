import React from 'react';
import {Link} from "react-router-dom";

const SearchUserCard = ({user}) => {
    const {userId, username, name, email, picture, description} = user;

    return (
        <div id="user-search-result-list">
            <div>
                <img src={picture} alt=""/>
            </div>
            <div><Link to={`/community/${userId}`}>{name} ({username})</Link></div>
            <div>{description}</div>
        </div>
    );
};

export default SearchUserCard;