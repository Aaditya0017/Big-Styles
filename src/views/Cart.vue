<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <div class="card-body">
    <h1 class="text-center">CART</h1>
  </div>
  <div class="gradient-custom">
    <div class="container h-100">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col">
          <div class="card mb-4">
            <div
              class="card-body p-4"
              v-for="product in products"
              :key="product"
            >
              <remove-cart v-if="removing" @removed="removed(product.name)" :pdName="product.name" />
              <div class="row align-items-center">
                <div class="col-md-2">
                  <img
                    :src="product.src"
                    class="img-fluid"
                    :alt="product.name"
                  />
                </div>
                <div class="col-md-2 d-flex justify-content-center">
                  <div>
                    <p class="small text-muted mb-4 pb-2">Name</p>
                    <p class="lead fw-normal mb-0">{{ product.name }}</p>
                  </div>
                </div>
                <div class="col-md-2 d-flex justify-content-center">
                  <div>
                    <p class="small text-muted mb-4 pb-2">Size</p>
                    <p class="lead fw-normal mb-0">
                      <i class="fas fa-circle me-2" style="color: #fdd8d2"></i>
                      {{ product.size }}
                    </p>
                  </div>
                </div>
                <div class="col-md-2 d-flex justify-content-center">
                  <div>
                    <p class="small text-muted mb-4 pb-2">Quantity</p>
                    <p class="lead fw-normal mb-0">
                      <button
                        class="btn btn-dark btn-sm btn-circle"
                        @click="
                          product.quantity > 0
                            ? --product.quantity
                            : product.quantity
                        "
                      >
                        -
                      </button>
                      {{ product.quantity }}
                      <button
                        class="btn btn-circle btn-dark btn-sm"
                        @click="
                          product.quantity < 100
                            ? ++product.quantity
                            : product.quantity
                        "
                      >
                        +
                      </button>
                    </p>
                  </div>
                </div>
                <div class="col-md-2 d-flex justify-content-center">
                  <div>
                    <p class="small text-muted mb-4 pb-2">Price</p>
                    <p class="lead fw-normal mb-0">
                      &#8377;{{ product.price }}
                    </p>
                  </div>
                </div>
                <div class="col-md-2 d-flex justify-content-center">
                  <div>
                    <p class="small text-muted mb-4 pb-2">Total</p>
                    <p class="lead fw-normal mb-0">
                      &#8377; {{ product.price * product.quantity }}
                      <i
                        @click="remove"
                        class="marg bi bi-trash text-danger"
                        style="position: relative"
                      ></i>
                    </p>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div class="card mb-5">
            <div class="card-body p-4">
              <div class="float-end">
                <p class="mb-0 me-5 d-flex align-items-center">
                  <span class="small text-muted me-2">Order total:</span>
                  <span class="lead fw-normal">&#8377; {{ total }}</span>
                </p>
              </div>
            </div>
          </div>

          <div class="d-flex justify-content-end">
            <button type="button" class="btn btn-light btn-lg me-2">
              Continue shopping
            </button>
            <button type="button" class="btn btn-primary btn-lg">
              Add to cart
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import removeCart from "@/components/removeCart.vue";
export default {
  components: { removeCart },
  computed: {
    total() {
      return this.products.reduce((total, product) => {
        return total + product.price * product.quantity;
      }, 0);
    },
  },
  methods: {
    removed(pd){
      console.log(pd);
    }
    ,
    remove() {
      this.removing = true;
    },
    back() {
      this.removingCart = false;
    },
  },
  data() {
    return {
      removing: false,
      products: [
        {
          name: "Product 1",
          src: "https://via.placeholder.com/150x150",
          sale: true,
          salePrice: 100,
          price: 120,
          quantity: 1,
          size: "S",
          rating: 4,
          sellerId: 112
        },
      ],
    };
  },
};
</script>

<style scoped>
.marg {
  margin-left: 5px;
}
.gradient-custom {
  background: #6a11cb;
  background: -webkit-linear-gradient(
    to right,
    rgba(106, 17, 203, 1),
    rgba(37, 117, 252, 1)
  );
  background: linear-gradient(
    to right,
    rgba(106, 17, 203, 1),
    rgba(37, 117, 252, 1)
  );
}
</style>
