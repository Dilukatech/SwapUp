package com.example.commercialsite.service;

import com.example.commercialsite.dto.request.ItemSaveRequestDTO;
import com.example.commercialsite.entity.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService {
    long countItemsByAvailableStatus(boolean availableStatus);


//    boolean saveItem(ItemSaveRequestDTO itemSaveRequestDTO);



}
