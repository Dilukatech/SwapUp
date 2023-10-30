//package com.example.commercialsite.entity;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import org.springframework.security.core.userdetails.User;
//
//import javax.persistence.*;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//@Getter
//@Entity
//@Table(name = "customer_subscription")
//public class CustomerSubscription {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "customer_subscription_id")
//    private Long customerSubscriptionId;
//
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private Users users;
//
//    @ManyToOne
//    @JoinColumn(name = "subscription_id")
//    private Subscription subscription;
//
//    @OneToOne
//    @JoinColumn(name = "payment_id")
//    private Payment payment;
//
//}
