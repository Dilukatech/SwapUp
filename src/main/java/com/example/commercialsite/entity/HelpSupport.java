package com.example.commercialsite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "HelpSupport")
@Table(name = "help_support")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HelpSupport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "help_request_id")
    private Long helpRequestId;

    @Column(name = "help_assistant_id")
    private Long helpAssistantId;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "message_received_time")
    private LocalDateTime receivedTime;

    @Column(name = "subject")
    private String subject;

    @Column(name = "message")
    private String message;

    @Column(name = "reply")
    private String reply;

    @Column(name = "status")
    private boolean status;

}
