package com.vit.customerapp.data.remote;

import com.vit.customerapp.data.model.RegisterRequest;
import com.vit.customerapp.data.model.RegisterResponse;
import com.vit.customerapp.data.model.VerifyPhoneResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface APIService {



    @POST("api/user/verify_phone")
    Call<VerifyPhoneResponse> postVerifyPhoneCall(@Field("phone_number") String phone_number,
                                              @Field("country_code") String country_code,
                                              @Field("request_type") String request_type,
                                              @Field("app_type") String app_type);

    @POST("api/user/register")
    Call<RegisterResponse> postRegisterResponse(@Body RegisterRequest registerRequest);



}
