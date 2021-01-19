<template>
  <div class="home">
    <div class="home-header">
      <div class='glow'>REGEN</div>
    </div>
    <div>  
      <p class="home-welcome">Random Encounter Generator</p>
    </div>  
  </div>
</template>

<script>
export default {
  name: "home",
};
</script>

<style>
.home {
  background-image: url('../assets/mouVTa.jpg');
  margin-top: 0px;
}
.home-header {
  font-size: 15vw;
  font-family: 'Germania One';
  text-align: center;
  background-image: linear-gradient(to bottom, rgba(0, 0, 0, 0.95), rgba(255, 232, 102, 1), rgba(255, 255, 255, 1));
	background-size: cover;
	color: transparent;
  font-weight: 400;
	background-clip: text;
	-webkit-background-clip: text;
  padding: 100px;
}
.home-welcome {
  font-family: 'Germania One';
  color: white;
  text-align: center;
  padding-bottom: 80px;
  font-size: 30px;

}
/* .glow {
  color: rgb(0, 0, 0);
  text-align: center;
  -webkit-animation: glow 1s ease-in-out infinite alternate;
  -moz-animation: glow 1s ease-in-out infinite alternate;
  animation: glow 1s ease-in-out infinite alternate;
}

@-webkit-keyframes glow {
  from {
    text-shadow: 0 0 10px rgba(241, 241, 241, 0.788), 0 0 10px rgba(255, 255, 255, 0.801), 0 0 10px #feffaa, 0 0 10px #fcc26c, 0 0 10px #ff6f36, 0 0 10px #ff4545, 0 0 10px #ffd16d;
  }
  to {
    text-shadow: 0 0 20px #fff, 0 0 10px #740101, 0 0 10px #ff0000, 0 0 10px #ff0000, 0 0 10px #ff0000, 0 0 10px #ff0000, 0 0 10px #b81803;
  }
} */
</style>