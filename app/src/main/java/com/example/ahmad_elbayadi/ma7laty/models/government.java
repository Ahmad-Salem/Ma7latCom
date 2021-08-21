package com.example.ahmad_elbayadi.ma7laty.models;

public class government {
    private String gov_id;
    private String gov_name;
    private String gov_activation;
    private city city_list;
    //default constractor

    public government() {
    }

    //custom contractor

    public government(String gov_id, String gov_name, String gov_activation, city city_list) {
        this.gov_id = gov_id;
        this.gov_name = gov_name;
        this.gov_activation = gov_activation;
        this.city_list = city_list;
    }


    //setter

    public void setGov_id(String gov_id) {
        this.gov_id = gov_id;
    }

    public void setGov_name(String gov_name) {
        this.gov_name = gov_name;
    }

    public void setGov_activation(String gov_activation) {
        this.gov_activation = gov_activation;
    }

    public void setCity_list(city city_list) {
        this.city_list = city_list;
    }
    //getter

    public String getGov_id() {
        return gov_id;
    }

    public String getGov_name() {
        return gov_name;
    }

    public String getGov_activation() {
        return gov_activation;
    }

    public city getCity_list() {
        return city_list;
    }
}
