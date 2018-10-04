package com.vit.customerapp.data.remote;

import com.vit.customerapp.data.model.request.SocialSignupRequest;
import com.vit.customerapp.data.model.response.BaseResponse;
import com.vit.customerapp.data.model.request.LoginRequest;
import com.vit.customerapp.data.model.request.RegisterRequest;
import com.vit.customerapp.data.model.response.RegisterResponse;
import com.vit.customerapp.data.model.request.ResetPasswordRequest;
import com.vit.customerapp.data.model.request.SocialSigninRequest;
import com.vit.customerapp.data.model.request.VerifyPhoneRequest;
import com.vit.customerapp.data.model.response.Technician;
import com.vit.customerapp.data.model.response.VerifyPhoneResponse;
import com.vit.customerapp.data.model.request.VerifyPincodeRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {

    @POST("api/user/verify_phone")
    Call<VerifyPhoneResponse> postVerifyPhoneCall(@Body VerifyPhoneRequest verifyPhoneRequest);

    @POST("api/user/verify_pincode")
    Call<BaseResponse> postVerifyPincode(@Body VerifyPincodeRequest verifyPincodeRequest);

    @POST("api/user/register")
    Call<RegisterResponse> postRegisterResponse(@Body RegisterRequest registerRequest);

    @POST("api/user/social_login")
    Call<RegisterResponse> postSocialSignin(@Body SocialSigninRequest socialSigninRequest);

    @POST("api/user/social_signup")
    Call<RegisterResponse> postSocialSignup(@Body SocialSignupRequest socialSignupRequest);

    @POST("api/user/reset_password")
    Call<BaseResponse> postResetPassword(@Body ResetPasswordRequest resetPasswordRequest);

    @POST("/api/user/login")
    Call<RegisterResponse> postLogin(@Body LoginRequest loginRequest);

    @GET("/api/user/technicians")
    Call<List<Technician>> getTechnician();

}
