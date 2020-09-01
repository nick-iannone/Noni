import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import Collections from '../views/Collections.vue'
import DetailsCollection from '../views/DetailsCollection.vue'
import NewCollection from '../views/NewCollection.vue'
import MyCollection from '../views/MyCollection.vue'
import MakeTrade from '../views/MakeTrade.vue'
import Review from '../views/Review.vue'
import Search from '../views/Search.vue'
import Results from '../views/Results.vue'
import Wishlist from '../views/Wishlist.vue'


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
        requiresAuth: false
      }
    },
    {
      path: '/collections',
      name: 'collections',
      component: Collections,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/collections/:id',
      name: 'details-collection',
      component: DetailsCollection,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/mycollections/:username',
      name: 'my-collections',
      component: MyCollection,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/collections/new',
      name: 'new-collection',
      component: NewCollection,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/trades',
      name: 'make-trade',
      component: MakeTrade,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/trades/:id',
      name: 'review',
      component: Review,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/wishlist/:id',
      name: 'wishlist',
      component: Wishlist,
      meta: {
        requiresAuth: true
      }
    },
    {
      path:'/search',
      name: 'search',
      component: Search,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/search/volumes/:id',
      name: 'results',
      component: Results,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
