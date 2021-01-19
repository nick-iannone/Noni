<template>
  <div class="contract">
    <div id="dropdown-nav">
      <div class="cont-header">
        <div class="cont-name">{{ contract.name }}</div>
        <div class="cont-cost">
          {{ contract.cost }} <font-awesome-icon
            :icon="['fa', 'coins']"
            class="icon"
            color="yellow"
          />
        </div>
      </div>
      <div class="dropdown-content">
        <div class="cont-desc">{{ contract.desc }}</div>
        <div class="cont-desc-dura">Duration: 
          <div class='cont-full-status'>
            <font-awesome-icon :icon="['fa', 'circle']" class="icon-contract" v-for="n in contract.dura" :key="n" />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "contract-tile",
  props: ["contract"],
};
</script>

<style>
.contract {
  background-image: linear-gradient(60deg, rgb(0, 0, 0), rgb(20, 25, 66), rgb(0, 0, 0));
  border-radius: 5px;
  border: 1px solid rgb(179, 179, 179);
  width: 93%;
  height: auto;
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  margin: 5px 5px 5px 10px;
}
.contract:hover {
  cursor: pointer;
  background-image: linear-gradient(60deg, rgb(71, 83, 197), rgb(63, 103, 184));
}
.cont-header {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  font-size: 15px;
}
.cont-name {
  color: white;
  font-weight: 550;
  font-family: "Marcellus";
  padding: 3px 13px 3px 5px;
  text-align: left;
  height: 20px;
}
.cont-cost {
  color: white;
  padding: 3px;
  font-family: "Marcellus";
  font-weight: 600;
}
#dropdown-nav {
  position: relative;
  display: inline-block;
}
.dropdown-content {
  display: none;
  position: absolute;
  background-image: linear-gradient(60deg, rgb(71, 83, 197), rgb(31, 65, 133));
  min-width: 265px;
  z-index: 1;
  margin-top: 5px;
  margin-left: 2px;
  border: 1px solid rgb(179, 179, 179);
  border-radius: 5px;
  transform: scale(1.1);
}
#dropdown-nav:hover .dropdown-content {
  display: flex;
  flex-direction: column;
  transform: translate(-320px, -80px);
}
.cont-desc {
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  padding: 4px;
  height: auto;
  color: white;
  font-family: "Marcellus";
  font-size: 14px;
}
.cont-desc-dura {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  padding: 0px 5px 4px 5px;
  height: auto;
  color: white;
  font-family: "Marcellus";
  font-size: 15px;
  align-items: center;
}
.icon-contract {
  color: rgb(252, 255, 101);
  font-size: 14px;
  padding: 0px 3px 0px 3px;
}
</style>
