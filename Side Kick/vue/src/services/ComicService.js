import axios from 'axios';

export default {

  addComic(comic, id) {
      return axios.post(`/collections/${id}`, comic);
  },
  searchForComicsByVolume(search) {
      return axios.get(`/search/${search}`);
  },
  getIssuesByVolume(id) {
      return axios.get(`/search/volumes/${id}`);
  }
}