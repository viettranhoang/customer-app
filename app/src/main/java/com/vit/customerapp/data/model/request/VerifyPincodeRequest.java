package com.vit.customerapp.data.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyPincodeRequest {

    @Expose
    @SerializedName("verify_request_id")
    private String verifyRequestId;

    @Expose
    @SerializedName("pincode")
    private String pincode;

    public VerifyPincodeRequest(String verifyRequestId, String pincode) {
        this.verifyRequestId = verifyRequestId;
        this.pincode = pincode;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getVerifyRequestId() {
        return verifyRequestId;
    }

    public void setVerifyRequestId(String verifyRequestId) {
        this.verifyRequestId = verifyRequestId;
    }
}
