package com.vit.customerapp.data.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SocialSigninRequest {

    @Expose
    @SerializedName("social_id")
    private String socialId;

    @Expose
    @SerializedName("social_type")
    private String socialType;

    public SocialSigninRequest(String socialId, String socialType) {
        this.socialId = socialId;
        this.socialType = socialType;
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
