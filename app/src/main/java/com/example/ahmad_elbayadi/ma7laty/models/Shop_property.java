package com.example.ahmad_elbayadi.ma7laty.models;

/**
 * Created by Ahmad_Elbayadi on 26/04/2018.
 */

public class Shop_property {

private String shop_id;
private String shop_name;
private String shop_country;
private String shop_address;
private String shop_description;
private String shop_user_id;
private String shop_photo;
private String shop_activity;
private String shop_lat;
private String shop_log;
private String shop_allowed_product;
private String shop_allowed_offers;
private String shop_goverment;
private String shop_city;

    //default constractor
    public Shop_property() {

    }

    public Shop_property(String shop_id, String shop_name, String shop_country, String shop_address, String shop_description, String shop_user_id, String shop_photo, String shop_activity, String shop_lat, String shop_log, String shop_allowed_product, String shop_allowed_offers, String shop_goverment, String shop_city) {
        this.shop_id = shop_id;
        this.shop_name = shop_name;
        this.shop_country = shop_country;
        this.shop_address = shop_address;
        this.shop_description = shop_description;
        this.shop_user_id = shop_user_id;
        this.shop_photo = shop_photo;
        this.shop_activity = shop_activity;
        this.shop_lat = shop_lat;
        this.shop_log = shop_log;
        this.shop_allowed_product = shop_allowed_product;
        this.shop_allowed_offers = shop_allowed_offers;
        this.shop_goverment = shop_goverment;
        this.shop_city = shop_city;
    }

        //setter

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public void setShop_country(String shop_country) {
        this.shop_country = shop_country;
    }

    public void setShop_address(String shop_address) {
        this.shop_address = shop_address;
    }

    public void setShop_description(String shop_description) {
        this.shop_description = shop_description;
    }

    public void setShop_user_id(String shop_user_id) {
        this.shop_user_id = shop_user_id;
    }

    public void setShop_photo(String shop_photo) {
        this.shop_photo = shop_photo;
    }

    public void setShop_activity(String shop_activity) {
        this.shop_activity = shop_activity;
    }

    public void setShop_lat(String shop_lat) {
        this.shop_lat = shop_lat;
    }

    public void setShop_log(String shop_log) {
        this.shop_log = shop_log;
    }

    public void setShop_allowed_product(String shop_allowed_product) {
        this.shop_allowed_product = shop_allowed_product;
    }

    public void setShop_allowed_offers(String shop_allowed_offers) {
        this.shop_allowed_offers = shop_allowed_offers;
    }

    public void setShop_goverment(String shop_goverment) {
        this.shop_goverment = shop_goverment;
    }

    public void setShop_city(String shop_city) {
        this.shop_city = shop_city;
    }

    //getter

    public String getShop_id() {
        return shop_id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public String getShop_country() {
        return shop_country;
    }

    public String getShop_address() {
        return shop_address;
    }

    public String getShop_description() {
        return shop_description;
    }

    public String getShop_user_id() {
        return shop_user_id;
    }

    public String getShop_photo() {
        return shop_photo;
    }

    public String getShop_activity() {
        return shop_activity;
    }

    public String getShop_lat() {
        return shop_lat;
    }

    public String getShop_log() {
        return shop_log;
    }

    public String getShop_allowed_product() {
        return shop_allowed_product;
    }

    public String getShop_allowed_offers() {
        return shop_allowed_offers;
    }

    public String getShop_goverment() {
        return shop_goverment;
    }

    public String getShop_city() {
        return shop_city;
    }
}
