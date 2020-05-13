import React, {useState, useEffect} from 'react';
import axios from "axios";
import {
    Card, Button
} from 'react-bootstrap'

const SearchList = ({input}) => {
    const [results, setResult] = useState([]);

    useEffect(() => {
        axios.post(`http://localhost:8080/user/search/${input}`)
            .then((res) => {
                console.log(res.data);
                setResult(res.data);
            }).catch((err) => console.log(err));
    },[]);

    return results.map((res) => {
        return (
        <Card maxWidth={320}
              maxHeight={180}
              alt="320x180" Inline >
            <Card.Img variant="top" src="http://placehold.it/130x80" />
            <Card.Body>
                <Card.Title>{res.name}</Card.Title>
                <Card.Text>
                    Some quick example text to build on the card title and make up the bulk of
                    the card's content.
                </Card.Text>
                <Button variant="primary">게시판</Button>
                <Button variant="primary">방송 보러가기</Button>
            </Card.Body>
        </Card>
        )
    })
};

export default SearchList;