<template>
  <div class="home">
    <h1><span>SIDE-</span><span>KICK</span></h1>
    <p class='phrase'>The Comic Collector's right-hand man!</p>
    <img class='logo' src='../assets/netclipart.com-thief-clipart-210203.png'/>
    <div class="call-container">
      <h3 class="call-message">Your partner for managing your comic book collections
        <img @click="sendToRegister" id="bolt" src="../assets/yellowBolt.png"/>
      </h3>
    </div>
    <div class="info-container" v-if="$mq === 'desktop'">
      <div class='bubs'>
        <img v-if="$mq === 'desktop'" class='pic' src='../assets/cap4.png'/>
        <img v-if="$mq === 'desktop'" id="trade-bub" class='pic' src='../assets/bub1.png'/>
        <img v-if="$mq === 'desktop'" class='pic' src='../assets/cap5.png'/>
      </div>
      <div class="info-tile">
        <h4 class="info-text">Our collection manager allows you to add as many new collections as you want. Set the visibility to private to hide your nefarious comic plans from prying eyes, or public to proudly display your favorite issues to the world. The choice is yours!</h4>
      </div>
      <div class="info-tile">
        <h4 class="info-text">Did you spot a comic in someone else's collection that you absolutely must have? Have an extra copy of a comic to unload? Add them to a trade! You can submit trade proposals to any user with a public collection. It will be up to them to approve or deny the offer!</h4>
      </div>
      <div class="info-tile">
        <h4 class="info-text">Side-Kick allows you to search up comics to add to your collection, or browse for issues to add to your wishlist! Our search is powered by the wonderful resource, <a id='comic-link' target='blank' href="https://comicvine.gamespot.com/">Comic Vine! </a> Let's get out there and find you some comics!</h4>
      </div>
    </div>
  </div>
</template>

<script>
import TradeService from '../services/TradeService';
export default {
  name: "home",
  methods: {
    sendToRegister() {
      this.$router.push({name: 'register'});
    }
  },
  created() {
      if (this.$store.state.token != '') {
        TradeService.getPendingTrades(this.$store.state.user.id).then(response => {
            if (response.data.length > 0 && response.status === 200) {
                this.$swal("My trade senses are tingling...", "You have a pending trade offer to review.", 'OK');
            }
        })
      }    
  }
};
</script>

<style>
#bolt {
  transition: all .2s ease-in-out;
  width: 30%;
  height: auto;

}
#bolt:hover {
  transform: scale(1.1);
  cursor: url('../assets/gauntlet.png'), pointer; 
}
#comic-link {
  font-family: 'Walter Turncoat';
  font-size: 25px;
  color: navy;
}
#comic-link:hover {
  color: red;
}
.bubs {
 display: flex;
 width: 100%;
 flex-direction: row;
 justify-content: space-around;
}
.info-text {
  font-family: 'Walter Turncoat';
  font-size: 25px;
  padding: 10px 20px 0px 20px;
}
.info-tile {
  background-image: linear-gradient(to bottom, rgba(255, 254, 198, 0.85), rgba(250, 166, 151, 0.85));
  border: 2px solid black;
  margin: -51px 20px 20px 20px;
  width: 30%;
  border-radius: 25px;
  box-shadow: 4px 4px black;
}
.pic {
  z-index: 2;
}
.info-container img {
  width:19%;
  height: auto;
}
.info-container {
  display: flex;
  flex-direction: row;
  justify-content: center;
  width: 100%;
  flex-wrap: wrap;
  margin-top: 30px;
  margin-bottom: 35px;
  
}
.home {
  border: 1px solid black;
  display: flex;
  flex-direction: column;
  background-image: url('../assets/newnew.jpg');
  background-repeat: no-repeat;
  width: 100%;
  height: auto;
}

.home p {
  font-family: 'Bangers';
  font-size: 4vw;
  text-align: center;
  text-shadow:2px 2px #fc680599;
}
.center-button {
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin-right: 45px;
  margin-top: 10px;
}
.get-started {
  font-family: 'Bangers';
  color: #fff;
  background-color: #00b637;
  border-radius: 10px;
  border-color: rgb(255, 255, 82);
  font-size: 40px;
  text-shadow: 2px 2px rgba(0,0,0,0.8);
  width: 230px;
  height: 90px;
  box-shadow: 5px 5px rgba(0, 0, 0, 0.5);
  transition: all .2s ease-in-out;
  display: flex;
  flex-direction: column;
  margin-left: auto;
  margin-right: auto;
  text-align: center;
  align-items: center;
  justify-content: center;
  margin-top: 25px;
  margin-bottom: 25px;
}
.get-started:hover {
    box-shadow: 10px 10px rgba(0, 0, 0, 0.5);
    transform: scale(1.05); 
    cursor: url('../assets/gauntlet.png'), pointer;
    background-color: rgb(0, 228, 0);
}
.logo {
  margin-left: auto;
  margin-right: auto;
  margin-bottom: 70px;
  margin-top: 20px;
  width: 30vw;
  height: auto;
}
.call-message {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-left: auto;
  margin-right: auto;
  margin-bottom: 150px;
  margin-top: 40px;
  width: 100%;
  height: 10vh;
  font-family: 'Bangers';
  text-align: center;
  font-size: 3vw;
  background-image: linear-gradient(to bottom, rgba(0, 0, 0, 0.8), rgba(0, 0, 0, 0));
  text-shadow: 2px 2px rgba(0, 0, 0, 1);
  color: #e4f317;
  padding: 20px;
}
h1 {
  font-family: 'Bangers';
  text-align: center;
  margin-top: 50px;
  margin-bottom: 0px;
  font-size: 10vw;
  padding: 0;
  color: white;
  text-shadow: 0 0.1em 20px rgba(0, 0, 0, 1), 0.05em -0.03em 0 rgba(0, 0, 0, 1),
    0.05em 0.005em 0 rgba(0, 0, 0, 1), 0em 0.08em 0 rgba(0, 0, 0, 1),
    0.05em 0.08em 0 rgba(0, 0, 0, 1), 0px -0.03em 0 rgba(0, 0, 0, 1),
    -0.03em -0.03em 0 rgba(0, 0, 0, 1), -0.03em 0.08em 0 rgba(0, 0, 0, 1), -0.03em 0 0 rgba(0, 0, 0, 1);
}
span {
  transform: scale(0.9);
  display: inline-block;
}
span:first-child {
  animation: bop 1s cubic-bezier(0.175, 0.885, 0.32, 1.275) forwards infinite
  alternate;
}
span:last-child {
  animation: bopB 1s 0.2s cubic-bezier(0.175, 0.885, 0.32, 1.275) forwards
  infinite alternate;
}

@keyframes bop {
  0% {
    transform: scale(0.9);
  }
  50%,
  100% {
    transform: scale(1);
  }
}

@keyframes bopB {
  0% {
    transform: scale(0.9);
  }
  80%,
  100% {
    transform: scale(1) rotateZ(-3deg);
  }
}


</style>
