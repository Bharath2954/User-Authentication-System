import React, { useState } from 'react';
import { resetPassword } from '../api/WhitelistService';
import '../styles/Reset.css';

const Reset = ({ userName }) => {
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');

  const handleReset = async () => {
    if(password=='' || confirmPassword=='')
    {
        alert("Passwords should not be Empty");
        return;
    }
    if (password !== confirmPassword) {
        alert("Passwords don't match!");
        return;
    }
    try {
      const response = await resetPassword(userName, password);
      // Redirect to Login
    } catch (exception) {
      alert(exception);
    }
  };

  return (
    <div className="reset-container">
      <div className="reset-text">
        <p>Reset Password</p>
      </div>
      <div className="reset-input-container">
        <input type="password" className="pass" placeholder="New Password" value={password} onChange={(e) => setPassword(e.target.value)} required/>
        <input type="password" className="pass" placeholder="Confirm Password" value={confirmPassword} onChange={(e) => setConfirmPassword(e.target.value)} />
      </div>
        <button className="reset-btn" onClick={handleReset}>Reset</button>
    </div>
  );
};

export default Reset;
