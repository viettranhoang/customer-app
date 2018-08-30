package com.vit.customerapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterRequest {


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

    public RegisterRequest(String verifyRequestId, String countryCode, String password,
                           String phoneNumber, String email, String lastName, String firstName) {
        this.verifyRequestId = verifyRequestId;
        this.countryCode = countryCode;
        this.password = password;
        this.phoneNumber = phoneNumber;
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
}
