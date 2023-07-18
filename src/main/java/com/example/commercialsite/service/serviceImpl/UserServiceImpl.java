package com.example.commercialsite.service.serviceImpl;

import com.example.commercialsite.dto.CustomerRegisterRequest;
import com.example.commercialsite.entity.Customer;
import com.example.commercialsite.entity.User;
import com.example.commercialsite.repository.CustomerRepo;
import com.example.commercialsite.repository.UserRepo;
import com.example.commercialsite.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public String registerCustomer(CustomerRegisterRequest customerRegisterRequest) {
        if(!userRepo.existsByEmailEquals(customerRegisterRequest.getEmail())){
            User user = modelMapper.map(customerRegisterRequest,User.class);
            user.setPassword(getEncodedPassword(customerRegisterRequest.getPassword()));
            user.setRole("CUSTOMER");
            User savedUser = userRepo.save(user);
            return "saved";
        }else {
            return "this user name already exist";
        }
    }
}
