package com.example.commercialsite.service.serviceImpl;

import com.example.commercialsite.dto.request.ItemSaveRequestDTO;
import com.example.commercialsite.entity.Item;
import com.example.commercialsite.repository.ItemRepo;
import com.example.commercialsite.service.ItemService;
import com.example.commercialsite.utill.ItemMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ItemMapper itemMapper;

//    @Override
//    public boolean saveItem(ItemSaveRequestDTO itemSaveRequestDTO) {
//        try {
//            // Save the item entity in the database
//            Item item = modelMapper.map(itemSaveRequestDTO, Item.class);
//            itemRepo.save(item);
//            return true; // Item saved successfully
//        }catch(Exception e) {
//            // Handle any exceptions that occurred during the save operation
//            e.printStackTrace();
//            return false; // Failed to save the item
//        }
//    }


}
