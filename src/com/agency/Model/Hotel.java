package com.agency.Model;

import com.agency.Database.DBConnector;
import com.agency.Helper.FieldListable;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Hotel implements FieldListable {
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

    @Override
    public Object[] getAllFields() {
        Object[] allFields = new Object[12];
        int i = 0;
        allFields[i++] = getId();
        allFields[i++] = getName();
        allFields[i++] = getCity();
        allFields[i++] = getRegion();
        allFields[i++] = getAddress();
        allFields[i++] = getEmail();
        allFields[i++] = getPhone();
        allFields[i++] = getStar();
        allFields[i++] = getSummerPeriodStart();
        allFields[i++] = getSummerPeriodEnd();
        allFields[i++] = getWinterPeriodStart();
        allFields[i++] = getWinterPeriodEnd();
        return allFields;
    }

    public static Hotel fetchHotel(int id) {
        List<Hotel> hotelList = fetchHotels("SELECT * FROM hotel WHERE id = "+id);
        return hotelList.size()>0 ? hotelList.get(0) : null;
    }

    public static List<Hotel> fetchHotels() {
        return fetchHotels("SELECT * FROM hotel");
    }

    public static List<Hotel> fetchHotels(String query) {
        List<Hotel> hotelList = new ArrayList<>();

        try {
            ResultSet rs = DBConnector.getInstance().createStatement().executeQuery(query);
            while (rs.next()) {
                hotelList.add(new Hotel(
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
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hotelList;
    }
}
