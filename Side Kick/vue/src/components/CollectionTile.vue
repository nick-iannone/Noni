<template>
    <div class="collection_tile">
        <router-link tag='a' v-bind:to="{ name: 'details-collection', params: {id: collection.collectionID}}">
            <h2> {{collection.name}} </h2>
            <h3> {{collection.username}}</h3>
            <img :src="evaluateImage()"/>
            <div>There are {{collection.comicList.length}} comics in this collection</div>
        </router-link>
    </div>
</template>

<script>

export default {
    name: 'collection-tile',
    props: ['collection'],
    methods: {
      getImageURL(pic) {
        console.log(pic);
        return require('../assets/' + pic);
      },
      evaluateImage() {
          if (this.collection.comicList.length == 0) {
              const pic = 'pow-stock.png';
              return this.getImageURL(pic);
          }
          if (this.collection.comicList.length > 0 && this.collection.comicList[0].imageName.length > 30) {
              return this.collection.comicList[0].imageName;
          }
          if (this.collection.comicList.length > 0) {
              const pic = this.collection.comicList[0].imageName;
              return this.getImageURL(pic);
          }
      }
    }
}
</script>

<style>
.collection_tile {
    border: 1px solid black;
    border-radius: 10px;
    display: block;
    background-color:black;
    margin: 20px;
    padding: 20px;
    
}
.collection_tile h2 {
    font-family:'Miriam Libre';
    font-size: 25px;
    font-weight: bolder;
}

.collection_tile h3 {
    font-family:'Miriam Libre';
    font-size: 17px;
    font-style: italic;
}
.collection_tile div {
    font-family:'Miriam Libre';
    font-size: 17px;
}
.collection_tile img {
    width: 250px;
    height: auto;
}
</style>