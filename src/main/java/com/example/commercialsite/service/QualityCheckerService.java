package com.example.commercialsite.service;

import com.example.commercialsite.dto.request.AcceptRequestDto;
import com.example.commercialsite.dto.request.RejectRequestDto;
import org.springframework.http.ResponseEntity;

public interface QualityCheckerService {
    ResponseEntity<String> AcceptRequestToken(AcceptRequestDto acceptRequestDto);
    ResponseEntity<String>  rejectRequestToken(RejectRequestDto rejectRequestDto);
}
