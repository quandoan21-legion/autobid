package com.autobid.autobid.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @Column(name = "balance")
    private double balance;

//    @OneToMany(mappedBy = "f_user_id", fetch = FetchType.EAGER)
//    private List<car_information> carInformationList;
}