package com.example.commercialsite.dto.response;

import com.example.commercialsite.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginResponse {
    private User user;
    private String jwtToken;
}

