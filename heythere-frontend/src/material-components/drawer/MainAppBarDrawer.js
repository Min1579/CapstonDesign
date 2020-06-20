import React, {useState, useEffect, useCallback} from 'react';
import clsx from "clsx";
import {makeStyles} from "@material-ui/core/styles";
import Drawer from "@material-ui/core/Drawer";
import List from "@material-ui/core/List";
import Divider from "@material-ui/core/Divider";
import ListItem from "@material-ui/core/ListItem";
import ListItemIcon from "@material-ui/core/ListItemIcon";
import ListItemText from "@material-ui/core/ListItemText";
import InboxIcon from "@material-ui/icons/MoveToInbox";
import MailIcon from "@material-ui/icons/Mail";
import IconButton from '@material-ui/core/IconButton';
import MenuIcon from '@material-ui/icons/Menu';
import UserListInDrawer from "./UserListInDrawer";
import PersonIcon from '@material-ui/icons/Person';
import AlternateEmailIcon from '@material-ui/icons/AlternateEmail';

const useStyles = makeStyles({
    list: {
        width: 300
    },
    fullList: {
        width: "auto",
    }
});

const MainAppBarDrawer = () => {

    const classes = useStyles();
    const [state, setState] = React.useState({
        top: false,
        left: false,
        bottom: false,
        right: false
    });

    const toggleDrawer = (anchor, open) => event => {
        if (event.type === "keydown" && (event.key === "Tab" || event.key === "Shift"))
            return;
        setState({...state, [anchor]: open});
    };

    const list = anchor => (
        <div
            className={clsx(classes.list, {
                [classes.fullList]: anchor === "top" || anchor === "bottom"
            })}
            role="presentation"
            onClick={toggleDrawer(anchor, false)}
            onKeyDown={toggleDrawer(anchor, false)}
        >

            <UserListInDrawer />
            <Divider/>

            <List>
                <ListItem>
                    <ListItemIcon>
                        { <AlternateEmailIcon />}
                    </ListItemIcon>
                    <ListItemText primary={"Developers"}/>
                </ListItem>
                {["Seungmin Han", "Moonhyun Lee", "Jungyoon Huh"].map((text, index) => (
                    <ListItem button key={text}>
                        <ListItemIcon>
                            {<PersonIcon />}
                        </ListItemIcon>
                        <ListItemText primary={text}/>
                    </ListItem>
                ))}
            </List>
        </div>
    );


    return (
        <div>
            <IconButton onClick={toggleDrawer("left", true)}>
                <MenuIcon style={{color:'white'}} fontSizeInherit/>
            </IconButton>
            <Drawer
                anchor={"left"}
                open={state["left"]}
                onClose={toggleDrawer("left", false)}
            >
                {list("left")}
            </Drawer>
        </div>
    );
};

export default MainAppBarDrawer;