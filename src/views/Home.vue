<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <div class="container px-4 px-lg-5 mt-5">
    <div
      class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center"
    >
      <div class="col mb-5" v-for="product in products" :key="product">
        <div class="card h-100">
          <!-- Sale badge-->
          <div
            class="badge bg-dark text-white position-absolute"
            style="top: 0.5rem; right: 0.5rem"
            v-if="product.sale"
          >
            Sale
          </div>
          <!-- Product image-->
          <img class="card-img-top" :src="product.src" :alt="product.name" />
          <!-- Product details-->
          <div class="card-body p-4">
            <div class="text-center">
              <!-- Product name-->
              <h5 class="fw-bolder">{{ product.name }}</h5>
              <!-- Product reviews-->
              <div
                class="d-flex justify-content-center small text-warning mb-2"
              >
                <div class="bi-star-fill" v-for="index in 4" :key="index"></div>
              </div>
              <!-- Product price-->
              <span
                class="text-muted text-decoration-line-through"
                v-if="product.sale"
                >&#8377;{{ product.price }}</span
              >
              &#8377;{{ product.salePrice ?? product.price }}
            </div>
          </div>
          <!-- Product actions-->
          <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
            <div class="text-center">
              <a class="btn btn-outline-dark mt-auto">Add to cart</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      products: [
        {
          name: "Product 1",
          src: "https://via.placeholder.com/150x150",
          sale: true,
          salePrice: 100,
          price: 120,
          rating: 4,
        },
        {
          name: "Product 2",
          src: "https://via.placeholder.com/150x150",
          sale: true,
          salePrice: 100,
          price: 120,
          rating: 4,
        },
        {
          name: "Product 3",
          src: "https://via.placeholder.com/150x150",
          sale: false,
          price: 120,
          rating: 3,
        },
      ],
    };
  },
};
</script>

<style></style>
