package com.example.ahmad_elbayadi.ma7laty.models;

/**
 * Created by Ahmad_Elbayadi on 27/04/2018.
 */

public class List_Of_Product_property {
    private String product_id;
    private String product_price;
    private String product_name;
    private String product_description;
    private String shop_id;
    private String user_name;
    private String email;
    private String photo;
    private String address;
    private String telephone1;
    private String telephone2;
    private String product_main_photo;
//    private String product_rest_photos;

    //default constractor
    public List_Of_Product_property(){}
    //custom constractor

    public List_Of_Product_property(String product_id, String product_price, String product_name, String product_description, String shop_id, String user_name, String email, String photo, String address, String telephone1, String telephone2, String product_main_photo) {
        this.product_id = product_id;
        this.product_price = product_price;
        this.product_name = product_name;
        this.product_description = product_description;
        this.shop_id = shop_id;
        this.user_name = user_name;
        this.email = email;
        this.photo = photo;
        this.address = address;
        this.telephone1 = telephone1;
        this.telephone2 = telephone2;
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

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTelephone1(String telephone1) {
        this.telephone1 = telephone1;
    }

    public void setTelephone2(String telephone2) {
        this.telephone2 = telephone2;
    }

//    public void setProduct_rest_photos(String product_rest_photos) {
//        this.product_rest_photos = product_rest_photos;
//    }

    //Getter

    public String getProduct_main_photo() {
        return product_main_photo;
    }

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

    public String getUser_name() {
        return user_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoto() {
        return photo;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephone1() {
        return telephone1;
    }

    public String getTelephone2() {
        return telephone2;
    }

//    public String getProduct_rest_photos() {
//        return product_rest_photos;
//    }
}
