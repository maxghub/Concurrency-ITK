package com.itk;

public class BankAccount {

    private static int accountCounter = 1;

    private int accountNumber;
    private double balance;

    public BankAccount(double initialBalance) {
        this.accountNumber = accountCounter++;
        this.balance = initialBalance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public synchronized void deposit(double amount) {
        balance += amount;
        System.out.println("Account " + accountNumber +
                " deposited: " + amount +
                " new balance: " + balance);
    }

    public synchronized boolean withdraw(double amount) {

        if (balance >= amount) {

            balance -= amount;

            System.out.println("Account " + accountNumber +
                    " withdrew: " + amount +
                    " new balance: " + balance);

            return true;
        }

        System.out.println("Account " + accountNumber +
                " insufficient balance");

        return false;
    }

    public synchronized double getBalance() {
        return balance;
    }
}