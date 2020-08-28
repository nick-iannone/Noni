<template>
  <div class="home">
    <div class="home-header">
      <div>REGEN</div>
    </div>
    <div>  
      <p class="home-welcome">Random Encounter Generator</p>
    </div>  
  </div>
</template>

<script>
export default {
  name: "home"
};
</script>

<style>
.home {
  background-image: url('../assets/mouVTa.jpg');
  margin-top: 0px;
}
.home-header {
  font-size: 20vw;
  font-family: 'Germania One';
  text-align: center;
  background-image: linear-gradient(to bottom, rgba(0, 0, 0, 0.8), rgba(255, 232, 102, 1), rgba(255, 255, 255, 1));
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


</style>