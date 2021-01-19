import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import Arena from '../views/Arena.vue'
import Browse from '../views/Browse.vue'
import MyDecks from '../views/MyDecks.vue'
import DeckBuilder from '../views/DeckBuilder.vue'
import FindMatch from '../views/FindMatch.vue'
import GameSetup from '../views/GameSetup.vue'

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: false,
        requiresOpponent: false,
      }
    },
    {
      path: '/arena',
      name: 'arena',
      component: Arena,
      meta: {
        requiresAuth: true,
        requiresOpponent: false,
      }
    },
    {
      path: '/matchmaking',
      name: 'find-match',
      component: FindMatch,
      meta: {
        requiresAuth: true,
        requiresOpponent: false,
      }
    },
    {
      path: '/setup',
      name: 'game-setup',
      component: GameSetup,
      meta: {
        requiresAuth: true,
        requiresOpponent: false,
      }
    },
    {
      path: '/browse',
      name: 'browse',
      component: Browse,
      meta: {
        requiresAuth: true,
        requiresOpponent: false,
      }
    },
    {
      path: '/mydecks',
      name: 'my-decks',
      component: MyDecks,
      meta: {
        requiresAuth: true,
        requiresOpponent: false,
      }
    },
    {
      path: '/deckbuilder',
      name: 'deck-builder',
      component: DeckBuilder,
      meta: {
        requiresAuth: true,
        requiresOpponent: false,
      }
    },        
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false,
        requiresOpponent: false,
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false,
        requiresOpponent: false,
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false,
        requiresOpponent: false,
      }
    },
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);
  const requiresOpponent = to.matched.some(x => x.meta.requiresOpponent);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  //check that they are matched with an opponent  
  } else if (requiresOpponent && store.state.game.oppGame.username === '') {
    next("/home");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
