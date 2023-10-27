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
@Entity(name = "InventoryManageTokenRequest") // hibernate annotations
@Table(name = "inventoryManageTokenRequest")
public class InventoryManageTokenRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_manager_token_request_id")
    private Long InventoryManagerTokenRequestId;

    // data jpa relationship
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "request_token_id")
    @ToString.Exclude // to fix circular reference problem
    private RequestToken requestToken;

    @Column(name = "request_date_time")
    private LocalDateTime requestDateTime;

    @Column(name = "shipment_status")
    private int shipmentStatus;

}
