import axios from 'axios';

export default {

    getRaceList() {
        return axios.get('/races');
    },
    getClassList() {
        return axios.get('/classes');
    },
    getWeaponList() {
        return axios.get('/weapons');
    },
    saveCharacter(newChar, id) {
        return axios.post(`/characters/${id}`, newChar);
    },
    getSavedCharacters(id) {
        return axios.get(`/characters/${id}`);
    },
    getSpellList(id) {
        return axios.get(`/spells/${id}`);
    }
}