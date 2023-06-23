<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <form class="px-5 py-5" @submit.prevent="login">
    <!-- Email input -->
    <div class="form-outline mb-4">
      <input
        type="email"
        id="form1Example1"
        :class="css"
        v-model.lazy="dt.email"
      />
      <label class="form-label" for="form1Example1">Email address</label>
    </div>

    <!-- Password input -->
    <div class="form-outline mb-4">
      <input
        type="password"
        id="form1Example2"
        :class="css"
        v-model.lazy="dt.loginPass"
      />
      <label class="form-label" for="form1Example2">Password</label>
    </div>
    <div>
      <div class="col">
        <a href="#!">Forgot password?</a>
      </div>
    </div>

    <!-- Submit button -->
    <button type="submit" class="btn btn-primary text-centre btn-block">
      Sign in
    </button>
  </form>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      css: ["form-control"],
      dt: {
        email: "",
        password: "",
      },
    };
  },
  methods: {
    async login() {
      let con =""
      if (this.dt?.email && this.dt.loginPass) {
        await axios
          .post("http://localhost:6969/login", this.dt)
          .then((res) => {
            con=res.data;
          })
          .catch((e) => console.log(e));
          console.log(con);
          if(con == 'success'){
            alert("success");
          }if (con=='wrong') {
            alert("wrong password");
          }if(con=='notregistered'){
            alert("user not registered");
          }
        this.css = this.css.filter((item) => item !== "error");
      } else {
        alert("please fill the data !");
        this.css.push("error");
      }
    },
  },
};
</script>

<style scoped>
.px-5 {
  padding-right: 35rem !important;
  padding-left: 35rem !important;
}
.py-5 {
  padding-top: 8rem !important;
  padding-bottom: 3rem !important;
}
.error {
  border: 1px solid red;
}
</style>
