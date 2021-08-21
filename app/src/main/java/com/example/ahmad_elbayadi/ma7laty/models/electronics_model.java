package com.example.ahmad_elbayadi.ma7laty.models;

/**
 * Created by Ahmad_Elbayadi on 22/04/2018.
 */

public class electronics_model {

    private String offer_id;
    private String offer_name;
    private String offer_description;
    private String offer_shop_id;
    private String offer_image_url;

    //shop information
    private String offer_shop_country;
    private String offer_shop_government;
    private String offer_shop_city;
    private String offer_shop_shop_activity;

    //rest shop information
    private String offer_shop_name;
    private String offer_shop_address;
    private String offer_shop_description;
    private String offer_shop_user_id;
    private String offer_shop_photo;
    private String offer_shop_lat;
    private String offer_shop_log;
    private String offer_shop_allowed_products;
    private String Offer_shop_allowed_offers;


    //user information
    private String offer_User_id;
    private String offer_User_name;
    private String offer_User_email;
    private String offer_User_photo;
    private String offer_User_telephone1;
    private String offer_User_telephone2;
    private String offer_User_address;
    private String offer_main_page;


    //default constractor
    public electronics_model(){}

    //constructor with all values
    public electronics_model(String offer_id, String offer_name, String offer_description,
                             String offer_shop_id, String offer_image_url, String offer_shop_country,
                             String offer_shop_government, String offer_shop_city, String offer_shop_shop_activity,
                             String offer_shop_name, String offer_shop_address, String offer_shop_description,
                             String offer_shop_user_id, String offer_shop_photo, String offer_shop_lat, String offer_shop_log,
                             String offer_shop_allowed_products, String Offer_shop_allowed_offers, String offer_User_id, String offer_User_name,
                             String offer_User_email, String offer_User_photo, String offer_User_telephone1,
                             String offer_User_telephone2, String offer_User_address, String offer_main_page) {
        this.offer_id = offer_id;
        this.offer_name = offer_name;
        this.offer_description = offer_description;
        this.offer_shop_id = offer_shop_id;
        this.offer_image_url = offer_image_url;
        this.offer_shop_country=offer_shop_country;
        this.offer_shop_government=offer_shop_government;
        this.offer_shop_city=offer_shop_city;
        this.offer_shop_shop_activity=offer_shop_shop_activity;
        this.offer_shop_name=offer_shop_name;
        this.offer_shop_address=offer_shop_address;
        this.offer_shop_description=offer_shop_description;
        this.offer_shop_user_id=offer_shop_user_id;
        this.offer_shop_photo=offer_shop_photo;
        this.offer_shop_lat=offer_shop_lat;
        this.offer_shop_log=offer_shop_log;
        this.offer_shop_allowed_products=offer_shop_allowed_products;
        this.Offer_shop_allowed_offers=Offer_shop_allowed_offers;

        this.offer_User_id=offer_User_id;
        this.offer_User_name=offer_User_name;
        this.offer_User_email=offer_User_email;
        this.offer_User_photo=offer_User_photo;
        this.offer_User_telephone1=offer_User_telephone1;
        this.offer_User_telephone2=offer_User_telephone2;
        this.offer_User_address=offer_User_address;
        this.offer_main_page=offer_main_page;

    }

    //setter fuction

    public void setOffer_id(String offer_id) {
        this.offer_id = offer_id;
    }

    public void setOffer_name(String offer_name) {
        this.offer_name = offer_name;
    }

    public void setOffer_description(String offer_description) {
        this.offer_description = offer_description;
    }

    public void setOffer_shop_id(String offer_shop_id) {
        this.offer_shop_id = offer_shop_id;
    }

    public void setOffer_image_url(String offer_image_url) {
        this.offer_image_url = offer_image_url;
    }

    public void setOffer_shop_country(String offer_shop_country) {
        this.offer_shop_country = offer_shop_country;
    }

    public void setOffer_shop_government(String offer_shop_government) {
        this.offer_shop_government = offer_shop_government;
    }

    public void setOffer_shop_city(String offer_shop_city) {
        this.offer_shop_city = offer_shop_city;
    }

