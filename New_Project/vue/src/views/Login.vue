<template>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <h1 class="prompt">Please Sign In</h1>
      <div class="alert" role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div class="alert" role="alert" v-if="this.$route.query.registration">
        Thank you for registering, please sign in.
      </div>
      <!-- <label for="username" class="sr-only">Username</label> -->
      <input
        type="text"
        id="username"
        placeholder="Username"
        class="form-control"
        v-model="user.username"
        required
        autofocus
      />
      <!-- <label for="password" class="sr-only">Password</label> -->
      <input
        type="password"
        id="password"
        placeholder="Password"
        class="form-control"
        v-model="user.password"
        required
      />
      <router-link :to="{ name: 'register' }" id="register-link"
        >Need an account?</router-link
      >
      <button class="btn-login" type="submit">Sign in</button>
    </form>
    <div class="loader">
      <semipolar-spinner
        :animation-duration="2000"
        :size="85"
        color="#e7eea4"
        v-if="isLoading"
      />
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";
import chatService from "../services/ChatService";
import { SemipolarSpinner } from "epic-spinners";

export default {
  name: "login",
  components: {
    SemipolarSpinner,
  },
  data() {
    return {
      fullPage: true,
      isLoading: false,
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      console.log("Starting login attempt...");
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            console.log("User successfully logged in!");
            this.$store.commit("SET_GAME_USERNAME", response.data.user.username);
            this.isLoading = true;
          }
        })
        .catch((error) => {
          const response = error.response;
          console.log(response);
          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
      chatService
        .getAllUsernames()
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_ALL_USERS", response.data);
          }
        })
        .catch((error) => {
          const response = error.response;
          console.log(response);
        });
      setTimeout(() => {
        if (!this.invalidCredentials) {
          this.$router.push({ name: "home" });
        }
      }, 3000);
    },
  },
};
</script>

<style>
.loader {
  min-height: 90px;
}
.sr-only {
  color: rgb(231, 238, 164);
  font-size: 1vw;
}
#login {
  background-image: url("../assets/login_background_1.jpg");
  background-size: 1920px 1080px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 1060px;
}
.form-signin {
  align-items: center;
  justify-content: center;
  text-align: center;
  display: flex;
  flex-direction: column;
  background-image: linear-gradient(
    to bottom,
    rgba(33, 37, 53, 0.644),
    rgba(29, 82, 133, 0.486),
    rgba(0, 0, 0, 0.651)
  );
  width: 50%;
  margin: 40px;
  border-radius: 80px;
}
.prompt {
  font-family: "Germania One";
  color: rgb(231, 238, 164);
  font-size: 6.5vw;
  margin-top: 15px;
  text-shadow: 2px 1px #000000;
  padding: 20px 20px 0px 20px;
}
.form-control {
  width: 50%;
  padding: 10px;
  margin-bottom: 20px;
  border-radius: 5px;
  font-size: 25px;
  border: 1px solid rgb(180, 180, 180);
  background-image: linear-gradient(
    to right,
    rgba(70, 70, 70, 0.1),
    rgba(0, 0, 0, 0.1)
  );
  color: rgb(0, 0, 0);
  font-family: "Marcellus";
  font-weight: 600;
}
.btn-login {
  background-color: rgb(231, 238, 164);
  padding: 10px;
  width: 200px;
  border-radius: 10px;
  border: 1px solid black;
  margin: 30px;
  font-family: "Germania One";
  font-size: 30px;
  transition: all 0.2s ease-in-out;
  box-shadow: 3px 3px rgba(0, 0, 0, 0.5);
}
.btn-login:hover {
  transform: scale(1.1);
  box-shadow: 6px 6px rgba(0, 0, 0, 0.5);
  background-color: rgb(145, 183, 255);
  color: white;
  border: 1px solid white;
  cursor: pointer;
}
#register-link {
  font-family: "Germania One";
  color: rgb(246, 248, 247);
  font-size: 30px;
  margin-top: 20px;
  text-shadow: 1px 1px black;
  text-decoration: none;
  transition: all 0.2s ease-in-out;
}
#register-link:hover {
  color: rgb(145, 183, 255);
  transform: scale(1.06);
}
.alert {
  font-family: "Marcellus";
  color: black;
  font-size: 20px;
  font-weight: 600;
  margin-top: 15px;
  color: rgb(159, 191, 252);
}
.sr-only {
  margin: 25px 0px 8px 0px;
  font-family: "Marcellus";
  font-weight: 600;
  font-size: 25px;
}
</style>
