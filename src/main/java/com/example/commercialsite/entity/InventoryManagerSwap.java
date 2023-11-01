package com.example.commercialsite.entity;

import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "InventoryManagerSwap")
@Table(name = "inventory_manager_swap")
@ToString
@Getter
@Setter
@Transactional
public class InventoryManagerSwap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "swap_id")
    private Long swapId;

    @Column(name = "inventory_manager_id")
    private Long inventoryManagerId;

    @Column(name = "time_created")
    private LocalDateTime timeCreated;

    @Column(name = "swapping_status")
    private boolean swappingStatus;

    @Column(name = "customer_id")
    private Long customerId;

}
