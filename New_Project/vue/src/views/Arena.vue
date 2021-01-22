<template>
  <div>
    <div class="arena">
      <div class="opp-area">
        <div class="status-div-opp-username">
          {{ this.$store.state.game.oppGame.username }}
        </div>
        <div class="status-div">
          <font-awesome-icon
            :icon="['fa', 'coins']"
            class="icon-arena"
            color="yellow"
          />
          {{ this.game.oppGame.gameState.resources.cache }}
        </div>
        <div class="status-div">
          <font-awesome-icon
            :icon="['fa', 'gopuram']"
            class="icon-arena"
            color="grey"
          />
          {{ this.game.oppGame.gameState.fortress.integrity }}
        </div>
        <div class="status-div">
          <font-awesome-icon :icon="['fa', 'users']" class="icon-arena-rep" />
          {{ this.game.oppGame.gameState.reputation.score }}
        </div>
        <div class="status-div">
          <font-awesome-icon :icon="['fa', 'scroll']" class="icon-arena-cont" />
          {{ this.game.oppGame.gameState.contracts.active.length }}
          Active
        </div>
        <div class="status-div">
          <font-awesome-icon
            :icon="['fa', 'align-justify']"
            class="icon-arena-deck"
          />
          {{ this.game.oppGame.gameState.deck.cards.length }}
          <div class="status-div">
            <font-awesome-icon
              :icon="['fa', 'hand-paper']"
              class="icon-arena-hand"
            />
            {{ this.game.oppGame.gameState.hand.cards.length }}
          </div>
        </div>
      </div>

      <div class="opp-zone">
        <tile
          :class="attackingOrTarget(card)"
          @contextmenu.prevent.native="resolveAttack(card)"
          :card="card"
          :key="card.id"
          v-for="card in this.game.oppGame.gameState.combatZone.cards"
        />
      </div>

      <div class="arena-header">
        <exit-modal v-on:exitGame="runAway" />
      </div>

      <div class="my-area">
        <div class="status-div-my-username">
          {{ this.game.myGame.username }}
        </div>
        <div class="status-div">
          <font-awesome-icon
            :icon="['fa', 'coins']"
            class="icon-arena"
            color="yellow"
          />
          {{ this.game.myGame.gameState.resources.cache }}
        </div>
        <div class="status-div">
          <font-awesome-icon
            :icon="['fa', 'gopuram']"
            class="icon-arena"
            color="grey"
          />
          {{ this.game.myGame.gameState.fortress.integrity }}
        </div>
        <div class="status-div">
          <font-awesome-icon :icon="['fa', 'users']" class="icon-arena-rep" />
          {{ this.game.myGame.gameState.reputation.score }}
        </div>
        <div class="status-div">
          <font-awesome-icon :icon="['fa', 'scroll']" class="icon-arena-cont" />
          {{ this.game.myGame.gameState.contracts.active.length }} Active
        </div>
        <div class="status-div">
          <font-awesome-icon
            :icon="['fa', 'align-justify']"
            class="icon-arena-deck"
          />
          {{ this.game.myGame.gameState.deck.cards.length }}
          <div class="status-div">
            <font-awesome-icon
              :icon="['fa', 'hand-paper']"
              class="icon-arena-hand"
            />
            {{ this.game.myGame.gameState.hand.cards.length }}
          </div>
        </div>
      </div>

      <draggable
        class="my-zone"
        v-model="game.myGame.gameState.combatZone.cards"
        :options="{ group: 'cards' }"
        @change="checkCardPurchase"
        v-if="
          this.game.myGame.keepHand === true &&
            this.game.myGame.keepContracts === true
        "
      >
        <!-- :disabled="!this.game.myGame.isTurn" -->
        <tile
          @contextmenu.prevent.native="toggleAttacking(card)"
          :class="attackingOrTarget(card)"
          :card="card"
          :key="card.id"
          v-for="card in this.game.myGame.gameState.combatZone.cards"
        />
      </draggable>

      <div class="my-zone-start" v-if="this.game.myGame.keepHand === false">
        <div class="mulligan-prompt">
          WOULD YOU LIKE TO KEEP THIS HAND OR MULLIGAN FOR A NEW ONE?
        </div>
        <button class="nav-button" @click.prevent="keepHand">KEEP</button>
        <button class="nav-button" @click.prevent="mulliganHand">
          MULLIGAN
        </button>
      </div>

      <div
        class="my-zone-start"
        v-if="
          this.game.myGame.keepHand === true &&
            this.game.myGame.keepContracts === false
        "
      >
        <div class="mulligan-prompt">
          WOULD YOU LIKE TO KEEP THESE CONTRACTS OR MULLIGAN FOR A NEW SET?
        </div>
        <button class="nav-button" @click.prevent="keepContracts">KEEP</button>
        <button class="nav-button" @click.prevent="mulliganContracts">
          MULLIGAN
        </button>
      </div>

      <draggable
        class="zone-hand"
        v-model="game.myGame.gameState.hand.cards"
        :options="{ group: 'cards' }"
      >
        <tile
          :class="{ castable: isCastable(card) }"
          :card="card"
          :key="card.id"
          v-for="card in this.game.myGame.gameState.hand.cards"
        />
      </draggable>

      <div class="deck">
        <img
          @click.prevent="drawCard"
          v-if="this.game.myGame.gameState.deck.isEmpty === false"
          class="deck-img"
          src="../assets/card_back.png"
        />
      </div>

      <div class="dead-zone">
        <div class="dead-zone">
          <dead-tile
            :card="card"
            :key="card.id"
            v-for="card in this.game.myGame.gameState.deadZone.cards"
          />
        </div>
      </div>

      <div class="enemy-cont-ac">
        <div class="contract-head">
          Enemy Contracts
        </div>
        <div class="cont-av">
          <enemy-cont
            v-for="contract in this.game.oppGame.gameState.contracts.active"
            :key="contract.name"
            :contract="contract"
          />
        </div>
      </div>

      <div class="cont-cont-ac">
        <div class="contract-head">
          Active Contracts
        </div>
        <draggable
          class="cont-ac"
          :options="{ group: 'contracts' }"
          v-model="game.myGame.gameState.contracts.active"
          @change="checkContPurchase"
        >
          <!-- @change="checkContPurchase" -->

          <enemy-cont
            :contract="contract"
            class="cont-tile"
            v-for="contract in this.game.myGame.gameState.contracts.active"
            :key="contract.name"
          />
        </draggable>
      </div>

      <div class="cont-cont-av">
        <draggable
          class="cont-av"
          :options="{ group: 'contracts' }"
          v-model="game.myGame.gameState.contracts.available"
        >
          <contract-tile
            v-for="contract in this.game.myGame.gameState.contracts.available"
            :key="contract.name"
            :contract="contract"
            :class="{ contractable: isContractable(contract) }"
          />
        </draggable>
      </div>
    </div>
    <modal
      name="victory-modal"
      v-if="show"
      height="355px"
      width="485px"
      :clickToClose="false"
      styles="background-color: rgb(0, 0, 0, 0.6); border-radius: 10px; border: 2px solid black;"
    >
      <div class='vic-modal-content'>
        <div class="vic-modal-header">
          VICTORY!
        </div>
        <p class="vic-modal-prompt">
          The enemy keep is yours!
        </p>
        <button class="modal-btn-quit" @click="exitGame">
          EXIT GAME
        </button>
      </div>
    </modal>
  </div>
