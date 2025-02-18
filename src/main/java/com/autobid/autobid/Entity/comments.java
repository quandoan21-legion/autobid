package com.autobid.autobid.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "comments")
public class comments {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "user_id", insertable = false, updatable = false)
    private int user_id;
    @Column(name = "car_id", insertable = false, updatable = false)
    private int car_id;
    @Column(name = "created_at", insertable = false, updatable = false)
    private Date created_at;
    @Column(name = "auction_id", insertable = false, updatable = false)
    private int auction_id;

    @ManyToOne
    @JoinColumn(name="car_id", referencedColumnName = "id")
    private car_information f_car_information_id;

    @ManyToOne
    @JoinColumn(name = "auction_id", referencedColumnName = "id")
    private auctions f_auction_id;
}
