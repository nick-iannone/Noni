<template>
  <div class="collection_details">
      <h1>{{this.collection.name}}</h1>
      <div class='settings'>
          <modal-form v-bind:collection = "collection"/>
      </div>
      <h2 class="username">{{this.collection.username}}'s {{this.collection.visibility}} Collection</h2>
      <div class="grid-container">
        <comic-tile class='grid-item' v-for="comic in pageOfItems" :key="comic.comicID" :comic="comic" :collection="collection"/>
      </div>
      <div class="page-tracker">
            <jw-pagination :items="collection.comicList" @changePage="onChangePage" :pageSize='10'></jw-pagination>
      </div>
      <add-comic v-if="$store.state.token != '' && $store.state.user.id === this.collection.userID"/>
  </div>
</template>

<script>
import ComicTile from '@/components/ComicTile.vue'
import AddComic from '@/components/AddComic.vue'
import CollectionService from '../services/CollectionService.js'
import ModalForm from '@/components/ModalForm.vue'


export default {
    data() {
        return {
            collection: {},
            pageOfItems: []
        };
    },
    components: {
        ComicTile,
        AddComic,
        ModalForm
    },
    methods: {
        onChangePage(pageOfItems) {
            // update page of items
            this.pageOfItems = pageOfItems;
        }
    },
    created() {
        CollectionService.viewCollection(this.$route.params.id).then(response => {
            this.collection = response.data;
        });
    }
}
</script>

<style>
.grid-container {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    justify-content: center;
    row-gap: 10px;
}
.grid-item {
    margin-left: 8px;
    margin-right: 8px;;
}
.collection_details h1 {
    font-family: 'Bangers';
    font-size: 4vw;
}
.settings {
    text-align: center;
}
.username {
    color: white;
    font-family: 'Miriam Libre';
    font-size: 30px;
    text-align: center;
    text-shadow: 2px 2px rgba(0, 0, 0, 0.8);
}
.pagination {
    justify-content: center;
    flex-wrap: wrap;
}
.page-tracker {
    display: flex;
    flex-direction: row;
    justify-content: center;
    background-color: rgba(10, 18, 87, 0.6);
    width: 700px;
    margin-left: auto;
    margin-right: auto;
    margin-top: 50px;
    margin-bottom: 50px;
    border-radius:15px;
    box-shadow: 4px 4px rgba(0,0,0,0.5);
    text-shadow: 2px 2px rgba(0,0,0,0.5);
}
@media only screen and (max-width: 1400px) {
  
  
}
</style>