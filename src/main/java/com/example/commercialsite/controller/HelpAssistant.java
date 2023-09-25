package com.example.commercialsite.controller;

import com.example.commercialsite.dto.request.CheckHelpDto;
import com.example.commercialsite.service.HelpAssistantService;
import com.example.commercialsite.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/help-assistant")
public class HelpAssistant {
  @Autowired
  private HelpAssistantService helpAssistantService;

    @PostMapping("/CheckHelpRequestFromHelpAssistant")
    public ResponseEntity<StandardResponse> CheckHelpRequestFromHelpAssistant(@RequestBody CheckHelpDto checkHelpDto){
        return helpAssistantService.CheckHelpRequest(checkHelpDto);
    }
}
