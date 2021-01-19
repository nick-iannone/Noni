<template>
  <div class="single-message" >
      <div class="bubbles" :class="{ mymess: message.fromUsername == this.$store.state.user.username }">
        <div class="msg-sender"> {{message.fromUsername}} </div>
        <div class="msg-content"> {{message.content}} </div>
      </div>
      <div class="msg-stamp"> {{message.timestamp}} </div>
  </div>
  
</template>

<script>
export default {
    name: 'chat-message',
    props: ['message']
}
</script>

<style>
.bubbles {
    display: flex;
    flex-direction: column;
    overflow-wrap: break-word;
    width: 75%;
    margin: 5px;
    padding: 8px;
    background-color: rgb(33, 77, 52);
    border-radius: 15px;
    box-shadow: 2px 2px rgba(0, 0, 0, 0.281);
}
.single-message {
    display: flex;
    flex-direction: row;
    align-items: center;  
}
.mymess {
    background-color: rgb(87, 114, 187);
    padding: 8px;    
}
.msg-stamp {
    color: rgb(177, 177, 177);
    font-size: 10px;
    font-family: 'Marcellus';
}
.msg-sender {
    font-weight: 600;
    color:rgb(235, 228, 165);
    font-family: 'Marcellus';
    font-size: 15px;
}
.msg-content {
    color: white;
    font-family: 'Marcellus';
    font-size: 14px;
}
</style>