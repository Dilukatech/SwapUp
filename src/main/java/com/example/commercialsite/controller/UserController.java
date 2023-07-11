package com.example.commercialsite.controller;


import com.example.commercialsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping(path = "/register" ,
            params = {"userName","password","firstName","lastName","telephoneNumber","address"}

    )

    public String registerUser( String userName,
                                String password,
                                String firstName,
                                String lastName,
                                String telephoneNumber,
                                String address) {

        userService.registerUser(userName, password,firstName,lastName,telephoneNumber, address);
        return "redirect:/login";
    }

}
