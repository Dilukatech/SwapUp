package com.example.commercialsite.utill;

import com.example.commercialsite.dto.ItemDTO;
import com.example.commercialsite.dto.response.ItemRemainingResponseDto;
import com.example.commercialsite.entity.Item;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    List<ItemDTO> mapItemsToItemDTOs(List<Item> favoriteItems);

    List<ItemRemainingResponseDto> entityListToDtoList(List<Item> itemList);
}
