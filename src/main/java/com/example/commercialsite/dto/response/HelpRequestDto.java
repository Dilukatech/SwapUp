package com.example.commercialsite.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HelpRequestDto {
    private Long helpRequestId;
    private Long helpAssistantId;
    private Long customerId;
    private LocalDateTime receivedTime;
    private String subject;
    private String message;
    private String reply;
    private boolean status;
}
