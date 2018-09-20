package com.vit.customerapp.data.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequest {

    @Expose
    @SerializedName("phone_number")
    private String phoneNumber;

    @Expose
    @SerializedName("password")
    private String password;

    @Expose
    @SerializedName("country_code")
    private String countryCode;

    public LoginRequest(String phoneNumber, String password, String countryCode) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.countryCode = countryCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
