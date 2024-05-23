import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/Home.css';

function Home() {
  return (
    <div className="homeContainer">
      <h1 className="hm-title">User Authentication System</h1>
      <div className="button-group">
        <Link to="/login" className="log-btn">Login</Link>
        <Link to="/register" className="reg-btn">Create</Link>
      </div>
    </div>
  );
}

export default Home;
