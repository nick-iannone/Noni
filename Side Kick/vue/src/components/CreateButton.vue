<template>
  <div class="create_collection">
      <a><router-link tag='a' v-bind:to="{name: 'new-collection'}">Create New Collection</router-link></a>
  </div> 
</template>

<script>
export default {
    name: 'create-button'
}
</script>

<style>
.create_collection {
    text-align: center;
    margin-left: auto;
    margin-right: auto;
    margin-bottom: 40px;
    margin-top: 40px;
    width: 20%;
    height: 90%;
    font-size: 40px;
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    padding: 30px;
    border: 1px solid black;
    background-color: black;
    box-shadow: 5px 5px rgba(0, 0, 0, 0.5);
    text-align: center;
    transition: all .2s ease-in-out;
    padding: 30px;
    border-radius: 15px;
}

.create_collection:hover {
    box-shadow: 10px 10px rgba(0, 0, 0, 0.5);
    transform: scale(1.05); 
}
</style>