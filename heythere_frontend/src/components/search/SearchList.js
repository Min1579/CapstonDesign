import React, {useState, useEffect} from 'react';
import axios from "axios";
import {
    Card, Button, Col
} from 'react-bootstrap'
import {Link, Route} from 'react-router-dom';
import BoardMain from "../board/BoardMain";

const SearchList = ({input, goToBoardById}) => {
    const [results, setResult] = useState([]);

    useEffect(() => {
        axios.post(`http://localhost:8080/user/search/${input}`)
            .then((res) => {
                console.log(res.data);
                setResult(res.data);
            }).catch((err) => console.log(err));
    },[input]);

    return results.map((res) => {
        return (
            <div>
                <Card maxWidth={320} maxHeight={180} alt="320x180" Inline >
                    <Card.Img variant="top" src="http://placehold.it/130x80" />
                    <Card.Body>
                        <Card.Title>{res.name}</Card.Title>
                        <Card.Text>
                            Some quick example text to build on the card title and make up the bulk of the card's content.
                        </Card.Text>
                        <Button variant="primary" onClick={()=>
                            goToBoardById(res.id)
                        }><Link to="/board">게시판으로 이동</Link></Button>
                        <Button variant="primary">방송가기</Button>
                    </Card.Body>
                </Card>
                <Route path="/board"
                       exact
                       render={() => <BoardMain boardId={res.id} />}/>
            </div>
        )
    })
};

export default SearchList;