<template>
  <div class="mm-tile">
    <div class="pick-one">
      {{ user.username }}
      <button
        class="chlng-btn"
        v-if="user.username != this.$store.state.user.username"
        @click="join(user.username)"
      >
        JOIN
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "prtile",
  props: ["user"],
  data() {
    return {
      sessionInfoMessage: {
        username: this.$store.state.user.username,
        sessionId: this.$store.state.sessionId,
        action: "matched",
      },
      joinMessage: {
        username: this.$store.state.user.username,
        readyForBattle: false,
      }
    };
  },
  methods: {
    join(username) {
      this.sendSessionInfoMessage();
      this.$store.commit("SET_OPPONENT", username);
      setTimeout(() => {
        let matchmakingSubscription = this.$store.state.stompClient.subscribe('/topic/matchmaking/' + this.$store.state.user.username, (tick) => {
        let message = JSON.parse(tick.body);
        if (message === '') {
          this.$store.commit('SET_OPPONENT', message);
          this.$store.commit('SET_OPPGAME_RFB', false);
          console.log('Opponent left!');
          this.sessionInfoMessage.action = 'ready';
        }
        else if (message.username != '') {
          this.$store.commit('SET_OPPONENT', message.username);
          console.log('Opponent found!');
          this.$store.commit('SET_OPPGAME_RFB', message.readyForBattle);
          this.sessionInfoMessage.action = 'matched';
        }
        this.sendSessionInfoMessage();
      });
      this.$store.commit('SET_MATCHMAKING_SUBSCRIPTION', matchmakingSubscription);
      this.$store.state.stompClient.send("/app/game/matchmaking/" + username, JSON.stringify(this.joinMessage));
      this.$router.push({ name: 'game-setup'});
      }, 500);
    },
    sendSessionInfoMessage() {
      if (
        this.$store.state.stompClient &&
        this.$store.state.stompClient.connected
      ) {
        this.$store.state.stompClient.send(
          "/app/game",
          JSON.stringify(this.sessionInfoMessage),
          {}
        );
      }
    },
  },
};
</script>

<style></style>
