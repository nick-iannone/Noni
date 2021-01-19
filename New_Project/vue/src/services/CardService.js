import axios from 'axios';

export default {

    getAllCards() {
        return axios.get('/cards');
    },

    getAllContracts() {
        return axios.get('/contracts');
    },
}