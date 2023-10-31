package com.example.commercialsite.service.serviceImpl;

import com.example.commercialsite.dto.ItemDTO;
import com.example.commercialsite.dto.request.ItemSaveRequestDTO;
import com.example.commercialsite.dto.response.InventoryManagerTokenShippingRequestDTO;
import com.example.commercialsite.dto.response.ItemRemainingResponseDto;
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
    private ItemRepo itemRepo;

    @Autowired
    private ItemMapper itemMapper;


//    @Override
//    public List<ItemRemainingResponseDto> getAllRemainingItemByAvailableStatus(boolean availableStatus) {
//        List<Item> itemList = itemRepo.findAllByAvailableStatus(availableStatus);
//        List<ItemRemainingResponseDto> itemRemainingResponseDtoList = itemMapper.entityListToDtoList(itemList);
//        return itemRemainingResponseDtoList;
//    }

    @Override
    public List<ItemDTO> gettAllRemainingItemByAvailableStatus(boolean availableStatus) {
        List<Item> itemList = itemRepo.findAllByAvailableStatus(availableStatus);
        List<ItemDTO> itemDTOList =itemMapper.kentityListToDtoList(itemList);
        return null;
    }
}
