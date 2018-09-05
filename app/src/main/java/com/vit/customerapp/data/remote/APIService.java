package com.vit.customerapp.data.remote;

import com.vit.customerapp.data.model.RegisterRequest;
import com.vit.customerapp.data.model.RegisterResponse;
import com.vit.customerapp.data.model.VerifyPhoneRequest;
import com.vit.customerapp.data.model.VerifyPhoneResponse;
import com.vit.customerapp.data.model.VerifyPincodeResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {

    @POST("api/user/verify_phone")
    Call<VerifyPhoneResponse> postVerifyPhoneCall(@Body VerifyPhoneRequest verifyPhoneRequest);

    @POST("api/user/verify_pincode")
    Call<VerifyPincodeResponse> postVerifyPincode(@Query("verify_request_id") String verifyRequestId,
                                                  @Query("pincode") String pincode);

    @POST("api/user/register")
    Call<RegisterResponse> postRegisterResponse(@Body RegisterRequest registerRequest);



}
