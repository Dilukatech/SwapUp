package com.example.commercialsite.utill;

import com.example.commercialsite.dto.response.getAllUsersToAdmin;
import com.example.commercialsite.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    List<getAllUsersToAdmin> entityListTogetAllUsersToAdminDtoList(List<User> users);
}