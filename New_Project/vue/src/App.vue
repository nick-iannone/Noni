<template>
  <div id="app">
      <the-header v-if="(this.$route.name === 'home' || this.$route.name === 'browse' || this.$route.name === 'deck-builder' || this.$route.name === 'find-match') && this.$store.state.token != ''"/>
      <router-view />
  </div>
</template>

<script>
import TheHeader from '@/components/TheHeader.vue'

export default {
  components: {
    TheHeader
  },
  data() {
    return {
      sessionInfoMessage: {
        username: this.$store.state.user.username,
        sessionId: "",
        action: "logoff",
      },
    }
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
    leaving() {
      this.sendSessionInfoMessage();
    },
  },
  mounted() {
    window.addEventListener("beforeunload", this.leaving);
  }
}
</script>

<style>
#app {
    overflow: hidden;
}
</style>