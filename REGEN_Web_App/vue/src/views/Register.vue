<template>
  <div id="register" class="text-center">
    <form class="form-signin" @submit.prevent="register">
      <h1 class="prompt">Create Account</h1>
      <div class="alert" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
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
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      <router-link :to="{ name: 'login' }" id='login-link'>Have an account?</router-link>
      <button class="btn-register" type="submit">
        Create Account
      </button>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style>
#register {
  background-image: url('../assets/login_register_2.jpg');
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.btn-register {
  background-color: black;
  padding: 10px;
  width: 180px;
  border-radius: 10px;
  margin: 40px;
  color: rgb(254, 255, 206);
  font-family: 'Germania One';
  font-size: 25px;
  transition: all .2s ease-in-out;
  box-shadow: 3px 3px rgba(0, 0, 0, 0.5);
}
.btn-register:hover {
  transform: scale(1.1);
  box-shadow: 6px 6px rgba(0, 0, 0, 0.5);
  background-color: rgb(105, 127, 168);
  cursor: pointer;
}
#login-link {
  font-family: 'Germania One';
  color: rgb(254, 255, 206);
  font-size: 40px;
  margin-top: 20px;
  text-shadow: 1px 1px black;
}
#login-link:hover {
  color: rgb(145, 183, 255);
}
</style>
