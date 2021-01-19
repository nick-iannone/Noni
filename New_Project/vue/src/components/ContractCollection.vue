<template>
  <div class="cont-collect">

    <div class="cont-collect-header">
      <div class="cont-collect-name">{{ contract.name }}</div>
      <div class="cont-collect-cost">
        {{ contract.cost }} <font-awesome-icon
            :icon="['fa', 'coins']"
            class="icon-contract"
            color="yellow"
          />
      </div>
    </div>

    <div class="cont-collect-desc">{{ contract.desc }}</div>

    <div class="cont-collect-dura">
      Duration:
      <div class="cont-collect-status">
        <font-awesome-icon :icon="['fa', 'circle']" class="icon-contract" v-for="n in contract.dura" :key="n" />
      </div>
    </div>

  </div>
</template>

<script>
export default {
  name: "contract-collection",
  props: ["contract"],
};
</script>

<style>
.cont-collect {
  border: 25px solid #b88846;
  border-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='75' height='75'%3E%3Cg fill='none' stroke='%23B88846' stroke-width='2'%3E%3Cpath d='M1 1h73v73H1z'/%3E%3Cpath d='M8 8h59v59H8z'/%3E%3Cpath d='M8 8h16v16H8zM51 8h16v16H51zM51 51h16v16H51zM8 51h16v16H8z'/%3E%3C/g%3E%3Cg fill='%23B88846'%3E%3Ccircle cx='16' cy='16' r='2'/%3E%3Ccircle cx='59' cy='16' r='2'/%3E%3Ccircle cx='59' cy='59' r='2'/%3E%3Ccircle cx='16' cy='59' r='2'/%3E%3C/g%3E%3C/svg%3E")
    25;

  background-image: url('../assets/soft-wallpaper.png'), linear-gradient(rgb(0, 0, 0), rgb(0, 0, 0));
  box-shadow: 4px 4px rgba(0, 0, 0, 0.3);

  display: flex;
  flex-direction: column;
  justify-content: space-evenly;

  margin: 5px 5px 5px 10px;
  width: 250px;
  height: 300px;
  
  transition: all 0.2s ease-in-out;
}
.cont-collect:hover {
  transform: scale(1.15);
  cursor: pointer;
}
.cont-collect-status {
  display: flex;
  flex-direction: row;
  gap: 4px;
  padding: 4px 10px 4px 4px;
}
.cont-collect-status > img {
  width: 15px;
  height: 15px;
  border: 5px solid rgb(82, 192, 55);
  border-radius: 50%;
  box-shadow: 1px 1px rgb(85, 85, 85);
}
.cont-collect-header {
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  font-size: 1rem;
}
.cont-collect-name {
  color: white;
  font-weight: 550;
  font-family: "Marcellus";
  padding: 3px 13px 3px 4px;
  text-align: left;
}
.cont-collect-cost {
  color: white;
  padding: 3px;
  font-family: "Marcellus";
  font-weight: 500;
  display: flex;
  flex-direction: row;
}
.cont-collect-desc {
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  padding: 0px 4px 0px 4px;
  height: auto;
  color: white;
  font-family: "Marcellus";
  font-size: 1rem;
}
.cont-collect-dura {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  padding: 0px 4px 0px 4px;
  height: auto;
  color: white;
  font-family: "Marcellus";
  font-size: 1rem;
  align-items: center;
}
</style>
