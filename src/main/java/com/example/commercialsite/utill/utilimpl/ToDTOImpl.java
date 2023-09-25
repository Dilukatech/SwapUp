package com.example.commercialsite.utill.utilimpl;

import com.example.commercialsite.dto.response.RequestTokenResponseDto;
import com.example.commercialsite.dto.response.UsersDTO;
import com.example.commercialsite.entity.RequestToken;
import com.example.commercialsite.entity.Users;
import com.example.commercialsite.utill.ToDTO;
import org.springframework.stereotype.Service;

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
        dto.setCustomerId(requestToken.getCustomerId());
//        dto.setItemId(requestToken.getItemId());
        dto.setStatus(requestToken.getStatus());
        dto.setItemDescription(requestToken.getItemDescription());
        dto.setItemImage(requestToken.getItemImage());
        dto.setRequestDateTime(requestToken.getRequestDateTime());

        return dto;
    }
}
