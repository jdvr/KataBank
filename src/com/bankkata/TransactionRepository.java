package com.bankkata;


public interface TransactionRepository extends Iterable<Transaction>{
    void insert(Transaction transaction);

    Transaction lastTransaction();
}
