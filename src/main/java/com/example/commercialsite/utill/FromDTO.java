package com.example.commercialsite.utill;


import com.example.commercialsite.dto.request.*;
import com.example.commercialsite.entity.*;

public interface FromDTO {
    Users getUsers (UserRegisterRequestDTO userRegisterRequestDTO); // take an usersDTo object and return a users

    RequestToken getRequestToken (RequestTokenRequestDto requestTokenRequestDto); //

    Item getItem (RequestToken requestToken, AcceptRequestDto acceptRequestDto); //

    HelpSupport setHelpRequestFromCustomer (HelpDto helpDto);

    HelpSupport checkHelpRequest(HelpSupport helpSupport, CheckHelpDto checkHelpDto);

    Swap getSwapRequest(SwapRequestDto swapRequestDto);


}
