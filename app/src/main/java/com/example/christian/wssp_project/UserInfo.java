package com.example.christian.wssp_project;

/**
 * Created by christian on 8/18/2017.
 */

public class UserInfo {
    private int id;
    private String name;
    private String username;
    private String email;
    private HomeAddress address;
    private String phone;
    private String website;
    private Company company;

    public class Geo {
        float lat;
        float lng;
    }

    public class HomeAddress {
        String street;
        String suite;
        String city;
        String zipcode;
        Geo geo;
    }

    public class Company {
        String name;
        String catchPhrase;
        String bs;
    }

    public int getId() {return id;}

    public String getName() {
        return name;
    }

    public String getUsername() { return username; }

    public String getEmail() {
        return email;
    }

    public HomeAddress getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public Company getCompany() {
        return company;
    }
}
