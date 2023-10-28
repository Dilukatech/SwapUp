//package com.example.commercialsite.entity;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import org.apache.catalina.User;
//
//import javax.persistence.*;
//import java.util.List;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//@Getter
//@Entity
//@Table(name = "subscription")
//public class Subscription {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "subscription_id")
//    private Long subscriptionId;
//
//    @Column(name = "name")
//    private String name;
//
//    @Column(name = "price")
//    private double price;
//
//    @Column(name = "validity_period")
//    private int validityPeriod;
//
//    @Column(name = "active")
//    private boolean active;
//
//    @OneToMany(mappedBy = "subscription")
//    private List<CustomerSubscription> customerSubscriptions;
//
//}
