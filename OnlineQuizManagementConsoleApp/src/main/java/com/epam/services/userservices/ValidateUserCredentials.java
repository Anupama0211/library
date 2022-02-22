package com.epam.services.userservices;


import com.epam.database.UsersDatabase;
import com.epam.entities.User;

import java.util.Map;

public class ValidateUserCredentials implements UserService {

    public boolean perform(String name, String password, int choice) {
        boolean check = false;

        Map<String, User> users = UsersDatabase.getUsers();
        if (users.containsKey(name) && users.get(name).getPassword().equals(password)
               &&((choice == 1 && users.get(name).getType().equals("Admin"))
                || (choice == 2 && users.get(name).getType().equals("Player")))) {
            check = true;
        }
        return check;
    }
}

