package com.ellison.model;

import java.sql.Timestamp;

public class Transaction {
    private String txnId;
    private String username;
    private Timestamp time;
    private double amount;

    public Transaction() {}

    public String getTxnId() { return txnId; }
    public void setTxnId(String txnId) { this.txnId = txnId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public Timestamp getTime() { return time; }
    public void setTime(Timestamp time) { this.time = time; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}
