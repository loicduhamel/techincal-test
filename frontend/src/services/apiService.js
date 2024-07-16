import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080';

export default {
  getAlbums(id = null) {
    const url = id ? `${API_BASE_URL}/albums/${id}` : `${API_BASE_URL}/albums`;
    return axios.get(url)
      .then(response => response.data)
      .catch(error => {
        console.error('Error get album(s)', error);
        throw error;
      });
  },

  getUsers(id = null) {
    const url = id ? `${API_BASE_URL}/users/${id}` : `${API_BASE_URL}/users`;
    return axios.get(url)
      .then(response => response.data)
      .catch(error => {
        console.error('Error get user(s)', error);
        throw error;
      });
  },

  getAlbumsByUsers() {
    const url = `${API_BASE_URL}/users/albums`;
    return axios.get(url)
      .then(response => response.data)
      .catch(error => {
        console.error('Error get album(s) by user(s)!', error);
        throw error;
      });
  }
};
