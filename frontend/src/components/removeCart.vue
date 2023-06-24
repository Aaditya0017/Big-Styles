<template>
  <div class="card-img-overlay bg-light bg-opacity-75 pt-lg-5 border-5">
    <form class="text-center mg bg-white pt-lg-5 border px-3 rounded-top border-primary">
        <h3 class="text-centre" >Do you want to remove {{pdName}} from cart</h3>
        <button  class="btn btn-outline-success mx-3" @click="$emit('back')">Back</button>
        <button  class="btn btn-outline-danger mx-3" @click="$emit('removed')">Remove</button>
    </form>
  </div>
</template>

<script>

export default {
    emits:['back','removed'],
    props:['pdName'],
    data() {
        return {
            
        }
    }

};
</script>

<style scoped>
.mg{
  margin-top: 15rem;
    padding-bottom: 3rem;
    padding-top: 3rem;
}
</style>
