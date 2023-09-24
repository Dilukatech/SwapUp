package com.example.commercialsite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity(name = "RequestToken")
@Table(name = "request_token")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_token_id")
    private Long requestTokenId;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "quality_checker_id")
    private Long qualityCheckerId;

    @Column(name = "status")
    private int status;

    @Column(name = "item_Description")
    private String itemDescription;

    @Column(name = "item_image")
    private String itemImage;

    @Column(name = "request_date_time")
    private LocalDateTime requestDateTime;


    // data jpa relationship
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "request_token_item_id", referencedColumnName = "item_id")
    private Item item;

}
