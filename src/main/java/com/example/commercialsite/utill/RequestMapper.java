package com.example.commercialsite.utill;

import com.example.commercialsite.dto.response.AllRequestResponseDto;
import com.example.commercialsite.entity.RequestToken;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RequestMapper {
    List<AllRequestResponseDto> entityListToDTOList(List<RequestToken> requestTokenList);
}
