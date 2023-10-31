package com.example.commercialsite.utill.utilimpl;

import com.example.commercialsite.dto.RequestTokenDto;
import com.example.commercialsite.dto.response.HelpRequestDto;
import com.example.commercialsite.dto.response.Inv_Mng_TokenRequestDto;
import com.example.commercialsite.dto.response.RequestTokenResponseDto;
import com.example.commercialsite.dto.response.UsersDTO;
import com.example.commercialsite.entity.*;
import com.example.commercialsite.utill.ToDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ToDTOImpl implements ToDTO {

    @Override
    public UsersDTO getUsersDTO(Users user) {

        UsersDTO dto = new UsersDTO();

        dto.setUserId( user.getUserId() );
        dto.setEmail( user.getEmail() );
        dto.setFirstName( user.getFirstName() );
        dto.setLastName( user.getLastName() );
        dto.setNic( user.getNic() );
        dto.setTelephone( user.getTelephone() );
        dto.setProfilePicture( user.getProfilePicture() );
        dto.setAddress( user.getAddress() );
        dto.setRole( user.getRole() );
        dto.setActiveStatus( user.isActiveStatus() );
        dto.setVerified( user.isVerified() );

        return dto;
    }

    @Override
    public RequestTokenResponseDto getRequestTokenResponseDto(RequestToken requestToken) {
        RequestTokenResponseDto dto = new RequestTokenResponseDto(); // new object

        dto.setRequestTokenId(requestToken.getRequestTokenId());
//        dto.setCustomerId(requestToken.getCustomerId());
        dto.setCustomerId(requestToken.getCustomerId().getUserId());
//        dto.setItemId(requestToken.getItemId());
        dto.setStatus(requestToken.getStatus());
        dto.setItemDescription(requestToken.getItemDescription());
        dto.setItemImage(requestToken.getItemImage());
        dto.setRequestDateTime(requestToken.getRequestDateTime());

        return dto;
    }

    @Override
    public HelpRequestDto getHelpRequest(HelpSupport helpSupport) {
        HelpRequestDto dto = new HelpRequestDto();

        dto.setHelpRequestId(helpSupport.getHelpRequestId());
        dto.setHelpAssistantId(helpSupport.getHelpAssistantId());
        dto.setCustomerId(helpSupport.getCustomerId());
        dto.setReceivedTime(helpSupport.getReceivedTime());
        dto.setSubject(helpSupport.getSubject());
        dto.setMessage(helpSupport.getMessage());
        dto.setReply(helpSupport.getReply());
        dto.setStatus(helpSupport.isStatus());


        return dto;
    }

    @Override
    public Inv_Mng_TokenRequestDto getAllUnprocessedSwapItems(InventoryManagerTokenRequest inventoryManagerTokenRequest) {
        Inv_Mng_TokenRequestDto dto = new Inv_Mng_TokenRequestDto();
        dto.setInventoryManagerTokenRequestId(inventoryManagerTokenRequest.getInventoryManagerTokenRequestId());
        dto.setInventoryManagerId(inventoryManagerTokenRequest.getInventoryManagerId());
        dto.setRequestTokenId(inventoryManagerTokenRequest.getRequestTokenId());
        dto.setShippedOrArrivedTime(inventoryManagerTokenRequest.getShippedOrArrivedTime());
        dto.setShipmentStatus(inventoryManagerTokenRequest.getShipmentStatus());

        return dto;
    }


    //quality checker get all request tokens
//    @Override
//    public RequestTokenDto getAllRequestToken(RequestToken requestToken) {
//        RequestTokenDto dto = new RequestTokenDto();
//        dto.setRequestTokenId(requestToken.getRequestTokenId());
//        dto.setCustomerId(requestToken.getCustomerId().getUserId());//
//        dto.setQualityCheckerId(requestToken.getQualityCheckerId().getUserId());//
//        dto.setStatus(requestToken.getStatus());
//        dto.setShippingApproval(requestToken.getShippingApproval());
//        dto.setItemDescription(requestToken.getItemDescription());
//        dto.setItemImage(requestToken.getItemImage());
//        dto.setRequestDateTime(requestToken.getRequestDateTime());
////        dto.setItem(requestToken.getItem().getItemId());//
//        return dto;
//    }

}
