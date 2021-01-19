<template>
  <div class="mm-tile">
    <div class="pick-one">
      {{ user.username }}

      <button
        class="chlng-btn"
        v-if="user.username === this.$store.state.user.username && this.$store.state.stompClient.connected"
        @click.prevent="readyUp"
      >
        READY-UP
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "mmtile",
  props: ["user"],
  data() {
    return {
      invitation: {
        toUsername: "",
        fromUsername: this.$store.state.user.username,
      },
      sessionInfoMessage: {
        username: this.$store.state.user.username,
        sessionId: this.$store.state.sessionId,
        action: "ready",
      },
    };
  },
  methods: {
    sendSessionInfoMessage() {
      if (this.$store.state.stompClient && this.$store.state.stompClient.connected) {
        this.$store.state.stompClient.send(
          "/app/game",
          JSON.stringify(this.sessionInfoMessage),
          {}
        );
      }
    },
    readyUp() {
      //subscribe to matchmaking/username channel > will receive join notification > on (tick) => set store opponent username > subscribe to gamestream/username > push to arena
      this.sendSessionInfoMessage();
      setTimeout(() => {
        let matchmakingSubscription = this.$store.state.stompClient.subscribe('/topic/matchmaking/' + this.$store.state.user.username, (tick) => {
        let message = JSON.parse(tick.body);
        if (message === '') {
          this.$store.commit('SET_OPPONENT', message);
          this.$store.commit('SET_OPPGAME_RFB', false);
          console.log('Opponent left!');
          this.sessionInfoMessage.action = 'ready';
        }
        else {
          this.$store.commit('SET_OPPONENT', message.username);
          console.log('Opponent found!');
          this.$store.commit('SET_OPPGAME_RFB', message.readyForBattle);
          this.sessionInfoMessage.action = 'matched';
        }
        this.sendSessionInfoMessage();
      });
      this.$store.commit('SET_MATCHMAKING_SUBSCRIPTION', matchmakingSubscription);
      this.$router.push({ name: 'game-setup'});
      }, 500);
    },
  },
};
</script>

<style>
.pick-one {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  color: white;
  font-family: "Marcellus";
  font-size: 22px;
  font-weight: 600;
  padding: 10px;
  border: 1px solid grey;
  border-radius: 5px;
  background-color: rgba(43, 43, 43, 0.555);
}
.chlng-btn {
  font-family: "Marcellus";
  font-size: 15px;
  border: 1px solid white;
  border-radius: 5px;
  background-color: rgb(122, 36, 36);
  color: white;
  padding: 5px 10px 5px 10px;
}
.chlng-btn:hover {
  cursor: pointer;
  background-color: rgb(37, 131, 207);
}
</style>
