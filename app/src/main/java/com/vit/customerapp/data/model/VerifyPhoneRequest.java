package com.vit.customerapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyPhoneRequest {


    @Expose
    @SerializedName("app_type")
    private String appType;
    @Expose
    @SerializedName("request_type")
    private String requestType;
    @Expose
    @SerializedName("country_code")
    private String countryCode;
    @Expose
    @SerializedName("phone_number")
    private String phoneNumber;

    public VerifyPhoneRequest(String phoneNumber, String countryCode, String requestType, String appType) {
        this.phoneNumber = phoneNumber;
        this.countryCode = countryCode;
        this.requestType = requestType;
        this.appType = appType;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
