import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";

Vue.use(Vuex);

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem("token");
const currentUser = JSON.parse(localStorage.getItem("user"));

if (currentToken != null) {
  axios.defaults.headers.common["Authorization"] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    game: {
      isOver: false,
      myGame: { 
        username: '',
        readyForBattle: false,
        keepHand: false,
        isTurn: false,
        turnCount: 0,
        turn: {
          begin: false,
          plan: false,
          combat: false,
          plan2: false,
          end: false,
        },
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
              cards: []
          },
          contracts: {
              available: [],
              active: [],
              completed: [],
              deck: [],
              mods: []
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
          }
        },
      },
      oppGame: { 
        username: '',
        readyForBattle: false,
        keepHand: false,
        isTurn: false,
        turnCount: 0,
        turn: {
          begin: false,
          plan: false,
          combat: false,
          plan2: false,
          end: false,
        },
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
              cards: []
          },
          contracts: {
              available: [],
              active: [],
              completed: [],
              deck: [],
              mods: []
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
          }
        },
      },
    },
    token: currentToken || "",
    user: currentUser || {},
    sessionId: '',
    allUsers: [],
    users: [],
    chatLog: {},
    socket: null,
    stompClient: null,
    matchmakingSubscription: null,
    gameSessionSubscription: null,
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem("token", token);
      axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem("user", JSON.stringify(user));
    },
    SET_GAME_USERNAME(state, username) {
      state.game.myGame.username = username;
    },
    SET_MYGAME_RFB(state, boolean) {
      state.game.myGame.readyForBattle = boolean;
    },
    SET_OPPGAME_RFB(state, boolean) {
      state.game.oppGame.readyForBattle = boolean;
    },
    SET_SESSION_ID (state, sessionId) {
      state.sessionId = sessionId;
    },
    CLEAR_SESSION_ID(state) {
      state.sessionId = '';
    },
    LOGOUT(state) {
      localStorage.removeItem("token");
      localStorage.removeItem("user");
      state.token = "";
      state.user = {};
      axios.defaults.headers.common = {};
    },
    SET_ALL_USERS(state, allUsers) {
      state.allUsers = allUsers;
    },
    USER_ONLINE_EVENT(state, users) {
      state.users = users;
    },
    SET_CHAT_LOG(state, chatLog) {
      let threads = chatLog.threads
      for (var i = 0; i < threads.length; i ++) {
        let messages = threads[i].messages;
        for (var n = 0; n < messages.length; n++) {
          if ((messages[n].fromUsername !== threads[i].chatWith) && (messages[n].toUsername !== threads[i].chatWith)) {
            messages.splice(n, 1);
            n--;
          }
        }
      }
      state.chatLog = chatLog;
    },
    SET_MATCHMAKING_SUBSCRIPTION(state, stompSub) {
      state.matchmakingSubscription = stompSub;
    },
    SET_GAME_SESSION_SUBSCRIPTION(state, stompSub) {
      state.gameSessionSubscription = stompSub;
    },
    SET_OPPONENT(state, username) {
      state.game.oppGame.username = username;
    },
    UPDATE_OPP_GAME_STATE(state, oppGameState) {
      state.game.oppGame = oppGameState;
    },
    UPDATE_MY_GAME_STATE(state, myGameState) {
      state.game.myGame = myGameState;
    }
  },
});
