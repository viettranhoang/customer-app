package com.vit.customerapp.ui.feature.signin;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.vit.customerapp.R;
import com.vit.customerapp.data.model.request.LoginRequest;
import com.vit.customerapp.data.model.request.SocialSigninRequest;
import com.vit.customerapp.data.model.response.BaseResponse;
import com.vit.customerapp.data.model.response.RegisterResponse;
import com.vit.customerapp.data.remote.ApiUtils;
import com.vit.customerapp.ui.feature.main.MainActivity;
import com.vit.customerapp.ui.util.Utils;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInPresenter implements SignInContract.Presenter {

    private static final String TAG = SignInPresenter.class.getSimpleName();

    private static final int RC_SIGN_IN = 001;

    private SignInContract.View mView;

    private CallbackManager mCallbackManager;

    private GoogleSignInClient mGoogleSignInClient;

    public SignInPresenter(SignInContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void postLogin(LoginRequest request) {
        ApiUtils.getAPIService().postLogin(request).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mView.onLoginSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                mView.onLoginError(t);
            }
        });
    }

    @Override
    public void postSocialSignIn(SocialSigninRequest request) {
        ApiUtils.getAPIService().postSocialSignin(request)
                .enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            mView.onSocialSignInSuccess(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {
                        mView.onSocialSignInError(t);
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
    public void signInGoogle(Activity activity) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(activity.getString(R.string.server_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(activity, gso);

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        activity.startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_SIGN_IN) {
            if (mCallbackManager != null) {
                mCallbackManager.onActivityResult(requestCode, resultCode, data);
            }

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);

                mView.onGoogleSignInSuccess(account);
            } catch (ApiException e) {
                Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            }
        }
    }

    private void initFacebookSignIn() {
        mCallbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                mView.onFacebookSignInSuccess(loginResult);
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

}
