package com.vit.customerapp.ui.feature.signin;

import android.app.Activity;
import android.content.Intent;

import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;
import com.vit.customerapp.data.model.request.LoginRequest;
import com.vit.customerapp.data.model.request.SocialSigninRequest;
import com.vit.customerapp.data.model.response.BaseResponse;
import com.vit.customerapp.data.model.response.RegisterResponse;
import com.vit.customerapp.ui.base.BasePresenter;
import com.vit.customerapp.ui.base.BaseView;

public class SignInContract {

    interface View extends BaseView<Presenter>{
        void onLoginSuccess(RegisterResponse response);

        void onLoginError(Throwable t);

        void onSocialSignInError(Throwable t);

        void onFacebookSignInSuccess(LoginResult loginResult);

        void onGoogleSignInSuccess(GoogleSignInAccount account);
    }

    interface Presenter extends BasePresenter{

        void postLogin(LoginRequest request);

        void postSocialSignIn(SocialSigninRequest request);

        void signInFacebook(Activity activity);

        void signInGoogle(Activity activity);

        void onActivityResult(int requestCode, int resultCode, Intent data);
    }
}
