package com.example.commercialsite.utill.utilimpl;

import com.example.commercialsite.dto.request.RequestTokenRequestDto;
import com.example.commercialsite.dto.request.UserRegisterRequestDTO;
import com.example.commercialsite.entity.RequestToken;
import com.example.commercialsite.entity.Users;
import com.example.commercialsite.utill.FromDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
class FromDTOImpl implements FromDTO { // from DTO to relevant entity object
    @Autowired
    private PasswordEncoder passwordEncoder;
    public String getEncodedPassword(String passWord){
        return passwordEncoder.encode(passWord);
    }
    @Override
    public Users getUsers(UserRegisterRequestDTO dto) {
        Users entity = new Users();
        entity.setEmail(dto.getEmail());
        entity.setPassword(getEncodedPassword(dto.getPassword()));
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setNic(dto.getNic());
        entity.setTelephone(dto.getTelephone());
        entity.setProfilePicture(dto.getProfilePicture());
        entity.setAddress(dto.getAddress());
        entity.setRole(dto.getRole());
        entity.setActiveStatus(true); // user is enabled at profile creation

        return entity;
    }

    @Override
    public RequestToken getRequestToken(RequestTokenRequestDto dto) {
        RequestToken entity = new RequestToken();

        entity.setCustomerId(dto.getCustomerId());
        entity.setItemImage(dto.getItemImage());
        entity.setItemDescription(dto.getItemDescription());

        return entity;
    }
}
