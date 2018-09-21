package com.vit.customerapp.ui.feature.signup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.vit.customerapp.data.model.request.RegisterRequest;
import com.vit.customerapp.data.model.request.SocialSignupRequest;
import com.vit.customerapp.data.model.response.RegisterResponse;
import com.vit.customerapp.data.remote.ApiUtils;
import com.vit.customerapp.ui.util.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpPresenter implements SignUpContract.Presenter {

    private static final String TAG = SignUpPresenter.class.getSimpleName();

    private SignUpContract.View mView;

    private CallbackManager mCallbackManager;

    public SignUpPresenter(SignUpContract.View mView) {
        this.mView = mView;
        this.mView.setPresenter(this);
    }

    @Override
    public void postRegister(RegisterRequest request) {
        ApiUtils.getAPIService().postRegisterResponse(request).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mView.onRegisterSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Log.e(TAG, "onPostRegisterFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public void postSocialSignup(SocialSignupRequest request) {
        ApiUtils.getAPIService().postSocialSignup(request).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mView.onRegisterSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Log.e(TAG, "onPostSocialSignupFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public void signInFacebook(Activity activity) {
        initFacebookSignIn();

        LoginManager.getInstance().logInWithReadPermissions(activity,
                Arrays.asList("public_profile", "email", "user_gender"));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mCallbackManager != null) {
            mCallbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void initFacebookSignIn() {
        mCallbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                getFacebookProfile(loginResult);
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "onCancel: Login canceled");
            }

            @Override
            public void onError(FacebookException error) {
                Log.e(TAG, "onError: Login failed");
            }
        });
    }

    private void getFacebookProfile(LoginResult loginResult) {
        Profile profile = Profile.getCurrentProfile();

        String id = loginResult.getAccessToken().getUserId();
        String token = loginResult.getAccessToken().getToken();
        String firstName = profile.getFirstName();
        String lastName = profile.getLastName();
        String avatar = profile.getProfilePictureUri(300, 300).toString();

        GraphRequest request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {

                        Log.v(TAG + "Facebook Response ", response.toString());

                        try {
                            String email = object.getString("email");
                            String gender = object.getString("gender");

                            SocialSignupRequest request = new SocialSignupRequest(
                                    avatar, email, lastName, firstName,
                                    token, Utils.SOCIAL_TYPE_FACEBOOK, id, "VN");

                            request.setGender(!gender.isEmpty() ? gender : "");

                            mView.onFacebookSignInSuccess(request);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,gender");
        request.setParameters(parameters);
        request.executeAsync();
    }
}
