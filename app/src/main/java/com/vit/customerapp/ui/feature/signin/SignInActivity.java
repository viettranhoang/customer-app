package com.vit.customerapp.ui.feature.signin;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
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
import com.vit.customerapp.data.model.response.BaseResponse;
import com.vit.customerapp.data.model.request.LoginRequest;
import com.vit.customerapp.data.model.response.RegisterResponse;
import com.vit.customerapp.data.model.request.SocialSigninRequest;
import com.vit.customerapp.data.remote.ApiUtils;
import com.vit.customerapp.ui.feature.main.MainActivity;
import com.vit.customerapp.ui.feature.signup.SignUpActivity;
import com.vit.customerapp.ui.util.Utils;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {

    private static final String TAG = SignUpActivity.class.getSimpleName();

    private static final int RC_SIGN_IN = 001;

    @BindView(R.id.input_phone)
    TextInputEditText mInputPhone;

    @BindView(R.id.input_password)
    TextInputEditText mInputPassword;

    @BindView(R.id.layout_sign_in)
    LinearLayout mLayoutSignIn;


    private GoogleSignInClient mGoogleSignInClient;
    private CallbackManager mCallbackManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);

        initGoogleSignIn();

        initFacebookSignIn();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }

        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick(R.id.button_sign_in)
    void onClickSignIn() {
        if (!isInputEmpty()) {
            postLogin(new LoginRequest(mInputPhone.getText().toString(), mInputPassword.getText().toString(), "VN"));
        }
    }

    @OnClick(R.id.text_forgot_password)
    void onClickForgotPassword() {
        startActivity(new Intent(this, ForgotPasswordActivity.class));
    }

    @OnClick(R.id.button_fb)
    void onClickFacebook()  {
        LoginManager.getInstance().logInWithReadPermissions(SignInActivity.this,
                Arrays.asList("public_profile", "email", "user_gender"));
    }

    @OnClick(R.id.button_g)
    void onClickGoogle()  {
        signInGoogle();
    }

    @OnClick(R.id.text_sign_up)
    void onClickSignUp() {
        startActivity(new Intent(this, SignUpActivity.class));
    }

    @OnTextChanged({R.id.input_phone, R.id.input_password})
    void onInputChanged() {
        if (!isInputEmpty()) {
            mLayoutSignIn.setAlpha(1);
        } else {
            mLayoutSignIn.setAlpha((float) 0.5);
        }
    }

    private boolean isInputEmpty() {
        if (mInputPhone.getText().toString().isEmpty() |
                mInputPassword.getText().toString().isEmpty()) {
            return true;
        }
        return false;
    }

    private void initFacebookSignIn() {
        mCallbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(SignInActivity.this, loginResult.getAccessToken().getUserId(), Toast.LENGTH_SHORT).show();
                postSocialSignIn(new SocialSigninRequest(loginResult.getAccessToken().getUserId(), Utils.SOCIAL_TYPE_FACEBOOK));
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

    private void initGoogleSignIn() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void signInGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            Toast.makeText(this, account.getId(), Toast.LENGTH_SHORT).show();

            postSocialSignIn(new SocialSigninRequest(account.getId(), Utils.SOCIAL_TYPE_GMAIL));
        } catch (ApiException e) {
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
        }
    }

    private void postSocialSignIn(SocialSigninRequest request) {
        ApiUtils.getAPIService().postSocialSignin(request)
                .enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            if (response.body().getStatus().equals(Utils.STATUS_SUCCESS)) {
                                Toast.makeText(SignInActivity.this, "Đăng nhập thành công với id:" + request.getSocialId(), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignInActivity.this, MainActivity.class));
                            } else {
                                Toast.makeText(SignInActivity.this, request.getSocialId() + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Log.e(TAG, "onResponse: ERROR");
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.toString());
                    }
                });
    }

    private void postLogin(LoginRequest request) {
        ApiUtils.getAPIService().postLogin(request).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getStatus().equals(Utils.STATUS_SUCCESS)) {
                        Toast.makeText(SignInActivity.this, "Login successfully!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignInActivity.this, MainActivity.class));
                    } else {
                        Toast.makeText(SignInActivity.this, response.body().getStatus() + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage() );
            }
        });
    }
}
