package com.ecom.apis.service.otp;

import com.ecom.apis.entity.UserEntity;
import com.ecom.apis.entity.UserOtp;
import com.ecom.apis.model.ForgetPass;
import com.ecom.apis.model.OTP;
import com.ecom.apis.repository.UserOtpRepository;
import com.ecom.apis.repository.UserRepository;
import com.ecom.apis.service.Mailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Random;

@Service
public class OtpServiceImplement implements OtpServiceInterface {
    @Autowired
    private UserOtpRepository userOtpRepository;
    @Autowired
    private Mailer mailer;
    @Autowired
    private UserRepository userRepository;

    @Override
    public String sendOtp(String email) {
        String otp =otpGenerator();
        UserOtp user = userOtpRepository.findUserOtpByEmail(email);
        if(Objects.isNull(user)){
            user = UserOtp.builder().code(otp).email(email).build();
        } else {
            user.setCode(otp);
        }
        userOtpRepository.save(user);
        mailer.sendMail(email,otp);
        return "success";
    }
    private String otpGenerator(){
        String numbers = "0123456789";
        Random rndm_method = new Random();
        char[] otp = new char[6];
        for (int i = 0; i < 6; i++) {
            otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
        }
        return new String(otp);
    }

    @Override
    public String verify(OTP otp) {
        UserOtp userOtp = userOtpRepository.findUserOtpByEmail(otp.getEmail());
        if (userOtp==null) return "otp not sent";
        if(otp.getOtp().equals(userOtp.getCode())) {
            UserEntity user =userRepository.findByUserEmail(otp.getEmail());
            if(user==null) return "no such user has registered";
            user.setVerified(1);
            if (Objects.nonNull(userRepository.save(user)))
            return "success";
            else return "error";
        }else {
            return "wrong";
        }
    }

    @Override
    public String forgetPass(ForgetPass otp) {
        if (otp!=null && otp.getEmail()!=null && otp.getOtp()!=null && otp.getPassword()!=null ){
            UserOtp userOtpByEmail = userOtpRepository.findUserOtpByEmail(otp.getEmail());
            if(Objects.nonNull(userOtpByEmail))
            {
                if(userOtpByEmail.getCode().equals(otp.getOtp())) {
                userRepository.updatePass(otp.getPassword(), otp.getEmail());
                return "success";} else return  "wrong otp";
            }else return "No such User";
        }else return "check_details";
    }
}
