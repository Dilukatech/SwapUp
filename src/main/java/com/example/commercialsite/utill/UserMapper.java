package com.example.commercialsite.utill;

import com.example.commercialsite.dto.response.UsersDTO;
import com.example.commercialsite.entity.Users;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    List<UsersDTO> entityListTogetAllUsersToAdminDtoList(List<Users> users);
}
