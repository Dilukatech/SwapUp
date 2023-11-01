package com.example.commercialsite.entity;

import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Transactional
@Entity(name = "InventoryManagerTokenRequest")
@Table(name = "inventory_manager_token_request")
public class InventoryManagerTokenRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_manager_token_request_id")
    private Long inventoryManagerTokenRequestId;

    @Column(name = "inventory_manager_id")
    private Long inventoryManagerId;

    // data jpa relationship
//    @OneToOne(cascade = CascadeType.ALL)
//    @ToString.Exclude // to fix circular reference problem
    @JoinColumn(name = "request_token_id")
    private Long requestTokenId;

    @Column(name = "time_created")
    private LocalDateTime timeCreated;

    @Column(name = "shipping_arrived",columnDefinition = "int default 0")
    private int shipmentStatus;
}



//public class InventoryManageTokenRequest {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "inventory_manager_token_request_id")
//    private Long InventoryManagerTokenRequestId;
//
//    // data jpa relationship
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "request_token_id")
//    @ToString.Exclude // to fix circular reference problem
//    private RequestToken requestTokenId;
//
//    @Column(name = "request_date_time")
//    private LocalDateTime requestDateTime;
//
//    @Column(name = "shipment_status")
//    private int shipmentStatus;
//
//}