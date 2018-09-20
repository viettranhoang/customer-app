package com.vit.customerapp.data.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SocialSignupRequest implements Serializable{

    @Expose
    @SerializedName("verify_request_id")
    private String verifyRequestId;
    @Expose
    @SerializedName("avatar")
    private String avatar;
    @Expose
    @SerializedName("gender")
    private String gender;
    @Expose
    @SerializedName("email")
    private String email;
    @Expose
    @SerializedName("last_name")
    private String lastName;
    @Expose
    @SerializedName("first_name")
    private String firstName;
    @Expose
    @SerializedName("country_code")
    private String countryCode;
    @Expose
    @SerializedName("phone_number")
    private String phoneNumber;
    @Expose
    @SerializedName("social_token")
    private String socialToken;
    @Expose
    @SerializedName("social_type")
    private String socialType;
    @Expose
    @SerializedName("social_id")
    private String socialId;

    public SocialSignupRequest(String avatar, String email, String lastName,
                               String firstName, String socialToken, String socialType, String socialId) {
        this.avatar = avatar;
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
        this.socialToken = socialToken;
        this.socialType = socialType;
        this.socialId = socialId;
    }

    public String getVerifyRequestId() {
        return verifyRequestId;
    }

    public void setVerifyRequestId(String verifyRequestId) {
        this.verifyRequestId = verifyRequestId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getSocialToken() {
        return socialToken;
    }

    public void setSocialToken(String socialToken) {
        this.socialToken = socialToken;
    }

    public String getSocialType() {
        return socialType;
    }

    public void setSocialType(String socialType) {
        this.socialType = socialType;
    }

    public String getSocialId() {
        return socialId;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }
}
