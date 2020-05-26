import React from 'react';
import "./Posts.css"

const Posts = () => {
    return (
        <div>
            <table className="table">
                <thead>
                <tr>
                    <td width="5%">#</td>
                    <td className="title">제목</td>
                    <td width="10%">글쓴이</td>
                    <td width="10%">날짜</td>
                </tr>
                </thead>
                <tbody>
                <tr className="table-post">
                    <td width="5%">1</td>
                    <td className="title">test</td>
                    <td width="10%">min</td>
                    <td width="10%">2019-12-12</td>
                </tr>
                <tr className="table-post">
                    <td width="5%">2</td>
                    <td className="title">test2</td>
                    <td width="10%">min</td>
                    <td width="10%">2019-12-12</td>
                </tr>
                </tbody>
            </table>
        </div>
    );
};

export default Posts;