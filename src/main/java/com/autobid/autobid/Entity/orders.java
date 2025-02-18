package com.autobid.autobid.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Entity
@Data
@Table(name = "orders")
public class orders {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "user_id", insertable = false, updatable = false)
    private int user_id;
    @Column(name = "auction_id", insertable = false, updatable = false)
    private int auction_id;
    @Column(name = "order_date")
    private Date order_date;
    @Column(name = "total_amount")
    private double total_amount;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
     @ToString.Exclude
    private users f_user_id;

    @OneToOne
    @JoinColumn(name = "auction_id", referencedColumnName = "id")
    private auctions f_auction_id;


}
