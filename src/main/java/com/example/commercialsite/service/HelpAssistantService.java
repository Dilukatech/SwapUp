package com.example.commercialsite.service;

import com.example.commercialsite.dto.request.CheckHelpDto;
import com.example.commercialsite.utill.StandardResponse;
import org.springframework.http.ResponseEntity;

public interface HelpAssistantService {
    ResponseEntity<StandardResponse> CheckHelpRequest(CheckHelpDto checkHelpDto);
}
