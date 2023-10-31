package com.example.commercialsite.entity;

import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Transactional
@Entity(name = "CustomerSubscription") // hibernate annotations
@Table(name = "customer_subscription")
//public class CustomerSubscription implements Serializable {
public class CustomerSubscription implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_subscription_id")
    private Long customerSubscriptionId;

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @OneToOne
    @JoinColumn(name = "user_id")
    private Users userId;

    @OneToOne
    @JoinColumn(name = "subscription_id")
    private Subscription subscriptionId;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment paymentId;

    @Column(name = "count")
    private int count; // allowed swap count for customer

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "state")
    private boolean state;

}
