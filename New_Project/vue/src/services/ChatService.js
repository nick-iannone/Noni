import axios from 'axios'

export default {
    
    getMyChatLog(username) {
        return axios.get(`/chat/${username}`);
    },

    getAllUsernames() {
        return axios.get('/chat/users');
    }
}