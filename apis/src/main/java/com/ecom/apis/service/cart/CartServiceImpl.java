package com.ecom.apis.service.cart;

import com.ecom.apis.Jwt.JwtAuthFilter;
import com.ecom.apis.entity.Cart;
import com.ecom.apis.entity.Products;
import com.ecom.apis.repository.CartRepository;
import com.ecom.apis.repository.ProductRepository;
import com.ecom.apis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService{
@Autowired CartRepository cartRepository;
@Autowired
    ProductRepository productRepository;
@Autowired UserRepository userRepository;
@Autowired JwtAuthFilter jwtAuthFilter;


    @Override
    public String addToCart(String id) {
        Cart cart = Cart.builder().user(userRepository.findByUserEmail(jwtAuthFilter.getUser())).products((productRepository.findProductsByProductId(Long.parseLong(id)))).build();
        cartRepository.save(cart);
        return "success";
    }
}
