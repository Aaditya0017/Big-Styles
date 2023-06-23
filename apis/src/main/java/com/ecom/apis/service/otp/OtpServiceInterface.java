package com.ecom.apis.service.otp;

import com.ecom.apis.entity.UserEntity;
import com.ecom.apis.model.ForgetPass;
import com.ecom.apis.model.OTP;

public interface OtpServiceInterface {
    String sendOtp(String email);

    String verify(OTP otp);

    String forgetPass(ForgetPass otp);
}
