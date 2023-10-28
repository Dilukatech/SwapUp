//package com.example.commercialsite.entity;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import org.springframework.security.core.userdetails.User;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//@Getter
//@Entity
//@Table(name = "payment")
//public class Payment {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "payment_id")
//    private Long paymentId;
//
//    @Column(name = "timestamp")
//    private LocalDateTime timestamp;
//
//    @Column(name = "amount")
//    private double amount;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private Users users; // Corrected the association to User
//
//
//}
