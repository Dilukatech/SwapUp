package com.example.commercialsite.service.serviceImpl;

import com.example.commercialsite.dto.response.AdminDashboardResponse;
import com.example.commercialsite.repository.RequestTokenRepo;
import com.example.commercialsite.service.RequestTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RequestTokenImpl implements RequestTokenService {

   @Autowired
   private RequestTokenRepo requestTokenRepo;




}
