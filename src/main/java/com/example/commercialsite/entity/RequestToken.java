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
    private Long requestTokenId;
    private Long customerId;
    private Long qualityCheckerId;
    private int status;
    private String itemDescription;
    private String itemImage;
    private LocalDateTime requestDateTime;


    // data jpa relationship
    @OneToOne(mappedBy = "requestToken", cascade = CascadeType.ALL)
    private Item item;

}
