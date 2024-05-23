import React, { useState } from 'react';
import { authenticate } from '../api/WhitelistService';
import { Link, useNavigate } from 'react-router-dom';
import '../styles/Login.css';

function Login() {
  const [user, setUser] = useState({ userName: '', password: '' });

  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setUser(prevUser => ({ ...prevUser, [name]: value }));
  };

  const handleLogin = async () => {
    try {
      const response = await authenticate(user);
      const token = response.data.token;
      navigate('/userPage', { state: { userName: user.userName, token: token } });
    } catch (exception) {
      alert("Login error");
      console.log(exception);
    }
  };

  return (
    <div className="loginContainer">
      <div className="log-text">
        <p>LOGIN</p>
      </div>
      <div className="log-container-1">
        <input className="inp-username" type="text" name="userName" placeholder="User Name" value={user.userName} onChange={handleChange} />
        <input className="inp-password" type="password" name="password" placeholder="Password" value={user.password} onChange={handleChange} />
        <button className="btn-log" onClick={handleLogin}>Login</button>
        <div className="log-container-2">
          <Link to="/reset" className="forget-pass">Forget Password?</Link>
          <Link to="/register" className="register">Don't have an account?</Link>
        </div>
      </div>
    </div>
  );
}

export default Login;
