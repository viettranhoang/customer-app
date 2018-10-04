package com.vit.customerapp.data.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Technician {

    @Expose
    @SerializedName("avatar_link")
    private String avatarLink;
    @Expose
    @SerializedName("average_rates")
    private double averageRates;
    @Expose
    @SerializedName("gender")
    private String gender;
    @Expose
    @SerializedName("birthday")
    private int birthday;
    @Expose
    @SerializedName("last_name")
    private String lastName;
    @Expose
    @SerializedName("first_name")
    private String firstName;
    @Expose
    @SerializedName("tech_id")
    private int techId;

    public Technician(String avatarLink, double averageRates, String lastName, String firstName) {
        this.avatarLink = avatarLink;
        this.averageRates = averageRates;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public String getAvatarLink() {
        return avatarLink;
    }

    public void setAvatarLink(String avatarLink) {
        this.avatarLink = avatarLink;
    }

    public double getAverageRates() {
        return averageRates;
    }

    public void setAverageRates(double averageRates) {
        this.averageRates = averageRates;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
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

    public int getTechId() {
        return techId;
    }

    public void setTechId(int techId) {
        this.techId = techId;
    }
}
