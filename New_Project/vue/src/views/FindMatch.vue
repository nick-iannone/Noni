<template>
  <div class="find-match">
    <div class="matchmaking">

      <div class="active-users">
        <div class="mm-header-online">ONLINE USERS</div>
        <mmtile
          v-for="user in updateOnlineUsers"
          :key="user.username"
          :user="user"
        />
      </div>

      <div class="ready-users">
        <div class="mm-header-ready">READY TO PLAY</div>
        <prtile
          v-for="user in filterReadyUsers"
          :user="user"
          :key="user.username"
        />
      </div>

    </div>
  </div>
</template>

<script>
import mmtile from "@/components/MatchmakingTile.vue";
import prtile from "@/components/PlayerReadyTile.vue";

export default {
  components: {
    mmtile,
    prtile,
  },
  computed: {
    updateOnlineUsers() {
      let onlineUsers = [];
      for (var i = 0; i < this.$store.state.users.length; i++) {
        if (this.$store.state.users[i].status === "online") {
          onlineUsers.push(this.$store.state.users[i]);
        }
      }
      return onlineUsers;
    },
    filterReadyUsers() {
      let readyUsers = [];
      for (var i = 0; i < this.$store.state.users.length; i++) {
        if (this.$store.state.users[i].status === "ready" && this.$store.state.users[i].username !== this.$store.state.user.username) {
          readyUsers.push(this.$store.state.users[i]);
        }
      }
      return readyUsers;
    },
  },
  methods: {
    // sendInvite(invite) {},
  },
};
</script>

<style>
.find-match {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-image: url("../assets/home.jpg");
  height: 1004px;
  width: 100%;
}
.matchmaking {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  width: 1200px;
  height: 800px;
  margin: 0px;
}
.active-users {
  display: flex;
  flex-direction: column;
  gap: 10px;
  padding: 20px;
  border: 1px solid white;
  border-radius: 15px;
  background-color: rgba(0, 0, 0, 0.7);
  width: 350px;
  overflow-y: auto;
}
.ready-users {
  padding: 20px;
  border: 1px solid white;
  border-radius: 15px;
  background-color: rgba(0, 0, 0, 0.7);
  width: 350px;
}
.mm-header-online {
  color: rgb(98, 98, 235);
  font-size: 45px;
  font-family: "Germania One";
  text-align: center;
  margin-bottom: 10px;
}
.mm-header-ready {
  color: rgb(0, 156, 78);
  font-size: 45px;
  font-family: "Germania One";
  text-align: center;
  margin-bottom: 10px;
}
</style>
