<template>
    <div class="page">
        <div class="collections_container">
            <collection-tile class="collection_tile" v-bind:collection="collection" v-bind:key="collection.collectionID" v-for="collection in this.collections"/>
        </div>
        <create-button/>   
    </div>
</template>

<script>
import CollectionTile from '@/components/CollectionTile.vue';
import CollectionService from '../services/CollectionService.js';
import CreateButton from '@/components/CreateButton.vue';

export default {
    components: {
        CollectionTile,
        CreateButton
    },
    data () {
        return {
            collections: []
        }
    },
    created() {
        CollectionService.getCollections().then(response => {
            this.collections = response.data;
        });
    }
}
</script>

<style>
.page {
    background-image:url('../assets/newnew.jpg');
}
.collections_container {
    display: flex;
    flex-direction: row;
    justify-content: center;
    flex-wrap: wrap;
}
.collection_tile {
    box-shadow: 5px 5px rgba(0, 0, 0, 0.5);
    text-align: center;
    transition: all .2s ease-in-out;
    padding: 30px;
}
.collection_tile:hover {
    box-shadow: 10px 10px rgba(0, 0, 0, 0.5);
    transform: scale(1.02); 
}
@media only screen and (max-width: 1400px) {
  
}
.page {
    background-image: url('../assets/iron-grip.png'), linear-gradient(90deg, rgba(2,31,73,1) 2%, rgba(13,133,182,1) 37%, rgb(2, 119, 113) 59%, rgb(1, 71, 49) 82%);
}
</style>