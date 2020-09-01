<template>
    <div id="page">
        <div class="call">
            <h2 class="create-header">YOUR NEW COLLECTION STARTS HERE !</h2>
            <p class="collection-call">Once created, you can add new comics as you get them, display details about each comic, and even trade with your friends!</p>
        </div>  
        <div id="form-container">  
            <form @submit.prevent="addNewCollection">
                <div class="form-group">
                    <label for='name'>Collection Name: </label>
                    <input id='name' type='text' v-model="collection.name" class="form-control"/>
                </div>
                <label class="form-group">Visibility: </label>
                <select name='visibility' id='visibility' class="select-css" v-model="selected" >
                    <option disabled value="">Please Select One</option>
                    <option>Public</option>
                    <option>Private</option>
                </select>
                <div class="actions">
                    <button class="form-submit" type='submit'>Create</button>
                    <button class="form-cancel" @click="reset">Cancel</button>
                </div>    
            </form>
        </div>
    </div>  
</template>

<script>
import CollectionService from '../services/CollectionService';

export default {
    name: "collection-create",
    data() {
        return{
            collection: {
                collectionID: '',
                name: '',
                username: '',
                userID: '',
                visibility: '',
                comicList: []
            },
            selected:''
        }
    },
    methods: {
        addNewCollection() {
            const newCollection = {
                collectionID: this.collection.collectionID,
                name: this.collection.name,
                username: this.$store.state.user.username,
                userID: this.$store.state.user.id,
                visibility: this.selected,
                comicList: this.collection.comicList
            };
            
            CollectionService.addCollection(newCollection).then(response => {
                if (response.status === 201) {
                    this.$router.push({name:'my-collections', params: {username: this.$store.state.user.username}});
                }
            }).catch(error => {
                if (error.response) {
                     this.errorMsg = "Error adding a new collection. Response received was: " + error.response.statusText + "!";
                 }
                 else if (error.request) {
                     this.errorMsg = "Server unavailable";
                 }
                 else {
                     this.errorMsg = "General Error";
                 }
                 console.log(error.status);
            }); 
        },
        reset() {
            this.$router.push('/collections');
        }
    }
}
</script>

<style>
#page {
     background-image: url('../assets/cropped.jpg');
}
.call {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: flex-start;
    width: 800px;
    margin-left: auto;
    margin-right: auto;
    border: 2px solid black;
    margin-top: 30px;
    border-radius: 15px;
    background-image: linear-gradient(to bottom right, rgb(95, 178, 247), rgba(216, 206, 252, 1));
    box-shadow: 4px 4px rgba(0, 0, 0, 0.8);
}
.create-header {
    font-family: 'Bangers';
    font-size: 60px;
    color: white;
    text-shadow: 2px 2px black;
}
.collection-call {
    text-align: center;
    font-family: 'Walter Turncoat';
    font-size: 25px;
    margin-top: -10px;
}
#form-container {
    display: flex;
    flex-direction: column;
    margin-left: auto;
    margin-right: auto;
    width: 500px;
    margin-top: 50px;
    margin-bottom: 50px;
}

.form-control {
    display: flex;
    align-items: flex-start;
    width: 100%;
    height: 30px;
    /* padding: 0.375rem 0.75rem; */
    font-size: 1rem;
    font-weight: 400;
    line-height: 1.5;
    color: #495057;
    border: 1px solid #ced4da;
    border-radius: 0.25rem;
}

#visibility {
    font-family: 'Miriam Libre';
}

.actions {
    display: flex;
    flex-direction: row;
    justify-content: space-evenly;
    margin-bottom: 40px;
}

.form-submit {
    font-family: 'Bangers';
    color: #fff;
    padding: 10px 10px;
    background-color: #38b412;
    border-radius: 10px;
    font-size: 30px;
    text-shadow: 2px 2px rgba(0,0,0,0.8);
    width: 150px;
    height: 60px;
    margin-top: 30px;
    margin-bottom: 40px;
    box-shadow: 5px 5px rgba(0, 0, 0, 0.5);
    transition: all .2s ease-in-out;
}

.form-cancel {
    font-family: 'Bangers';
    color: #fff;
    padding: 10px 10px;
    background-color: #b43212;
    border-radius: 10px;
    font-size: 30px;
    text-shadow: 2px 2px rgba(0,0,0,0.8);
    width: 150px;
    height: 60px;
    margin-top: 30px;
    margin-bottom: 40px;
    box-shadow: 5px 5px rgba(0, 0, 0, 0.5);
    transition: all .2s ease-in-out;
}

.form-submit:hover {
    box-shadow: 10px 10px rgba(0, 0, 0, 0.5);
    transform: scale(1.05); 
    cursor: url('../assets/gauntlet.png'), pointer;
}

.form-cancel:hover {
    box-shadow: 10px 10px rgba(0, 0, 0, 0.5);
    transform: scale(1.05); 
    cursor: url('../assets/gauntlet.png'), pointer;
}

.select-css {
	display: block;
	font-size: 16px;
	font-family: sans-serif;
	font-weight: 700;
	color: #444;
	line-height: 1.3;
	padding: .6em 1.4em .5em .8em;
	width: 100%;
	max-width: 100%;
	box-sizing: border-box;
	margin: 0;
	border: 1px solid #aaa;
	box-shadow: 0 1px 0 1px rgba(0,0,0,.04);
	border-radius: .5em;
	-moz-appearance: none;
	-webkit-appearance: none;
	appearance: none;
	background-color: #fff;
	background-image: url('data:image/svg+xml;charset=US-ASCII,%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20width%3D%22292.4%22%20height%3D%22292.4%22%3E%3Cpath%20fill%3D%22%23007CB2%22%20d%3D%22M287%2069.4a17.6%2017.6%200%200%200-13-5.4H18.4c-5%200-9.3%201.8-12.9%205.4A17.6%2017.6%200%200%200%200%2082.2c0%205%201.8%209.3%205.4%2012.9l128%20127.9c3.6%203.6%207.8%205.4%2012.8%205.4s9.2-1.8%2012.8-5.4L287%2095c3.5-3.5%205.4-7.8%205.4-12.8%200-5-1.9-9.2-5.5-12.8z%22%2F%3E%3C%2Fsvg%3E'),
	linear-gradient(to bottom, #ffffff 0%,#e5e5e5 100%);
	background-repeat: no-repeat, repeat;
	background-position: right .7em top 50%, 0 0;
	background-size: .65em auto, 100%;
}
.select-css::-ms-expand {
	display: none;
}
.select-css:hover {
	border-color: #888;
}
.select-css:focus {
	border-color: #aaa;
	box-shadow: 0 0 1px 3px rgba(59, 153, 252, .7);
	box-shadow: 0 0 0 3px -moz-mac-focusring;
	color: #222;
	outline: none;
}
.select-css option {
	font-weight:normal;
}
</style>