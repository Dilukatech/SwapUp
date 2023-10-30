package com.example.commercialsite.controller;

import com.example.commercialsite.dto.request.HoldDto;
import com.example.commercialsite.dto.request.UserRegisterRequestDTO;
import com.example.commercialsite.dto.response.AllRequestResponseDto;
import com.example.commercialsite.dto.response.ItemRemainingResponseDto;
import com.example.commercialsite.dto.response.PaymentTableResponseDTO;
import com.example.commercialsite.dto.response.UsersDTO;
import com.example.commercialsite.service.*;
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
    private PaymentService paymentService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private CustomerService customerService;

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

    @GetMapping(path = "/get-all-membership-by-active-state/{status}")
    public List<PaymentTableResponseDTO> getAllPaymentsByActiveState(@PathVariable(value = "status") boolean isPayment){
        List<PaymentTableResponseDTO> paymentTableResponseDTOS = paymentService.getAllPaymentsByIsPayment(isPayment);
        return paymentTableResponseDTOS;

    }

    @GetMapping(path = "/get-all-item-in-remaining-store/{status}")
    public List<ItemRemainingResponseDto> getAllRemainingItemByAvailableStatus(@PathVariable(value = "status") boolean availableStatus){
        List<ItemRemainingResponseDto> itemRemainingResponseDtos = itemService.getAllRemainingItemByAvailableStatus(availableStatus);
        return itemRemainingResponseDtos;

    }

    @GetMapping(path = "/get-all-request/{status}")
    public List<AllRequestResponseDto> getAllRequestByStatus(@PathVariable(value = "status") boolean status){
        List<AllRequestResponseDto> allRequestResponseDtos = customerService.getAllRequestByStatus(status);
        return allRequestResponseDtos;

    }

}
