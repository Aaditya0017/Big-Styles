<template>
  <div class="card-img-overlay bg-light bg-opacity-75 pt-lg-5 border-5">
    <div
      class="text-center mg bg-white pt-lg-5 border px-3 rounded-top border-primary"
    >
      <label for="otpVerify" class="pb-3"
        >Otp has been sent to <b> {{ email }}</b></label
      ><br />
      <input
        type="number"
        class="form-control"
        id="otpVerify"
        v-model="otpObj.otp"
      /><br />
      <button class="btn btn-outline-danger mx-3" @click="$emit('back')">
        Back
      </button>
      <button class="btn btn-outline-success mx-3" @click="verify">
        Verify
      </button>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  props: ["email"],
  emits: ["back", "verified"],
  computed: {},
  data() {
    return {
      otpObj: {
        otp: 0,
        email: this.email,
      },
    };
  },
  methods: {
   async verify() {
      console.log("we are verified");
      var sta='';

      //axios to assign value to any variables inside axios use asyn & await as axios will be executed later then the rest code
      await axios
        .post("http://localhost:6969/verify", this.otpObj)
        .then(function (response) {
          if (response.data == "success") sta="success";
          else sta='error';
        })
        .catch((e) => console.log(e));
        if(sta=="success"){
          console.log("we are in success");
          this.$emit('back');
          this.$emit('verified');
        }
    },
  },
};
</script>

<style scoped>
.mg {
  margin-top: 10rem;
  margin-right: 35rem;
  margin-left: 35rem;
  padding-bottom: 3rem;
}
</style>
