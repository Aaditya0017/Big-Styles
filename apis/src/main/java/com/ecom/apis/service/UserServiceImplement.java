package com.ecom.apis.service;

import com.ecom.apis.entity.UserEntity;
import com.ecom.apis.entity.UserOtp;
import com.ecom.apis.repository.UserOtpRepository;
import com.ecom.apis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserServiceImplement implements UserServiceInterface{
    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }


}