</template>

<script>
import Tile from "@/components/Tile.vue";
import DeadTile from "@/components/DeadTile.vue";
import ContractTile from "@/components/ContractTile.vue";
import CardService from "../services/CardService.js";
import draggable from "vuedraggable";
import ExitModal from "@/components/GameComponents/ExitModal.vue";
import EnemyCont from "@/components/EnemyCont.vue";

export default {
  data() {
    return {
      game: {
        isOver: false,
        isStarting: true,
        myGame: {
          username: this.$store.state.user.username,
          readyForBattle: true,
          concede: false,
          keepHand: false,
          keepContracts: false,
          turnBegin: false,
          isTurn: false,
          turnCount: 0,
          attacker: {},
          target: {},
          hasAttacked: [],
          deadOppCard: {},
          gameState: {
            deck: {
              cards: [],
              isEmpty: false,
            },
            hand: {
              cards: [],
            },
            combatZone: {
              cards: [],
              mods: [],
            },
            deadZone: {
              cards: [],
            },
            contracts: {
              available: [],
              active: [],
              completed: [],
              deck: [],
              mods: [],
            },
            fortress: {
              integrity: 40,
              mods: [],
            },
            reputation: {
              score: 10,
              mods: [],
            },
            resources: {
              cache: 500,
              mods: [],
            },
          },
        },
        oppGame: {
          username: this.$store.state.game.oppGame.username,
          readyForBattle: true,
          keepHand: false,
          keepContracts: false,
          turnBegin: false,
          isTurn: false,
          turnCount: 0,
          gameState: {
            deck: {
              cards: [],
              isEmpty: false,
            },
            hand: {
              cards: [],
            },
            combatZone: {
              cards: [],
              mods: [],
            },
            deadZone: {
              cards: [],
            },
            contracts: {
              available: [],
              active: [],
              completed: [],
              deck: [],
              mods: [],
            },
            fortress: {
              integrity: 40,
              mods: [],
            },
            reputation: {
              score: 10,
              mods: [],
            },
            resources: {
              cache: 500,
              mods: [],
            },
          },
        },
      },
    };
  },
  components: {
    Tile,
    DeadTile,
    ContractTile,
    draggable,
    ExitModal,
    EnemyCont,
  },
  methods: {
    drawCard() {
      if (this.game.myGame.gameState.hand.cards.length <= 6) {
        var card = this.game.myGame.gameState.deck.cards.shift();
        this.game.myGame.gameState.hand.cards.push(card);
      }
      if (this.game.myGame.gameState.deck.cards.length === 0) {
        this.game.myGame.gameState.deck.isEmpty = true;
      }
    },
    drawContract() {
      if (this.game.myGame.gameState.contracts.available.length <= 5) {
        var contract = this.game.myGame.gameState.contracts.deck.shift();
        this.game.myGame.gameState.contracts.available.push(contract);
      }
    },
    shuffle(array) {
      for (var i = array.length - 1; i > 0; i--) {
        var j = Math.floor(Math.random() * (i + 1));
        var temp = array[i];
        array[i] = array[j];
        array[j] = temp;
      }
    },
    keepHand() {
      this.game.myGame.keepHand = true;
    },
    mulliganHand() {
      for (var i = 0; i < this.game.myGame.gameState.hand.cards.length; i++) {
        this.game.myGame.gameState.deck.cards.push(
          this.game.myGame.gameState.hand.cards[i]
        );
      }
      this.game.myGame.gameState.hand.cards = [];
      this.shuffle(this.game.myGame.gameState.deck.cards);
      this.drawCard();
      this.drawCard();
      this.drawCard();
      this.drawCard();
      this.game.myGame.keepHand = true;
    },
    keepContracts() {
      this.game.myGame.keepContracts = true;
      this.sendGameState();
    },
    mulliganContracts() {
      for (
        var i = 0;
        i < this.game.myGame.gameState.contracts.available.length;
        i++
      ) {
        this.game.myGame.gameState.contracts.deck.push(
          this.game.myGame.gameState.contracts.available[i]
        );
      }
      this.game.myGame.gameState.contracts.available = [];
      this.shuffle(this.game.myGame.gameState.contracts.deck);
      this.drawContract();
      this.drawContract();
      this.drawContract();
      this.drawContract();
      this.game.myGame.keepContracts = true;
      this.sendGameState();
    },
    updateMyGameState() {
      this.$store.commit("UPDATE_MY_GAME_STATE", this.game.myGame);
    },
    checkContPurchase({ added }) {
      if (added) {
        if (
          added.element.cost > this.game.myGame.gameState.resources.cache ||
          this.game.myGame.gameState.contracts.active.length == 6
        ) {
          this.game.myGame.gameState.contracts.active.splice(added.newIndex, 1);
          this.game.myGame.gameState.contracts.available.push(added.element);
        } else {
          const charge = added.element.cost;
          this.game.myGame.gameState.resources.cache -= charge;
          this.sendGameState();
        }
      }
    },
    checkCardPurchase({ added }) {
      if (added) {
        if (
          added.element.cost > this.game.myGame.gameState.resources.cache ||
          this.game.myGame.gameState.combatZone.cards.length == 11
        ) {
          this.game.myGame.gameState.combatZone.cards.splice(added.newIndex, 1);
          this.game.myGame.gameState.hand.cards.push(added.element);
        } else {
          const charge = added.element.cost;
          this.game.myGame.gameState.resources.cache -= charge;
          this.sendGameState();
        }
      }
    },
    isCastable(card) {
      return card.cost <= this.game.myGame.gameState.resources.cache;
    },
    isContractable(contract) {
      return contract.cost <= this.game.myGame.gameState.resources.cache;
    },
    isAttacking(card) {
      return card.isAttacking;
    },
    isTarget(card) {
      return card.isTarget;
    },
    toggleAttacking(card) {
      // if (this.game.myGame.isTurn === true) {
      if (this.game.myGame.hasAttacked != []) {
        for (var i = 0; i < this.game.myGame.hasAttacked.length; i++) {
          if (this.game.myGame.hasAttacked[i].name === card.name) {
            card.canAttack = false;
          }
        }
      }
      if (card.canAttack) {
        card.isAttacking = !card.isAttacking;
      }
      for (
        var n = 0;
        n < this.game.myGame.gameState.combatZone.cards.length;
        n++
      ) {
        if (this.game.myGame.gameState.combatZone.cards[n].name != card.name) {
          this.game.myGame.gameState.combatZone.cards[n].isAttacking = false;
        }
      }
      this.game.myGame.attacker = card;
      this.sendGameState();
      // }
    },
    resolveDeadCard() {
      for (
        var i = 0;
        i < this.game.myGame.gameState.combatZone.cards.length;
        i++
      ) {
        if (
          this.game.myGame.gameState.combatZone.cards[i].name ===
          this.game.oppGame.deadOppCard.name
        ) {
          this.game.myGame.gameState.deadZone.cards.push(
            this.game.myGame.gameState.combatZone.cards[i]
          );
          this.game.myGame.gameState.combatZone.cards.splice(i, 1);
        }
      }
      this.game.oppGame.deadOppCard = {};
      this.game.myGame.deadOppCard = {};
    },
    resolveAttack(card) {
      card.isTarget = !card.isTarget;
      setTimeout(() => {
        for (
          var i = 0;
          i < this.game.myGame.gameState.combatZone.cards.length;
          i++
        ) {
          if (
            this.game.myGame.gameState.combatZone.cards[i].isAttacking === true
          ) {
            this.game.myGame.gameState.combatZone.cards[i].resilience -=
              card.attack;
            card.resilience -= this.game.myGame.gameState.combatZone.cards[
              i
            ].attack;
          }
        }
      }, 1000);
      setTimeout(() => {
        for (var i = 0; i < this.game.myGame.gameState.combatZone.cards.length; i++) {
          if (this.game.myGame.gameState.combatZone.cards[i].isAttacking === true) {
            this.game.myGame.hasAttacked.push(this.game.myGame.gameState.combatZone.cards[i]);
            this.game.myGame.gameState.combatZone.cards[i].isAttacking = false;
            this.game.myGame.gameState.combatZone.cards[i].canAttack = false;
          }
          if (this.game.myGame.gameState.combatZone.cards[i].resilience <= 0) {
            this.game.myGame.gameState.deadZone.cards.push(
              this.game.myGame.gameState.combatZone.cards[i]
            );
            this.game.myGame.gameState.combatZone.cards.splice(i, 1);
          }
        }
        card.isTarget = !card.isTarget;
        if (card.resilience <= 0) {
          this.game.myGame.deadOppCard = card;
          for ( var x = 0; x < this.game.oppGame.gameState.combatZone.cards.length; x++) {
            if (
              this.game.oppGame.gameState.combatZone.cards[x].name === card.name
            ) {
              this.game.oppGame.gameState.deadZone.cards.push(
                this.game.oppGame.gameState.combatZone.cards[x]
              );
              this.game.oppGame.gameState.combatZone.cards.splice(x, 1);
            }
          }
        }
        if (card.resilience > 0) {
          this.game.myGame.target = card;
        }
        this.sendGameState();
        this.game.myGame.target = {}
      }, 2000);
    },
    resolveDamagedCard() {
      for (var w = 0; w < this.game.myGame.gameState.combatZone.cards.length; w++) {
        if(this.game.oppGame.target.name === this.game.myGame.gameState.combatZone.cards[w].name) {
          this.game.myGame.gameState.combatZone.cards[w].resilience = this.game.oppGame.target.resilience;
          this.game.oppGame.target = {};
        }
      }
    },
    attackingOrTarget: (card) => {
      return {
        attacking: card.isAttacking,
        targeted: card.isTarget,
      };
    },
    sendGameState() {
      this.$store.state.stompClient.send(
        "/app/game/match/" + this.$store.state.game.oppGame.username,
        JSON.stringify(this.game.myGame)
      );
    },
    show() {
      this.$modal.show("victory-modal");
    },
    hide() {
      this.$modal.hide("victory-modal");
    },
    runAway() {
      this.game.myGame.concede = true;
      this.sendGameState();
      this.exitGame();
    },
    exitGame() {
      this.$store.commit("SET_OPPONENT", "");
      this.$store.commit("SET_MYGAME_RFB", false);
      this.$store.commit("SET_OPPGAME_RFB", false);
      this.$router.push({ name: "home" });
    },

  },
  created() {
    CardService.getAllCards().then((response) => {
      this.game.myGame.gameState.deck.cards = response.data;
      this.shuffle(this.game.myGame.gameState.deck.cards);
      this.drawCard();
      this.drawCard();
      this.drawCard();
      this.drawCard();
    });
    CardService.getAllContracts().then((response) => {
      this.game.myGame.gameState.contracts.deck = response.data;
      this.shuffle(this.game.myGame.gameState.contracts.deck);
      this.drawContract();
      this.drawContract();
      this.drawContract();
      this.drawContract();
    });
    let gameSessionSub = this.$store.state.stompClient.subscribe(
      "/topic/match/" + this.$store.state.user.username,
      (tick) => {
        let message = JSON.parse(tick.body);
        this.$store.commit("UPDATE_OPP_GAME_STATE", message);
        this.game.oppGame = message;
        if (this.game.oppGame.deadOppCard != {}) {
          this.resolveDeadCard();
        }
        if (this.game.oppGame.target != {}) {
          this.resolveDamagedCard();
        }
        if (message.concede === true) {
          this.showVictory = true;
          this.show();
        }
      }
    );
    this.$store.commit("SET_GAME_SESSION_SUBSCRIPTION", gameSessionSub);
  },
  mounted() {
    this.updateMyGameState();
  },
};
</script>