    public void setOffer_shop_shop_activity(String offer_shop_shop_activity) {
        this.offer_shop_shop_activity = offer_shop_shop_activity;
    }

    public void setOffer_shop_name(String offer_shop_name) {
        this.offer_shop_name = offer_shop_name;
    }

    public void setOffer_shop_address(String offer_shop_address) {
        this.offer_shop_address = offer_shop_address;
    }

    public void setOffer_shop_description(String offer_shop_description) {
        this.offer_shop_description = offer_shop_description;
    }

    public void setOffer_shop_user_id(String offer_shop_user_id) {
        this.offer_shop_user_id = offer_shop_user_id;
    }

    public void setOffer_shop_photo(String offer_shop_photo) {
        this.offer_shop_photo = offer_shop_photo;
    }

    public void setOffer_shop_lat(String offer_shop_lat) {
        this.offer_shop_lat = offer_shop_lat;
    }

    public void setOffer_shop_log(String offer_shop_log) {
        this.offer_shop_log = offer_shop_log;
    }

    public void setOffer_shop_allowed_products(String offer_shop_allowed_products) {
        this.offer_shop_allowed_products = offer_shop_allowed_products;
    }

    public void setOffer_shop_allowed_offers(String offer_shop_allowed_offers) {
        Offer_shop_allowed_offers = offer_shop_allowed_offers;
    }

    public void setOffer_User_id(String offer_User_id) {
        this.offer_User_id = offer_User_id;
    }

    public void setOffer_User_name(String offer_User_name) {
        this.offer_User_name = offer_User_name;
    }

    public void setOffer_User_email(String offer_User_email) {
        this.offer_User_email = offer_User_email;
    }

    public void setOffer_User_photo(String offer_User_photo) {
        this.offer_User_photo = offer_User_photo;
    }

    public void setOffer_User_telephone1(String offer_User_telephone1) {
        this.offer_User_telephone1 = offer_User_telephone1;
    }

    public void setOffer_User_telephone2(String offer_User_telephone2) {
        this.offer_User_telephone2 = offer_User_telephone2;
    }

    public void setOffer_User_address(String offer_User_address) {
        this.offer_User_address = offer_User_address;
    }

    public void setOffer_main_page(String offer_main_page) {
        this.offer_main_page = offer_main_page;
    }

    //getter functions

    public String getOffer_id() {
        return offer_id;
    }

    public String getOffer_name() {
        return offer_name;
    }

    public String getOffer_description() {
        return offer_description;
    }

    public String getOffer_shop_id() {
        return offer_shop_id;
    }

    public String getOffer_image_url() {
        return offer_image_url;
    }

    public String getOffer_shop_country() {
        return offer_shop_country;
    }

    public String getOffer_shop_government() {
        return offer_shop_government;
    }

    public String getOffer_shop_city() {
        return offer_shop_city;
    }

    public String getOffer_shop_shop_activity() {
        return offer_shop_shop_activity;
    }

    public String getOffer_shop_name() {
        return offer_shop_name;
    }

    public String getOffer_shop_address() {
        return offer_shop_address;
    }

    public String getOffer_shop_description() {
        return offer_shop_description;
    }

    public String getOffer_shop_user_id() {
        return offer_shop_user_id;
    }

    public String getOffer_shop_photo() {
        return offer_shop_photo;
    }

    public String getOffer_shop_lat() {
        return offer_shop_lat;
    }

    public String getOffer_shop_log() {
        return offer_shop_log;
    }

    public String getOffer_shop_allowed_products() {
        return offer_shop_allowed_products;
    }

    public String getOffer_shop_allowed_offers() {
        return Offer_shop_allowed_offers;
    }

    public String getOffer_User_id() {
        return offer_User_id;
    }

    public String getOffer_User_name() {
        return offer_User_name;
    }

    public String getOffer_User_email() {
        return offer_User_email;
    }

    public String getOffer_User_photo() {
        return offer_User_photo;
    }

    public String getOffer_User_telephone1() {
        return offer_User_telephone1;
    }

    public String getOffer_User_telephone2() {
        return offer_User_telephone2;
    }

    public String getOffer_User_address() {
        return offer_User_address;
    }

    public String getOffer_main_page() {
        return offer_main_page;
    }
}
