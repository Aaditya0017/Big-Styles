package com.ecom.apis.service.user;

import com.ecom.apis.entity.UserEntity;
import com.ecom.apis.model.Login;
import org.springframework.stereotype.Service;


public interface UserServiceInterface {
    void addUser(UserEntity userEntity);


    String remove(Long id);

    String adminAdd(UserEntity userEntity);

    boolean verified(String email);
}
