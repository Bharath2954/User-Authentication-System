import axios from 'axios';

const BASE_URL = 'http://localhost:8080';

export const getByUsername = async (username, token) => {
  try {
    const response = await axios.get(`${BASE_URL}/user/get/${username}`, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    });
    return response.data;
  } catch (error) {
    console.error("Error fetching user data:", error);
    throw error;
  }
};

export const updateUser = async (username, token, userData) => {
  try {
    console.log(userData);
    const response = await axios.put(`${BASE_URL}/user/update/${username}`, userData, {
      headers: {
        Authorization: `Bearer ${token}`,
        "Content-Type": "text/plain"
      }
    });
    return response.data;
  } catch (error) {
    console.error("Error updating user data:", error);
    throw error;
  }
};

export const logout = async (token) => {
  try {
    const response = await axios.post(`${BASE_URL}/logout`, {token}, {
      headers: {
        Authorization: `Bearer ${token}`,
        'Content-Type': 'application/json'
      }
    });
    return response.data;
  } catch (error) {
    console.error("Error during logout:", error);
    throw error;
  }
};
