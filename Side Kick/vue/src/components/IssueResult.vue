<template>
  <div class='align-block'>
    <div class='result'>
      <p>{{issue.name === null ? 'Untitled' : issue.name}}, {{issue.issue_number}}, {{issue.cover_date}}</p>
      <img :src="issue.image.small_url"/>

    </div>
        <div id='dropdown-collections' v-if="$store.state.token != ''">
          <button class='btn-style'> ADD  
                <font-awesome-icon :icon="['fa','caret-down']" class='caret-down'/>
                <div class='dropdown-collect'>
                    <li @click="addToCollection(comic, collection.collectionID)" class="collection-options" v-for="collection in collections" v-bind:key="collection.collectionID">Add To: {{collection.name}}</li>
                    <li @click="addToWishlist($store.state.user.id, comic)" class="collection-options">Add To: Wish List</li>
                </div>
            </button>
        </div>
  </div>    
</template>

<script>
import ComicService from '../services/ComicService.js'
import CollectionService from '../services/CollectionService.js'

export default {
    name: 'issue-result',
    props: ['issue', 'collections'],
    data() {
      return {
        comic: {
          title: '',
          volume: '',
          issue: '',
          imageName: '',
          yearPublished: ''
        }
      }
    },
    methods: {
      addToWishlist(id, comic) {
        CollectionService.addToWishlistFromAPI(id, comic).then(response => {
          if (response.status === 201) {
            this.$swal('Wish Made', 'You have two more wishes... just kidding you have as many as you want!', 'OK');
          }
        });
      },
      addToCollection(comic, id) {
        ComicService.addComic(comic, id).then(response => {
          if(response.status === 201) {
                    //successful
                    this.$swal('Success!', 'Your comic has been added!', 'OK');
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
      }
    },
    created () {
      this.comic.title = this.issue.name;
      if (this.comic.title === null) {
        this.comic.title = 'Untitled'
      }
      this.comic.volume = this.$store.state.activeVolume;
      this.comic.issue = this.issue.issue_number;
      this.comic.imageName = this.issue.image.small_url;
      
      if (this.issue.cover_date === null) {
        this.comic.yearPublished = 'Unlisted'
      }
      else {this.comic.yearPublished = this.issue.cover_date.substring(0,4)}
    }
}
</script>

<style>
.align-block {
  justify-content: center;
  align-items: center;
  display: flex;
  flex-direction: column;
}
.btn-style {
    margin: 10px;
    padding-left: 13px;
    padding-right: 13px;
    padding-top: 5px;
    padding-bottom: 5px;
    border-radius: 5px;
    border-color:rgba(90, 90, 90, 0.75);
    background-color: rgba(45, 4, 61, 0.5);
    font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
    color: white;
    font-weight: 500;
}
.btn-style:hover {
    border-color:rgba(1, 82, 37, 0.75);
    background-color:rgba(7, 163, 67, 0.8);
    color: white;
    font-weight: bold;
    cursor: url('../assets/gauntlet.png'), pointer;
}
</style>