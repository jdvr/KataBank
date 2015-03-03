package com.bankkata;


import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.scene.control.skin.VirtualFlow;

public class AccountService {
    private Console console;
    private Clock clock;
    private List<Transaction> transactions;

    public AccountService(Console console, Clock clock) {
        this.console = console;
        this.clock = clock;
        this.transactions = new ArrayList<Transaction>();
    }

    public void deposit(int amount) {

        this.transactions.add(0, new Transaction(amount, clock.now()));
    }

    public void withdraw(int amount) {
        this.transactions.add(0, new Transaction(-amount, clock.now()));
    }

    public void printStatement() {
        printHeader();
        printRows();
    }

    private void printRows() {
        int balance = 0;
        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
            console.printLine(transaction.getDate() + " | " + transaction.getAmount() + " | " + balance);
        }
    }

    private void printHeader() {
        console.printLine("DATE | AMOUNT | BALANCE");
    }

    private class Transaction {
        private final int amount;
        private final String date;

        public Transaction(int amount, String date){
            this.amount = amount;
            this.date = date;
        }

        public int getAmount() {
            return amount;
        }

        public String getDate() {
            return date;
        }
    }








}
