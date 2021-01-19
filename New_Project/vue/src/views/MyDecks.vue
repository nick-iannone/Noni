<template>
  <div>
    <button @click="backToMain" class="nav-btn">MAIN MENU</button>
  </div>
</template>

<script>
export default {
  methods: {
    backToMain() {
      this.$router.push({ name: "home" });
    },
  },
};
</script>

<style>
</style>
