<template>
  <div class="card">
    <div class="card-header">
      <div class="card-name">{{ card.name }}</div>
      <div class="card-cost">
        {{ card.cost }} <font-awesome-icon
            :icon="['fa', 'coins']"
            class="icon"
            color="yellow"
          />
      </div>
    </div>
    <div
      class="image-cont"
      :style="{
        backgroundImage: `url(${require('@/assets/' + card.imageName)})`,
      }"
    >
      <div class="card-desc" v-if="this.card.abilities != null">
        <div id="abilities" >
          {{ card.abilities }}
        </div>
        <div id="flavor">
          {{ card.flavor }}
        </div>
      </div>

      <div class="card-attr">
        <div class="attribute">
          {{ card.attack }}
          <p id="attr-desc">Attack</p>
        </div>
        <div class="attribute">
          {{ card.resilience }}
          <p id="attr-desc">Resilience</p>
        </div>
        <div class="attribute">
          {{ card.loyalty }}
          <p id="attr-desc">Loyalty</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "card",
  props: ["card"],
  methods: {
    getImageURL(pic) {
      return require("../assets/" + pic);
    },
  },
};
</script>

<style>
.card {
  display: flex;
  flex-direction: column;
  border: 10px solid black;
  height: 350px;
  width: 250px;
  align-items: center;
  justify-content: center;
  padding: 8px 12px 8px 12px;
  border-radius: 15px;
  background-image: url('../assets/soft-wallpaper.png'), linear-gradient(rgb(2, 0, 27), rgb(2, 0, 27));
  transition: all 0.2s ease-in-out;
  box-shadow: 2px 2px rgba(0, 0, 0, 0.5);
}
.card:hover {
  box-shadow: 5px 5px rgba(0, 0, 0, 0.5);
  transform: scale(1.03);
  cursor: pointer;
}
.card-header {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  height: 30px;
}
.card-name {
  color: white;
  font-size: 15px;
  font-weight: 550;
  font-family: "Marcellus";
  padding: 3px 13px 3px 13px;
  border: 1px solid white;
  border-radius: 5px;
  background-image: linear-gradient(60deg, rgb(0, 0, 0), rgb(54, 54, 54));
  text-align: left;
  box-shadow: 4px 4px rgba(0, 0, 0, 0.3);
  height: 20px;
}
.card-cost {
  color: white;
  padding: 3px;
  font-family: "Marcellus";
  font-size: 15px;
  font-weight: 600;
  border: 1px solid white;
  border-radius: 5px;
  background-image:
    linear-gradient(60deg, rgb(0, 0, 0), rgb(54, 54, 54));
  text-align: right;
  box-shadow: 4px 4px rgba(0, 0, 0, 0.3);
  height: 20px;
}
.coin {
  height: 12px;
  margin-top: 3px;
  margin-left: 5px;
}
.image-cont {
  border: 1px solid white;
  border-radius: 5px;
  margin: 5px 0px 0px 0px;
  height: 300px;
  width: 100%;
  background-position: center;
  background-size: 250px 300px;
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
}
.card-desc {
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  border: 1px solid rgb(160, 160, 160);
  border-radius: 5px;
  padding: 4px;
  height: auto;
  background-image: linear-gradient(
    60deg,
    rgba(82, 82, 82, 0.568),
    rgba(8, 17, 44, 0.61)
  );
  transition: all 0.2s ease-in-out;
}
.card-desc:hover {
  transform: scale(1.4);
  background-image: linear-gradient(
    60deg,
    rgba(4, 7, 32, 0.938),
    rgba(9, 13, 44, 0.918)
  );
}
#abilities {
  color: white;
  font-size: 13px;
  font-family: "EB Garamond";
  font-weight: 500;
}
#flavor {
  color: white;
  font-family: "EB Garamond";
  font-style: italic;
  font-size: 12px;
  margin-top: 10px;
}
.card-attr {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  width: 100%;
}
.attribute {
  color: white;
  font-weight: 600;
  font-family: "Marcellus";
  font-size: 28px;
  padding: 1px 10px 1px 10px;
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
.attribute:hover {
  transform: scale(1.5);
  background-image: linear-gradient(
    60deg,
    rgb(0, 41, 24, 0.95),
    rgb(0, 110, 73, 0.95)
  );
}
#attr-desc {
  font-size: 10px;
  font-weight: 500;
}
p {
  margin: 2px;
}
</style>
