package com.example.commercialsite.service.serviceImpl;

import com.example.commercialsite.entity.User;
import com.example.commercialsite.repository.UserRepo;
import com.example.commercialsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public String registerUser(User user) {
        String response = null;
        if (!userRepo.existsByUserGmailEquals(user.getUserGmail())) {
             try{
                 String hashPassword = passwordEncoder.encode(user.getPassword());
                 user.setPassword(hashPassword);
                 User saveUser = userRepo.save(user);

                 if (saveUser.getUserId()>0){
                     response =  "user register success";
                 }

              }catch (Exception e){
                 response = "an exception occurred die to" + e.getMessage();
              }
            return response;
        }else {
            return "thi gmail already exists";
        }

    }
}
