package com.agency.Model;

import com.agency.Database.DBConnector;
import com.agency.Helper.FieldListable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class User implements FieldListable {
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

    @Override
    public Object[] getAllFields() {
        Object[] allFields = new Object[5];
        int i = 0;
        allFields[i++] = getId();
        allFields[i++] = getUsername();
        allFields[i++] = getPassword();
        allFields[i++] = getFullName();
        allFields[i++] = getCategory();
        return allFields;
    }

    public static User fetchUser(String username) {
        List<User> userList = fetchUsers("SELECT * FROM user WHERE username = '"+username+"'");
        return userList.size()>0 ? userList.get(0) : null;
    }

    public static User fetchUser(int id) {
        List<User> userList = fetchUsers("SELECT * FROM user WHERE id = "+id);
        return userList.size()>0 ? userList.get(0) : null;
    }

    public static List<User> fetchUsers() {
        return fetchUsers("SELECT * FROM user");
    }

    public static List<User> fetchUsers(String query) {
        List<User> userList = new ArrayList<>();

        try {
            ResultSet rs = DBConnector.getInstance().createStatement().executeQuery(query);
            while (rs.next()) {
                userList.add(new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("full_name"),
                        User.UserCategory.valueOf(rs.getString("category").toUpperCase(Locale.ROOT))
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

}
