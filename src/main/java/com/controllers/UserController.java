package com.controllers;

import com.models.User;
import com.services.IUserService;

import java.util.Scanner;

public class UserController {

    private IUserService userService;
    private Scanner scanner;
    private User loggedInUser;

    public UserController(IUserService userService)
    {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
        this.loggedInUser = null;
    }

    public void showMainMenu()
    {
        while (true)
        {
            if(loggedInUser == null)
            {
                System.out.println("\n=== E-Wallet Application ===");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");

                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice)
                {
                    case 1 -> registerUser();
                    case 2 -> loginUser();
                    case 3 -> {
                        System.out.println("Exiting... Goodbye!");
                        System.exit(0);
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }
            }
            else {
                showUserMenu();
            }
        }
    }

    private void showUserMenu()
    {
        System.out.println("\n=== User Menu ===");
        System.out.println("1. Deposit Money");
        System.out.println("2. Withdraw Money");
        System.out.println("3. Transfer Money");
        System.out.println("4. Show All Users & Balances");
        System.out.println("5. Logout");

        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> depositMoney();
            case 2 -> withdrawMoney();
            case 3 -> transferMoney();
            case 4 -> userService.displayAllUsers();
            case 5 -> {
                loggedInUser = null;
                System.out.println("Logged out successfully.");
            }
            default -> System.out.println("Invalid option. Please try again.");
        }
    }

    private void registerUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (userService.registerUser(username, password)) {
            System.out.println("Registration successful! You can now log in.");
        } else {
            System.out.println("Username already exists. Try another one.");
        }
    }

    private void loginUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = userService.loginUser(username, password);
        if (user != null) {
            loggedInUser = user;
            System.out.println("Login successful! Welcome, " + user.getUsername() + ".");
        } else {
            System.out.println("Invalid username or password. Try again.");
        }
    }

    private void depositMoney() {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if (userService.deposit(loggedInUser, amount)) {
            System.out.println("Deposit successful! New balance: $" + loggedInUser.getBalance());
        } else {
            System.out.println("Invalid amount. Try again.");
        }
    }

    private void withdrawMoney() {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if (userService.withdraw(loggedInUser, amount)) {
            System.out.println("Withdrawal successful! New balance: $" + loggedInUser.getBalance());
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    private void transferMoney() {
        System.out.print("Enter recipient username: ");
        String recipientUsername = scanner.nextLine();

        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if (userService.transfer(loggedInUser, recipientUsername, amount)) {
            System.out.println("Transfer successful! Your new balance: $" + loggedInUser.getBalance());
        } else {
            System.out.println("Transfer failed. Check balance and recipient username.");
        }
    }



}
