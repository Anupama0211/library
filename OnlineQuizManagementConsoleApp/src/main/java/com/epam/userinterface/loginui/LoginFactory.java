package com.epam.userinterface.loginui;


import java.util.Optional;

public class LoginFactory {
    public Optional<Login> getLogin(int choice) {
        Login login = null;
        if (choice == 1 || choice == 2) {
            login = new UserLogin();
        } else if (choice == 3 || choice == 4) {
            login = new UserRegistration();
        }
        return Optional.ofNullable(login);
    }
}
