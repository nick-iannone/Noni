<template>
  <div class="contract-full">
    <div class="cont-full-header">
      <div class="cont-full-name">{{ contract.name }}</div>
      <div class="cont-full-cost">
        {{ contract.cost }}<font-awesome-icon
            :icon="['fa', 'coins']"
            class="icon"
            color="yellow"
          />
      </div>
    </div>
    <div class="cont-full-desc">{{ contract.desc }}</div>
    <div class="cont-full-dura">Duration: 
        <div class='cont-full-status'>
            <font-awesome-icon :icon="['fa', 'circle']" class="icon-contract" v-for="n in contract.dura" :key="n" />
        </div>
        <div class="cont-counters">
            Counters: 
        </div>  
    </div>
  </div>
</template>

<script>
export default {
  name: "contract",
  props: ["contract"],
};
</script>

<style>
.contract-full {
  background-image: linear-gradient(45deg, rgb(37, 102, 83), rgb(2, 58, 50));
  box-shadow: 4px 4px rgba(0, 0, 0, 0.3);
  border-radius: 5px;
  border: 1px solid rgb(179, 179, 179);
  width: 93%;
  height: auto;
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  margin: 5px 5px 5px 10px;
  transition: all 0.2s ease-in-out;
}
.contract-full:hover {
  transform: scale(1.15);
  cursor: pointer;
}
.cont-full-status {
    display: flex;
    flex-direction: row;
    gap: 4px;
    padding: 4px 10px 4px 4px;
}
.cont-full-status > img {
    width: 15px;
    height: 15px;
    border: 5px solid rgb(82, 192, 55);
    border-radius: 50%;
    box-shadow: 1px 1px rgb(85, 85, 85);
}
.cont-full-header {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  font-size: 13px;
}
.cont-full-name {
  color: white;
  font-weight: 550;
  font-family: "Marcellus";
  padding: 3px 13px 3px 4px;
  text-align: left;
  height: 20px;
}
.cont-full-cost {
  color: white;
  padding: 3px;
  font-family: "Marcellus";
  font-weight: 500;
}
.cont-full-desc {
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  padding: 0px 4px 0px 4px;
  height: auto;
  color: white;
  font-family: "Marcellus";
  font-size: 11px;
}
.cont-full-dura {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  padding: 0px 4px 0px 4px;
  height: auto;
  color: white;
  font-family: "Marcellus";
  font-size: 11px;
  align-items: center;
}
</style>
