package com.example.commercialsite.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {

    private Long userId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String nic;
    private String telephone;
    private String profilePicture;
    private String address;
    private boolean status;
    private String role;
}
