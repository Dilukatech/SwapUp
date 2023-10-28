package com.example.commercialsite.dto.request;

import com.example.commercialsite.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentRequestDto {


    private Long userId;
    private double subscriptionPlan;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
