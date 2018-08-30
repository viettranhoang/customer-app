package com.vit.customerapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterResponse {


    @Expose
    @SerializedName("payload")
    private Payload payload;
    @Expose
    @SerializedName("status")
    private String status;

    public Payload getPayload() {
        return payload;
    }

    public String getStatus() {
        return status;
    }

    public static class Payload {
        @Expose
        @SerializedName("token")
        private String token;
        @Expose
        @SerializedName("user")
        private User user;

        public String getToken() {
            return token;
        }

        public User getUser() {
            return user;
        }
    }

    public static class User {
        @Expose
        @SerializedName("is_first_login")
        private String is_first_login;
        @Expose
        @SerializedName("country_code")
        private String country_code;
        @Expose
        @SerializedName("parking_instr")
        private String parking_instr;
        @Expose
        @SerializedName("apt")
        private String apt;
        @Expose
        @SerializedName("city")
        private String city;
        @Expose
        @SerializedName("zipcode")
        private String zipcode;
        @Expose
        @SerializedName("lng")
        private double lng;
        @Expose
        @SerializedName("lat")
        private double lat;
        @Expose
        @SerializedName("address")
        private String address;
        @Expose
        @SerializedName("state")
        private String state;
        @Expose
        @SerializedName("gender")
        private String gender;
        @Expose
        @SerializedName("avatar")
        private String avatar;
        @Expose
        @SerializedName("email")
        private String email;
        @Expose
        @SerializedName("phone_number")
        private String phone_number;
        @Expose
        @SerializedName("user_name")
        private String user_name;
        @Expose
        @SerializedName("last_name")
        private String last_name;
        @Expose
        @SerializedName("first_name")
        private String first_name;
        @Expose
        @SerializedName("user_id")
        private int user_id;

        public String getIs_first_login() {
            return is_first_login;
        }

        public String getCountry_code() {
            return country_code;
        }

        public String getParking_instr() {
            return parking_instr;
        }

        public String getApt() {
            return apt;
        }

        public String getCity() {
            return city;
        }

        public String getZipcode() {
            return zipcode;
        }

        public double getLng() {
            return lng;
        }

        public double getLat() {
            return lat;
        }

        public String getAddress() {
            return address;
        }

        public String getState() {
            return state;
        }

        public String getGender() {
            return gender;
        }

        public String getAvatar() {
            return avatar;
        }

        public String getEmail() {
            return email;
        }

        public String getPhone_number() {
            return phone_number;
        }

        public String getUser_name() {
            return user_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public String getFirst_name() {
            return first_name;
        }

        public int getUser_id() {
            return user_id;
        }
    }


}
