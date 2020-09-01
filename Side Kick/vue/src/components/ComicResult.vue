<template>
  <div @click="sendUserToResults()" class='result'>
      <p>{{volume.name}}, {{volume.id}}</p>
      <img :src="volume.image.small_url">
  </div>
</template>

<script>

export default {
    name:'comic-result',
    props: ['volume'],
    methods: {
        sendUserToResults() {
            this.$store.commit('SET_ACTIVE_VOLUME', this.volume.name);
            this.$router.push({name: 'results', params: {id: this.volume.id}});
        }
    }
}
</script>

<style>
.result {
    text-align: center;
    background-color:white;
    color: black;
    font-family: 'Miriam Libre';
    font-size:15px;
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    margin: 5px 10px 5px 10px;
    width: 300px;
    height: 500px;
    background-color: rgba(0,0,0,0.5);
    color:white;
    justify-content: center;
    border-radius: 10px;
    box-shadow: 4px 4px rgba(255,255,255,0.3); 
}
.result:hover {
    cursor: url('../assets/gauntlet.png'), pointer;
}
.result p {
    margin:5px;
    font-size: 0.75vb;
}
.result img {
    max-width: 90%;
    height: auto;
    padding: 0px 5px 0px 5px;
    position: relative;
    overflow: hidden;
}
</style>