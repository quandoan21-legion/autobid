// File: `src/main/java/com/autobid/autobid/Controller/WithdrawDepositRequest.java`
package com.autobid.autobid.Controller;

public class WithdrawDepositRequest {
    private Integer userId;
    private double amount;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}