<template>
  <div id='search'>
      <comic-search/>
      <p id="attribution">*This search function uses data from <a id='vine-link' target='blank' href="https://comicvine.gamespot.com/">Comic Vine</a></p> 
  </div>    
</template>

<script>
import ComicSearch from '@/components/ComicSearch.vue'

export default {
    components: {
        ComicSearch
    }
}
</script>

<style>
#search {
    background-image: url('../assets/iron-grip.png'), linear-gradient(90deg, rgba(2,31,73,1) 2%, rgba(13,133,182,1) 37%, rgb(2, 119, 113) 59%, rgb(1, 71, 49) 82%);
    padding-top: 20px;
    text-align: center;
    font-family: 'Miriam Libre';
    font-size: 15px;
    color: white;
}
#vine-link {
    font-family: 'Miriam Libre';
    font-size: 15px;
    padding-bottom: 20px;
}
#vine-link:hover {
    color: limegreen;
}
#attribution {
    padding-bottom: 20px;
    font-style: italic;
}
</style>