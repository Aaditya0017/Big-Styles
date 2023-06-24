<template>
  <nav class="p-3 mb-2 bg-primary text-white">
    <div>
      <router-link to="/">Home</router-link> |
      <router-link to="/Login">Login</router-link> |
      <router-link to="/register">Register</router-link> |
      <router-link
        to="/cart"
        class="bi bi-cart"
        style="position: absolute; right:60px; top: 10px; font-size: 22px"
        >Cart</router-link
      >
    </div>
  </nav>

  <router-view />
</template>

<style scoped>
a{
  color: whitesmoke;
}
</style>
