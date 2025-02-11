package com.models;

public class User {
    private String username;
    private String password;
    private double balance;

    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
        this.balance = 0.0;
    }

    public String getUsername()
    {
        return username;
    }

    public boolean checkPassword(String password)
    {
        return this.password.equals(password);
    }

    public double getBalance()
    {
        return balance;
    }

    public void deposit(double amount)
    {
        if(amount > 0)
        {
            balance += amount;
        }
    }

    public boolean withdraw(double amount)
    {
        if (amount > 0 && amount <= balance)
        {
            balance -= amount;
            return true;
        }

        return false;
    }
}
