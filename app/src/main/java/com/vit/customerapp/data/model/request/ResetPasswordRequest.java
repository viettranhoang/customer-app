package com.vit.customerapp.data.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResetPasswordRequest {

    @Expose
    @SerializedName("new_password")
    private String newPassword;

    @Expose
    @SerializedName("verify_request_id")
    private String verifyRequestId;

    @Expose
    @SerializedName("app_type")
    private String appType;

    public ResetPasswordRequest(String newPassword, String verifyRequestId, String appType) {
        this.newPassword = newPassword;
        this.verifyRequestId = verifyRequestId;
        this.appType = appType;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getVerifyRequestId() {
        return verifyRequestId;
    }

    public void setVerifyRequestId(String verifyRequestId) {
        this.verifyRequestId = verifyRequestId;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
