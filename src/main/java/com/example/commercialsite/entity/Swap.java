package com.example.commercialsite.entity;

import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
//@Data     // gives a circular reference error // moving toString annotation
@ToString
@Getter
@Setter
@Transactional
@Entity(name = "Swap") // hibernate annotations
@Table(name = "swap")
public class Swap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "swap_id")
    private Long swapId;

    // data jpa relationship // owner
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "users_id")
    @ToString.Exclude // to fix circular reference problem
    private Users usersId;

    // data jpa relationship // owner
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    @ToString.Exclude // to fix circular reference problem
    private Item itemId;

    // data jpa relationship // owner
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "token_id")
    @ToString.Exclude // to fix circular reference problem
    private Token tokenId;

    // data jpa relationship // owner
    @OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "swap_id")
    @ToString.Exclude // to fix circular reference problem
    private InventoryManagerSwap inventoryManageSwap;

    @Column(name = "request_date_time")
    private LocalDateTime requestDateTime;

}
