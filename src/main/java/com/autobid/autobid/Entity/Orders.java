package com.autobid.autobid.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "orders")
public class Orders {  // Correct class naming
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private int userId;  // camelCase property

    @Column(name = "auction_id")
    private int auctionId;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "total_amount")
    private double totalAmount;

    @Column(name = "commission")
    private double commission;

    @PrePersist
    @PreUpdate
    public void calculateCommission() {
        this.commission = this.totalAmount * 0.02; // 2% commission
    }
}
