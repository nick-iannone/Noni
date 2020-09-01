<template>
  <div class='search-field'>
      <label for='search'>Search For Comics By Volume Name: </label>
      <input type='text' v-model="search" placeholder="Search for your favorite character or series! >> 'Spider-Man'"/>
      <div class='btn-container'>
        <button type='submit' class='search-btn' @click.prevent="searchComicsByVolume(search)">Search</button>
        <button @click="clearResults" class="reset-btn">Clear</button>
      </div>
      <div class="results-container">
        <comic-result :volume='result' v-for="result in resultList" :key="result.id"/>
      </div>
  </div>  
</template>

<script>
import ComicResult from '@/components/ComicResult.vue'
import ComicService from '../services/ComicService.js'

export default {
    name: 'comic-search',
    components: {
        ComicResult
    },
    data() {
        return {
            search: '',
            resultList: {}
        }
    },
    methods: {
        clearResults() {
            this.$store.commit('RESET_SEARCH');
            location.reload();
        },
        searchComicsByVolume(search) {
            this.$store.commit('UPDATE_SEARCH_TERM', search);
            ComicService.searchForComicsByVolume(search).then(response => {
                this.resultList = response.data;
            }).catch(error => {
                if (error.response) {
                     this.errorMsg = "Error searching. Response received was: " + error.response.statusText + "!";
                 }
                 else if (error.request) {
                     this.errorMsg = "Server unavailable";
                 }
                 else {
                     this.errorMsg = "General Error";
                 }
                 console.log(error.status);
            });
        }
    },
    created () {
        const word = this.$store.state.searchTerm;
        ComicService.searchForComicsByVolume(word).then(response => {
            this.resultList = response.data;
        }).catch(error => {
            if (error.response) {
                this.errorMsg = "Error searching. Response received was: " + error.response.statusText + "!";
            }
            else if (error.request) {
                this.errorMsg = "Server unavailable";
            }
            else {
                this.errorMsg = "General Error";
            }
            console.log(error.status);
        });
    }
}
</script>

<style>
.btn-container {
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    margin-top: 20px;
}
.search-btn {
  font-family: 'Bangers';
  color: #fff;
  border-color:rgba(90, 90, 90, 0.75);
  background-color: rgba(45, 4, 61, 0.5);
  border-radius: 10px;
  font-size: 30px;
  text-shadow: 2px 2px rgba(0,0,0,0.8);
  width: 200px;
  height: 60px;
  box-shadow: 5px 5px rgba(0, 0, 0, 0.5);
  transition: all .2s ease-in-out;
  display: flex;
  flex-direction: column;
  margin-left: auto;
  margin-right: auto;
  text-align: center;
  align-items: center;
  justify-content: center;
  margin-bottom: 40px;
  margin-right: 5px;
}
.search-btn:hover {
    box-shadow: 10px 10px rgba(0, 0, 0, 0.5);
    transform: scale(1.05); 
    cursor: url('../assets/gauntlet.png'), pointer;
    background-color: rgb(0, 175, 0);
}
.reset-btn {
    font-family: 'Bangers';
    color: #fff;
    padding: 10px 10px;
    border-color:rgba(90, 90, 90, 0.75);
    background-color: rgba(212, 32, 32, 0.6);
    border-radius: 10px;
    font-size: 30px;
    text-shadow: 2px 2px rgba(0,0,0,0.8);
    width: 200px;
    height: 60px;
    margin-bottom: 30px;
    box-shadow: 5px 5px rgba(0, 0, 0, 0.5);
    transition: all .2s ease-in-out;
    display: flex;
    flex-direction: column;
    margin-left: auto;
    margin-right: auto;
    text-align: center;
    align-items: center;
    margin-bottom: 40px;
    margin-left:5px;
}
.reset-btn:hover {
    box-shadow: 10px 10px rgba(0, 0, 0, 0.5);
    transform: scale(1.05); 
    cursor: url('../assets/gauntlet.png'), pointer;
    background-color: red;
}
.results-container {
    display: flex;
    flex-direction: row;
    justify-content: center;
    flex-wrap: wrap;
}
.search-field {
    margin-top: 20px;
    margin-bottom: 20px;
    font-family: 'Miriam Libre';
    font-size: 25px;
    color:white;
    text-shadow: 1px 1px black;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-content: center;
    align-items: center;
}
.search-field label {
    margin-top: 20px;
}
.search-field input {
    width: 518px;
    height: 40px;
    border-radius: 5px;
    font-size: 17px;
}
</style>