<template>
  <div>
     <p class="copyright"> &#169; {{copyText}} </p>
  </div>
</template>

<script>
export default {
    name: 'the-footer',
    data() {
        return {
           copyText: 'Java Green Enterprises LLC.'
        };
    }

}
</script>

<style>
.copyright {
  color: white;
  font-size: 15px;
  font-family:'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
  text-align: center;
}
</style>