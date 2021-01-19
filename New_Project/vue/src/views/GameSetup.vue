<template>
  <div class="game-setup">
    <div class="player-panel">
      <div class="left-side-panel">
        <div class="player-banner">
          <div class="banner-username">
            {{ this.$store.state.user.username }}
          </div>
          <div class="user-stats">Level: 4 | Lifetime Wins: 12</div>
        </div>
        <!-- <div class="leader-tile"></div> -->
        <button
          class="ready-button"
          :class="{ ready: this.readyForBattleMessage.readyForBattle }"
          @click.prevent="toggleReadyForBattle"
          v-if="this.$store.state.game.oppGame.username != ''"
        >
          READY FOR BATTLE
        </button>
        <button class="ready-button" @click.prevent="retreat">LEAVE GAME</button>
      </div>
 <!-- && this.selectedDeck != null -->
      <div class="decks-container">
        <div class="deck-tile"></div>
        <div class="deck-tile"></div>
        <div class="deck-tile"></div>
      </div>
    </div>

    <div
      class="vs"
      v-if="
        this.$store.state.game.myGame.readyForBattle === false ||
          this.$store.state.game.oppGame.readyForBattle === false
      "
    >
      VS
    </div>

    <div
      class="vs-cd"
      v-if="
        this.$store.state.game.myGame.readyForBattle === true &&
          this.$store.state.game.oppGame.readyForBattle === true
      "
    >
      GAME STARTING IN... <countdown v-on:startGame="sendToGame" />
    </div>

    <div class="player-panel">
      <div
        class="waiting-cont"
        v-if="this.$store.state.game.oppGame.username === ''"
      >
        <p class="waiting">WAITING FOR OPPONENT...</p>
      </div>

      <div
        class="opp-panel"
        v-if="this.$store.state.game.oppGame.username != ''"
      >
        <div class="left-side-panel">
          <div class="player-banner">
            <div class="banner-username">
              {{ this.$store.state.game.oppGame.username }}
            </div>
            <div class="user-stats">Level: 4 | Lifetime Wins: 12</div>
          </div>
          <div class="opp-ready-status">
            <div
              class="waiting-cont"
              v-if="this.$store.state.game.oppGame.readyForBattle === false"
            >
              <p class="waiting">preparing for battle...</p>
            </div>
            <div
              class="waiting-cont"
              v-if="this.$store.state.game.oppGame.readyForBattle === true"
            >
              <p class="waiting">READY TO FIGHT!</p>
            </div>
          </div>
          <!-- <div class="leader-tile"></div> -->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Countdown from "@/components/Countdown.vue";

export default {
  components: {
    Countdown,
  },
  data() {
    return {
      selectedDeck: null,
      sessionInfoMessage: {
        username: this.$store.state.user.username,
        sessionId: this.$store.state.sessionId,
        action: "",
      },
      readyForBattleMessage: {
        username: this.$store.state.user.username,
        readyForBattle: false,
      },
    };
  },
  computed: {},
  methods: {
    retreat() {
      this.$store.state.stompClient.send(
        "/app/game/matchmaking/" + this.$store.state.game.oppGame.username,
        JSON.stringify("")
      );
      this.$store.commit("SET_OPPONENT", "");
      this.$store.commit("SET_OPPGAME_RFB", false);
      this.$store.commit("SET_MYGAME_RFB", false);
      this.$store.state.matchmakingSubscription.unsubscribe();
      this.$router.push({ name: "find-match" });
    },
    leaving() {
      this.$store.state.stompClient.send(
        "/app/game/matchmaking/" + this.$store.state.game.oppGame.username,
        JSON.stringify("")
      );
      this.$store.commit("SET_OPPONENT", "");
      this.$store.commit("SET_MYGAME_RFB", false);
      this.$store.commit("SET_OPPGAME_RFB", false);
    },
    toggleReadyForBattle() {
      if (this.readyForBattleMessage.readyForBattle === true) {
        this.readyForBattleMessage.readyForBattle = false;
        this.$store.commit(
          "SET_MYGAME_RFB",
          this.readyForBattleMessage.readyForBattle
        );
      } else if (this.readyForBattleMessage.readyForBattle === false) {
        this.readyForBattleMessage.readyForBattle = true;
        this.$store.commit(
          "SET_MYGAME_RFB",
          this.readyForBattleMessage.readyForBattle
        );
      }
      this.$store.state.stompClient.send(
        "/app/game/matchmaking/" + this.$store.state.game.oppGame.username,
        JSON.stringify(this.readyForBattleMessage)
      );
    },
    sendToGame() {
      this.$router.push({ name: "arena" });
    },
  },
  mounted() {
    window.addEventListener("beforeunload", this.leaving);
  },
};
</script>

