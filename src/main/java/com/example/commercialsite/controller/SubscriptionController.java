package com.example.commercialsite.controller;

import com.example.commercialsite.dto.request.HoldDto;
import com.example.commercialsite.dto.request.UserRegisterRequestDTO;
import com.example.commercialsite.dto.response.UsersDTO;
import com.example.commercialsite.service.AdminService;
import com.example.commercialsite.service.UserService;


import java.util.List;



public class SubscriptionController {
    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @PostMapping("/handle-subscription")
    public ResponseEntity<String> holdAccount(@RequestBody HoldDto holdDto){
        return adminService.holdAccount(holdDto);
    }

//     @PostMapping(path = "/register-staff")
// //    @PreAuthorize("hasRole('ADMIN')")
//     public ResponseEntity<String> registerStaff(@RequestBody UserRegisterRequestDTO userRegisterRequestDTO) throws Exception {
//         return userService.registerUser(userRegisterRequestDTO);
//     }
//     @PostMapping("/hold-user")
//     public ResponseEntity<String> holdAccount(@RequestBody HoldDto holdDto){
//         return adminService.holdAccount(holdDto);
//     }

//     @GetMapping(path = "/get-all-users")
//     public ResponseEntity<List<UsersDTO>> getAllUsers(){
//         return userService.getAllUsers();
//     }
}
