package com.example.ahmad_elbayadi.ma7laty.models;

/**
 * Created by Ahmad_Elbayadi on 21/04/2018.
 */

public class Advertisement_model {

    private String Advertisement_id;
    private String Advertisement_title;
    private String Advertisement_description;
    private String Advertisement_government;
    private String Advertisement_city;
    private String Advertisement_image_url;
    private String Advertisement_activated;
    private String Advertisement_from_date;
    private String Advertisement_to_date;

    //default constractor
    public Advertisement_model()
    {

    }
    //constractor with parameters


    public Advertisement_model(String advertisement_id, String advertisement_title, String advertisement_description, String advertisement_government, String advertisement_city, String advertisement_image_url, String advertisement_activated, String advertisement_from_date, String advertisement_to_date) {
        Advertisement_id = advertisement_id;
        Advertisement_title = advertisement_title;
        Advertisement_description = advertisement_description;
        Advertisement_government = advertisement_government;
        Advertisement_city = advertisement_city;
        Advertisement_image_url = advertisement_image_url;
        Advertisement_activated = advertisement_activated;
        Advertisement_from_date = advertisement_from_date;
        Advertisement_to_date = advertisement_to_date;
    }

    //setter functions

    public void setAdvertisement_id(String advertisement_id) {
        Advertisement_id = advertisement_id;
    }

    public void setAdvertisement_title(String advertisement_title) {
        Advertisement_title = advertisement_title;
    }

    public void setAdvertisement_description(String advertisement_description) {
        Advertisement_description = advertisement_description;
    }

    public void setAdvertisement_government(String advertisement_government) {
        Advertisement_government = advertisement_government;
    }

    public void setAdvertisement_city(String advertisement_city) {
        Advertisement_city = advertisement_city;
    }

    public void setAdvertisement_image_url(String advertisement_image_url) {
        Advertisement_image_url = advertisement_image_url;
    }

    public void setAdvertisement_activated(String advertisement_activated) {
        Advertisement_activated = advertisement_activated;
    }

    public void setAdvertisement_from_date(String advertisement_from_date) {
        Advertisement_from_date = advertisement_from_date;
    }

    public void setAdvertisement_to_date(String advertisement_to_date) {
        Advertisement_to_date = advertisement_to_date;
    }

    //getter functions

    public String getAdvertisement_id() {
        return Advertisement_id;
    }

    public String getAdvertisement_title() {
        return Advertisement_title;
    }

    public String getAdvertisement_description() {
        return Advertisement_description;
    }

    public String getAdvertisement_government() {
        return Advertisement_government;
    }

    public String getAdvertisement_city() {
        return Advertisement_city;
    }

    public String getAdvertisement_image_url() {
        return Advertisement_image_url;
    }

    public String getAdvertisement_activated() {
        return Advertisement_activated;
    }

    public String getAdvertisement_from_date() {
        return Advertisement_from_date;
    }

    public String getAdvertisement_to_date() {
        return Advertisement_to_date;
    }
}
