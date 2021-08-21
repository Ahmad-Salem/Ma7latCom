package com.example.ahmad_elbayadi.ma7laty.models;

public class List_Of_Product_Text_property {
    private String product_id;
    private String shop_id;
    private String product_name;
    private String product_price;

    //default constractor
    public List_Of_Product_Text_property() {

    }

    //custom constractor

    public List_Of_Product_Text_property(String product_id, String shop_id, String product_name, String product_price) {
        this.product_id = product_id;
        this.shop_id = shop_id;
        this.product_name = product_name;
        this.product_price = product_price;
    }

    //setter

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    //getter

    public String getProduct_id() {
        return product_id;
    }

    public String getShop_id() {
        return shop_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_price() {
        return product_price;
    }
}
