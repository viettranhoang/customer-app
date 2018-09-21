package com.vit.customerapp.ui.feature.signup;

import android.app.Activity;
import android.content.Intent;

import com.vit.customerapp.data.model.request.RegisterRequest;
import com.vit.customerapp.data.model.request.SocialSignupRequest;
import com.vit.customerapp.data.model.response.RegisterResponse;
import com.vit.customerapp.ui.base.BasePresenter;
import com.vit.customerapp.ui.base.BaseView;

public class SignUpContract {

    interface View extends BaseView<Presenter> {
        void onRegisterSuccess(RegisterResponse response);

        void onFacebookSignInSuccess(SocialSignupRequest request);

    }

    interface Presenter extends BasePresenter {

        void postRegister(RegisterRequest request);

        void postSocialSignup(SocialSignupRequest request);

        void signInFacebook(Activity activity);

        void onActivityResult(int requestCode, int resultCode, Intent data);

    }

}
