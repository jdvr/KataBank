package com.bankkata;

public class Transaction {
    private final int amount;
    private final String date;
    private final Transaction previous;
    public Transaction(int amount, String date, Transaction previous){
        this.amount = amount;
        this.date = date;
        this.previous = previous;
    }

    public Transaction getPrevious() {
        return previous;
    }

    public int getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }
}
