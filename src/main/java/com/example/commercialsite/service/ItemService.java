package com.example.commercialsite.service;

import com.example.commercialsite.dto.request.ItemSaveRequestDTO;
import org.springframework.stereotype.Service;

@Service
public interface ItemService {

    void saveItem(com.example.commercialsite.dto.request.ItemSaveRequestDTO itemSaveRequestDTO);
}
