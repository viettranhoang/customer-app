package com.vit.customerapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyPhoneResponse {


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
        @SerializedName("verifying_request_id")
        private String verifyingRequestId;

        public String getVerifyingRequestId() {
            return verifyingRequestId;
        }

        @Override
        public String toString() {
            return "Payload{" +
                    "verifyingRequestId='" + verifyingRequestId + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "VerifyPhoneResponse{" +
                "payload=" + payload +
                ", status='" + status + '\'' +
                '}';
    }
}
