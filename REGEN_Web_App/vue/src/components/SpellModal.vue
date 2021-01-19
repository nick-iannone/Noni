<template>
  <div>
    <modal name='modal-form' class='modal-content' height=300px width=285px styles="background-color: rgb(220, 220, 220); border-radius: 15px; border: 1px solid black">
      <div>
        <h3 class='spell-name'>{{spell.name}}</h3>
        <p class='spell-desc'>{{spell.description}}</p>
      </div>
    </modal>
  </div>    
</template>

<script>
export default {
  name: 'spell-modal',
  props: ['spell']
}
</script>

<style>

</style>