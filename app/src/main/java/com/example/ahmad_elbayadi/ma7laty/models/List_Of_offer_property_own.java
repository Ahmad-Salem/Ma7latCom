package com.example.ahmad_elbayadi.ma7laty.models;

/**
 * Created by Ahmad_Elbayadi on 05/06/2018.
 */

public class List_Of_offer_property_own {
    private String offer_id;
    private String offer_name;
    private String offer_description;
    private String offer_photo;
    private String shop_id;

    //default constractor
    public List_Of_offer_property_own(){}
    //customer constractor

    public List_Of_offer_property_own(String offer_id, String offer_name, String offer_description, String offer_photo, String shop_id) {
        this.offer_id = offer_id;
        this.offer_name = offer_name;
        this.offer_description = offer_description;
        this.offer_photo = offer_photo;
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

    public String getShop_id() {
        return shop_id;
    }
}
