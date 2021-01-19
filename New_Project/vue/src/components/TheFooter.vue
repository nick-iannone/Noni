<template>
  <div class='footer'>
      <p id="copyright"> &#169; {{copyText}} </p>
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
#copyright {
    font-family:Arial, Helvetica, sans-serif;
    color: white;
    text-align: center;
}
.footer {
    width: 100%;
    background-color: black;
    padding-top: 15px;
}
</style>