package com.bankkata;


public class AccountServiceControl {
    private Console console;
    private Clock clock;
    private TransactionRepository repository;

    public AccountServiceControl(Clock clock, TransactionRepository repository, Console console) {
        this.clock = clock;
        this.repository = repository;
        this.console = console;
    }

    public void deposit(int amount) {
        this.repository.insert(transactionWith(amount));
    }

    public void withdraw(int amount) {
        this.repository.insert(transactionWith(-amount));
    }

    public void printStatement() {
        printHeader();
        printRows();
    }

    private Transaction transactionWith(int amount) {
        return new Transaction(amount, clock.now(), repository.lastTransaction());
    }

    private void printRows() {
        for (Transaction transaction : repository)
            console.printLine(transaction.getDate() + " | " + transaction.getAmount() + " | " + balance(transaction));
    }

    private int balance(Transaction transaction) {
        if(transaction == null) return  0;
        return transaction.getAmount() + balance(transaction.getPrevious());
    }

    private void printHeader() {
        console.printLine("DATE | AMOUNT | BALANCE");
    }


}
