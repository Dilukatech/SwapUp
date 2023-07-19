package com.example.commercialsite.service.serviceImpl;

import com.example.commercialsite.dto.request.ItemSaveRequestDTO;
import com.example.commercialsite.entity.Item;
import com.example.commercialsite.repository.ItemRepo;
import com.example.commercialsite.service.ItemService;
import com.example.commercialsite.utill.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceIMPL implements ItemService {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ItemMapper itemMapper;


    @Override
    public void saveItem(ItemSaveRequestDTO itemSaveRequestDTO) {
        Item item = itemMapper.dtoToEntity(itemSaveRequestDTO);
        itemRepo.save(item);
    }
}
