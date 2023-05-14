<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <form class="px-5 py-5" @submit.prevent="login">
    <!-- Email input -->
    <div class="form-outline mb-4">
      <input
        type="email"
        id="form1Example1"
        :class="css"
        v-model.lazy="data.loginEmail"
      />
      <label class="form-label" for="form1Example1">Email address</label>
    </div>

    <!-- Password input -->
    <div class="form-outline mb-4">
      <input
        type="password"
        id="form1Example2"
        :class="css"
        v-model.lazy="data.loginPass"
      />
      <label class="form-label" for="form1Example2">Password</label>
    </div>

    <!-- 2 column grid layout for inline styling -->
    <div class="row mb-4">
      <div class="col d-flex justify-content-center">
        <!-- Checkbox -->
        <div class="form-check">
          <input
            class="form-check-input"
            type="checkbox"
            id="form1Example3"
            checked
          />
          <label class="form-check-label" for="form1Example3">
            Remember me
          </label>
        </div>
      </div>

      <div class="col">
        <!-- Simple link -->
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
export default {
  data() {
    return {
      css: ["form-control"],
      data: {
        loginEmail: "",
        loginPass: "",
        error: false,
      },
    };
  },
  methods: {
    login() {
      if (this.data?.loginEmail && this.data.loginPass) {
        console.log(`${this.data.loginEmail}  ${this.data.loginPass}`);
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
