package com.example.commercialsite.service;

import com.example.commercialsite.dto.ItemDTO;
import com.example.commercialsite.dto.request.ItemSaveRequestDTO;
import com.example.commercialsite.dto.response.ItemRemainingResponseDto;
import com.example.commercialsite.entity.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService {



    //List<ItemRemainingResponseDto> getAllRemainingItemByAvailableStatus(boolean availableStatus);

    List<ItemDTO> gettAllRemainingItemByAvailableStatus(boolean availableStatus);


//    boolean saveItem(ItemSaveRequestDTO itemSaveRequestDTO);



}
