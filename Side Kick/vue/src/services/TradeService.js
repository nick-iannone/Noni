import axios from 'axios';

export default {

  submitTrade(trade) {
      return axios.post('/trades', trade);
  },
  getPendingTrades(id) {
      return axios.get(`/trades/${id}`);
  },
  updateTrade(trade) {
      return axios.put('/trades/update', trade);
  },
  getUserById(id) {
      return axios.get(`/users/${id}`);
  }
}