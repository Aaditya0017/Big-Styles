package com.ecom.apis.controller;

import com.ecom.apis.entity.UserEntity;
import com.ecom.apis.model.OTP;
import com.ecom.apis.service.OtpServiceInterface;
import com.ecom.apis.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080/")
@RestController
public class ApiController {
    @Autowired
    private UserServiceInterface userServiceInterface;
    @Autowired
    private OtpServiceInterface otpServiceInterface;

    @RequestMapping("/")
    public String welcome(){
        return "welcome to spring";
    }
    @PostMapping("/registration")
    public void userRegistration (@RequestBody UserEntity userEntity){
        userServiceInterface.addUser(userEntity);
    }
    @PostMapping("/verify")
    public String verify (@RequestBody OTP otp)
    { System.out.println(otp.getOtp()+" "+ otp.getEmail());
          return otpServiceInterface.verify(otp);
    }

    @GetMapping("/sendmail/{email}")
    public String sendOtp( @PathVariable("email") String email){
        System.out.println(email);
        return otpServiceInterface.sendOtp(email);
    }
}
