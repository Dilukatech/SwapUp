package com.example.commercialsite.utill;

import com.example.commercialsite.dto.ItemDTO;
import com.example.commercialsite.dto.request.ItemSaveRequestDTO;
import com.example.commercialsite.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    List<ItemDTO> mapItemsToItemDTOs(List<Item> favoriteItems);
}
