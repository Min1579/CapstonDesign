import React, {useState} from 'react';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import Avatar from '@material-ui/core/Avatar';
import axios from "axios";

export default function Register() {
    const [profileImg, setProfileImg] = useState({preview: null, raw: null});
    const [email, setEmail] = useState({input: '', message: '', valid: false});
    const [username, setUsername] = useState({input: '', message: '', valid: false});
    const [name, setName] = useState({input: '', message: '', valid: false});
    const [password, setPassword] = useState({input: '', message: '', valid: false});
    const [introduction, setIntroduction] = useState('');

    const [open, setOpen] = useState(false);

    const fileUploadHandler = e => {
        if (e.target.files[0] !== undefined) {
            setProfileImg({
                preview: URL.createObjectURL(e.target.files[0]),
                raw: e.target.files[0]
            });
            return;
        }
        setProfileImg({preview: null, raw: null});
    };

    const emailChangeHandler = async e => {
        const FORMAT_WARNING_MESSAGE = 'Email Format Not Accepted!';
        const DUPLICATED_WARNING_MESSAGE = 'Email is Being Used!';
        const ACCEPTED_MESSAGE = 'Possible!';

        const emailInput = e.target.value;
        if (!emailInput.includes("@") || !emailInput.includes('.')) {
            setEmail({input: emailInput, message: FORMAT_WARNING_MESSAGE, valid: false});
            return;
        }

        await axios.post("http://localhost:8080/user/check/email",
            {email: emailInput})
            .then((res) => {
                if (res.data.valid)
                    setEmail({email: emailInput, message: DUPLICATED_WARNING_MESSAGE, valid: false});
            })
            .catch(err => console.log(err));

        setEmail({input: emailInput, message: ACCEPTED_MESSAGE, valid: true});
    };

    const usernameChangeHandler = async e => {
        const FORMAT_WARNING_MESSAGE = 'User ID Too Short! At least 6 letters';
        const DUPLICATED_WARNING_MESSAGE = 'User ID is Being Used!';
        const ACCEPTED_MESSAGE = 'Possible!';

        const usernameInput = e.target.value;
        if (usernameInput.length < 6) {
            setUsername(
                {
                    input: usernameInput,
                    message: FORMAT_WARNING_MESSAGE,
                    valid: false
                }
            );
            return;
        }

        await axios.post("http://localhost:8080/user/check/email",
            {username: usernameInput})
            .then((res) => {
                if (res.data.valid)
                    console.log(res.data);
                setUsername(
                    {
                        input: usernameInput,
                        message: DUPLICATED_WARNING_MESSAGE,
                        valid: false
                    }
                );
            })
            .catch(err => console.log(err));

        setUsername({input: usernameInput, message: ACCEPTED_MESSAGE, valid: true});
    };

    const nameChangeHandler = async e => {
        const FORMAT_WARNING_MESSAGE = 'Name Must be at least 1 letter!';
        const DUPLICATED_WARNING_MESSAGE = 'Name is Being Used!';
        const ACCEPTED_MESSAGE = 'Possible!';

        const nameInput = e.target.value;

        if (nameInput.length < 1) {
            setName(
                {
                    input: nameInput,
                    message: FORMAT_WARNING_MESSAGE,
                    valid: false
                });
            return;
        }

        await axios.post("http://localhost:8080/user/check/name",
            {name: nameInput})
            .then((res) => {
                if (res.data.valid)
                    setName(
                        {
                            input: nameInput,
                            message: DUPLICATED_WARNING_MESSAGE,
                            valid: false
                        });
            })
            .catch(err => console.log(err));


        setName(
            {
                input: nameInput,
                message: ACCEPTED_MESSAGE,
                valid: true
            });
    };

    const passwordChangeHandler = (e) => {
        const FORMAT_WARNING_MESSAGE = "Password Must Be At Least 6 Letters";
        const ACCEPTED_MESSAGE = "GOOD! :)";

        const pwdInput = e.target.value;
        if (pwdInput.length < 6) {
            setPassword({input: pwdInput, message: FORMAT_WARNING_MESSAGE, valid: false});
            return;
        }
        setPassword(
            {
                input: pwdInput,
                message: ACCEPTED_MESSAGE,
                valid: true
            });
    };
    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    const onSubmitWithImg = () => {
        const form = new FormData();
        form.append("img", profileImg.raw);
        form.append("email", email.input);
        form.append("username", username.input);
        form.append("name", username.input);
        form.append("pwd", password.input);
        form.append("introduction", introduction);

        axios.post("http://localhost:8080/user/register/1",
            form,
            {
                headers: {
                    "Content-Type": "multipart/form-data",
                }
            })
            .then((res) => {
                console.log(res.data);
                handleClose();
            })
            .catch(err => console.log(err));
    }

    const onSubmit = () => {
        if (!email.valid || !username.valid || !name.valid || !password.valid) {
            return;
        }
        if (profileImg.raw !== null) {
            onSubmitWithImg();
            return;
        }


        axios.post("http://localhost:8080/user/register/0",
            {
                'email': email.input,
                'username': username.input,
                'name': name.input,
                'pwd': password.input,
                'introduction': introduction
            }
        )
            .then((res) => {
                console.log(res.data);
                handleClose();
            })
            .catch(err => console.log(err));
    };

    return (
        <div>
            <Button color="primary" onClick={handleClickOpen}>
                Register
            </Button>
            <Dialog fullWidth open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
                <DialogTitle id="form-dialog-title">Register</DialogTitle>
                <DialogContent>
                    <DialogContentText>
                        Welcome! Your information is valuable.
                        <br/>
                        <span style={{color: "red"}}>You must fill in the form where * is existed.</span>
                    </DialogContentText>

                    <form>
                        <label htmlFor="file">
                            <Avatar button alt="Remy Sharp" src={profileImg.preview}
                                    style={{height: 200, width: 200, margin: '0 auto'}}>
                                Click!
                            </Avatar>
                        </label>
                        <input accept="image/*" style={{display: 'none'}} id="file" type="file"
                               onChange={fileUploadHandler}/>

                        <TextField
                            autoFocus
                            margin="dense"
                            id="email"
                            label="Email Address"
                            type="email"
                            name="email"
                            fullWidth
                            required
                            onChange={emailChangeHandler}
                        />
                        <div
                            style={email.valid ? {color: "blue"}
                                : {color: "red"}}>
                            {email.message}
                        </div>

                        <TextField
                            autoFocus
                            margin="dense"
                            id="username"
                            label="User ID"
                            type="text"
                            name="username"
                            fullWidth
                            required
                            onChange={usernameChangeHandler}
                        />
                        <div
                            style={username.valid ? {color: "blue"}
                                : {color: "red"}}>
                            {username.message}
                        </div>
                        <TextField
                            autoFocus
                            margin="dense"
                            id="name"
                            label="Name"
                            type="text"
                            name="name"
                            fullWidth
                            required
                            onChange={nameChangeHandler}
                        />
                        <div
                            style={name.valid ? {color: "blue"}
                                : {color: "red"}}>
                            {name.message}
                        </div>

                        <TextField
                            autoFocus
                            margin="dense"
                            id="password"
                            label="Password"
                            type="password"
                            name="password"
                            fullWidth
                            required
                            onChange={passwordChangeHandler}
                        />
                        <div
                            style={password.valid ? {color: "blue"}
                                : {color: "red"}}>
                            {password.message}
                        </div>

                        <TextField
                            autoFocus
                            margin="dense"
                            id="introduction"
                            label="Introduction(Multiline possible)"
                            type="text"
                            name="introduction"
                            multiline
                            fullWidth
                            onChange={(e) => setIntroduction(e.target.value)}
                        />

                    </form>
                </DialogContent>
                <DialogActions>
                    <Button onClick={onSubmit} color="primary">
                        Finish!
                    </Button>
                </DialogActions>
            </Dialog>
        </div>
    );
}
