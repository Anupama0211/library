package com.epam.database;

import com.epam.entities.User;


import java.util.Map;
import java.util.HashMap;


public class UsersDatabase {
    static Map<String, User> users = new HashMap<>();
    static final String ADMIN ="Admin";

    static {
        users.put("Anupama", new User(ADMIN, "Anupama ", "abcdef"));
        users.put("Anuj", new User(ADMIN, "Anuj", "1234563"));
        users.put("Anurag", new User(ADMIN, "Anurag", "abcdef"));
    }

    private UsersDatabase() {

    }

    public static void addUser(User newUser) {
        users.put(newUser.getName(), newUser);
    }

    public static void deleteUser(String name) {
        users.remove(name);
    }


    public static Map<String, User> getUsers() {
        return users;
    }
}
