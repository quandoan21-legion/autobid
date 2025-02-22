package com.autobid.autobid.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "bids")
public class bids {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "user_id" , insertable = false, updatable = false)
    private int user_id;
    @Column(name = "auction_id", insertable = false, updatable = false)
    private int auction_id;
    @Column(name = "bid_amount")
    private double bid_amount;
    @Column(name = "bid_time")
    private Date bid_time;
//
//    @ManyToOne
//    @JoinColumn(name = "auction_id", referencedColumnName = "id")
//    private auctions f_auction_id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private users f_user_id;
}
