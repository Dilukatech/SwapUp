package com.example.commercialsite.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CheckHelpDto {
    private Long helpRequestId;
    private Long helpAssistantId;
    private String reply;
}



