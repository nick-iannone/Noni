<template>
  <div>
    <p class="countdown">
       {{ countdown }}
    </p>
  </div>
</template>

<script>
export default {
  name: "countdown",
  data() {
    return {
      countdown: 10,
    };
  },
  methods: {
    countDownTimer() {
      if (this.countdown === 0) {
        this.$emit('startGame');
      }
      if (this.countdown > 0) {
        setTimeout(() => {
          this.countdown -= 1;
          this.countDownTimer();
        }, 1000);
      }
    },
  },
  created() {
    this.countDownTimer();
  },
};
</script>

<style>
.countdown {
  font-size: 220px;
  margin: -75px 0px -75px 0px;
  color: white;
  font-family: "Germania One";
  text-shadow: 8px 8px rgba(0, 0, 0, 0.575);
  z-index: 1;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  width: 100%;
}
</style>
