import axios from 'axios';

export default {

  getCollections() {
    return axios.get('/collections');
  },

  getCollectionsByUsername(username) {
    return axios.get(`/mycollections/${username}`);
  },

  viewCollection(id) {
      return axios.get(`/collections/${id}`);
  },

  addCollection(collection) {
      return axios.post('/collections', collection);
  },

  updateCollection(collection) {
    return axios.put('/collections', collection);
  },
  moveComic(comic, newId, oldId) {
    return axios.put(`/move/${newId}/${oldId}`, comic);
  },
  getWishListByUserId(id) {
    return axios.get(`/wishlist/${id}`);
  },
  addToWishlist(userID, comicID) {
    return axios.post(`/wishlist/${userID}/${comicID}`);
  },
  addToWishlistFromAPI(id, comic) {
    return axios.post(`/wishlist/${id}`, comic);
  },
  addComicFromWishlist(id, comicID) {
    return axios.post(`/collections/${id}/${comicID}`);
  },
  deleteCollection(id) {
    return axios.delete(`/collections/${id}`);
  },
  deleteComicFromCollection(id, comicID) {
    return axios.delete(`/collections/${id}/${comicID}`);
  },
  deleteComicFromWishList(userID, comicID) {
    return axios.delete(`/wishlist/${userID}/${comicID}`);
  }
}