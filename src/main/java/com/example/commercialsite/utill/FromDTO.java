package com.example.commercialsite.utill;

import com.example.commercialsite.dto.request.RequestTokenRequestDto;
import com.example.commercialsite.dto.request.UserRegisterRequestDTO;
import com.example.commercialsite.entity.RequestToken;
import com.example.commercialsite.entity.Users;

public interface FromDTO {
    Users getUsers (UserRegisterRequestDTO userRegisterRequestDTO); // take an usersDTo object and return a users

    RequestToken getRequestToken (RequestTokenRequestDto requestTokenRequestDto); //
}
