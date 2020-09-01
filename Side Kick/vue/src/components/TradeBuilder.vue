<template>
  <div id="page-background">
    <div id="view" class='flex-list'>
        <div class="flex-item">
        <a><router-link id='mycollection-link' tag='a' v-bind:to="{ name: 'my-collections', params: {username: $store.state.user.username}}">CLICK HERE TO ADD MY COMICS</router-link></a>
          <ul class="trade-table">
            <li class="li-header">The comics you don't love anymore go here</li>
            <li v-for="comic in this.$store.state.trade.sendComicList" v-bind:key='comic.comicID' class="list-tracker"> {{comic.volume}}, {{comic.issue}}, {{comic.title}}</li>
          </ul>
        </div>
        <div class="flex-item">
          <a><router-link id='collection-link' tag='a' v-bind:to="{ name: 'collections'}">CLICK HERE TO ADD THEIR COMICS</router-link></a>
          <ul class="trade-table">
            <li class="li-header">The comics you can't live without go here</li>
            <li v-for="comic in this.$store.state.trade.toComicList" v-bind:key='comic.comicID' class="list-tracker"> {{comic.volume}}, {{comic.issue}}, {{comic.title}} </li>
          </ul>
        </div>
      </div>
      <div class='button-container'>
        <button @click="submitTrade" class='submit-offer'>SUBMIT OFFER</button>
        <button @click="cancelTrade" class="trade-cancel">CLEAR TRADE</button>
      </div>
  </div>
</template>

<script>
import TradeService from '../services/TradeService.js'

export default {
  name: 'trade-builder',
  data() {
        return {
            collection: {}
        };
  },
  methods: {
      submitTrade() {
        const newTrade = {
            status: 1,
            tradeSenderID: this.$store.state.user.id,
            tradeReceiverID: this.$store.state.trade.tradeReceiverID,
            sendComicList: this.$store.state.trade.sendComicList,
            toComicList: this.$store.state.trade.toComicList
        };
        if (newTrade.sendComicList.length === 0 && newTrade.toComicList.length === 0) {
            this.$swal('Error', 'Such Empty! Please add comics to the trade before submitting an offer.', 'OK')
        }
        else {TradeService.submitTrade(newTrade).then(response => {
          if (response.status === 201) {
              this.$swal('Success!', 'Your trade offer is on its way!', 'OK');
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
            this.resetTrade();
      }},
      resetTrade() {
        this.$store.commit('RESET_TRADE');
      },
      cancelTrade() {
        this.$store.commit('RESET_TRADE');
        location.reload();
      }
  }
}
</script>


<style>
.comic-title {
    color: white;
}
#page-background {
  background-image: url('../assets/cropped.jpg');
}
#mycollection-link {
  font-family: 'Bangers';
  text-shadow: 2px 2px black;
  font-size: 40px;
}
#mycollection-link:hover {
  color:rgb(234, 166, 248);
}
#collection-link {
  font-family: 'Bangers';
  text-shadow: 2px 2px black;
  font-size: 40px;
}
#collection-link:hover {
  color:rgb(234, 166, 248);
}
.button-container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-content: center;
  align-items: center;
  
}
.trade-cancel {
    font-family: 'Bangers';
    color: #fff;
    padding: 10px 10px;
    background-color: #5f0854;
    border-radius: 10px;
    font-size: 30px;
    text-shadow: 2px 2px rgba(0,0,0,0.8);
    width: 150px;
    height: 60px;
    margin-bottom: 30px;
    box-shadow: 5px 5px rgba(0, 0, 0, 0.5);
    transition: all .2s ease-in-out;
}
.trade-cancel:hover {
    box-shadow: 10px 10px rgba(0, 0, 0, 0.5);
    transform: scale(1.05); 
    cursor: url('../assets/gauntlet.png'), pointer;
    background-color: red;
}
.submit-offer {
  font-family: 'Bangers';
  color: #fff;
  background-color: #fc8626;
  border-radius: 10px;
  font-size: 30px;
  text-shadow: 2px 2px rgba(0,0,0,0.8);
  width: 230px;
  height: 60px;
  box-shadow: 5px 5px rgba(0, 0, 0, 0.5);
  transition: all .2s ease-in-out;
  display: flex;
  flex-direction: column;
  margin-left: auto;
  margin-right: auto;
  text-align: center;
  align-items: center;
  justify-content: center;
  margin-top: 40px;
  margin-bottom: 40px;
}
.submit-offer:hover {
    box-shadow: 10px 10px rgba(0, 0, 0, 0.5);
    transform: scale(1.05); 
    cursor: url('../assets/gauntlet.png'), pointer;
    background-color: rgb(0, 175, 0);
}
#view{
  display:flex;
  justify-content: center;
  flex-direction: row;
}
.clickable:hover{
  cursor: url('../assets/gauntlet.png'), pointer;
  background:#cfc;
}
.flex-item h2 {
  color:white;
  font-family: 'Miriam Libre';
  font-size: 30px;
  text-shadow: 1px 1px black;
}
.flex-item {
  margin: 30px;
}
.trade-table {
  width:100%;
  list-style:none;
  padding:0;
}
.list-tracker{
  border: 1px solid black;
  padding:10px;
  background:white; 
  width: 500px;
  min-width: 500px;
  font-family: 'Miriam Libre';

}
.li-header {
  font-style: italic;
  color: rgb(255, 255, 255);
  font-weight:800;
  border: 1px solid black;
  padding:10px;
  background:rgb(48, 48, 48); 
  width: 500px;
  height: 25px;
  min-width: 500px;
  text-align: center;
  font-family: 'Miriam Libre';
  font-weight: 200;
}
</style>