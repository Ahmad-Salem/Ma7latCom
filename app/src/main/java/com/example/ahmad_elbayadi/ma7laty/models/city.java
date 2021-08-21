package com.example.ahmad_elbayadi.ma7laty.models;

public class city {
    private String city_id;
    private String city_name;
    private String city_activation;
    private String gov_id;

    //default constractor

    public city() {
    }

    //custom contractor

    public city(String city_id, String city_name, String city_activation, String gov_id) {
        this.city_id = city_id;
        this.city_name = city_name;
        this.city_activation = city_activation;
        this.gov_id = gov_id;
    }


    //setter

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public void setCity_activation(String city_activation) {
        this.city_activation = city_activation;
    }

    public void setGov_id(String gov_id) {
        this.gov_id = gov_id;
    }
    //getter

    public String getCity_id() {
        return city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public String getCity_activation() {
        return city_activation;
    }

    public String getGov_id() {
        return gov_id;
    }
}
