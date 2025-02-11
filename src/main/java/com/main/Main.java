package com.main;

import com.controllers.UserController;
import com.services.IUserService;
import com.services.UserService;

public class Main {
    public static void main(String[] args) {
        IUserService userService = new UserService();
        UserController userController = new UserController(userService);
        userController.showMainMenu();
    }
}
