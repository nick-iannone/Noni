import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import About from '../views/About.vue'
import Create from '@/views/Create.vue'
import Saved from '@/views/Saved.vue'
import Encounter from '@/views/Encounter.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    component: About
  },
  {
    path: '/create',
    name: 'character-creator',
    component: Create
  },
  {
    path: '/saved',
    name: 'saved-characters',
    component: Saved
  },
  {
    path: '/encounter',
    name: 'encounter-builder',
    component: Encounter
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
