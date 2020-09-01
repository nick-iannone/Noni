<template>
  <div>
      <div class="trade-container">
          <h2>Trade Offer from {{user.username}}</h2>
          <div class="trade-list"> 
          <ul class="ul-table">
              <h3>Comics You Give Up</h3>
              <li class="list-trade" v-for="comic in trade.toComicList" v-bind:key="comic.comicID">{{comic.volume}}, {{comic.issue}}, {{comic.title}}</li>
          </ul>
          <ul class="ul-table">
              <h3>Comics You Receive</h3>
              <li class="list-trade" v-for="comic in trade.sendComicList" v-bind:key="comic.comicID">{{comic.volume}}, {{comic.issue}}, {{comic.title}}</li>
          </ul>
          </div>
      </div>
      <div class='response-buttons'>
        <button @click="acceptTrade(trade)" class='accept-btn'>ACCEPT</button>
        <button @click="rejectTrade(trade)" class="reject-btn">REJECT</button>
      </div>
  </div>
</template>

<script>
import TradeService from '../services/TradeService.js'

export default {
    name: 'trade-review',
    props: ['trade'],
    data() {
        return {
            user: {}
        }
    },
    created() {
        TradeService.getUserById(this.trade.tradeSenderID).then(response => {
            this.user = response.data;
        }).catch(error => {
                if (error.response) {
                     this.errorMsg = "Error getting user. Response received was: " + error.response.statusText + "!";
                 }
                 else if (error.request) {
                     this.errorMsg = "Server unavailable";
                 }
                 else {
                     this.errorMsg = "General Error";
                 }
                 console.log(error.status);
            });
    },
    methods: {
        acceptTrade(trade) {
            trade.status = 2;
            TradeService.updateTrade(trade).then(response => {
                if (response.status === 200) {
                    this.$router.push({name:'my-collections', params: {username: this.$store.state.user.username}});
                }
            }).catch(error => {
                if (error.response) {
                     this.errorMsg = "Error accepting trade. Response received was: " + error.response.statusText + "!";
                 }
                 else if (error.request) {
                     this.errorMsg = "Server unavailable";
                 }
                 else {
                     this.errorMsg = "General Error";
                 }
                 console.log(error.status);
            });
        },
        rejectTrade(trade) {
            trade.status = 3;
            TradeService.updateTrade(trade).then(response => {
                if (response.status === 200) {
                    this.$router.push('/collections');
                }
            }).catch(error => {
                if (error.response) {
                     this.errorMsg = "Error rejecting trade. Response received was: " + error.response.statusText + "!";
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
.trade-container {
    display:flex;
    flex-direction: column;
    justify-content: space-around;
}
.trade-list {
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    margin-left: 50px;
    margin-right: 50px;
}
#review-page h2 {
    color:white;
    font-family: 'Bangers';
    font-size: 70px;
    text-align: center;
    margin-left: auto;
    margin-right: auto;
    padding: 20px;
    border-radius: 20px;
    text-shadow: -2px -2px 0 #000, 4px -2px 0 #000, -2px 4px 0 #000, 2px 2px 0 #000;
}
#review-page h3 {
    color:white;
    font-family: 'Miriam Libre';
    font-size: 30px;
    text-shadow: 1px 1px black;
}
.list-trade {
  border: 1px solid black;
  padding:10px;
  background:rgb(255, 255, 255); 
  width: 500px;
  min-width: 500px;
  font-family: 'Miriam Libre';
  color: black;
  box-shadow: 5px 5px rgba(0, 0, 0, 0.5);
}
.ul-table {
    list-style:none;
}
.response-buttons {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-content: center;
    align-items: center;
}
.accept-btn {
  font-family: 'Bangers';
  color: #fff;
  background-color: #00087e;
  border-radius: 10px;
  font-size: 30px;
  text-shadow: 2px 2px rgba(0,0,0,0.8);
  width: 200px;
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
.accept-btn:hover {
    box-shadow: 10px 10px rgba(0, 0, 0, 0.5);
    transform: scale(1.05); 
    cursor: url('../assets/gauntlet.png'), pointer;
    background-color: rgb(66, 63, 247);
}
.reject-btn {
    font-family: 'Bangers';
    color: #fff;
    padding: 10px 10px;
    background-color: #960000;
    border-radius: 10px;
    font-size: 30px;
    text-shadow: 2px 2px rgba(0,0,0,0.8);
    width: 200px;
    height: 60px;
    margin-bottom: 30px;
    box-shadow: 5px 5px rgba(0, 0, 0, 0.5);
    transition: all .2s ease-in-out;
}
.reject-btn:hover {
    box-shadow: 10px 10px rgba(0, 0, 0, 0.5);
    transform: scale(1.05); 
   cursor: url('../assets/gauntlet.png'), pointer;
    background-color: rgb(255, 0, 0);
}
</style>