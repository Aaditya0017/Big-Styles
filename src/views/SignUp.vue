<template>
  <div class="text-center"><h1 class="text-bg-dark">User Registration</h1></div>
  <div>
    <form class="form-control text-center" @submit.prevent="register">
      <label for="userEmail">Enter your email:</label><br />
      <input
        type="email"
        name="userEmail"
        id="userEmail"
        class="border-3"
        v-model.lazy="dt.userEmail"
      /><br />
      <label for="userPassword">Enter your password:</label><br />
      <input
        type="password"
        name="userPassword"
        id="userPassword"
        v-model.lazy="dt.userPassword"
      /><br />
      <label for="confirmPassword">Enter your password again:</label><br />
      <input
        type="password"
        name="confirmPassword"
        id="confirmPassword"
        v-model.lazy="dt.confirmPassword"
      /><br />
      <label for="userNumber">Enter Contact Number</label><br />
      <input type="number" id="userNumber" v-model.lazy="dt.userNumber" /><br />
      <label for="userAddress">Enter your Address</label><br />
      <input
        type="text"
        id="userAddress"
        v-model.lazy="dt.userAddress"
      /><br />
      <label for="userCity">Pincode</label><br />
      <input type="number" id="userCity" v-model.lazy="dt.userCity" /><br />
      <br />
      <button type="submit">Submit</button>
    </form>
  </div>


  <div>
    <otpVerify v-if="registered" :email="dt?.userEmail" @back="back" />
  </div>
</template>

<script>
import otpVerify from '../components/otpVerify.vue'
export default {
  components:{
    otpVerify
  },
  data() {
    return {
      registered: false,
      dt: {
        userEmail: "",
        userPassword: "",
        confirmPassword: "",
        userNumber: "",
        userAddress: "",
        userCity: "",
      },
    };
  },
  methods: {
    back(){
      this.registered=false;
    },
    register() {
      if (
        this.dt.userEmail == "" ||
        this.dt.userPassword == "" ||
        this.dt.confirmPassword == "" ||
        this.dt.userNumber == "" ||
        this.dt.userAddress == "" ||
        this.dt.userCity == ""
      ) {
        alert("Please fill all the fields");
      } else {
        if (this.dt.userPassword == this.dt.confirmPassword) {
          console.log(this.dt);
          this.registered= !this.registered;
        } else {
          alert("Passwords do not match");
        }
      }
    },
  },
};
</script>

<style></style>
