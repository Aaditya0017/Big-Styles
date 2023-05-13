<template>
  <div class="card-img-overlay bg-light bg-opacity-75 pt-lg-5 border-5">
    <form class="text-center pt-lg-5 border px-3 rounded-top border-primary">
        <label for="otpVerify" class="pb-3">Otp has been sent to <b> {{email}}</b></label><br>
        <input type="number" id="otpVerify" v-model="otpVerify"><br>
        <button  class="btn btn-outline-danger" @click="$emit('back')">Back</button>
        <button  class="btn btn-outline-success">Verify</button>
    </form>
  </div>
</template>

<script>

export default {
    props:['email'],
    emits:['back'],
    data() {
        return {
            otpVerify: ''
        }
    }

};
</script>

<style></style>
