package com.epam.services.userservices;

import java.util.Optional;

public class UserServicesFactory {
    public Optional<UserService> getUserservice(int choice) {
        UserService service = null;
        if (choice == 1 || choice == 2) {
            service = new ValidateUserCredentials();
        } else if (choice == 3 || choice == 4) {
            service = new RegisterUser();
        }
        return Optional.ofNullable(service);
    }
}
