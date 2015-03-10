package com.bankkata;

public class AccountService {
    private AccountServiceControl control;

    public AccountService() {
        this.control = new AccountServiceControl(createClock(), createRepository(), createConsole());
    }

    private Console createConsole() {
        return null;
    }

    private TransactionRepository createRepository() {
        return null;
    }

    private Clock createClock() {
        return null;
    }

    public void deposit(int amount) {
        control.deposit(amount);
    }

    public void withdraw(int amount) {
        control.withdraw(amount);
    }

    public void printStatement() {
        control.printStatement();
    }
}
