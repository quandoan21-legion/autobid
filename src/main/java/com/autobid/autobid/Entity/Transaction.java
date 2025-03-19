package com.autobid.autobid.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private users user;

    @Column(name = "user_id")
    private int userId; // Correct field name

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "amount")
    private double amount;
}
