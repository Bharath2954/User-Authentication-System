import React, { useState, useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { getByUsername, logout, updateUser } from '../api/UserService';
import '../styles/UserPage.css';

const UserPage = () => {
  const location = useLocation();
  const { userName, token } = location.state || { userName: '', token: '' };

  const [user, setUser] = useState({
    username: '',
    password: '',
    firstname: '',
    lastname: '',
    email: '',
    role: ''
  });
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [isEditing, setIsEditing] = useState(false);
  const [editableUser, setEditableUser] = useState({
    username: '',
    password: '',
    firstname: '',
    lastname: '',
    email: '',
    role: ''
  });

  const navigate = useNavigate();

  useEffect(() => {
    const fetchUser = async () => {
      try {
        const userData = await getByUsername(userName, token);
        setUser(userData);
        setEditableUser(userData);
      } catch (error) {
        setError("Error fetching user data");
        console.error("Error fetching user data:", error);
      } finally {
        setLoading(false);
      }
    };

    if (userName && token) {
      fetchUser();
    } else {
      setLoading(false);
      setError("Missing username or token");
    }
  }, [userName, token]);

  const handleEdit = () => {
    setIsEditing(true);
  };

  const handleSave = async () => {
    try {
      setError(null);
      await updateUser(userName, token, editableUser);
      setUser(editableUser);
      setIsEditing(false);
    } catch (error) {
      setError("Error updating user data");
      console.error("Error updating user data:", error);
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setEditableUser({ ...editableUser, [name]: value });
  };

  const handleLogout = async () => {
    try {
      await logout(token);
    } catch (error) {
      console.error("Error during logout:", error);
    }
    navigate("/");
  };

  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>{error}</div>;
  }

  return (
    <div className='container'>
      <div className="title-container">
        <p>Welcome, {userName}</p>
      </div>
      {user && (
        <div className="user-details">
          <div className="field">
            <label>Username:</label>
            <input
              type="text"
              name="username"
              value={editableUser.username}
              onChange={handleChange}
              readOnly={!isEditing}
            />
          </div>
          <div className="field">
            <label>Firstname:</label>
            <input
              type="text"
              name="firstname"
              value={editableUser.firstname}
              onChange={handleChange}
              readOnly={!isEditing}
            />
          </div>
          <div className="field">
            <label>Lastname:</label>
            <input
              type="text"
              name="lastname"
              value={editableUser.lastname}
              onChange={handleChange}
              readOnly={!isEditing}
            />
          </div>
          <div className="field">
            <label>E-mail:</label>
            <input
              type="email"
              name="email"
              value={editableUser.email}
              onChange={handleChange}
              readOnly={!isEditing}
            />
          </div>
          <div className="btns">
            {!isEditing ? (
              <button className="edit" onClick={handleEdit}>Edit</button>
            ) : (
              <button className="save" onClick={handleSave}>Save</button>
            )}
            <button className="logout" onClick={handleLogout}>Logout</button>
          </div>
        </div>
      )}
    </div>
  );
};

export default UserPage;
