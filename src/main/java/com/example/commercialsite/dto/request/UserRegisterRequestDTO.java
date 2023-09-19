package com.example.commercialsite.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRegisterRequestDTO {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String nic;
    private String telephone;
    private String profilePicture;
    private String address;
    private String role;
}
