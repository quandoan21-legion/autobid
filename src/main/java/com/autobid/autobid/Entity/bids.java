package com.autobid.autobid.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "bids")
public class bids {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "auction_id")
    private int auction_id;

    @Column(name = "bid_amount")
    private double bid_amount;

    @Column(name = "bid_time")
    private Date bid_time;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private users f_user_id;

    @ManyToOne
    @JoinColumn(name = "auction_id", referencedColumnName = "id", insertable = false, updatable = false)
    private car_information f_car_information;
}
