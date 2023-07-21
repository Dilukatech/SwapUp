package com.example.commercialsite.service;

import com.example.commercialsite.dto.request.ItemSaveRequestDTO;
import org.springframework.stereotype.Service;

@Service
public interface ItemService {


    boolean saveItem(ItemSaveRequestDTO itemSaveRequestDTO);
}
