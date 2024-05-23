import React, { useState } from 'react';
import { register } from '../api/WhitelistService';
import { Link, useNavigate } from 'react-router-dom';
import '../styles/Register.css';
import { updateUser } from '../api/UserService';

function Register() {
  const [user, setUser] = useState({
    userName: '',
    password: '',
    firstname: '',
    lastname: '',
    email: '',
    role: ''
  });

  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setUser(prevUser => ({ ...prevUser, [name]: value }));
  };

  const handleRegister = async () => {
    try {
      const response = await register(user);
      const token = response.data.token;
      navigate('/userPage', { state: { userName: user.userName, token: token } });
    } catch (exception) {
      alert("Register error");
      console.log(exception);
    }
  };

  return (
    <div className="registerContainer">
      <div className="reg-text">
        <p>Create an Account</p>
      </div>
      <div className="reg-container-1">
        <input className="inp-username" type="text" name="userName" placeholder="User Name" value={user.userName} onChange={handleChange} />
        <input className="inp-password" type="password" name="password" placeholder="Password" value={user.password} onChange={handleChange} />
        <input className="inp-firstname" type="text" name="firstname" placeholder="First Name" value={user.firstname} onChange={handleChange} />
        <input className="inp-lastname" type="text" name="lastname" placeholder="Last Name" value={user.lastname} onChange={handleChange} />
        <input className="inp-email" type="email" name="email" placeholder="E-mail" value={user.email} onChange={handleChange} />
        <select className="inp-role" name="role" value={user.role} onChange={handleChange}>
          <option value="ADMIN">ADMIN</option>
          <option value="USER">USER</option>
        </select>
        <button className="btn-reg" onClick={handleRegister}>Create</button>
        <div className="reg-container-2">
          <Link to="/login" className="login">Already have an account?</Link>
        </div>
      </div>
    </div>
  );
}

export default Register;
