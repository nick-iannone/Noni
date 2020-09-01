<template>
    <div class="comic_tile">
        <vue-flip active-hover height=500px width=350px>
            <template v-slot:front class="front">
                <h2 class="comic-tile-header">{{comic.title}}</h2> 
                <h3 class="comic-issue-volume"> {{comic.volume}}, Issue {{comic.issue}}</h3>
                <img class="comic-image" v-bind:src="comic.imageName.length > 30 ? comic.imageName : getImageURL(comic.imageName)"/>
            </template>
            <template v-slot:back class="back">
                <p>Publisher: {{comic.publisher}}</p>
                <p>Publication Year: {{comic.yearPublished}}</p>
                <p>Description: {{comic.description}}</p>
            </template>
        </vue-flip>
        <div class='trade-buttons'>
            <button class='initial' :class="{clicked: isClicked}" @click="updateReceiverID(targetCollection.userID); addToTargetList(comic); isClicked = !isClicked" v-if="collection.userID != $store.state.user.id && $store.state.token != ''">{{!isClicked ? 'ADD TO TRADE' : 'ADDED'}}</button>
            <button class='initial' :class="{clicked: isClicked}" @click="addToMyComicList(comic); isClicked = !isClicked" v-if="collection.userID === $store.state.user.id">{{!isClicked ? 'ADD TO TRADE' : 'ADDED'}}</button>
            <button class='initial' @click="addToWishlist($store.state.user.id, comic.comicID)" v-if="collection.userID != $store.state.user.id && $store.state.token != ''">ADD TO WISHLIST</button>
            <div id='dropdown-collections'>
            <button class='initial' v-if="collection.userID === $store.state.user.id"> MANAGE  
                <font-awesome-icon :icon="['fa','caret-down']" class='caret-down'/>
                <div class='dropdown-collect'>
                    <li @click="moveComic(comic, collection.collectionID, targetCollection.collectionID)" class="collection-options" v-for="collection in collections" v-bind:key="collection.collectionID">Move To: {{collection.name}}</li>                   
                    <li @click="deleteFromCollection(collection.collectionID, comic.comicID)" class="collection-options">Delete From Collection</li>
                </div>
            </button>
            </div>
        </div>
        <modal name="modal-error" v-if="showErrorMSG">That comic has already been added to the trade. Please try again!</modal>
    </div>
</template>

<script>
import VueFlip from 'vue-flip'
import CollectionService from '../services/CollectionService.js'

