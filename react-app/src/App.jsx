import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './Components/Home';
import Login from './Components/Login';
import Register from './Components/Register';
import Reset from './Components/Reset';
import UserPage from './Components/UserPage';
import './styles/App.css';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/reset" element={<Reset />} />
        <Route path="/userPage" element={<UserPage />} />
      </Routes>
    </Router>
  );
}

export default App;
