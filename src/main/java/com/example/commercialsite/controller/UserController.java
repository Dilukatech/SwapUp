package com.example.commercialsite.controller;


import com.example.commercialsite.dto.request.LoginRequest;
import com.example.commercialsite.dto.request.ProfilePictureRequest;
import com.example.commercialsite.dto.request.UserRegisterRequestDTO;
import com.example.commercialsite.dto.response.AuthResponse;
import com.example.commercialsite.dto.response.ItemDetailResponse;
import com.example.commercialsite.dto.response.MessageResponse;
import com.example.commercialsite.securityConfig.JwtService;
import com.example.commercialsite.service.UserService;
import com.example.commercialsite.services.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
@RequestMapping("/api/v1/user")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    CustomService customService;


    @PostMapping(path = "/register-customer")
    public ResponseEntity<String> registerUser(@RequestBody UserRegisterRequestDTO userRegisterRequestDTO) throws Exception {
//        userService.sendVerificationEmail(userRegisterRequest);
        return userService.registerUser(userRegisterRequestDTO);
    }



    @PostMapping({"/login"})
    public ResponseEntity<AuthResponse> createJwtTokenAndLogin(@RequestBody LoginRequest loginRequest) throws Exception {
        return jwtService.createJwtToken(loginRequest);
    }


//    @PostMapping({"/login"})
//    public ResponseEntity<?> createJwtTokenAndLogin(@RequestBody LoginRequest loginRequest) throws Exception {
//
//        return new ResponseEntity<>("LOGIN", HttpStatus.OK);
//    }

    @GetMapping(path = "/verification",params = "code")
    public ResponseEntity<?> verifyCustomer(@RequestParam(value = "code")String code) {
        return userService.verifyCustomer(code);
    }


    @GetMapping("/get-user/{id}")
    public ResponseEntity<?> getMe(@PathVariable Long id){
        AuthResponse response=customService.getMe(id);
        if(response.getUserId()!=null){
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        return new ResponseEntity<>("USER NOT FOUND",HttpStatus.INTERNAL_SERVER_ERROR);

    }


    @PutMapping(path="/update-profile-pic/{id}")
    public ResponseEntity<?> updateProfilePic(@PathVariable Long id, @RequestBody ProfilePictureRequest profilePictureRequest)throws Exception{
        MessageResponse response=customService.updateProfilePic(id,profilePictureRequest);

        if(Objects.equals(response.getMessage(), "User not found")){
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }

        if(Objects.equals(response.getMessage(), "Updated successfully")){
            return new ResponseEntity<>(response,HttpStatus.OK);
        }

        if(Objects.equals(response.getMessage(), "Server error")){
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setMessage("Server error");
        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @GetMapping("/item-detail/{itemId}")
    public ResponseEntity<?> getItemDetails(@PathVariable Long itemId){
        ItemDetailResponse response=customService.getItemDetails(itemId);

        if(Objects.equals(response.getMessage(), "All fields are required")){
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        if(Objects.equals(response.getMessage(), "Item not found")){
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        if(Objects.equals(response.getMessage(), "user found")){
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        response.setMessage("Server error");
        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
