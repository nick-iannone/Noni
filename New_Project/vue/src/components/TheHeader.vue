<template>
  <div>
    <chat v-show="showChat" />
    <div id="nav-bar">
      <button class="nav-button" @click.prevent="toggleShowChat" v-if="this.$store.state.token != '' && this.$route.name != 'arena'">
        {{ showChat ? "HIDE CHAT" : "CHAT MENU" }}
      </button>
      <button
        class="nav-button"
        @click="goToLogin"
        v-if="this.$store.state.token === ''"
      >
        LOGIN
      </button>
      <button
        class="nav-button"
        @click="goToCollection"
        v-if="this.$store.state.token != '' && this.$route.name != 'browse' "
      >
        BROWSE
      </button>
      <button
        class="nav-button"
        @click="goToDeckBuilder"
        v-if="$store.state.token != '' && this.$route.name != 'deck-builder'"
      >
        DECK BUILDER
      </button>
      <button class="nav-button" @click.prevent="sendToHome" v-if="this.$route.name != 'home'">
        MAIN MENU
      </button>
      <button
        class="nav-button"
        @click="logout"
        v-if="$store.state.token != ''"
      >
        LOGOUT
      </button>
    </div>
  </div>
</template>

<script>
import Chat from "@/components/Chat.vue";

export default {
  name: "the-header",
  components: {
    Chat,
  },
  data() {
    return {
      showChat: false,
    };
  },
  methods: {
    toggleShowChat() {
      if (!this.showChat) {
        this.showChat = true;
      } else {
        this.showChat = false;
      }
    },
    goToArena() {
      this.$router.push({ name: "arena" });
    },
    goToLogin() {
      this.$router.push({ name: "login" });
    },
    goToCollection() {
      this.$router.push({ name: "browse" });
    },
    goToDeckBuilder() {
      this.$router.push({ name: "deck-builder" });
    },
    sendToHome() {
      this.$router.push({ name: 'home' });
    },
    logout() {
      this.$router.push({ name: "logout" });
    },
  },
};
</script>

<style>
#nav-bar {
  background-color: black;
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  padding: 10px;
  height: 40px;
}
.nav-button {
  margin: 0px 15px 0px 5px;
  background-color: black;
  color: white;
  border: 1px solid white;
  border-radius: 5px;
  width: 140px;
  height: 40px;
  font-family: "Marcellus";
  font-size: 18px;
  box-shadow: 4px 4px rgb(0, 0, 0, 0.5);
  transition: all 0.2s ease-in-out;
  text-shadow: 1px 2px rgb(51, 51, 51);
}
.nav-button:hover {
  transform: scale(1.03);
  cursor: pointer;
  box-shadow: 6px 6px rgb(0, 0, 0, 0.5);
  background-image: url("../assets/egg-shell.png"),
    linear-gradient(180deg, rgba(37, 122, 226, 0.8), rgba(0, 0, 105, 0.8));
}
</style>
