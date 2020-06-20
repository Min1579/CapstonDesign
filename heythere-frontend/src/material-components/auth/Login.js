import React, {useState, useReducer} from 'react';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import AccountCircle from '@material-ui/icons/AccountCircle';
import Register from "./Register";
import axios from "axios";
import useLocalStorage from "../../hook/useLocalStorage";

const loginReducer = (state, action) => {
    return {
        ...state,
        [action.name]: action.value,
    }
};

export default function Login() {
    const [accessToken, setAccessToken] = useLocalStorage("accessToken", "");

    const [loginState, dispatch] = useReducer(loginReducer, {
        usernameOrEmail: '',
        password: ''
    });

    const [open, setOpen] = useState(false);

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
        window.location.reload();
    };

    const onChangeHandler = e => {
        dispatch(e.target);
    };

    const onSubmit = (e) => {
        e.preventDefault();

        axios.post("http://localhost:8080/api/auth/signin", loginState)
            .then((res) => {
                console.log(res.data);
                const {userId, accessToken} = res.data;
                setAccessToken(`Bearer ${accessToken}`);
            })
            .catch((err) => {
                console.log(err);
            });
        handleClose();
    };

    return (
        <>
            <AccountCircle onClick={handleClickOpen}/>

            <form>
                <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
                    <DialogTitle id="form-dialog-title">Login</DialogTitle>
                    <DialogContent>
                        <DialogContentText>
                            We encrypt and store your information and do not browse and use it without permission!
                        </DialogContentText>
                        <TextField
                            autoFocus
                            margin="dense"
                            name="usernameOrEmail"
                            label="Email Address Or Username"
                            type="text"
                            fullWidth
                            onChange={onChangeHandler}
                            require
                        />
                        <TextField
                            autoFocus
                            margin="dense"
                            name="password"
                            label="Password"
                            type="password"
                            fullWidth
                            onChange={onChangeHandler}
                            require
                        />
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={onSubmit} color="primary">
                            Login
                        </Button>

                        <Register/>

                        <Button onClick={handleClose} color="primary">
                            Forget?!
                        </Button>
                    </DialogActions>
                </Dialog>
            </form>
        </>
    );
}
