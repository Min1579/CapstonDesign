import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Table, Image} from "react-bootstrap";

const RecommendList = () => {
    return (
        <div>
            <Table striped hover variant="dark" size="sm">
                <thead>
                <td colSpan="3">Recommend</td>
                </thead>
                <tr>
                    <td><Image src="http://placehold.it/30x30" roundedCircle/></td>
                    <td>aaaa</td>
                    <td>asdfasdf</td>
                </tr>
                <tr>
                    <td><Image src="http://placehold.it/30x30"  roundedCircle/></td>
                    <td>aaaa</td>
                    <td>asdfasdf</td>
                </tr>
                <tr>
                    <td><Image src="http://placehold.it/30x30" roundedCircle/></td>
                    <td>aaaa</td>
                    <td>asdfasdf</td>
                </tr>
                <tr>
                    <td><Image src="http://placehold.it/30x30" roundedCircle/></td>
                    <td>aaaa</td>
                    <td>asdfasdf</td>
                </tr>
                <tr>
                    <td><Image src="http://placehold.it/30x30" roundedCircle/></td>
                    <td>aaaa</td>
                    <td>asdfasdf</td>
                </tr>
            </Table>
        </div>
    );
};

export default RecommendList;