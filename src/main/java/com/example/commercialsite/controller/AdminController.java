package com.example.commercialsite.controller;

import com.example.commercialsite.dto.ItemDTO;
import com.example.commercialsite.dto.request.HoldDto;
import com.example.commercialsite.dto.request.UserRegisterRequestDTO;
import com.example.commercialsite.dto.response.*;
import com.example.commercialsite.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private InventoryManagerService inventoryManagerService;

    @PostMapping(path = "/register-staff")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> registerStaff(@RequestBody UserRegisterRequestDTO userRegisterRequestDTO) throws Exception {
        return userService.registerUser(userRegisterRequestDTO);
    }
    @PostMapping("/hold-user")
    public ResponseEntity<String> holdAccount(@RequestBody HoldDto holdDto){
        return adminService.holdAccount(holdDto);
    }

    @GetMapping(path = "/get-all-users")
    public ResponseEntity<List<UsersDTO>> getAllUsers(){
        return userService.getAllUsers();
    }

//    @GetMapping(path = "/get-all-item-in-remaining-store/{status}")
//    public List<ItemRemainingResponseDto> getAllRemainingItemByAvailableStore(@PathVariable(value = "status") boolean availableStatus){
//        List<ItemRemainingResponseDto> itemRemainingResponseDtos = itemService.getAllRemainingItemByAvailableStatus(availableStatus);
//        return itemRemainingResponseDtos;
//
//    }

    @GetMapping(path = "/get-all-item-in-remaining-store/{status}")
    public ItemCountResponseDto countAllRemainingItemByAvailableStore(@PathVariable(value = "status") boolean availableStatus){
        List<ItemDTO> itemDTOS = itemService.gettAllRemainingItemByAvailableStatus(availableStatus);
        int count = itemDTOS.size();
        return new ItemCountResponseDto(count);

    }


    @GetMapping(path = "/get-all-request/{status}")
    public List<AllRequestResponseDto> getAllRequestByStatus(@PathVariable(value = "status") boolean status){
        List<AllRequestResponseDto> allRequestResponseDtos = customerService.getAllRequestByStatus(status);
        return allRequestResponseDtos;

    }

    @GetMapping(path = "/get-all-received-item/{status}")
    public List<AllRequestResponseDto> getAllReceivedItemByStatus(@PathVariable(value = "status") boolean status){
        List<AllRequestResponseDto> allRequestResponseDtos = customerService.getAllRequestByStatus(status);
        return allRequestResponseDtos;

    }

    @GetMapping("/count-shipment-status")
    public ResponseEntity<InventoryManagerTokenShippingRequestDTO> CountShipmentStatus(@RequestParam("status") int shipmentStatus) {
        InventoryManagerTokenShippingRequestDTO result = inventoryManagerService.countShipmentStatus(shipmentStatus);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }





}
