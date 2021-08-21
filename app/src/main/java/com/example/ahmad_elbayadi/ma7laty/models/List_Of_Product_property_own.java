package com.example.ahmad_elbayadi.ma7laty.models;

/**
 * Created by Ahmad_Elbayadi on 05/06/2018.
 */

public class List_Of_Product_property_own {
    private String product_id;
    private String product_price;
    private String product_name;
    private String product_description;
    private String shop_id;
    private String product_main_photo;
//    private String product_rest_photos;
    //default constarctor
    public List_Of_Product_property_own()
    {

    }
    //constarctor

    public List_Of_Product_property_own(String product_id, String product_price, String product_name, String product_description, String shop_id, String product_main_photo) {
        this.product_id = product_id;
        this.product_price = product_price;
        this.product_name = product_name;
        this.product_description = product_description;
        this.shop_id = shop_id;
        this.product_main_photo = product_main_photo;
    }


    //setter

    public void setProduct_main_photo(String product_main_photo) {
        this.product_main_photo = product_main_photo;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

//    public void setProduct_rest_photos(String product_rest_photos) {
//        this.product_rest_photos = product_rest_photos;
//    }
    //getter

    public String getProduct_id() {
        return product_id;
    }

    public String getProduct_price() {
        return product_price;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_description() {
        return product_description;
    }

    public String getShop_id() {
        return shop_id;
    }

    public String getProduct_main_photo() {
        return product_main_photo;
    }
    //    public String getProduct_rest_photos() {
//        return product_rest_photos;
//    }
}
