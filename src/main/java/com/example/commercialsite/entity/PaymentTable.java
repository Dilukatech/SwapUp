package com.example.commercialsite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Transactional
@Entity
@Table(name = "payment_table")
public class PaymentTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String planName;
    private LocalDateTime createdDate;
    private LocalDateTime expiredDate;
    private double price;
    private boolean isPayment=false;
    private Long userId;
}
