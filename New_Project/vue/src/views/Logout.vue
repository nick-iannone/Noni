<template>
  <h1>Logout</h1>
</template>

<script>
export default {
  data() {
    return {
      sessionInfoMessage: {
        username: this.$store.state.user.username,
        sessionId: "",
        action: "",
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
  },
  created() {
      if (this.$store.state.stompClient) {
        this.sessionInfoMessage.action = "logoff";
        this.sendSessionInfoMessage();
        setTimeout(() => {
          this.$store.state.stompClient.disconnect();
        }, 1000);
      }
    this.$store.commit("LOGOUT");
    this.$router.push("/login");
  }
};
</script>
