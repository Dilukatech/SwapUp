package com.example.commercialsite.controller;

import com.example.commercialsite.dto.request.HoldDto;
import com.example.commercialsite.dto.request.UserRegisterRequestDTO;
import com.example.commercialsite.dto.response.AdminDashboardResponse;
import com.example.commercialsite.dto.response.UsersDTO;
import com.example.commercialsite.entity.Customer;
import com.example.commercialsite.entity.PaymentTable;
import com.example.commercialsite.entity.RequestToken;
import com.example.commercialsite.repository.CustomerRepo;
import com.example.commercialsite.repository.PaymentTableRepository;
import com.example.commercialsite.repository.RequestTokenRepo;
import com.example.commercialsite.service.*;
import com.example.commercialsite.utill.StandardResponse;
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
    RequestTokenRepo requestTokenRepo;


    @Autowired
    private InventoryManagerService inventoryManagerService;

    @Autowired
    private RequestTokenService requestTokenService;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private PaymentTableRepository paymentTableRepository;


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


    @GetMapping("/admin-dashboard-data")
    public  ResponseEntity<?> getAdminData(){
        AdminDashboardResponse adminDashboardResponse=new AdminDashboardResponse();

        // Total Request
        List< RequestToken> totalRequestArray = requestTokenRepo.findAll();
        long total= totalRequestArray.size();
        adminDashboardResponse.setTotalRequests(total);

        // Rejected Request
        List<RequestToken> re=requestTokenRepo.findAllByStatus(-1);
        long rej1=re.size();

        List<RequestToken> total1RejectArray = requestTokenRepo.findAllByShippingApproval(-1);
        long rej2= total1RejectArray.size();
        long totals= rej1+rej2;

        adminDashboardResponse.setRejectedRequests(totals);

        //Accepted Requests
        List<RequestToken> totalAcceptedArray = requestTokenRepo.findAllByStatus(1);
        long totalAcceptedRequests = totalAcceptedArray.size();
        adminDashboardResponse.setAcceptedRequests(totalAcceptedRequests);


        //Total Swaps

        //Received Items

        //Total Customer
        List<Customer> totalCustomerArray = customerRepo.findAll();
        long totalCustomers = totalCustomerArray.size();
        adminDashboardResponse.setTotalCustomers(totalCustomers);

        //Total Profit
        List<PaymentTable> totalPayments = paymentTableRepository.findByIsPaymentTrue();
        double totalPrice = 0.0;

        for (PaymentTable payment : totalPayments) {
                totalPrice += payment.getPrice(); // Add the price to the total
        }
        adminDashboardResponse.setTotalProfit(totalPrice);

        //count gold membership
        List<PaymentTable> goldPayments = paymentTableRepository.findByIsPaymentIsTrueAndPlanName("gold");
        double totalGoldPayment = goldPayments.stream().mapToDouble(PaymentTable::getPrice).sum();
        adminDashboardResponse.setTotalGoldMembership(totalGoldPayment);

        //count platinum Membership
        List<PaymentTable> platinumPayments = paymentTableRepository.findByIsPaymentIsTrueAndPlanName("platinum");
        double totalPlatinumPayment = platinumPayments.stream().mapToDouble(PaymentTable::getPrice).sum();
        adminDashboardResponse.setPlatinumMembership(totalPlatinumPayment);


        //count Diamond Membership
        List<PaymentTable> diamondPayments = paymentTableRepository.findByIsPaymentIsTrueAndPlanName("diamond");
        double totalDiamondPayment = platinumPayments.stream().mapToDouble(PaymentTable::getPrice).sum();
        adminDashboardResponse.setDiamondMembership(totalDiamondPayment);


        return new ResponseEntity<>(adminDashboardResponse, HttpStatus.OK);
    }


}


