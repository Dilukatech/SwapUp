package com.example.commercialsite.controller;

import com.example.commercialsite.dto.request.CheckHelpDto;
import com.example.commercialsite.service.HelpAssistantService;
import com.example.commercialsite.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/help-assistant")
public class HelpAssistant {
  @Autowired
  private HelpAssistantService helpAssistantService;

    @PutMapping("/CheckHelpRequestFromHelpAssistant")
    public ResponseEntity<StandardResponse> CheckHelpRequestFromHelpAssistant(@RequestBody CheckHelpDto checkHelpDto){
        return helpAssistantService.CheckHelpRequest(checkHelpDto);
    }

    @GetMapping("/GetHelpRequestFromHelpAssistant/{helpRequestId}")
    public ResponseEntity<StandardResponse> GetHelpRequestFromHelpAssistant(@PathVariable Long helpRequestId){
      return helpAssistantService.GetHelpRequestFromHelpRequestId(helpRequestId);
    }

    @GetMapping(value = "GetHelpRequestsFromStatus",params = "status")
    public ResponseEntity<StandardResponse> GetAllHelpRequestFromStatus(@RequestParam(value = "status") boolean status){
      return helpAssistantService.GetAllHelpRequestFromStatus(status);
    }

  @GetMapping(value = "GetHelpRequests")
  public ResponseEntity<StandardResponse> GetAllHelpRequests(){
    return helpAssistantService.GetAllHelpRequests();
  }



}
