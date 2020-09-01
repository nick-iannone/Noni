<template>
  <div id='review-page'>
    <div class="offer-container" v-if="trades.length === 0">
      <h4 class="review-header" v-if="trades.length === 0">Trade big or go home!</h4>
      <div class="offer-tile">
        <p class="review-call" v-if="trades.length === 0">You don't have any trade offers at the moment. Go out and <a id="trade-link" href="/collections">browse collections</a> to add comics to a trade!</p>
      </div>
    </div>
    <trade-review v-for="trade in this.trades" v-bind:key="trade.tradeID" v-bind:trade="trade"/>
  </div>
</template>

<script>
import TradeReview from '@/components/TradeReview.vue'
import TradeService from '../services/TradeService'

export default {
    data () {
        return {
            trades: []
        }
    },
    components: {
        TradeReview
    },
    created() {
        TradeService.getPendingTrades(this.$route.params.id).then(response => {
            this.trades = response.data;
        })
    }
}
</script>

<style>
#review-page {
    background-image: url('../assets/cropped.jpg');
    background-repeat:no-repeat;
    color: black;
    margin: 0px 0px 40px 0px;
    padding: 50px;
}
.offer-container {
    display:flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
.offer-tile {
    background-image: linear-gradient(to bottom, rgba(255, 254, 198, 0.95), rgba(255, 254, 203, 0.85));
    border: 2px solid black;
    margin: 0px 20px 20px 20px;
    width: 30%;
    border-radius: 25px;
    box-shadow: 4px 4px black;
}
.review-header {
    font-size: 5vw;
    font-family: 'Bangers';
    color: rgb(255, 255, 255);
    margin: 40px;
    text-align: center;
    text-shadow: 4px 4px black;
}
.review-call {
    text-align: center;
    font-family: 'Walter Turncoat';
    font-size: 25px;
}
#trade-link {
    font-family: 'Walter Turncoat';
    font-size: 25px;
    color: rgb(1, 61, 173);
}
#trade-link:hover {
    color:rgb(255, 161, 37);
}
</style>