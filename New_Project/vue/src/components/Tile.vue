<template>
  <div class="tile"
  :style="{
        backgroundImage: `url(${require('@/assets/' + card.imageName)})`,
      }"
  >
    <div class="tile-header">
      <div class="tile-name">{{ card.name }}</div>
      <div class="tile-cost">
        {{ card.cost }} <font-awesome-icon
            :icon="['fa', 'coins']"
            class="icon"
            color="yellow"
          />
      </div>
    </div>

    <div class="tile-desc" :style="card.abilities ? {opacity: 1} : {opacity: 0}">
        <div id="abilities"  >
          {{ card.abilities }}
        </div>
    </div>

    <div class="tile-attr">
        <div class="attr">
          {{ card.attack }}
          <p id="attr-desc">Attack</p>
        </div>
        <div class="attr">
          {{ card.resilience }}
          <p id="attr-desc">Resilience</p>
        </div>
        <div class="attr">
          {{ card.loyalty }}
          <p id="attr-desc">Loyalty</p>
        </div>
    </div>    
  </div>
</template>

<script>
export default {
  name: "tile",
  props: ["card"],
  methods: {
    getImageURL(pic) {
      return require("../assets/" + pic);
    },
  },
};
</script>

<style>
.tile {
  flex-direction: column;
  border: 8px solid black;
  width: 210px;
  height: 158px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 8px 12px 8px 12px;
  border-radius: 15px;
  transition: all 0.2s ease-in-out;
  box-shadow: 2px 2px rgba(0, 0, 0, 0.5);
}
.tile:hover {
  box-shadow: 5px 5px rgba(0, 0, 0, 0.5);
  transform: scale(1.02);
  cursor: pointer;
}
.tile-header {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  height: 35px;
}
.tile-name {
  color: white;
  font-size: 14px;
  font-weight: 550;
  font-family: "Marcellus";
  padding: 4px 4px 0px 4px;
  border: 1px solid white;
  border-radius: 5px;
  background-image: url("../assets/egg-shell.png"),
    linear-gradient(60deg, rgba(3, 24, 49, 0.8), rgb(34, 34, 92, 0.8));
  text-align: left;
  height: 25px;
}
.tile-cost {
  color: white;
  padding: 3px;
  font-family: "Marcellus";
  font-size: 15px;
  font-weight: 600;
  border: 1px solid white;
  border-radius: 5px;
  background-image: url("../assets/egg-shell.png"),
    linear-gradient(60deg, rgba(3, 24, 49, 0.8), rgb(34, 34, 92, 0.8));
  text-align: right;
  height: 25px;
}
.tile-desc {
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  border: 1px solid rgb(160, 160, 160);
  border-radius: 5px;
  padding: 4px;
  height: auto;
  background-image: linear-gradient(
    60deg,
    rgba(82, 82, 82, 0.7),
    rgba(8, 17, 44, 0.7)
  );
  transition: all 0.2s ease-in-out;
}
.tile-desc:hover {
  transform: scale(1.4);
  background-image: linear-gradient(
    60deg,
    rgba(4, 7, 32, 0.938),
    rgba(9, 13, 44, 0.918)
  );
}
.tile-attr {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  width: 100%;
}
.attr {
  color: white;
  font-weight: 600;
  font-family: "Marcellus";
  font-size: 20px;
  width: 30%;
  text-align: center;
  background-image: linear-gradient(
    180deg,
    rgba(0, 0, 0, 0.3),
    rgba(155, 143, 143, 0.3)
  );
  transition: all 0.2s ease-in-out;
  border-radius: 5px;
}
.attr:hover {
  transform: scale(1.5);
  background-image: linear-gradient(
    60deg,
    rgb(0, 41, 24, 0.95),
    rgb(0, 110, 73, 0.95)
  );
}
</style>
