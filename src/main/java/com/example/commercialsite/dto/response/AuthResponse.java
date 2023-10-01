package com.example.commercialsite.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AuthResponse {

    private Long userId;
    private String email;
    private String firstName;
    private String lastName;
    private String nic;
    private String telephone;
    private String profilePicture;
    private String address;
    private String role;
    private String token;
}
