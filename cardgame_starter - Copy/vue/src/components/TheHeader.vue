<template>
    <div id="nav-bar">
        <div class="nav-link"><a><router-link tag='a' v-bind:to="{ name: 'home' }">Home</router-link></a></div>
        <div id="dropdown-nav">
          <a class='drop-a'>Characters</a>
          <div class='dropdown-content'>
            <a><router-link tag='a' v-bind:to="{}">New Character</router-link></a>
            <a><router-link tag='a' v-bind:to="{}">Saved Characters</router-link></a>
            <a><router-link tag='a' v-bind:to="{}">Public Characters</router-link></a>
          </div>
        </div>
        <div id="dropdown-nav">
          <a class='drop-a'>Encounters</a>
          <div class='dropdown-content'>
            <a><router-link tag='a' v-bind:to="{}">New Encounter</router-link></a>
            <a><router-link tag='a' v-bind:to="{}">Saved Encounters</router-link></a>
            <a><router-link tag='a' v-bind:to="{}">Community</router-link></a>
          </div>
        </div>
        <div class="nav-link"><a><router-link tag='a' v-bind:to="{}">Guide</router-link></a></div>
        <div class="nav-link"><a><router-link tag='a' v-bind:to="{}">About</router-link></a></div>
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
    justify-content: space-evenly;
    padding: 20px;
}
.nav-link {
    
    margin: 0px 30px 0px 20px;
}
a {
    font-family: 'Germania One';
    font-size: 30px;
    color: rgb(254, 255, 206);
    text-decoration: none;
}
a:hover {
    color: rgb(143, 0, 0);
    cursor: pointer;
}
#dropdown-nav {
  position: relative;
  display: inline-block;
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
  background-color: rgb(143, 0, 0);
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
</style>