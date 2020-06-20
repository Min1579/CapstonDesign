import React, {useState} from 'react';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import IconButton from '@material-ui/core/IconButton';
import Badge from '@material-ui/core/Badge';
import VideocamIcon from '@material-ui/icons/Videocam';

export default function StreamingSetting() {
    const [open, setOpen] = useState(false);

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    return (
        <>
            <IconButton aria-label="streaming now!" color="inherit">
                <Badge color="secondary">
                    <VideocamIcon onClick={handleClickOpen}/>
                </Badge>
            </IconButton>

            <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
                <DialogTitle id="form-dialog-title">Before Streaming!</DialogTitle>
                <DialogContent>
                    <DialogContentText>
                        before you streaming, set your live information!

                        <h2>Sorry, Streaming Service is now being prepared!</h2>
                    </DialogContentText>
                    <TextField
                        autoFocus
                        margin="dense"
                        id="Room Name"
                        label="Room Name"
                        type="text"
                        fullWidth
                        require
                    />

                    <TextField
                        autoFocus
                        margin="dense"
                        id="Streaming Description"
                        label="Streaming Description"
                        type="text"
                        multiline
                        fullWidth
                        require
                    />

                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose} color="primary">
                        Start Streaming Now!
                    </Button>
                </DialogActions>
            </Dialog>
        </>
    );
}
