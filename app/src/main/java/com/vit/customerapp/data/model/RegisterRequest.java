package com.vit.customerapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RegisterRequest implements Serializable{


    @Expose
    @SerializedName("verify_request_id")
    private String verifyRequestId;
    @Expose
    @SerializedName("country_code")
    private String countryCode;
    @Expose
    @SerializedName("password")
    private String password;
    @Expose
    @SerializedName("phone_number")
    private String phoneNumber;
    @Expose
    @SerializedName("email")
    private String email;
    @Expose
    @SerializedName("last_name")
    private String lastName;
    @Expose
    @SerializedName("first_name")
    private String firstName;

    public RegisterRequest(String password, String email, String lastName, String firstName) {
        this.password = password;
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public String getVerifyRequestId() {
        return verifyRequestId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getLast_name() {
        return lastName;
    }

    public String getFirst_name() {
        return firstName;
    }

    public void setVerifyRequestId(String verifyRequestId) {
        this.verifyRequestId = verifyRequestId;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
