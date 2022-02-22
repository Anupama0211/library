package com.epam.userinterface.loginui;

import com.epam.services.userservices.UserService;
import com.epam.userinterface.AdminPortalUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class UserRegistration implements Login {
    private static final Logger LOGGER = LogManager.getLogger(UserRegistration.class);

    @Override
    public void perform(Scanner scanner, UserService userService, int choice) {
        LOGGER.info("Enter Username ");
        String username = scanner.nextLine();
        LOGGER.info("Enter Password");
        String password = scanner.nextLine();
        if (userService.perform(username, password, choice)) {
            LOGGER.info("Registration Successful");
            if (choice == 3){
                new AdminPortalUI().goToTheLibraries(scanner);
            } else {
                LOGGER.info("PLAYER FUNCTIONALITIES NOT DEFINED");
            }
        } else {
            LOGGER.warn("Username already exists , try another username!");
        }
    }
}