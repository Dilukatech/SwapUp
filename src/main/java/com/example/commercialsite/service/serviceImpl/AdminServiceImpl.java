package com.example.commercialsite.service.serviceImpl;

import com.example.commercialsite.dto.request.HoldDto;
import com.example.commercialsite.entity.HoldUser;
import com.example.commercialsite.entity.Users;
import com.example.commercialsite.repository.HoldUserRepo;
import com.example.commercialsite.repository.UserRepo;
import com.example.commercialsite.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private HoldUserRepo holdUserRepo;

    @Override
    public ResponseEntity<String> holdAccount(HoldDto holdDto) {
        Users users = userRepo.findByUserId(holdDto.getCustomerId());

        if (users != null) { //customer is not empty
            users.setActiveStatus(holdDto.getAction());  //set the action of customer as hold or remove hold
            userRepo.save(users);

            HoldUser holdUser = new HoldUser();
            holdUser.setAdminId(holdDto.getAdminId());
            holdUser.setCustomerId(holdUser.getCustomerId());
            holdUser.setHoldTime(LocalDateTime.now());
            holdUser.setReason(holdUser.getReason());
            holdUserRepo.save(holdUser);    //saved request detail of hold user table

            return new ResponseEntity<>("request Success.", HttpStatus.OK);
        } else { // user is empty
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
