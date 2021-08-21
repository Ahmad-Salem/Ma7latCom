package com.example.ahmad_elbayadi.ma7laty.models;

/**
 * Created by Ahmad_Elbayadi on 27/04/2018.
 */

public class List_Of_offer_property {
    private String offer_id;
    private String offer_name;
    private String offer_description;
    private String offer_photo;
    private String shop_owner_user_name;
    private String shop_owner_email;
    private String shop_owner_photo;
    private String shop_owner_address;
    private String shop_owner_telephone1;
    private String shop_owner_telephone2;
    private String shop_id;

    //default constractor
    public List_Of_offer_property(){}
    //customer constractor

    public List_Of_offer_property(String offer_id, String offer_name, String offer_description, String offer_photo, String shop_owner_user_name, String shop_owner_email, String shop_owner_photo, String shop_owner_address, String shop_owner_telephone1, String shop_owner_telephone2, String shop_id) {
        this.offer_id = offer_id;
        this.offer_name = offer_name;
        this.offer_description = offer_description;
        this.offer_photo = offer_photo;
        this.shop_owner_user_name = shop_owner_user_name;
        this.shop_owner_email = shop_owner_email;
        this.shop_owner_photo = shop_owner_photo;
        this.shop_owner_address = shop_owner_address;
        this.shop_owner_telephone1 = shop_owner_telephone1;
        this.shop_owner_telephone2 = shop_owner_telephone2;
        this.shop_id = shop_id;
    }


    //setter


    public void setOffer_id(String offer_id) {
        this.offer_id = offer_id;
    }

    public void setOffer_name(String offer_name) {
        this.offer_name = offer_name;
    }

    public void setOffer_description(String offer_description) {
        this.offer_description = offer_description;
    }

    public void setOffer_photo(String offer_photo) {
        this.offer_photo = offer_photo;
    }

    public void setShop_owner_user_name(String shop_owner_user_name) {
        this.shop_owner_user_name = shop_owner_user_name;
    }

    public void setShop_owner_email(String shop_owner_email) {
        this.shop_owner_email = shop_owner_email;
    }

    public void setShop_owner_photo(String shop_owner_photo) {
        this.shop_owner_photo = shop_owner_photo;
    }

    public void setShop_owner_address(String shop_owner_address) {
        this.shop_owner_address = shop_owner_address;
    }

    public void setShop_owner_telephone1(String shop_owner_telephone1) {
        this.shop_owner_telephone1 = shop_owner_telephone1;
    }

    public void setShop_owner_telephone2(String shop_owner_telephone2) {
        this.shop_owner_telephone2 = shop_owner_telephone2;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    //Getter

    public String getOffer_id() {
        return offer_id;
    }

    public String getOffer_name() {
        return offer_name;
    }

    public String getOffer_description() {
        return offer_description;
    }

    public String getOffer_photo() {
        return offer_photo;
    }

    public String getShop_owner_user_name() {
        return shop_owner_user_name;
    }

    public String getShop_owner_email() {
        return shop_owner_email;
    }

    public String getShop_owner_photo() {
        return shop_owner_photo;
    }

    public String getShop_owner_address() {
        return shop_owner_address;
    }

    public String getShop_owner_telephone1() {
        return shop_owner_telephone1;
    }

    public String getShop_owner_telephone2() {
        return shop_owner_telephone2;
    }

    public String getShop_id() {
        return shop_id;
    }
}
