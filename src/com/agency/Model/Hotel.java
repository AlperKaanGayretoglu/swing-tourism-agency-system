package com.agency.Model;

import com.agency.Database.DBConnector;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class Hotel {
    public final int id;
    public final String name;
    public final String city;
    public final String region;
    public final String address;
    public final String email;
    public final String phone;
    public final int star;
    public final Date summerPeriodStart;
    public final Date summerPeriodEnd;
    public final Date winterPeriodStart;
    public final Date winterPeriodEnd;

    public Hotel(int id, String name, String city, String region, String address, String email, String phone, int star, Date summerPeriodStart, Date summerPeriodEnd, Date winterPeriodStart, Date winterPeriodEnd) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.region = region;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.star = star;
        this.summerPeriodStart = summerPeriodStart;
        this.summerPeriodEnd = summerPeriodEnd;
        this.winterPeriodStart = winterPeriodStart;
        this.winterPeriodEnd = winterPeriodEnd;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getRegion() {
        return region;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public int getStar() {
        return star;
    }

    public Date getSummerPeriodStart() {
        return summerPeriodStart;
    }

    public Date getSummerPeriodEnd() {
        return summerPeriodEnd;
    }

    public Date getWinterPeriodStart() {
        return winterPeriodStart;
    }

    public Date getWinterPeriodEnd() {
        return winterPeriodEnd;
    }

    public static Hotel getFetch(int id) {
        String query = "SELECT * FROM hotel WHERE id = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();

            if (rs.next()) {
                return new Hotel(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("city"),
                        rs.getString("region"),
                        rs.getString("address"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getInt("star"),
                        rs.getDate("summer_period_start"),
                        rs.getDate("summer_period_end"),
                        rs.getDate("winter_period_start"),
                        rs.getDate("winter_period_end")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
