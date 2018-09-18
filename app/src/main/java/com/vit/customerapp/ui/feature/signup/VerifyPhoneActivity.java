package com.vit.customerapp.ui.feature.signup;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.vit.customerapp.R;
import com.vit.customerapp.data.model.RegisterRequest;
import com.vit.customerapp.data.model.RegisterResponse;
import com.vit.customerapp.data.model.SocialSignupRequest;
import com.vit.customerapp.data.model.VerifyPhoneRequest;
import com.vit.customerapp.data.model.VerifyPhoneResponse;
import com.vit.customerapp.data.model.VerifyPincodeResponse;
import com.vit.customerapp.data.remote.APIService;
import com.vit.customerapp.data.remote.ApiUtils;
import com.vit.customerapp.ui.feature.signup.adapter.CountrySpinnerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyPhoneActivity extends AppCompatActivity {

    private static final String TAG = VerifyPhoneActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.text_toolbar)
    TextView mToolbarTitle;

    @BindView(R.id.input_phone_number)
    EditText mInputPhone;

    @BindView(R.id.button_verify)
    Button mButtonVerify;

    @BindView(R.id.button_continue)
    Button mButtonContinue;

    @BindView(R.id.layout_input_phone)
    LinearLayout mLayoutInputPhone;

    @BindView(R.id.text_content)
    TextView mTextContent;

    @BindView(R.id.text_phone)
    TextView mTextPhone;

    @BindView(R.id.input_code)
    EditText mInputCode;

    @BindView(R.id.text_two_min)
    TextView mTextTwoMin;

    @BindView(R.id.text_resend)
    TextView mTextResend;


    @BindView(R.id.spinner_country)
    Spinner mSpinnerCountry;


    private APIService mAPIService;

    private RegisterRequest mRegisterRequest;

    private SocialSignupRequest mSocialRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_verify_phone);
        ButterKnife.bind(this);

        initActionBar();

        mSpinnerCountry.setAdapter(new CountrySpinnerAdapter(this));

        mAPIService = ApiUtils.getAPIService();

        mRegisterRequest = (RegisterRequest) getIntent().getSerializableExtra("registerRequest");

        mSocialRequest = (SocialSignupRequest) getIntent().getSerializableExtra("socialRequest");

        Toast.makeText(this, mSocialRequest.getSocialToken(), Toast.LENGTH_SHORT).show();
    }

    private void initActionBar() {
        mToolbarTitle.setText(R.string.verify_phone_number);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_left_black));
    }

    @OnClick(R.id.button_continue)
    void onClickContinue() {
        if (!mInputPhone.getText().toString().isEmpty()) {
            String phone = mSpinnerCountry.getSelectedItem() + mInputPhone.getText().toString();
            VerifyPhoneRequest request = new VerifyPhoneRequest(phone, "VN", "REGISTER", "CUSTOMER");
            postVerifyPhone(request);
            mRegisterRequest.setPhoneNumber(phone);
            mRegisterRequest.setCountryCode("VN");
        }
    }

    @OnClick(R.id.button_verify)
    void onClickVerify() {
        if (!mInputCode.getText().toString().isEmpty() && !mRegisterRequest.getVerifyRequestId().isEmpty()) {
            Toast.makeText(this, mRegisterRequest.getVerifyRequestId(), Toast.LENGTH_SHORT).show();
            postVerifyPincode(mRegisterRequest.getVerifyRequestId(), mInputCode.getText().toString());
        }
    }


    // ---------------------------------------------------------------------------------------------
    // PRIBATE METHODS
    // ---------------------------------------------------------------------------------------------


    private void postVerifyPhone(VerifyPhoneRequest request) {
        mAPIService.postVerifyPhoneCall(request)
                .enqueue(new Callback<VerifyPhoneResponse>() {
                    @Override
                    public void onResponse(Call<VerifyPhoneResponse> call, Response<VerifyPhoneResponse> response) {

                        if (response.isSuccessful()) {
                            VerifyPhoneResponse verifyResponse = response.body();
                            if (verifyResponse.getStatus().equals("success")) {
                                changeInterface();
                                mRegisterRequest.setVerifyRequestId(verifyResponse.getPayload().getVerifyingRequestId());
                            } else {
                                showDialog(verifyResponse.getCode(), verifyResponse.getMessage());
                            }

                            Log.i(TAG, "postVerifyPhone: " + verifyResponse.getStatus());
                        }
                    }

                    @Override
                    public void onFailure(Call<VerifyPhoneResponse> call, Throwable t) {
                        Log.e(TAG, "error postVerifyPhone: " + t.toString());
                    }
                });
    }

    private void postVerifyPincode(String verifyingRequestId, String pincode) {
        mAPIService.postVerifyPincode(verifyingRequestId, pincode)
                .enqueue(new Callback<VerifyPincodeResponse>() {
                    @Override
                    public void onResponse(Call<VerifyPincodeResponse> call, Response<VerifyPincodeResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equals("success")) {

                                mRegisterRequest.setVerifyRequestId(verifyingRequestId);
                                postRegister(mRegisterRequest);
                                Toast.makeText(VerifyPhoneActivity.this, "postVerifyPincode: success", Toast.LENGTH_SHORT).show();
                            } else {
                                mTextTwoMin.setText(R.string.text_invalid_verification_code);
                                mTextTwoMin.setTextColor(Color.RED);
                                Toast.makeText(VerifyPhoneActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<VerifyPincodeResponse> call, Throwable t) {

                    }
                });
    }

    private void postRegister(RegisterRequest request) {
        mAPIService.postRegisterResponse(request).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()) {
                    RegisterResponse registerResponse = response.body();
                    if (registerResponse.getStatus().equals("success")) {
                        showDialog(registerResponse.getStatus(), registerResponse.getPayload().getToken());
                    } else {
                        showDialog(registerResponse.getCode(), registerResponse.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Log.e(TAG, "error postRegister: " + t.toString());
            }
        });

    }

    private void changeInterface() {
        mLayoutInputPhone.setVisibility(View.GONE);
        mTextContent.setText(R.string.text_verify2);
        mTextPhone.setText(mInputPhone.getText().toString());
        mTextPhone.setVisibility(View.VISIBLE);
        mInputCode.setVisibility(View.VISIBLE);
        mTextTwoMin.setVisibility(View.VISIBLE);
        mTextResend.setVisibility(View.VISIBLE);
        mButtonContinue.setVisibility(View.GONE);
        mButtonVerify.setVisibility(View.VISIBLE);

    }

    public void showDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
