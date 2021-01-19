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
            copyText: 'Nonius 2020'
        }
    }
}
</script>

<style>
.copyright {
    font-family:Arial, Helvetica, sans-serif;
    color: white;
    text-align: center;
}
</style>