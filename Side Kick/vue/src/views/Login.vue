<template>
  <div id="login" class="form-container">
    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
      <label for="username" class="sr-only"></label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only"></label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <button type="submit">Sign in</button>
      <router-link class='need-account' :to="{ name: 'register' }">Need an account?</router-link>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style>
#login {
  background-image: url('../assets/cropped.jpg');
}
.sr-only {
  font-family: 'Miriam Libre';
  font-size: 25px;
}
.form-signin {
    display: flex;
    flex-direction: column;
    margin-left: auto;
    margin-right: auto;
    width: 500px;
    align-items: center;
    color: white;
    text-shadow: 1px 1px black;
}
.form-signin h1 {
  font-size: 7.9em;
  margin-bottom: 50px;
}
.form-signin > button {
    font-family: 'Bangers';
    color: #fff;
    padding: 10px 10px;
    background-color: #fc8626;
    border-radius: 10px;
    font-size: 30px;
    text-shadow: 2px 2px rgba(0,0,0,0.8);
    width: 150px;
    height: 60px;
    margin-top: 30px;
    margin-bottom: 40px;
    box-shadow: 5px 5px rgba(0, 0, 0, 0.5);
    transition: all .2s ease-in-out;
}
.form-signin > button:hover {
    box-shadow: 10px 10px rgba(0, 0, 0, 0.5);
    transform: scale(1.05); 
    cursor: url('../assets/gauntlet.png'), pointer;
}
.form-control {
  margin-top: 10px;
  margin-bottom: 10px;
}
.need-account {
  text-align: center;
  margin-top: 10px;
  margin-bottom: 15px;
  font-size: 40px;
  text-shadow: 2px 2px rgba(0,0,0,0.8);
}
.alert {
  font-family: 'Miriam Libre';
}

</style>