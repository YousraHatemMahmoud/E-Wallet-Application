package com.services;

import com.models.User;

import java.util.HashMap;
import java.util.Map;

public class UserService implements IUserService {

    private Map<String, User> users = new HashMap<>();

    //Register a new user
    @Override
    public boolean registerUser(String username, String password)
    {
        if(!users.containsKey(username))
        {
            users.put(username, new User(username, password));
            return true; // Registration successful
        }
        return false; // Username already exists
    }

    // Authenticate user
    @Override
    public User loginUser(String username, String password)
    {
        User user = users.get(username);
        return (user != null && user.checkPassword(password)) ? user : null;
    }

    // Get user by username
  @Override
    public User getUserByUsername(String username)
    {
        return  users.get(username);
    }

    @Override
    public boolean deposit(User user, double amount)
    {
        if(user != null && amount > 0)
        {
            user.deposit(amount);
            return true;
        }

        return false;
    }

    @Override
    public boolean withdraw(User user, double amount)
    {
        if(user != null)
        {
            return user.withdraw(amount);
        }

        return false;
    }

    @Override
    public boolean transfer(User sender, String receiverUsername, double amount) {

        User receiver = users.get(receiverUsername);

        if (sender == null) {
            System.out.println("Transfer failed: Sender does not exist.");
            return false;
        }

        if (receiver == null) {
            System.out.println("Transfer failed: Receiver not found.");
            return false;
        }

        if (sender.withdraw(amount)) {
            receiver.deposit(amount);
            System.out.println("Transfer successful: $" + amount + " transferred from " + sender.getUsername() + " to " + receiver.getUsername());
            return true;
        }

        System.out.println("Transfer failed: Insufficient balance.");
        return false;
    }

    @Override
    public void displayAllUsers() {
        if (users.isEmpty()) {
            System.out.println("No users found!");
            return;
        }

        System.out.println("List of all users and their balances:");
        System.out.println("-------------------------------------------------");
        for (User user : users.values()) {
            System.out.println("Username: " + user.getUsername() + " | Balance: $" + user.getBalance());
        }
        System.out.println("-------------------------------------------------");
    }


}
