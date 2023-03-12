package com.agency.Model;

import com.agency.Database.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class User {
    public enum UserCategory {
        CUSTOMER,
        ADMIN;
    }

    private final String username;
    private final String password;
    private final String fullName;
    private final UserCategory category;

    public User(String username, String password, String fullName, UserCategory category) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.category = category;
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

    public static User getFetch(String username) {
        String query = "SELECT * FROM user WHERE username = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, username);
            ResultSet rs = pr.executeQuery();

            if (rs.next()) { // If a user was found
                return new User(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("full_name"),
                        UserCategory.valueOf(rs.getString("category").toUpperCase(Locale.ROOT))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
