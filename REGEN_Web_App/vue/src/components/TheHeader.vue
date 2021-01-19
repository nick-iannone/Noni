<template>
    <div id="nav-bar">
      <div class='user-banner'>
        <font-awesome-icon class='d20-icon' :icon="['fa', 'dice-d20']"/>
        <div class="nav-link" v-if="$store.state.token == ''"><a><router-link tag='a' :to="{ name: 'login' }">Login</router-link></a></div>
        <div class="user-welcome" v-if="$store.state.token != ''">{{'Welcome back, ' + $store.state.user.username}}</div>
      </div>
      <div class="nav-link"><a><router-link tag='a' :to="{ name: 'main-menu' }">Home</router-link></a></div>
      <div id="dropdown-nav" v-if="$store.state.token != ''">
        <a class='drop-a'>Characters</a>
        <div class='dropdown-content'>
          <a><router-link tag='a' v-bind:to="{name: 'newchar'}">New Character</router-link></a>
          <a><router-link tag='a' v-bind:to="{}">Saved Characters</router-link></a>
          <a><router-link tag='a' v-bind:to="{}">Public Characters</router-link></a>
        </div>
      </div>
      <div id="dropdown-nav" v-if="$store.state.token != ''">
        <a class='drop-a'>Encounters</a>
          <div class='dropdown-content'>
            <a><router-link tag='a' v-bind:to="{}">New Encounter</router-link></a>
            <a><router-link tag='a' v-bind:to="{}">Saved Encounters</router-link></a>
            <a><router-link tag='a' v-bind:to="{}">Community</router-link></a>
          </div>
        </div>
      <div class="nav-link"><a><router-link tag='a' v-bind:to="{}">Guide</router-link></a></div>
      <div class="nav-link"><a><router-link tag='a' v-bind:to="{}">About</router-link></a></div>
      <div class="nav-link" v-if="$store.state.token != ''"><a><router-link tag='a' v-bind:to="{ name: 'logout' }">Logout</router-link></a></div>
    </div>
</template>

<script>
export default {
    name: 'the-header'
}
</script>

<style>
#nav-bar {
    background-color: black;
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    justify-content: flex-end;
    padding: 20px;
}
.nav-link {
    margin: 0px 30px 0px 30px;
}
a {
    font-family: 'Germania One';
    font-size: 30px;
    color: rgb(254, 255, 206);
    text-decoration: none;
}
a:hover {
    color: rgb(152, 179, 228);
    cursor: pointer;
}
#dropdown-nav {
  position: relative;
  display: inline-block;
  margin: 0px 30px 0px 30px;
}
.dropdown-content {
  display: none;
  position: absolute;
  background-color: #313131;
  min-width: 300px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
  border-top: 20px solid black;
  border-bottom: 2px solid black;
  border-right: 2px solid black;
  border-left: 2px solid black;
  border-radius: 10px;
}
.dropdown-content a:hover {
  background-color: rgb(105, 127, 168);
  border-radius: 10px;
}
#dropdown-nav:hover .dropdown-content {
  display: block;
}
.dropdown-content a {
  color: rgb(254, 255, 206);
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}
.d20-icon {
  color: rgb(254, 255, 206);
  font-size: 35px;
}
.user-banner {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  position: absolute;
  left: 0;
  margin-left: 40px;
}
.user-welcome {
    font-family: 'Bellefair';
    font-size: 25px;
    color: rgb(254, 255, 206);
    text-decoration: none;
    padding-left: 20px;
    margin: 0px;
}
</style>