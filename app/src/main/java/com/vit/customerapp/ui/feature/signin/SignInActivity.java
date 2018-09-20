package com.vit.customerapp.ui.feature.signin;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.vit.customerapp.R;
import com.vit.customerapp.data.model.request.LoginRequest;
import com.vit.customerapp.data.model.request.SocialSigninRequest;
import com.vit.customerapp.data.model.response.BaseResponse;
import com.vit.customerapp.data.model.response.RegisterResponse;
import com.vit.customerapp.ui.feature.main.MainActivity;
import com.vit.customerapp.ui.feature.password.ForgotPasswordActivity;
import com.vit.customerapp.ui.feature.signup.SignUpActivity;
import com.vit.customerapp.ui.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class SignInActivity extends AppCompatActivity implements SignInContract.View {

    private static final String TAG = SignUpActivity.class.getSimpleName();

    @BindView(R.id.input_phone)
    TextInputEditText mInputPhone;

    @BindView(R.id.input_password)
    TextInputEditText mInputPassword;

    @BindView(R.id.layout_sign_in)
    LinearLayout mLayoutSignIn;


    private SignInContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);

        mPresenter = new SignInPresenter(this);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mPresenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onLoginSuccess(RegisterResponse response) {
        if (response.getStatus().equals(Utils.STATUS_SUCCESS)) {
            Toast.makeText(SignInActivity.this, "Login successfully!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SignInActivity.this, MainActivity.class));
        } else {
            Toast.makeText(SignInActivity.this, response.getStatus() + response.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSocialSignInSuccess(BaseResponse response) {
        if (response.getStatus().equals(Utils.STATUS_SUCCESS)) {
            Toast.makeText(SignInActivity.this, "Login successfully!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SignInActivity.this, MainActivity.class));
        } else {
            Toast.makeText(SignInActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoginError(Throwable t) {
        Log.e(TAG, "onLoginError: " + t.getMessage());
    }

    @Override
    public void onSocialSignInError(Throwable t) {
        Log.e(TAG, "onSocialSignInError: " + t.getMessage());
    }

    @Override
    public void onFacebookSignInSuccess(LoginResult loginResult) {
        Toast.makeText(SignInActivity.this, loginResult.getAccessToken().getUserId(), Toast.LENGTH_SHORT).show();

        mPresenter.postSocialSignIn(new SocialSigninRequest(loginResult.getAccessToken().getUserId(), Utils.SOCIAL_TYPE_FACEBOOK));
    }

    @Override
    public void onGoogleSignInSuccess(GoogleSignInAccount account) {
        Toast.makeText(this, account.getId(), Toast.LENGTH_SHORT).show();

        mPresenter.postSocialSignIn(new SocialSigninRequest(account.getId(), Utils.SOCIAL_TYPE_GMAIL));
    }

    @OnClick(R.id.button_sign_in)
    void onClickSignIn() {
        if (!isInputEmpty()) {
            mPresenter.postLogin(new LoginRequest(mInputPhone.getText().toString(), mInputPassword.getText().toString(), "VN"));
        }
    }

    @OnClick(R.id.text_forgot_password)
    void onClickForgotPassword() {
        startActivity(new Intent(this, ForgotPasswordActivity.class));
    }

    @OnClick(R.id.button_fb)
    void onClickFacebook() {
        mPresenter.signInFacebook(SignInActivity.this);
    }

    @OnClick(R.id.button_g)
    void onClickGoogle() {
        mPresenter.signInGoogle(SignInActivity.this);
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
}
