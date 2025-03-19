package com.autobid.autobid.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "watchlist")
public class watchlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "listing_id")
    private int listingId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private users user;

    @ManyToOne
    @JoinColumn(name = "listing_id", referencedColumnName = "id", insertable = false, updatable = false)
    private car_information listing;
}
