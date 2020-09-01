<template>
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <h1 class="register-header">Create Account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
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
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      <button class="btn btn-lg btn-primary btn-block" type="submit">
        Create Account
      </button>
      <router-link class='need-account' :to="{ name: 'login' }">Have an account?</router-link>
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
#create-pow {
  transition: all .2s ease-in-out;
  width: 50%;
  height: auto;
}
#create-pow:hover {
  transform: scale(1.05);
}
#register {
  background-image: url('../assets/cropped.jpg');
}
.sr-only {
  font-family: 'Miriam Libre';
  font-size: 25px;
}
.form-register {
    display: flex;
    flex-direction: column;
    margin-left: auto;
    margin-right: auto;
    width: 500px;
    align-items: center;
    color: white;
    text-shadow: 1px 1px black;
}
.form-register h1 {
  font-size: 7em;
  margin-bottom: 50px;
}
.form-register > button {
    font-family: 'Bangers';
    color: #fff;
    padding: 5px 5px;
    background-color: #fc8626;
    border-radius: 10px;
    font-size: 30px;
    text-shadow: 2px 2px rgba(0,0,0,0.8);
    width: 210px;
    height: 55px;
    margin-top: 30px;
    margin-bottom: 40px;
    box-shadow: 5px 5px rgba(0, 0, 0, 0.5);
    transition: all .2s ease-in-out;
    height: 98px;
}
.form-register > button:hover {
    box-shadow: 10px 10px rgba(0, 0, 0, 0.5);
    transform: scale(1.05); 
    cursor: url('../assets/gauntlet.png'), pointer;
}
.form-control {
  margin-bottom: 10px;
}
.need-account {
  text-align: center;
  margin-top: 10px;
  margin-bottom: 15px;
  font-size: 40px;
  text-shadow: 2px 2px rgba(0,0,0,0.8);
}
</style>
