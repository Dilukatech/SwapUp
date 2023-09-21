package com.example.commercialsite.utill.utilimpl;

import com.example.commercialsite.dto.request.UserRegisterRequestDTO;
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
    public Users getUsers(UserRegisterRequestDTO userRegisterRequestDTO) {
        Users users = new Users();
        users.setEmail(userRegisterRequestDTO.getEmail());
        users.setPassword(getEncodedPassword(userRegisterRequestDTO.getPassword()));
        users.setFirstName(userRegisterRequestDTO.getFirstName());
        users.setLastName(userRegisterRequestDTO.getLastName());
        users.setNic(userRegisterRequestDTO.getNic());
        users.setTelephone(userRegisterRequestDTO.getTelephone());
        users.setProfilePicture(userRegisterRequestDTO.getProfilePicture());
        users.setAddress(userRegisterRequestDTO.getAddress());
        users.setRole(userRegisterRequestDTO.getRole());
        users.setActiveStatus(true); // user is enabled at profile creation

        return users;
    }
}
