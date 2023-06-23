package com.ecom.apis.service.user;

import com.ecom.apis.entity.UserEntity;
import com.ecom.apis.model.Login;
import com.ecom.apis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImplement implements UserServiceInterface {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void addUser(UserEntity userEntity) {

            userEntity.setUserPassword(passwordEncoder.encode(userEntity.getUserPassword()));
            userEntity.setConfirmPassword(passwordEncoder.encode(userEntity.getConfirmPassword()));
            userEntity.setRole("USER");
            userEntity.setVerified(0);
            if(Objects.nonNull(userRepository.save(userEntity))) System.out.println("success");;


    }



    @Override
    public String remove(Long id) {
        if(Objects.isNull(userRepository.findById(id))) return "No such seller ";
        else {
            userRepository.deleteByUserId(id);
            return "deleted";
        }
    }

    @Override
    public String adminAdd(UserEntity userEntity) {
        if(userEntity!= null && userEntity.getUserPassword()!=null && userEntity.getUserEmail()!=null
                && userEntity.getPhoneNumber()!=0 && userEntity.getAddress()!=null) {
            userEntity.setUserPassword(passwordEncoder.encode(userEntity.getUserPassword()));
            userEntity.setConfirmPassword(passwordEncoder.encode(userEntity.getConfirmPassword()));
            userEntity.setVerified(1);
            if(Objects.nonNull(userRepository.save(userEntity))) return "success";
            else return  "error";
        }
        else return "check details";
    }

    @Override
    public boolean verified(String email) {
        return userRepository.findByUserEmail(email).getVerified() ==1 ;
    }


}
