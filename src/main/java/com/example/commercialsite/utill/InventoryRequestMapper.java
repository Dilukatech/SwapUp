package com.example.commercialsite.utill;

import com.example.commercialsite.dto.response.InventoryManagerTokenShippingRequestDTO;
import com.example.commercialsite.entity.InventoryManagerTokenRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InventoryRequestMapper {

    List<InventoryManagerTokenShippingRequestDTO> entityListToDtoList(List<InventoryManagerTokenRequest> inventoryManagerTokenRequests);
}
