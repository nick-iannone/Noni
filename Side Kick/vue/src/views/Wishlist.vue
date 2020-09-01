<template>
  <div id="wishlist-page">
      <h2 class="wishlist-header">{{wishList.length > 0 ? 'WISH LIST' : 'WISH BIG OR GO HOME!'}}</h2>
      <div class="offer-tile" v-if="wishList.length === 0">
        <p class="review-call" v-if="wishList.length === 0">Go out and <a id="trade-link" href="/collections">browse collections</a> or use the <a id="trade-link" href="/search">search</a> feature to track down the comics you dream about owning!</p>
      </div>
      <div class="results-container">
        <wishlist-display v-for="comic in wishList" :key="comic.comicID" :comic="comic" :collections="collections"/>
      </div>
  </div>    
</template>

<script>
import CollectionService from '../services/CollectionService.js'
import WishlistDisplay from '@/components/WishlistDisplay.vue'

export default {
    components: {
        WishlistDisplay
    },
    data() {
        return {
            wishList: {},
            collections: {}
        }
    },
    created() {
        CollectionService.getWishListByUserId(this.$route.params.id).then(response => {
            this.wishList = response.data;
        });
        CollectionService.getCollectionsByUsername(this.$store.state.user.username).then(response => {
            this.collections = response.data;
        });
    }
}
</script>

<style>
#wishlist-page {
    background-image: url('../assets/iron-grip.png'), linear-gradient(90deg, rgba(2,31,73,1) 2%, rgba(13,133,182,1) 37%, rgb(2, 119, 113) 59%, rgb(1, 71, 49) 82%);
    margin: 0px 0px 40px 0px;
    padding: 50px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
.wishlist-header {
  font-size: 5vw;
  font-family: 'Bangers';
  color: rgb(255, 255, 255);
  margin: 40px;
  text-align: center;
  text-shadow: 4px 4px black;
}
</style>