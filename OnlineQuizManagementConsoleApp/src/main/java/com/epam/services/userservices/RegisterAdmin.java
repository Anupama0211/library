package com.epam.services.userservices;

import com.epam.database.UsersDatabase;
import com.epam.entities.User;

public class RegisterAdmin implements UserService {
    @Override
    public boolean perform(String name, String password) {
        boolean status;
        if (UsersDatabase.getUsers().containsKey(name)) {
            status = false;
        } else {
            User player = new User("Admin", name, password);
            UsersDatabase.addUser(player);
            status=true;
        }
        return status;
    }
}
