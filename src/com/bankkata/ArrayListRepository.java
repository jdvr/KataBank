package com.bankkata;

import java.util.ArrayList;

public class ArrayListRepository extends ArrayList<Transaction> implements TransactionRepository {
    @Override
    public void insert(Transaction transaction) {
        this.add(0, transaction);
    }

    @Override
    public Transaction lastTransaction() {
        if (this.isEmpty()) return null;
        return this.get(0);
    }
}
