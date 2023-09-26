package com.example.commercialsite.utill;


import com.example.commercialsite.dto.request.AcceptRequestDto;
import com.example.commercialsite.dto.request.RequestTokenRequestDto;
import com.example.commercialsite.dto.request.UserRegisterRequestDTO;
import com.example.commercialsite.entity.Item;
import com.example.commercialsite.dto.request.CheckHelpDto;
import com.example.commercialsite.dto.request.HelpDto;
import com.example.commercialsite.entity.HelpSupport;

import com.example.commercialsite.entity.RequestToken;
import com.example.commercialsite.entity.Users;

import java.util.Optional;

public interface FromDTO {
    Users getUsers (UserRegisterRequestDTO userRegisterRequestDTO); // take an usersDTo object and return a users

    RequestToken getRequestToken (RequestTokenRequestDto requestTokenRequestDto); //


    Item getItem (RequestToken requestToken, AcceptRequestDto acceptRequestDto); //

    HelpSupport setHelpRequestFromCustomer (HelpDto helpDto);

    HelpSupport checkHelpRequest(HelpSupport helpSupport, CheckHelpDto checkHelpDto);


}
