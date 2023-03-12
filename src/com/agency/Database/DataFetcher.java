package com.agency.Database;

import com.agency.Model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DataFetcher {
    private DataFetcher() {}

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
