package com.example.commercialsite.utill;

import com.example.commercialsite.dto.request.ItemSaveRequestDTO;
import com.example.commercialsite.entity.Item;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    Item dtoToEntity(ItemSaveRequestDTO itemSaveRequestDTO);
}
