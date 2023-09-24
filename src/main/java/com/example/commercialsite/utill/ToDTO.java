package com.example.commercialsite.utill;

import com.example.commercialsite.dto.response.RequestTokenResponseDto;
import com.example.commercialsite.dto.response.UsersDTO;
import com.example.commercialsite.entity.RequestToken;
import com.example.commercialsite.entity.Users;

public interface ToDTO {
    UsersDTO getUsersDTO (Users user); // take an users object and return a usersDTo

    RequestTokenResponseDto getRequestTokenResponseDto (RequestToken requestToken); //

}
