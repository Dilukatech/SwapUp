package com.example.commercialsite.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;
@JsonIgnoreProperties({"hibernateLazyInitializer"})// use for resolve serializer internal server error in request token shipping approval
@AllArgsConstructor
@NoArgsConstructor
//@Data     // gives a circular reference error // moving toString annotation
@ToString
@Getter
@Setter
@Transactional
@Entity(name = "Users") // hibernate annotations
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "first_name",length = 100)
    private String firstName;

    @Column(name = "last_name",length = 100)
    private String lastName;

    @Column(name = "nic",length = 13)
    private String nic;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "profile_picture")
    private String profilePicture;

    @Column(name = "address")
    private String address;

    @Column(name="role")
    private String role;

    @Getter
    @Column(name = "active_status")
    private boolean activeStatus;

    @Column(name = "verification_code", length = 64)
    private String verificationCode;

    @Column(name = "verified")
    private boolean verified;



}

