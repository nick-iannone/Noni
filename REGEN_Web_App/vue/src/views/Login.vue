<template>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <h1 class="prompt">Please Sign In</h1>
      <div
        class="alert"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
      <label for="username" class="sr-only">Username</label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only">Password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <router-link :to="{ name: 'register' }" id="register-link">Need an account?</router-link>
      <button class='btn-submit' type="submit">Sign in</button>
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
  background-image: url('../assets/login_register_2.jpg');
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.form-signin {
  align-items: center;
  justify-content: center;
  text-align: center;
  display: flex;
  flex-direction: column;
  background-image: linear-gradient(to bottom, rgba(255, 255, 255, 0.329), rgba(255, 253, 154, 0.233), rgba(0, 0, 0, 0.2));
  width: 30%;
  margin: 40px;
  border-radius: 80px;
}
.prompt {
  font-family: 'Germania One';
  color: rgb(0, 0, 0);
  font-size: 80px;
}
.form-control {
  width: 80%;
  padding: 10px;
  margin: 20px;
  border-radius: 5px;
  font-size: 15px;
}
.btn-submit {
  background-color: black;
  padding: 10px;
  width: 120px;
  border-radius: 10px;
  margin: 40px;
  color: rgb(254, 255, 206);
  font-family: 'Germania One';
  font-size: 25px;
  transition: all .2s ease-in-out;
  box-shadow: 3px 3px rgba(0, 0, 0, 0.5);
}
.btn-submit:hover {
  transform: scale(1.1);
  box-shadow: 6px 6px rgba(0, 0, 0, 0.5);
  background-color: rgb(105, 127, 168);
  cursor: pointer;
}
#register-link {
  font-family: 'Germania One';
  color: rgb(254, 255, 206);
  font-size: 40px;
  margin-top: 20px;
  text-shadow: 1px 1px black;
}
#register-link:hover {
  color: rgb(145, 183, 255);
}
.alert {
  font-family: 'Unifraktur Cook';
  color: black;
  font-size: 21px;
}
</style>