package com.example.commercialsite.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SubscribedResponse {

    private boolean isSubscribed;
    private String planName;
    private LocalDateTime createdDate;
    private LocalDateTime expiredDate;
}