export default {
    components: {
        VueFlip
    },
    name: 'comic-tile',
    props: ['comic', 'collection'],
    data() {
        return {
            collections: [],
            isClicked: false,
            targetCollection: {},
            currentCollection: {},
            errorMSG: false
        }
    },     
    methods: {
        showErrorMSG () {
            this.$modal.show('modal-error');
        },
        hideErrorMSG () {
            this.$modal.hide('modal-error');
        },
        getImageURL(pic) {
            return require('../assets/' + pic);
        },
        deleteFromCollection(collectionID, comicID) {
            CollectionService.deleteComicFromCollection(collectionID, comicID).then(response => {
                if (response.status === 200) {
                    location.reload();
                }
            })
        },
        moveComic(comic, newId, oldId) {
            if (newId != oldId) {
                CollectionService.moveComic(comic, newId, oldId).then(response => {
                    if (response.status === 200) {
                        this.$router.push({name:'my-collections', params: {username: this.$store.state.user.username}});
                    }
                    console.log(response.status);
                })
                
            }
        },
        addToTargetList(comic) {
            if (this.$store.state.trade.tradeReceiverID != this.targetCollection.userID && this.$store.state.trade.toComicList.length > 0) {
                this.$store.state.trade.toComicList.forEach(com => {
                    if (com.comicID === comic.comicID) {
                        this.$swal('Holy Duplicate, Batman!','You have already added this comic to the trade!', 'OK');
                        this.errorMSG=true;
                        this.isClicked = !this.isClicked
                    }
                })  
            }
            if (this.$store.state.trade.tradeReceiverID == this.targetCollection.userID) {
                this.$store.state.trade.toComicList.forEach(com => {
                    if (com.comicID === comic.comicID) {
                        this.$swal('Holy Duplicate, Batman!', 'You have already added this comic to the trade!', 'OK');
                        this.errorMSG=true;
                        this.isClicked = !this.isClicked
                    }
                })  
            }
            if (!this.errorMSG) {
                this.$store.commit('ADD_COMIC_TO_RECIPIENT', comic)
            }    
        },
        addToMyComicList(comic) {
            if (this.$store.state.trade.sendComicList.length > 0) {
                this.$store.state.trade.sendComicList.forEach(com => {
                    if (com.comicID === comic.comicID) {
                        this.$swal('Holy Duplicate, Batman!', 'You have already added this comic to the trade!', 'OK');
                        this.errorMSG=true;
                        this.isClicked = !this.isClicked
                    }
                })
            }
            if (!this.errorMSG) {
                this.$store.commit('ADD_COMIC_TO_SENDER', comic)
            }
        },
        updateReceiverID(id) {
            if (this.$store.state.trade.toComicList.length === 0) {
                this.$store.commit('UPDATE_RECEIVER_ID', id);
            }
            if (this.$store.state.trade.tradeReceiverID != this.targetCollection.userID) {
                this.$swal('You wouldn\'t like me when I\'m angry...', 'You already have an active trade with another user! Please cancel or submit that trade before trading with another user.', 'OK');
                this.errorMSG=true;
                this.isClicked = !this.isClicked;
            }
        },
        addToWishlist(userID, comicID) {
            CollectionService.addToWishlist(userID, comicID).then(response => {
                if (response.status === 201) {
                    this.$swal('Wish Made', 'You have two more wishes... just kidding you have as many as you want!', 'OK');
                }
            })
        }
    },
    created () {
        this.targetCollection = this.collection;
        CollectionService.getCollectionsByUsername(this.$store.state.user.username).then(response => {
            this.collections = response.data;
        });
    }
}
</script>

<style>
#dropdown-collections {
    position: relative;
    display: inline-block;
}
#dropdown-collections:hover .dropdown-collect {
  display: block;
}
#dropdown-collections:hover .dropbtn {
  background-color: #3e8e41;
}
.dropdown-collect {
  display: none;
  position: absolute;
  background-color: rgb(255, 255, 255);
  min-width: 225px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
  margin-top: 9px;
}
.collection-options {
    font-family: 'Miriam Libre';
    font-size: 15px;
    list-style: none;
    padding: 5px;
    color: black;
}
.collection-options:hover {
    color: rgb(5, 5, 110);
    background-color:rgb(197, 197, 197);
}
.caret-down {
    font-size: 15px;
}
.front {
    border-radius: 10px;
    display: block;
    font-size: 80%;
    font-family: 'Miriam Libre';
    text-align: center;
    color: white;
    width: 350px;
    height: 500px;
    background-color: black;
}
 .front img{
     width: 235px;
     height: auto;
 }
 .comic-issue-volume {
     margin: 25px;
 }
.back {
    border-radius: 10px;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    background-color: rgba(16, 9, 87, 0.5);
    color: white;
    width: 100%;
    height: 100%;
    font-size: 17px;
    font-family: 'Miriam Libre';
}
.back p {
    margin-left: 25px;
    margin-right: 25px;
}
.trade-buttons {
    display: flex;
    flex-direction: row;
    justify-content: center;
}
.initial {
    margin: 10px;
    padding-left: 13px;
    padding-right: 13px;
    padding-top: 5px;
    padding-bottom: 5px;
    border-radius: 5px;
    border-color:rgba(209, 209, 209, 0.75);
    background-color: rgba(12, 34, 136, 0.349);
    font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
    color: rgb(255, 255, 255);
    font-weight: 500;
}
.initial:hover {
    border-color:rgba(1, 82, 37, 0.75);
    background-color:rgba(7, 163, 67, 0.8);
    color: white;
    font-weight: bold;
    cursor: url('../assets/gauntlet.png'), pointer;
}
.clicked {
    border-color:rgba(1, 82, 37, 0.75);
    background-color:rgba(7, 163, 67, 1);
    color: white;
    font-weight: bold;
    margin: 10px;
    padding-left: 10px;
    padding-right: 10px;
    padding-top: 3px;
    padding-bottom: 3px;
    border-radius: 5px;
}
</style>