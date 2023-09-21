package com.example.commercialsite.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsersDTO {

    private Long userId;
    private String email;
    private String firstName;
    private String lastName;
    private String nic;
    private String telephone;
    private String profilePicture;
    private String address;
    private String role;
    private boolean activeStatus;
    private boolean verified;
}
