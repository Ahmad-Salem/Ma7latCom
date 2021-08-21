package com.example.ahmad_elbayadi.ma7laty.models;

/**
 * Created by Ahmad_Elbayadi on 01/09/2018.
 */

public class More_user_info {

    private String user_id;
    private String user_name;
    private String user_email;
    private String user_photo;
    private String user_telephone1;
    private String user_telephone2;
    private String user_address;

    //Default constractor
    public More_user_info() {
    }

    //Customer constractor

    public More_user_info(String user_id, String user_name, String user_email, String user_photo, String user_telephone1, String user_telephone2, String user_address) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_photo = user_photo;
        this.user_telephone1 = user_telephone1;
        this.user_telephone2 = user_telephone2;
        this.user_address = user_address;
    }

    //setter


    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public void setUser_photo(String user_photo) {
        this.user_photo = user_photo;
    }

    public void setUser_telephone1(String user_telephone1) {
        this.user_telephone1 = user_telephone1;
    }

    public void setUser_telephone2(String user_telephone2) {
        this.user_telephone2 = user_telephone2;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    //Getter


    public String getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public String getUser_photo() {
        return user_photo;
    }

    public String getUser_telephone1() {
        return user_telephone1;
    }

    public String getUser_telephone2() {
        return user_telephone2;
    }

    public String getUser_address() {
        return user_address;
    }
}
