package com.example.commercialsite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "HoldUser")
@Table(name = "hold_user")
@Data
public class HoldUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hold_id")
    private Long holdId;

    @Column(name = "admin_id")
    private Long adminId;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "hold_time")
    private LocalDateTime holdTime;

    @Column(name = "reason")
    private String reason;


}
