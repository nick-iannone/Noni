<template>
  <div id="home">
    <hr class="psuedo-header" v-if="this.$store.state.token === ''" />
    <div class="home-header">
      <div class="glow">CARD GAME</div>
    </div>
    <button
      @click.prevent="goFindMatch"
      class="play-button"
      v-if="this.$store.state.token != ''"
    >
      PLAY
    </button>
    <button
      @click.prevent="goToLogin"
      class="nav-button"
      v-if="this.$store.state.token === ''"
    >
      LOGIN
    </button>
  </div>
</template>

<script>
export default {
  name: "home",
  methods: {
    goToLogin() {
      this.$router.push({ name: "login" });
    },
    goFindMatch() {
        this.$router.push({ name: 'find-match' });
    }
  },
};
</script>

<style>
#home {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  background-image: url("../assets/home.jpg");
  height: 1004px;
  width: 100%;
}
.home-nav {
  display: flex;
  flex-direction: row;
}

.home-header {
  font-size: 19vw;
  font-family: "Germania One";
  text-align: center;
  background-image: url("../assets/egg-shell.png"),
    linear-gradient(
      to bottom,
      rgba(146, 78, 0, 0.95),
      rgba(255, 232, 102, 1),
      rgba(255, 255, 255, 1)
    );
  /* background-size: cover; */
  color: transparent;
  font-weight: 600;
  background-clip: text;
  -webkit-background-clip: text;
  padding: 50px;
}
.psuedo-header {
  min-height: 50px;
  background-color: black;
  width: 100%;
  border: black;
  margin-top: 0px;
}
.play-button {
  width: 200px;
  height: 80px;
  font-size: 40px;
  font-family: "Germania One";
  color: white;
  background-color: black;
  border: 1px solid white;
  border-radius: 10px;
  box-shadow: 4px 4px rgba(0, 0, 0, 0.445);
  transition: all 0.2s ease-in-out;
}
.play-button:hover {
  cursor: pointer;
  transform: scale(1.1);
  box-shadow: 6px 6px rgba(0, 0, 0, 0.445);
  background-image: url("../assets/egg-shell.png"),
    linear-gradient(180deg, rgba(37, 122, 226, 0.8), rgba(0, 0, 105, 0.8));
}
</style>
