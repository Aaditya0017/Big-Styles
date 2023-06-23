package com.ecom.apis.controller;

import com.ecom.apis.Jwt.JwtService;
import com.ecom.apis.entity.ImageData;
import com.ecom.apis.entity.Products;
import com.ecom.apis.entity.UserEntity;
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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;
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

    @RequestMapping("/")
    public String welcome(){
        return "Welcome to Big-styles";
    }

    //validation checks
    @PostMapping("/registration")
    public void userRegistration (@RequestBody @Valid UserEntity userEntity){
        userServiceInterface.addUser(userEntity);
    }

    //working fine
    @GetMapping("/sendmail/{email}")
    public String sendOtp( @PathVariable("email") String email){
        System.out.println(email);
        return otpServiceInterface.sendOtp(email);
    }

    //working fine
    @PostMapping("/verify")
    public String verify (@RequestBody OTP otp)
    { System.out.println(otp.getOtp()+" "+ otp.getEmail());
          return otpServiceInterface.verify(otp);
    }

    //working fine
    @PostMapping("/authenticate")
    public String login (@RequestBody Login login){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
        if (authenticate.isAuthenticated())
            if(userServiceInterface.verified(login.getEmail()))return jwtService.generateToken(login.getEmail());
            else return "Not Verified";
        else throw new UsernameNotFoundException("Invalid Credentials");
    }


    @PostMapping("/forgetPass")
    public String forgetPass (@RequestBody ForgetPass otp)
    { System.out.println(otp.getOtp()+" "+ otp.getEmail());
        return otpServiceInterface.forgetPass(otp);
    }
    @GetMapping("/products")
    public List<Products> allProducts(){
        return productService.allProducts();
    }


    @GetMapping("/user/addToCart/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public String addToCart(@PathVariable("id") String id){
        return cartService.addToCart(id);
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
    public String removeProduct(@PathVariable("id") Long id){
        if(id==null) return "id is null";
        return productService.removeProduct(id);
    }
    @PostMapping("/seller/addImage")
    @PreAuthorize("hasAuthority('SELLER')")
    public String addProductImage(@ModelAttribute ImageModel imageData) throws IOException {
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
    public String removeSeller(@PathVariable("id") Long id){
        if(id==null) return "id is null";
        return userServiceInterface.remove(id);
    }

}
