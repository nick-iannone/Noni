<template>
  <div id="app">
    <div id="nav">
      <a><router-link to="/" class="nav-link">Home</router-link></a>
      <a><router-link to="/create" class="nav-link">Create New Character</router-link></a>
      <a><router-link to="/saved" class="nav-link">View Saved Characters</router-link></a>
      <a><router-link to="/encounter" class="nav-link">Encounter Builder</router-link></a>
      <a><router-link to="/about" class="nav-link">About</router-link></a>
    </div>
    <router-view/>
  </div>
</template>

<style>
#app {
  font-family: 'Germania';
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 5px;
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;
  justify-content: space-around;
  color: white;
  background-color: #2c2c2c;
  border-radius: 10px;
  margin-bottom: 7px;
  position: fixed;
  top: 0;
  width: 100%;
  text-decoration: none;
}

#nav > .nav-link{
    margin: 5px;
    padding: 20px;
    border: solid 1px rgb(0, 0, 0);
    border-radius: 5px;
    background-color: #616161;
    font-family: 'Germania One';
    font-size: 20px;
}

#nav > .nav-link:hover {
    margin: 5px;
    padding: 20px;
    border: solid 1px white;
    background-color: #5a0e0e;
    border-radius: 5px;
    cursor: pointer;
}
</style>
