package com.example.commercialsite.service;

import com.example.commercialsite.entity.User;

public interface UserService {


    void registerUser(String userName, String password, String firstName, String lastName, String telephoneNumber, String address);
}
