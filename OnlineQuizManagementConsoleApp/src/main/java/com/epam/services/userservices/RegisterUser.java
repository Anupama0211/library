package com.epam.services.userservices;

import com.epam.database.UsersDatabase;
import com.epam.entities.User;

public class RegisterUser implements UserService {
    @Override
    public boolean perform(String name, String password,int choice) {
        boolean status;
        if (UsersDatabase.getUsers().containsKey(name)) {
            status = false;
        } else if(choice==3) {
            User player = new User("Admin", name, password);
            UsersDatabase.addUser(player);
            status=true;
        }else{
            User player = new User("Player", name, password);
            UsersDatabase.addUser(player);
            status=true;
        }

        return status;
    }
}
