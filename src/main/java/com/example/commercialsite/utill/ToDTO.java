package com.example.commercialsite.utill;


import com.example.commercialsite.dto.response.HelpRequestDto;
import com.example.commercialsite.dto.response.Inv_Mng_TokenRequestDto;
import com.example.commercialsite.dto.response.RequestTokenResponseDto;
import com.example.commercialsite.dto.response.UsersDTO;
import com.example.commercialsite.entity.HelpSupport;
import com.example.commercialsite.entity.InventoryManagerTokenRequest;
import com.example.commercialsite.entity.RequestToken;
import com.example.commercialsite.entity.Users;

public interface ToDTO {
    UsersDTO getUsersDTO (Users user); // take an users object and return a usersDTo

    RequestTokenResponseDto getRequestTokenResponseDto (RequestToken requestToken); //

    HelpRequestDto getHelpRequest(HelpSupport helpSupport);

    Inv_Mng_TokenRequestDto getAllUnprocessedSwapItems(InventoryManagerTokenRequest inventoryManagerTokenRequest);

//    RequestTokenDto getAllRequestToken(RequestToken requestToken);
}
