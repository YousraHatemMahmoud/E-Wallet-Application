package com.services;

import com.models.User;

public interface IUserService {
    boolean registerUser(String username, String password);
    User loginUser(String username, String password);
    User getUserByUsername(String username);
    boolean deposit(User user, double amount);
    boolean withdraw(User user, double amount);
    boolean transfer(User sender, String receiverUsername, double amount);
    void displayAllUsers();
}
