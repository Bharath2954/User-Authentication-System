import axios from "axios";

const API_URL = "http://localhost:8080/whitelist";

export async function register(user) {
  return await axios.post(API_URL + "/register", user);
}

export async function authenticate(user) {
    return await axios.post(API_URL + "/authenticate", user)
}

export async function resetPassword(username, password) {
  return await axios.put(API_URL + '/reset' + username, password);
}