<style>
.arena {
  display: grid;
  grid-template-rows: 400px 49px 400px 200px;
  grid-template-columns: 250px 200px 1080px 360px;
  row-gap: 5px;
  column-gap: 5px;
  grid-template-areas:
    "opp-status opp-zone opp-zone enemy-contracts"
    "arena-header arena-header arena-header arena-header"
    "my-status my-zone my-zone contracts-active"
    "dead deck hand contracts";

  background-image: url("../assets/background_3.jpg");
}
.arena-header {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  grid-area: arena-header;
  margin-left: 10px;
  margin-top: 5px;
}
.status-div {
  color: rgb(223, 223, 223);
  font-family: "Germania One";
  font-size: 35px;
  display: flex;
  align-items: center;
  padding: 5px 20px 5px 20px;
}
.status-div-my-username {
  color: rgb(255, 255, 255);
  font-family: "Germania One";
  font-size: 35px;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 2px 0px 2px 0px;
  border: 1px solid rgb(151, 151, 151);
  border-radius: 15px;
  margin: 0px 5px 0px 5px;
  background-image: linear-gradient(180deg, rgb(0, 6, 39), rgb(0, 43, 160));
}
.status-div-opp-username {
  color: rgb(255, 255, 255);
  font-family: "Germania One";
  font-size: 35px;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 2px 0px 2px 0px;
  border: 1px solid rgb(151, 151, 151);
  border-radius: 15px;
  margin: 0px 5px 0px 5px;
  background-image: linear-gradient(180deg, rgb(39, 0, 0), rgb(160, 0, 0));
}
.opp-zone {
  grid-area: opp-zone;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  flex: 1;
  flex-wrap: wrap;
  background-image: linear-gradient(
    60deg,
    rgba(0, 0, 0, 0.5),
    rgba(0, 0, 0, 0.5)
  );
  margin: 5px;
  gap: 5px;
  border-radius: 10px;
  border: 1px solid rgb(43, 43, 43);
  height: 100%;
  width: 100%;
}
.opp-area {
  border: 1px solid rgb(43, 43, 43);
  margin: 5px;
  gap: 5px;
  height: 400px;
  width: 250px;
  border-radius: 10px;
  background-image: linear-gradient(
    60deg,
    rgba(2, 2, 2, 0.7),
    rgba(0, 0, 0, 0.7)
  );
  grid-area: opp-status;
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
}
.my-zone {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  flex: 1;
  flex-wrap: wrap;
  gap: 5px;
  background-image: linear-gradient(
    60deg,
    rgba(2, 2, 2, 0.5),
    rgba(0, 0, 0, 0.5)
  );
  margin: 5px;
  border: 1px solid rgb(43, 43, 43);
  border-radius: 10px;
  height: 100%;
  width: 100%;
  grid-area: my-zone;
}
.my-zone-start {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  flex: 1;
  flex-wrap: wrap;
  gap: 5px;
  background-image: linear-gradient(
    60deg,
    rgba(2, 2, 2, 0.5),
    rgba(0, 0, 0, 0.5)
  );
  margin: 5px;
  border: 1px solid rgb(43, 43, 43);
  border-radius: 10px;
  height: 100%;
  width: 100%;
  grid-area: my-zone;
}
.my-area {
  border: 1px solid rgb(43, 43, 43);
  margin: 5px;
  gap: 5px;
  height: 400px;
  width: 250px;
  border-radius: 10px;
  background-image: linear-gradient(
    60deg,
    rgba(0, 0, 0, 0.7),
    rgba(0, 0, 0, 0.7)
  );
  grid-area: my-status;
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
}
.player-area {
  display: flex;
  flex-direction: row;
  height: auto;
  justify-content: space-evenly;
}
.zone-hand {
  display: grid;
  grid-template-columns: 10% 10% 10% 10% 10% 10% 30%;
  justify-content: center;
  align-items: center;
  background-image: linear-gradient(
    60deg,
    rgba(24, 21, 58, 0.473),
    rgba(43, 17, 58, 0.473)
  );
  border-radius: 10px;
  border: 1px solid black;
  grid-area: hand;
  margin: 5px;
}
.deck {
  margin: 2px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  grid-area: deck;
  border: 1px solid rgb(102, 102, 102);
  border-radius: 10px;
  width: 80%;
  background-color: rgba(90, 90, 90, 0.5);
}
.deck-img {
  width: 155px;
  height: 190px;
  transition: all 0.2s ease-in-out;
}
.deck-img:hover {
  transform: scale(1.01);
  cursor: pointer;
}
.dead-zone {
  min-height: 200px;
  min-width: 180px;
  padding: 10px 10px 10px 10px;
  display: grid;
  grid-template-rows: 8px 8px 8px 8px 8px 8px 8px 8px 8px 8px 8px 8px 8px 8px 8px;
  justify-items: center;
}
.cont-cont-av {
  background-image: url("../assets/old-paper.jpg");
  background-position: right;
  background-size: 1900px 1080px;

  border-radius: 10px;
  border: 1px solid rgb(48, 48, 48);

  width: 95%;
  height: 188px;
  margin: 5px;

  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-content: center;

  grid-area: contracts;
}
.cont-cont-ac {
  background-image: url("../assets/old-paper.jpg");
  background-position: right;
  background-size: 1900px 1080px;

  border-radius: 10px;
  border: 1px solid rgb(48, 48, 48);

  width: 95%;
  height: 100%;
  margin: 5px;

  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-content: center;

  grid-area: contracts-active;
}
.enemy-cont-ac {
  background-image: url("../assets/old-paper.jpg");
  background-position: right;
  background-size: 1900px 1080px;

  border-radius: 10px;
  border: 1px solid rgb(48, 48, 48);

  width: 95%;
  height: 100%;
  margin: 5px;

  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-content: center;

  grid-area: enemy-contracts;
}
.cont-av {
  min-width: 300px;
  min-height: 300px;
}
.cont-ac {
  min-width: 300px;
  min-height: 300px;
}
.contract-head {
  padding: 3px;
  color: rgb(233, 236, 222);
  font-family: "Eczar";
  font-weight: 500;
  font-size: 25px;
  text-shadow: 2px 2px rgb(77, 77, 77);
  text-align: center;
}
.castable {
  box-shadow: 0 0 10px #fff, -3px 0 4px rgb(255, 240, 35),
    3px 0 4px rgb(255, 240, 35);
}
.castable:hover {
  box-shadow: 0 0 10px #fff, -3px 0 4px rgb(255, 240, 35),
    3px 0 4px rgb(255, 240, 35);
}
.contractable {
  box-shadow: -5px 0 5px rgb(18, 128, 218), 5px 0 5px rgb(116, 142, 255);
}
.contractable:hover {
  box-shadow: -5px 0 5px rgb(18, 128, 218), 5px 0 5px rgb(116, 142, 255);
}
.icon-arena {
  margin-right: 20px;
}
.icon-arena-cont {
  margin-right: 20px;
  color: rgb(223, 224, 165);
}
.icon-arena-rep {
  margin-right: 20px;
  color: rgb(130, 142, 255);
}
.icon-arena-deck {
  margin-right: 20px;
  color: rgb(228, 52, 52);
}
.icon-arena-hand {
  margin-right: 20px;
  color: rgb(46, 175, 68);
}
.mulligan-prompt {
  font-family: "Germania One";
  font-size: 30px;
  color: white;
}
.attacking {
  box-shadow: 0 0 5px 5px rgb(255, 43, 43), 0 0 5px 5px rgb(255, 255, 255),
    0 0 5px 5px rgb(255, 0, 0);
}
.attacking:hover {
  box-shadow: 0 0 5px 5px rgb(255, 43, 43), 0 0 5px 5px rgb(255, 255, 255),
    0 0 5px 5px rgb(255, 0, 0);
}
.targeted {
  box-shadow: 0 0 5px 5px rgb(43, 100, 255), 0 0 5px 5px rgb(255, 255, 255),
    0 0 5px 5px rgb(43, 100, 255);
}
.targeted:hover {
  box-shadow: 0 0 5px 5px rgb(43, 100, 255), 0 0 5px 5px rgb(255, 255, 255),
    0 0 5px 5px rgb(43, 100, 255);
}
.vic-modal-content {
  height: 100%;
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.vic-modal-header{
  font-family: 'Germania One';
  font-size: 90px;
  color: white;
  text-shadow: 2px 2px rgb(88, 88, 88);
}
.vic-modal-prompt {
    color: white;
    font-family: 'Marcellus';
    font-size: 25px;
    margin-top: 20px;
    font-style: italic;
}
</style>
