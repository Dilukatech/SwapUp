package com.example.commercialsite.controller;

import com.example.commercialsite.dto.request.HoldDto;
import com.example.commercialsite.dto.request.UserRegisterRequestDTO;
import com.example.commercialsite.dto.response.UsersDTO;
import com.example.commercialsite.service.*;
import com.example.commercialsite.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private RequestTokenService requestTokenService;


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


    // Reaming Item in store
    @GetMapping(path = "/get-all-item-in-remaining-store/{status}")
    public StandardResponse countItemsByAvailableStatus(@RequestParam("availableStatus") boolean availableStatus) {
        long count = itemService.countItemsByAvailableStatus(availableStatus);
        return new StandardResponse(200, "Success", (int) count);
    }


    // All requests count
    @GetMapping("/count-all-requests")
    public ResponseEntity<Long> getCountOfRequests() {
        return requestTokenService.getCountOfRequestTokens();
    }

    // Accept request count
    @GetMapping("/count-accept-requests")
    public ResponseEntity<Long> getCountOfRequestTokensWithStatusOne() {
        return requestTokenService.getCountOfRequestTokensWithStatusOne();
    }

    // Reject request count
    @GetMapping("/count-reject-requests")
    public ResponseEntity<Long> getCountOfRequestTokensWithStatusAndShippingApproval() {
        return requestTokenService.getTotalCountOfRequestTokensWithStatusAndShippingApproval();
    }



    // Rejected Request
//    @GetMapping(path = "/get-all-request/{status}")
//    public StandardResponse countAllRequestByStatus(@PathVariable(value = "status") int shipmentStatus){
//        long count = .countRequestByStatus(shipmentStatus);
//        return new StandardResponse(200, "Success", (int) count);
//    }


    // How Many Item Swap for InventoryManagerSwap
}


