<template>
  <div class="chat-comp">
    <div class="left-bar">
      <div class="user-directory">
        <div class="user-directory-online">
          <h3 class="users-header">ONLINE ({{ updateOnlineUsers.length }})</h3>
          <div
            :class="{ itme: isItMe(username) }"
            class="users-online"
            v-for="username in updateOnlineUsers"
            :key="username"
            @click="selectUser(username)"
          >
            {{ username }}
          </div>
        </div>

        <div class="user-directory-online">
          <h3 class="users-header">IN GAME ({{ updateUsersInGame.length }})</h3>
          <div
            :class="{ itme: isItMe(username) }"
            class="users-online"
            v-for="username in updateUsersInGame"
            :key="username"
            @click="selectUser(username)"
          >
            {{ username }}
          </div>
        </div>

        <div class="user-directory-offline">
          <h3 class="users-header">
            OFFLINE ({{ updateOfflineUsers.length }})
          </h3>
          <div
            :class="{ itme: isItMe(username) }"
            class="users-offline"
            v-for="username in updateOfflineUsers"
            :key="username"
            @click="selectUser(username)"
          >
            {{ username }}
          </div>
        </div>
      </div>

      <button
        class="dc-btn"
        v-on:click="hideChat"
        v-if="this.selectedUser != ''"
      >
        HIDE MESSAGES
      </button>
      <button
        class="dc-btn"
        v-on:click="disconnectStomp"
        v-if="this.connected == true"
      >
        DISCONNECT
      </button>
      <button
        class="dc-btn"
        v-on:click="connect"
        v-if="this.connected == false"
      >
        CONNECT
      </button>
    </div>

    <div class="chat-window" v-if="selectedUser != ''">
      <div class="convo" v-chat-scroll="{ always: false, smooth: true }">
        <div class="convo-header">Chat with {{ this.selectedUser }}</div>
        <div class="display-messages">
          <chat-message
            v-for="message in fetchSelectedUserThread"
            :key="message.messageId"
            :message="message"
          />
        </div>
      </div>

      <div class="msg-bar" v-if="selectedUser != '' && this.selectedUser != './12./12./12./12.'">
        <chatTextInput v-model="localMessage.content" @send="sendToUser" />
        <button class="send-btn" @click="sendToUser">
          <font-awesome-icon :icon="['fa', 'paper-plane']" class="icon" />
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import ChatService from "../services/ChatService.js";
import ChatMessage from "@/components/ChatMessage.vue";
import ChatTextInput from "@/components/ChatTextInput.vue";

