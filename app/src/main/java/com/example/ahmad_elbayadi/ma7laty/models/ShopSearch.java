package com.example.ahmad_elbayadi.ma7laty.models;

public class ShopSearch {

    private String type;


    private String shop_name;
    private String shop_country;
    private String shop_address;
    private String shop_description;

    private String shop_user_id;
    private String shop_id;

    private String shop_photo;
    private String shop_shop_activity;
    private String shop_lat;
    private String shop_log;
    private String shop_allowed_product;
    private String shop_allowed_offers;
    private String shop_government;
    private String shop_city;

    // offers
    private String offer_id;
    private String offer_name;
    private String offer_description;
    private String offer_photo;

    //product
    private String product_id;
    private String product_name;
    private String product_price;
    private String product_description;
    private String product_photo;

    //user
    private String user_name;
    private String email;
    private String photo;
    private String address;
    private String telephone1;
    private String telephone2;


    //default constractor

    public ShopSearch() {
    }


    //custom contractor

    public ShopSearch(String type, String shop_name, String shop_country, String shop_address, String shop_description, String shop_user_id, String shop_id, String shop_photo, String shop_shop_activity, String shop_lat, String shop_log, String shop_allowed_product, String shop_allowed_offers, String shop_government, String shop_city, String offer_id, String offer_name, String offer_description, String offer_photo, String product_id, String product_name, String product_price, String product_description, String product_photo, String user_name, String email, String photo, String address, String telephone1, String telephone2) {
        this.type = type;
        this.shop_name = shop_name;
        this.shop_country = shop_country;
        this.shop_address = shop_address;
        this.shop_description = shop_description;
        this.shop_user_id = shop_user_id;
        this.shop_id = shop_id;
        this.shop_photo = shop_photo;
        this.shop_shop_activity = shop_shop_activity;
        this.shop_lat = shop_lat;
        this.shop_log = shop_log;
        this.shop_allowed_product = shop_allowed_product;
        this.shop_allowed_offers = shop_allowed_offers;
        this.shop_government = shop_government;
        this.shop_city = shop_city;
        this.offer_id = offer_id;
        this.offer_name = offer_name;
        this.offer_description = offer_description;
        this.offer_photo = offer_photo;
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_description = product_description;
        this.product_photo = product_photo;
        this.user_name = user_name;
        this.email = email;
        this.photo = photo;
        this.address = address;
        this.telephone1 = telephone1;
        this.telephone2 = telephone2;
    }


    //setter

    public void setType(String type) {
        this.type = type;
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

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public void setShop_photo(String shop_photo) {
        this.shop_photo = shop_photo;
    }

    public void setShop_shop_activity(String shop_shop_activity) {
        this.shop_shop_activity = shop_shop_activity;
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

    public void setShop_government(String shop_government) {
        this.shop_government = shop_government;
    }

    public void setShop_city(String shop_city) {
        this.shop_city = shop_city;
    }

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

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public void setProduct_photo(String product_photo) {
        this.product_photo = product_photo;
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

    //getter


    public String getType() {
        return type;
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

    public String getShop_id() {
        return shop_id;
    }

    public String getShop_photo() {
        return shop_photo;
    }

    public String getShop_shop_activity() {
        return shop_shop_activity;
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

    public String getShop_government() {
        return shop_government;
    }

    public String getShop_city() {
        return shop_city;
    }

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

    public String getProduct_id() {
        return product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public String getProduct_description() {
        return product_description;
    }

    public String getProduct_photo() {
        return product_photo;
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
}
