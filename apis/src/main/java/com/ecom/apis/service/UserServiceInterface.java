package com.ecom.apis.service;

import com.ecom.apis.entity.UserEntity;
import org.springframework.stereotype.Service;


public interface UserServiceInterface {
    void addUser(UserEntity userEntity);

}
