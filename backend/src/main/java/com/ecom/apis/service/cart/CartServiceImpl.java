package com.ecom.apis.service.cart;

import com.ecom.apis.Jwt.JwtAuthFilter;
import com.ecom.apis.entity.Cart;
import com.ecom.apis.entity.Products;
import com.ecom.apis.entity.UserEntity;
import com.ecom.apis.exceptionHandling.exceptions.NotFoundException;
import com.ecom.apis.repository.CartRepository;
import com.ecom.apis.service.product.ProductServiceImpl;
import com.ecom.apis.service.user.UserServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService{
@Autowired CartRepository cartRepository;
@Autowired ProductServiceImpl productService;
@Autowired UserServiceImplement userServiceImplement;
@Autowired JwtAuthFilter jwtAuthFilter;


    @Override
    public String addToCart(String id, int quantity) throws  NotFoundException {
        Products product=productService.findProductById(Long.parseLong(id));
        UserEntity user = userServiceImplement.getUserByEmail(jwtAuthFilter.getUser());
            Cart cart =cartRepository.findWithProductUser(product,user);
            if(cart==null)
                cart = Cart.builder().user(user).products(product).quantity(quantity).build();

            else
                cart.setQuantity(cart.getQuantity() + quantity);

            cartRepository.save(cart);
            return "product added to cart";

    }

    @Override
    public Map<Products, Long> listOfProducts() throws NotFoundException {
        Map<Products,Long> productsMap=new HashMap<>();
        cartRepository.findAllByUser(userServiceImplement.getUserByEmail(jwtAuthFilter.getUser())).forEach(cart -> {
            Products prod=cart.getProducts();
            prod.setQuantity(cart.getQuantity());
            productsMap.put(prod, Long.valueOf(cart.getCartId()));
        });

        return productsMap;
    }

    @Override
    public String deleteCartItem(Long cartProductID) throws NotFoundException {
        if(cartRepository.existsById(cartProductID)){
            cartRepository.deleteById(cartProductID);
            return "Cart item removed";
        }else {
            throw new NotFoundException("No cart product available with id "+cartProductID);
        }
    }
}
