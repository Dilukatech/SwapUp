package com.example.commercialsite.service;

import com.example.commercialsite.dto.request.AcceptRequestDto;
import com.example.commercialsite.dto.request.RejectRequestDto;
import com.example.commercialsite.utill.StandardResponse;
import org.springframework.http.ResponseEntity;

public interface QualityCheckerService {
    ResponseEntity<StandardResponse> AcceptRequestToken(AcceptRequestDto acceptRequestDto);
    ResponseEntity<StandardResponse>  rejectRequestToken(RejectRequestDto rejectRequestDto);

    ResponseEntity<StandardResponse> imageChecking(Long requestId,Long qualityCheckerId, int imageStatus);

    ResponseEntity<StandardResponse> getAllRequestToken();

    ResponseEntity<StandardResponse> getShippingApprovedRequestToken();
}
