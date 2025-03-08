// File: `src/main/java/com/autobid/autobid/Entity/Transaction.java`
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

    @Column(name = "user_id")
    private int userId;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "amount")
    private double amount;
}