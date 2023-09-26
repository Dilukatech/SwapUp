package com.example.commercialsite.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HelpDto { //customer request help-support from help Assistant
    private Long customerId;
    private String subject;
    private String message;

}