export default {
  name: "chat",
  components: {
    ChatMessage,
    ChatTextInput,
  },
  data() {
    return {
      localMessage: {
        fromUsername: this.$store.state.user.username,
        toUsername: "",
        content: "",
        timestamp: "",
      },
      sessionInfoMessage: {
        username: this.$store.state.user.username,
        sessionId: "",
        action: "",
      },
      connected: false,
      socket: null,
      stompClient: null,
      selectedUser: "",
      notify: false,
    };
  },
  computed: {
    fetchSelectedUserThread() {
      let arr = [];
      arr = this.$store.state.chatLog.threads;
      for (var i = 0; i < arr.length; i++) {
        if (
          arr[i].chatWith === this.selectedUser &&
          arr[i].ownerUsername === this.$store.state.user.username
        ) {
          return arr[i].messages;
        }
      }
      return null;
    },
    updateOnlineUsers() {
      let onlineUsers = [];
      for (var i = 0; i < this.$store.state.users.length; i++) {
        if (this.$store.state.users[i].status == "online") {
          onlineUsers.push(this.$store.state.users[i].username);
        }
      }
      return onlineUsers;
    },
    updateOfflineUsers() {
      let offlineUsers = [];
      for (var i = 0; i < this.$store.state.users.length; i++) {
        if (this.$store.state.users[i].status == "offline") {
          offlineUsers.push(this.$store.state.users[i].username);
        }
      }
      return offlineUsers;
    },
    updateUsersInGame() {
      let usersInGame = [];
      for (var i = 0; i < this.$store.state.users.length; i++) {
        if (
          this.$store.state.users[i].status === "ready" ||
          this.$store.state.users[i].status === "ingame"
        ) {
          usersInGame.push(this.$store.state.users[i].username);
        }
      }
      return usersInGame;
    },
  },

  methods: {
    selectUser(user) {
      this.selectedUser = "";
      this.selectedUser = user;
      if (this.selectedUser === this.$store.state.user.username) {
        this.selectedUser = "";
      }
    },
    hideChat() {
      this.selectedUser = "";
    },
    isItMe(username) {
      if (username === this.$store.state.user.username) {
        return true;
      } else return false;
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
    sendToUser() {
      this.localMessage.toUsername = this.selectedUser;
      if (
        this.$store.state.stompClient &&
        this.connected &&
        this.localMessage.content != "" &&
        this.localMessage.toUsername != ""
      ) {
        this.$store.state.stompClient.send(
          "/app/game/" + this.localMessage.toUsername,
          JSON.stringify(this.localMessage),
          {}
        );
        this.localMessage.content = "";
        this.localMessage.toUsername = "";
        setTimeout(() => {
          ChatService.getMyChatLog(this.$store.state.user.username)
            .then((response) => {
              if (response.status == 200) {
                this.$store.commit("SET_CHAT_LOG", response.data);
              }
            })
            .catch((error) => {
              const response = error.response;
              console.log(response);
            });
        }, 500);
      }
    },
    connect() {
      this.$store.state.socket = new SockJS("http://localhost:8080/game");
      this.$store.state.stompClient = Stomp.over(this.$store.state.socket);
      this.$store.state.stompClient.connect(
        {
          token: this.$store.state.token,
          username: this.$store.state.user.username,
        },
        () => {
          this.connected = true;
          this.sessionInfoMessage.sessionId = this.$store.state.socket._transport.url.substring(
            29,
            37
          );
          this.$store.commit(
            "SET_SESSION_ID",
            this.sessionInfoMessage.sessionId
          );
          this.$store.state.stompClient.subscribe(
            "/topic/game",
            (tick) => {
              let users = JSON.parse(tick.body);
              this.$store.commit("USER_ONLINE_EVENT", users);
            },
            {}
          );
          this.sessionInfoMessage.action = "login";
          this.sendSessionInfoMessage();

          this.$store.state.stompClient.subscribe(
            "/topic/messages/" + this.$store.state.user.username,
            () => {
              setTimeout(() => {
                ChatService.getMyChatLog(this.$store.state.user.username)
                  .then((response) => {
                    if (response.status == 200) {
                      this.$store.commit("SET_CHAT_LOG", response.data);
                    }
                  })
                  .catch((error) => {
                    const response = error.response;
                    console.log(response);
                  });
              }, 1000);
            }
          );
        },
        (error) => {
          console.log(error);
          this.connected = false;
        }
      );
    },
    disconnectStomp() {
      if (this.$store.state.stompClient) {
        this.connected = false;
        this.selectedUser = "";
        this.sessionInfoMessage.action = "logoff";
        this.sendSessionInfoMessage();
        this.$store.commit("CLEAR_SESSION_ID");
        setTimeout(() => {
          this.$store.state.stompClient.disconnect();
        }, 1000);
      }
    },
    tickleConnection() {
      this.connected ? this.disconnectStomp() : this.connect();
    },
    leaving() {
      this.sessionInfoMessage.action = "logoff";
      this.sendSessionInfoMessage();
    },
  },
  mounted() {
    ChatService.getMyChatLog(this.$store.state.user.username)
      .then((response) => {
        if (response.status == 200) {
          this.$store.commit("SET_CHAT_LOG", response.data);
        }
      })
      .catch((error) => {
        const response = error.response;
        console.log(response);
      });
    this.connect();
    window.addEventListener("beforeunload", this.leaving);
  },
};
</script>

<style scoped>
.icon {
  font-size: 23px;
}
.display-messages {
  display: flex;
  flex-direction: column;
  margin-top: 35px;
  overflow-y: auto;
}
.chat-comp {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  border-radius: 10px;
  width: auto;
  height: 650px;
  position: fixed;
  margin-top: 60px;
}
.left-bar {
  display: flex;
  flex-direction: column;
  height: 805px;
  border-right: 1px solid rgb(105, 105, 105);
  background-color: rgba(0, 0, 0, 0.87);
}
.user-directory {
  display: flex;
  flex-direction: column;
  justify-content: left;
  align-content: center;
  height: 805px;
  width: 150px;
}
.user-directory-online {
  display: flex;
  flex-direction: column;
  justify-content: left;
  align-content: center;
  height: 225px;
  overflow-y: auto;
}
.user-directory-offline {
  display: flex;
  flex-direction: column;
  justify-content: left;
  align-content: center;
  height: 225px;
  overflow-y: auto;
}
.chat-window {
  display: flex;
  flex-direction: column;
  width: 550px;
  height: 550px;
}
.convo {
  background-color: rgba(0, 0, 0, 0.87);
  height: 550px;
  overflow-y: auto;
}
.convo-header {
  font-family: "Marcellus";
  color: rgb(255, 255, 255);
  font-size: 15px;
  width: auto;
  text-align: center;
  font-weight: 600;
  padding: 7px 40px 7px 40px;
  background-color: rgb(16, 33, 53);
  border-bottom: 1px solid rgb(14, 41, 71);
  box-shadow: 1px 1px rgb(156, 156, 156);
  border-bottom-right-radius: 15px;
  position: fixed;
}
.msg-bar {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  background-color: rgba(0, 0, 0, 0.658);
  height: 100px;
  border-bottom-right-radius: 10px;
}
.send-btn {
  color: white;
  height: 60%;
  width: 20%;
  background-color: rgb(0, 194, 81);
  transition: all 0.2s ease-in-out;
  align-items: center;
  justify-content: center;
  margin-right: 13px;
  border-radius: 5px;
}
.send-btn:hover {
  cursor: pointer;
}
.dc-btn {
  color: white;
  font-family: "Marcellus";
  font-size: 15px;
  width: 120px;
  height: 40px;
  margin: 5px 0px 6px 0px;
  background-color: rgb(56, 56, 56);
  transition: all 0.2s ease-in-out;
  display: flex;
  align-items: center;
  justify-content: center;
  align-self: center;
  border-radius: 5px;
}
.dc-btn:hover {
  background-color: crimson;
  cursor: pointer;
}
.users-header {
  font-family: "Marcellus";
  font-weight: 600;
  font-size: 15px;
  text-align: center;
  margin: 0px;
  padding: 7px;
  background-color: rgb(56, 56, 56);
  color: rgb(255, 255, 255);
  border-bottom: 1px solid rgb(59, 59, 59);
}
.users-online {
  font-family: "Marcellus";
  font-size: 17px;
  color: rgb(255, 255, 255);
  font-weight: 600;
  padding: 4px 0px 0px 4px;
}
.users-online:hover {
  color: rgb(88, 155, 255);
  cursor: pointer;
}
.itme {
  color: lime;
  font-family: "Marcellus";
  font-size: 17px;
}
.itme:hover {
  cursor: pointer;
}
.users-offline {
  font-family: "Marcellus";
  font-size: 16px;
  color: grey;
  font-weight: 500;
  font-style: italic;
  padding: 4px 0px 0px 4px;
}
.users-offline:hover {
  color: rgb(88, 155, 255);
  cursor: pointer;
}
.invitations {
  font-family: "Marcellus";
  font-weight: 600;
  font-size: 15px;
  text-align: center;
  margin: 0px;
  padding: 7px;
  background-color: rgb(56, 56, 56);
  color: rgb(255, 255, 255);
  border-bottom: 1px solid rgb(59, 59, 59);
}
.invitations:hover {
  color:rgb(0, 194, 81);
  cursor: pointer;
}
</style>
