package com.vit.customerapp.data.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyPhoneResponse {


    @Expose
    @SerializedName("payload")
    private Payload payload;
    @Expose
    @SerializedName("status")
    private String status;
    @Expose
    @SerializedName("code")
    private String code;

    @Expose
    @SerializedName("message")
    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Payload getPayload() {
        return payload;
    }

    public String getStatus() {
        return status;
    }

    public static class Payload {
        @Expose
        @SerializedName("verifying_request_id")
        private String verifyingRequestId;

        public String getVerifyingRequestId() {
            return verifyingRequestId;
        }

    }

}
