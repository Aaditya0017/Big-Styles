<!-- eslint-disable vue/multi-word-component-names -->
<template>
    <form @submit.prevent="login">
        <div>
        <div class="form-group">
            <label for="loginEmail">Email address</label>
            <input type="email" class="form-control" id="loginEmail" placeholder="Enter your email" v-model.lazy="data.loginEmail">
        </div>
        <div class="form-group">
            <label for="loginPass">Password</label>
            <input type="password" class="form-control" id="loginPass" placeholder="password" v-model.lazy="data.loginPass" >
        </div>
        <button type="submit" class="btn btn-outline-success">Sign in</button>
        </div>
        <div>
            Image
        </div>
    
    </form>
</template>

<script>
export default {
    
    data() {
        return {
            data:{
            loginEmail: '',
            loginPass: '',
            }
        }
    },
    methods: {
        login() {if(this.data?.loginEmail && this.data.loginPass)
                console.log(`${this.data.loginEmail}  ${this.data.loginPass}`)
                else alert("please fill the data !")
        }
        }
    }


</script>

<style>

</style>