<template>
  <div
    class="dead-tile"
  >
      <div class="dead-tile-header">
        <div class="dead-tile-name">{{ card.name }}</div>
      </div>
  </div>
</template>

<script>
export default {
  name: "dead-tile",
  props: ["card"],
  methods: {
    getImageURL(pic) {
      return require("../assets/" + pic);
    },
  },
};
</script>

<style>
.dead-tile {
  flex-direction: column;
  border: 2px solid black;
  width: 200px;
  height: 50px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 15px;
  background-size: 300px 300px;
  margin: 10px;
  transition: all 0.2s ease-in-out;
  box-shadow: 2px 2px rgba(0, 0, 0, 0.5);
  background-color: rgb(34, 34, 34);
}
.dead-tile:hover {
  box-shadow: 5px 5px rgba(0, 0, 0, 0.5);
  transform:scale(1.01);
  cursor: pointer;
}
.dead-tile-header {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 35px;
  padding: 5px;
}
.dead-tile-name {
  color: white;
  font-size: 15px;
  font-weight: 550;
  font-family: "Marcellus";
  padding: 3px 13px 3px 13px;
  border: 1px solid white;
  border-radius: 5px;
  background-image: url("../assets/egg-shell.png"),
    linear-gradient(60deg, rgb(119, 119, 119), rgb(34, 34, 34));
  text-align: left;
  height: 27px;
}
</style>
