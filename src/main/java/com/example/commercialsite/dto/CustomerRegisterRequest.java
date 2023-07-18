package com.example.commercialsite.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerRegisterRequest {
    private String email;
    private String password;
    private String firsName;
    private String lastName;
    private String nic;
    private String telephone;
    private String profilePicture;
    private String address;

}
