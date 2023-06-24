package com.ecom.apis.controller;

import com.ecom.apis.Jwt.JwtService;
import com.ecom.apis.entity.Products;
import com.ecom.apis.entity.UserEntity;
import com.ecom.apis.exceptionHandling.exceptions.NotFoundException;
import com.ecom.apis.model.ForgetPass;
import com.ecom.apis.model.ImageModel;
import com.ecom.apis.model.Login;
import com.ecom.apis.model.OTP;
import com.ecom.apis.service.cart.CartService;
import com.ecom.apis.service.otp.OtpServiceInterface;
import com.ecom.apis.service.product.ProductService;
import com.ecom.apis.service.user.UserServiceInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "http://localhost:8080/")
@Validated
@RestController
public class ApiController {
    @Autowired
    private UserServiceInterface userServiceInterface;
    @Autowired
    private OtpServiceInterface otpServiceInterface;
    @Autowired
    private ProductService productService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtService jwtService;
    @Autowired
    CartService cartService;


    @GetMapping("/test")
    public  void test() throws NotFoundException {
        System.out.println("hello");
        userServiceInterface.remove(4000l);
    }

    @RequestMapping("/")
    public String welcome(){
        return "Welcome to Big-styles";
    }

    //tested
    @PostMapping("/registration")
    public String userRegistration (@RequestBody @Valid UserEntity userEntity){
        return userServiceInterface.addUser(userEntity);
    }

    //working fine
    @GetMapping("/sendmail/{email}")
    public String sendOtp( @PathVariable("email") String email){
        return otpServiceInterface.sendOtp(email);
    }
    //working fine
    @PostMapping("/verify")
    public String verify (@RequestBody @Valid OTP otp) throws NotFoundException {
        return otpServiceInterface.verify(otp);
    }

    //working fine
    @PostMapping("/authenticate")
    public String login (@RequestBody @Valid Login login) throws NotFoundException {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
        if (authenticate.isAuthenticated())
            if(userServiceInterface.verified(login.getEmail()))return jwtService.generateToken(login.getEmail());
            else return "Not Verified";
        else throw new NotFoundException("Invalid Credentials");
    }

    //working fine
    @PostMapping("/forgetPass")
    public String forgetPass (@RequestBody @Valid ForgetPass otp)
    {
        return otpServiceInterface.forgetPass(otp);
    }

    @GetMapping("/products")
    public List<Products> allProducts(){
        return productService.allProducts();
    }
    //fine
    @GetMapping("/products/{id}")
    public  Products productFromId(@PathVariable("id") Long productId) throws NoSuchElementException, NotFoundException {
        return productService.productFromId(productId);
    }
    @GetMapping("/productRating/{productId}")
    public  Double getProductRating(@PathVariable("productId") Long productId) throws NoSuchElementException, NotFoundException {
        return productService.getProductRating(productId);
    }

    @GetMapping("/user/addToCart/{id}/{quantity}")
    @PreAuthorize("hasAuthority('USER')")
    public String addToCart(@PathVariable("id") String productID,@PathVariable("quantity") int quantity) throws NoSuchElementException, NotFoundException {
        return cartService.addToCart(productID,quantity);
    }

    @GetMapping("/user/cartProducts")
    @PreAuthorize("hasAuthority('USER')")
    public Map<Products,Long> ListOfCartProducts() throws NotFoundException {
        return cartService.listOfProducts();
    }

    @DeleteMapping ("/user/removeCartItem/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public String deleteCartItem(@PathVariable("id") Long cartProductID) throws NoSuchElementException, NotFoundException {
        return cartService.deleteCartItem(cartProductID);
    }
    @GetMapping("/user/rating/{productId}/{rating}")
    @PreAuthorize("hasAuthority('USER')")
    public String rateProduct(@PathVariable("productId") Long productId, @PathVariable("rating") Double rating) throws NotFoundException {
        return productService.addRating(productId,rating);
    }

    @PostMapping("/seller/addProducts")
    @PreAuthorize("hasAuthority('SELLER')")
    public String addNewProduct(@RequestBody Products products){
        return productService.addProducts(products);
    }


    @GetMapping("/seller/products/{id}")
    @PreAuthorize("hasAuthority('SELLER')")
    public List<Products> sellerProducts(@PathVariable("id") Long id){
        return productService.sellerProducts(id);
    }

    @DeleteMapping("/seller/removeProduct/{id}")
    @PreAuthorize("hasAuthority('SELLER')")
    public String removeProduct(@PathVariable("id") Long id) throws NotFoundException {
        if(id==null) return "id is null";
        return productService.removeProduct(id);
    }
    @PostMapping("/seller/addImage")
    @PreAuthorize("hasAuthority('SELLER')")
    public String addProductImage(@ModelAttribute ImageModel imageData) throws IOException, NotFoundException {
        return productService.addProductImage(imageData);
    }
    @GetMapping("/seller/viewImage/{id}")
    @PreAuthorize("hasAuthority('SELLER')")
    public ResponseEntity<?> viewImage(@PathVariable("id") Long id) throws NoSuchElementException {
        byte[] bytes = productService.showImage(id);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(bytes);
    }

    @PostMapping("/admin/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String adminAdd(@RequestBody UserEntity userEntity){
        return userServiceInterface.adminAdd(userEntity);
    }


    @DeleteMapping("/admin/remove/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String removeSeller(@PathVariable("id") Long id) throws NotFoundException {
        if(id==null) return "id is null";
        return userServiceInterface.remove(id);
    }

}
