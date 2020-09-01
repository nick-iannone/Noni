<template>
  <div class='align-block'>
    <div class='wishlist-result'>
      <p>{{comic.volume}}, {{comic.issue}}, {{comic.title}}</p>
      <img class="wishlist-img" :src="comic.imageName.length > 30 ? comic.imageName : getImageURL(comic.imageName)">
    </div>
    <div v-if="$store.state.token != ''" id='dropdown-collections'>
          <button class='btn-style'> ADD  
                <font-awesome-icon :icon="['fa','caret-down']" class='caret-down'/>
                <div class='dropdown-collect'>
                    <li @click="addToCollection(collection.collectionID, comic.comicID)" class="collection-options" v-for="collection in collections" v-bind:key="collection.collectionID">Add To: {{collection.name}}</li>
                    <li @click="removeComic($store.state.user.id, comic.comicID)" class="collection-options">Remove from Wish List</li>
                </div>
            </button>
        </div>
  </div>
</template>

<script>
import CollectionService from '../services/CollectionService.js'

export default {
    name: 'wishlist-display',
    props: ['comic', 'collections'],
    methods: {
        getImageURL(pic) {
            return require('../assets/' + pic);
        },
        removeComic(userID, comicID) {
            CollectionService.deleteComicFromWishList(userID, comicID).then(response => {
                if (response.status === 200) {
                    location.reload();
                }
            })
        },
        addToCollection(id, comicID) {
        CollectionService.addComicFromWishlist(id, comicID).then(response => {
          if(response.status === 201) {
                    //successful
                    this.$swal('Success!', 'Your comic has been added!', 'OK');
                    this.$router.push({name:'details-collection', params: {id: id}});
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
    }
}
</script>

<style>
.wishlist-result {
    text-align: center;
    background-color:white;
    color: black;
    font-family: 'Miriam Libre';
    font-size:0.7vw;
    display: flex;
    padding: 0px 2px 0px 2px;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    margin: 0px 10px 5px 10px;
    width: 300px;
    height: 500px;
    background-color: rgba(0,0,0,0.7);
    color:white;
    justify-content: center;
    border-radius: 10px;
    box-shadow: 4px 4px rgba(255,255,255,0.3);
    position: relative;
}
.wishlist-img {
    max-width: 250px;
    max-height: 100%;
    position: relative;
    overflow: hidden;
}
.wishlist-result p {
    margin-left: 5px;
    margin-right: 5px;
}
</style>