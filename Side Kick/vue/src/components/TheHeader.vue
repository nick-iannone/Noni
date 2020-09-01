<template>
  <div id="nav">
      <div v-if="$store.state.token != ''" class='active-user'>Welcome, {{$store.state.user.username}} </div>
      <div class="nav-button"><a><router-link tag='a' v-bind:to="{ name: 'home' }">Home</router-link></a></div>
      <div id="dropdown-nav">
          <a class='drop-a'>Collections</a>
          <div class='dropdown-content'>
            <a><router-link tag='a' v-if="$store.state.token != ''" v-bind:to="{ name: 'my-collections', params: {username: $store.state.user.username}}">My Collections</router-link></a>
            <a><router-link tag='a' v-bind:to="{ name: 'collections'}">Public Collections</router-link></a>
            <a><router-link tag='a' v-bind:to="{ name: 'wishlist', params: {id: $store.state.user.id}}">Wishlist</router-link></a>
          </div>
      </div>
      <div class="nav-button"><a><router-link tag='a' v-bind:to="{ name: 'search' }">Comic Search</router-link></a></div>
      <div id="dropdown-nav">
          <a class='drop-a'>Trades</a>
          <div class='dropdown-content'>
            <a><router-link tag='a' v-bind:to="{ name: 'make-trade'}">Make Trade</router-link></a>
            <a><router-link tag='a' v-bind:to="{ name: 'review', params: {id: this.$store.state.user.id}}">Review Trade Offers</router-link></a>
          </div>
      </div>
      <div class="nav-button" v-if="$store.state.token === ''"><a><router-link tag='a' v-bind:to="{ name: 'login'}" >Login</router-link></a></div>
      <div class="nav-button" v-if="$store.state.token != ''"><a><router-link tag='a' v-bind:to="{ name: 'logout' }" >Logout</router-link></a></div>
  </div>
</template>

<script>
export default {
    name: 'the-header'
}
</script>

<style>
#nav {
  background-color: black;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: flex-end;
  padding: 20px;
  border: 1px solid black;
}
.drop-a {
  color: white;
  padding: 16px;
  border: none;
}
#dropdown-nav {
  position: relative;
  display: inline-block;
}
.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f1f1f1;
  min-width: 265px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
  margin-top: 15px;
  border: 5px solid black;
}
.dropdown-content a:hover {
  background-color: #ddd;
}
#dropdown-nav:hover .dropdown-content {
  display: block;
}
#dropdown-nav:hover .dropbtn {
  background-color: #3e8e41;
}
.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}
.active-user {
  color: white;
  text-decoration: none;
  font-family: 'Miriam Libre';
  justify-content: center;
  font-size: 20px;
  position: absolute;
  top: 1;
  left: 0;
  margin-left: 50px;
}

.nav-button {
    margin-right: 30px;
    margin-left: 30px;
}

a {
  color: white;
  text-decoration: none;
  font-family: 'Bangers';
  justify-content: center;
  font-size: 30px;
}

a:hover {
  color: rgb(20, 130, 220);
  cursor: url('../assets/gauntlet.png'), pointer;
}
</style>