<style>
.game-setup {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-image: url("../assets/background_5.jpg");
  background-repeat: no-repeat;
}
.player-panel {
  border: 1px solid rgb(116, 116, 116);
  border-radius: 10px;
  width: 1500px;
  height: 450px;
  margin: 15px 0px 10px 0px;
  background-image: linear-gradient(
    180deg,
    rgba(0, 0, 0, 0.7),
    rgba(0, 0, 0, 0.9)
  );
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}
.left-side-panel {
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  align-items: center;
}
.player-banner {
  width: 500px;
  height: 150px;
  border: 1px solid white;
  border-radius: 10px;
  background-image: url("../assets/egg-shell.png"),
    linear-gradient(black, rgb(66, 99, 189));
  margin: 30px 30px 30px 30px;
}
.banner-username {
  font-size: 60px;
  color: white;
  font-weight: 600;
  font-family: "Marcellus";
  padding: 10px;
  text-align: center;
}
.user-stats {
  font-family: "Marcellus";
  padding: 10px;
  color: white;
  font-size: 30px;
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
}
.leader-tile {
  width: 300px;
  height: 150px;
  border: 1px solid grey;
  border-radius: 10px;
  background-image: url("../assets/egg-shell.png"),
    linear-gradient(black, rgb(56, 66, 95));
  margin: 5px 30px 15px 30px;
}
.ready-button {
  width: 40%;
  height: 55px;
  background-color: black;
  border: 1px solid white;
  border-radius: 10px;
  color: white;
  font-family: "Marcellus";
  font-size: 20px;
  font-weight: 600;
  margin: 10px 10px 20px 10px;
  outline: none;
}
.ready-button:hover {
  background-color: rgb(255, 0, 0);
  cursor: pointer;
  outline: none;
}
.decks-container {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  gap: 40px;
}
.deck-tile {
  width: 250px;
  height: 350px;
  border: 1px solid grey;
  border-radius: 15px;
}
.vs {
  font-size: 220px;
  margin: -75px 0px -75px 0px;
  color: white;
  font-family: "Germania One";
  text-shadow: 8px 8px rgba(0, 0, 0, 0.575);
  z-index: 1;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  width: 100%;
}
.vs-cd {
  font-size: 100px;
  margin: -5px 0px -5px 0px;
  color: white;
  font-family: "Germania One";
  text-shadow: 8px 8px rgba(0, 0, 0, 0.575);
  z-index: 1;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  width: 100%;
}
.waiting-cont {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.waiting {
  position: relative;
  text-transform: uppercase;
  letter-spacing: 4px;
  overflow: hidden;
  background: linear-gradient(90deg, #000, rgb(255, 255, 255), #000);
  background-repeat: no-repeat;
  background-size: 80%;
  animation: animate 3s linear infinite;
  -webkit-background-clip: text;
  background-clip: text;
  -webkit-text-fill-color: rgba(255, 255, 255, 0);
  font-family: "Germania One";
  font-size: 70px;
}

@keyframes animate {
  0% {
    background-position: -500%;
  }
  100% {
    background-position: 500%;
  }
}
.ready {
  background-color: red;
  border: 1px solid red;
  box-shadow: 0 0 10px 10px rgb(255, 43, 43),
    0 0 10px 10px rgb(255, 255, 255),
    0 0 10px 10px rgb(255, 0, 0);
}
.ready:focus {
  outline: none;
}
</style>
