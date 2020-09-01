<template>
  <div id="form-container">
      <div v-if="showForm === false" class="add-comic">
        <a href='#' v-on:click.prevent="showForm = true" >Add Comic</a>
      </div>  
      <form class="comic-form" v-if="showForm === true" v-on:submit.prevent="addComic">
        <div class="form-group">
            <label class="add-label" for='title'>Title: </label>
            <input id='title' type='text' v-model="newComic.title" class="form-control"/>
        </div>
        <div class="form-group">
            <label class="add-label" for='issue'>Issue: </label>
            <input id='issue' type='text' v-model="newComic.issue" class="form-control"/>
        </div>
        <div class="form-group">
            <label class="add-label" for='volume'>Volume: </label>
            <input id='volume' type='text' v-model="newComic.volume" class="form-control"/>
        </div>
        <div class="form-group">
            <label class="add-label" for='publisher'>Publisher: </label>
            <input id='publisher' type='text' v-model="newComic.publisher" class="form-control"/>
        </div>
        <div class="form-group">
            <label class="add-label" for='yearpublished'>Year Published: </label>
            <input id='yearpublished' type='text' v-model="newComic.yearPublished" class="form-control"/>
        </div>
        
        <div class="actions">
            <button class="btn btn-submit" type='submit'>Submit</button>
            <button class="btn btn-cancel" v-on:click.prevent="resetForm" type="cancel">Cancel</button>
        </div>
      </form>
  </div>
</template>

<script>
import ComicService from '../services/ComicService.js';

export default {
    name: 'add-comic',
    data() {
        return {
            showForm: false,
            newComic: {
                issue: '',
                volume: '',
                title: '',
                category: '',
                publisher: '',
                yearPublished: '',
                imageName: "pow-stock.png"
            }
        }
    },
    methods: {
        addComic() {
            const newComic = {
                issue: this.newComic.issue,
                volume: this.newComic.volume,
                title: this.newComic.title,
                category: this.newComic.category,
                publisher: this.newComic.publisher,
                yearPublished: this.newComic.yearPublished,
                imageName: this.newComic.imageName
            };
            ComicService.addComic(newComic, this.$route.params.id).then(response => {
                if(response.status === 201) {
                    //successful
                    location.reload();
                }
            }).catch(error => {
                if (error.response) {
                     this.errorMsg = "Error adding a new comic. Response received was: " + error.response.statusText + "!";
                 }
                 else if (error.request) {
                     this.errorMsg = "Server unavailable";
                 }
                 else {
                     this.errorMsg = "General Error";
                 }
                 console.log(error.status);
            });
            this.resetForm();
        },
        resetForm() {
            this.showForm = false;
        }
    }
}
</script>

<style>
#form-container {
    display: flex;
    flex-direction: column;
    margin-left: auto;
    margin-right: auto;
    width: 500px;
}
.add-label {
    margin: 0px;
}
.add-comic {
    text-align: center;
    margin-left: auto;
    margin-right: auto;
    margin-bottom: 10px;
    margin-top: 20px;
    width: 25%;
    height: 90%;
    font-size: 40px;
    display: flex;
    flex-direction: row;
    justify-content: center;
    padding: 30px;
    border: 1px solid black;
    background-color: black;
    box-shadow: 5px 5px rgba(0, 0, 0, 0.5);
    text-align: center;
    transition: all .2s ease-in-out;
    padding: 30px;
    border-radius: 15px;
}

.add-comic:hover {
    box-shadow: 10px 10px rgba(0, 0, 0, 0.5);
    transform: scale(1.05); 
}

.comic-form {
    margin-top: 30px;
}

.form-group {
  /* margin-bottom: 10px;
  margin-top: 10px; */
  font-size: 25px;
  font-family: 'Miriam Libre';
  text-shadow: 2px 1px black;
  color: white;
}

.form-control {
  display: flex;
  align-items: flex-start;
  width: 100%;
  height: 40px;
  padding: 0.375rem 0.75rem;
  font-size: 1rem;
  font-weight: 400;
  line-height: 2;
  color: #495057;
  border: 1px solid #ced4da;
  border-radius: 0.25rem;
}

.actions {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  padding-top: 20px;
}

.btn-submit {
  font-family: 'Bangers';
  color: #fff;
  padding: 10px 24px;
  background-color: #38b412;
  box-shadow: 5px 5px rgba(0, 0, 0, 0.5);
  transition: all .2s ease-in-out;
  border-radius: 10px;
  font-size: 28px;
  text-shadow: 2px 2px rgba(0,0,0,0.8);
}

.btn-cancel {
  font-family: 'Bangers';
  color: white;  
  padding: 10px 24px;
  background-color: darkred;
  box-shadow: 5px 5px rgba(0, 0, 0, 0.5);
  transition: all .2s ease-in-out;
  border-radius: 10px;
  font-size: 28px;
  text-shadow: 2px 2px rgba(0,0,0,0.8);
}

.btn-submit:hover {
  
  color: #fff;
  padding: 10px 24px;
  background-color: #65f307;
  box-shadow: 10px 10px rgba(0, 0, 0, 0.5);
  transform: scale(1.05); 
  cursor: url('../assets/gauntlet.png'), pointer;  
}

.btn-cancel:hover {
  padding: 10px 24px;
  color: white;
  background-color: red;
  box-shadow: 10px 10px rgba(0, 0, 0, 0.5);
  transform: scale(1.05); 
  cursor: url('../assets/gauntlet.png'), pointer;
}

</style>