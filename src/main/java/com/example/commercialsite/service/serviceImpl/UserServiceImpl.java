package com.example.commercialsite.service.serviceImpl;

import com.example.commercialsite.dto.request.UserRegisterRequest;
import com.example.commercialsite.entity.User;
import com.example.commercialsite.repository.CustomerRepo;
import com.example.commercialsite.repository.UserRepo;
import com.example.commercialsite.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    public String getEncodedPassword(String passWord){
        return passwordEncoder.encode(passWord);
    }

    @Override
    public String registerUser(UserRegisterRequest userRegisterRequest) {
        if (!userRepo.existsByEmailEquals(userRegisterRequest.getEmail())) {
            if (userRegisterRequest.getRole() == null || userRegisterRequest.getRole() == "") {
                User user = modelMapper.map(userRegisterRequest, User.class);
                user.setPassword(getEncodedPassword(userRegisterRequest.getPassword()));
                user.setRole("CUSTOMER");
                userRepo.save(user);
                return "saved customer";
            } else {
                User user = modelMapper.map(userRegisterRequest, User.class);
                user.setPassword(getEncodedPassword(userRegisterRequest.getPassword()));
                userRepo.save(user);
                return "saved " + user.getRole();
            }
        }else{
            return "this user name already exist";
        }

    }
}
