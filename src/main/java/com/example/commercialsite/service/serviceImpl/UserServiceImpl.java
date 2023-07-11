package com.example.commercialsite.service.serviceImpl;

import com.example.commercialsite.entity.Customer;
import com.example.commercialsite.entity.User;
import com.example.commercialsite.repository.CustomerRepo;
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
    private CustomerRepo customerRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String getEncodedPassword(String passWord){
        return passwordEncoder.encode(passWord);
    }


    @Override
    public void registerUser(String userName, String password, String firstName, String lastName, String phoneNumber, String address) {
        // Create a new User entity
        User user = new User();
        user.setUserName(userName);
        user.setUserPassword(getEncodedPassword(password));
        user.setFirsName(firstName);
        user.setLastName(lastName);
        user.setRole("CUSTOMER");

        // Save the User entity
        User savedUser = userRepo.save(user);

        // Create a new Customer entity
        Customer customer = new Customer();
        customer.setPhoneNo(phoneNumber);
        customer.setAddress(address);
        customer.setUser(savedUser);

        // Save the Customer entity
        customerRepo.save(customer);

    }
}
