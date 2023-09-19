package com.example.commercialsite.utill.utilimpl;

import com.example.commercialsite.dto.response.UsersDTO;
import com.example.commercialsite.entity.Users;
import com.example.commercialsite.utill.ToDTO;
import org.springframework.stereotype.Service;

@Service
public class ToDTOImpl implements ToDTO {

    @Override
    public UsersDTO getUsersDTO(Users user) {

    UsersDTO usersDTO = new UsersDTO();

    usersDTO.setUserId( user.getUserId() );
    usersDTO.setEmail( user.getEmail() );
    usersDTO.setFirstName( user.getFirstName() );
    usersDTO.setLastName( user.getLastName() );
    usersDTO.setNic( user.getNic() );
    usersDTO.setTelephone( user.getTelephone() );
    usersDTO.setProfilePicture( user.getProfilePicture() );
    usersDTO.setAddress( user.getAddress() );
    usersDTO.setRole( user.getRole() );
    usersDTO.setActiveStatus( user.isActiveStatus() );
    usersDTO.setVerified( user.isVerified() );

    return usersDTO;
    }
}
