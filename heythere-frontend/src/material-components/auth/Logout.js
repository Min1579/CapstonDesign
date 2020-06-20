import React, {useState, useEffect} from 'react';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import ExitToAppIcon from '@material-ui/icons/ExitToApp';

const Logout = () => {



    const [open, setOpen] = useState(false);

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
        localStorage.clear();
    };


    return (
        <div>
            <ExitToAppIcon onClick={handleClickOpen}/>

            <form>
                <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
                    <DialogTitle>Logout</DialogTitle>
                    <DialogContent>
                        <DialogContentText>
                            You are logged out safely!
                        </DialogContentText>

                    </DialogContent>
                    <DialogActions>
                        <Button onClick={handleClose} color="primary">
                            Bye!
                        </Button>
                    </DialogActions>
                </Dialog>
            </form>
        </div>
    );
};

export default Logout;