import React from 'react';
import "./Sidebar.css"
import Footer from "../components/sidebar/Footer";
import SidebarUserList from "../components/sidebar/SidebarUserList";
import axios from "axios";

const Sidebar = () => {
    return (
        <div className="sidebar">
            <SidebarUserList />
            <Footer />
        </div>
    );
};

export default Sidebar;