package com.itk;

import java.util.*;

public class ConcurrentBank {

    private List<BankAccount> accounts = new ArrayList<>();

    public synchronized BankAccount createAccount(double initialBalance) {

        BankAccount account = new BankAccount(initialBalance);

        accounts.add(account);

        System.out.println("Created account " +
                account.getAccountNumber() +
                " with balance " + initialBalance);

        return account;
    }

    public void transfer(BankAccount from, BankAccount to, double amount) {

        synchronized (from) {

            synchronized (to) {

                if (from.withdraw(amount)) {

                    to.deposit(amount);

                    System.out.println("Transferred " + amount +
                            " from Account " + from.getAccountNumber() +
                            " to Account " + to.getAccountNumber());
                }
            }
        }
    }

    public synchronized double getTotalBalance() {

        double total = 0;

        for (BankAccount acc : accounts) {
            total += acc.getBalance();
        }

        return total;
    }
}