package com.example.commercialsite.entity;

import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Transactional
@Entity(name = "Payment")
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    @Column(name = "time_stamp")
    private LocalDateTime timestamp;

    @Column(name = "amount")
    private double amount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users; // Corrected the association to User


}
