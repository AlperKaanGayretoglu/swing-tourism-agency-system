package com.agency.Model;

public class User {
    public enum UserCategory {
        CUSTOMER,
        ADMIN;
    }

    private final int id;
    private final String username;
    private final String password;
    private final String fullName;
    private final UserCategory category;

    public User(int id, String username, String password, String fullName, UserCategory category) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public UserCategory getCategory() {
        return category;
    }

}
