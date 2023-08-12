package com.example.commercialsite.service;

import com.example.commercialsite.dto.request.ItemSaveRequestDTO;
import com.example.commercialsite.entity.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService {


    boolean saveItem(ItemSaveRequestDTO itemSaveRequestDTO);

    List<Item> getAllItems();

}
