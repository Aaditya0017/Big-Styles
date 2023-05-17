package com.ecom.apis.service;

import com.ecom.apis.entity.UserEntity;
import com.ecom.apis.entity.UserOtp;
import com.ecom.apis.model.OTP;
import com.ecom.apis.repository.UserOtpRepository;
import com.ecom.apis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Random;

@Service
public class OtpServiceImplement implements OtpServiceInterface{
    @Autowired
    private UserOtpRepository userOtpRepository;

    @Autowired
    private Mailer mailer;

    @Override
    public String sendOtp(String email) {
        String numbers = "0123456789";
        Random rndm_method = new Random();
        char[] otp = new char[6];
        for (int i = 0; i < 6; i++) {
            otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
        }
        UserOtp user = userOtpRepository.findUserOtpByEmail(email);
        UserOtp userOtp;
        if(Objects.isNull(user)){
            userOtp = UserOtp.builder().code(new String(otp)).email(email).build();
        } else {
            userOtp = userOtpRepository.findUserOtpByEmail(email);
            userOtp.setCode(new String(otp));
        }
        userOtpRepository.save(userOtp);
        mailer.sendMail(email,String.valueOf(otp));
        return "success";
    }

    @Override
    public String verify(OTP otp) {
        UserOtp userOtp = userOtpRepository.findUserOtpByEmail(otp.getEmail());
        if(otp.getOtp().equals(userOtp.getCode())) {
            userOtp.setStatus(true);
            userOtpRepository.save(userOtp);
            return "success";
        }else {
            return "wrong";
        }
    }
}
