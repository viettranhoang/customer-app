package com.vit.customerapp.ui.feature.signup;

import android.app.AlertDialog;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.vit.customerapp.R;
import com.vit.customerapp.data.model.response.BaseResponse;
import com.vit.customerapp.data.model.request.RegisterRequest;
import com.vit.customerapp.data.model.response.RegisterResponse;
import com.vit.customerapp.data.model.request.SocialSignupRequest;
import com.vit.customerapp.data.model.request.VerifyPhoneRequest;
import com.vit.customerapp.data.model.response.VerifyPhoneResponse;
import com.vit.customerapp.data.model.request.VerifyPincodeRequest;
import com.vit.customerapp.data.remote.APIService;
import com.vit.customerapp.data.remote.ApiUtils;
import com.vit.customerapp.ui.base.BaseActivity;
import com.vit.customerapp.ui.feature.signup.adapter.CountrySpinnerAdapter;
import com.vit.customerapp.ui.util.Utils;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyPhoneActivity extends BaseActivity {

    private static final String TAG = VerifyPhoneActivity.class.getSimpleName();

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
    protected int getLayoutId() {
        return R.layout.activity_verify_phone;
    }

    @Override
    protected int getTitleToolbarId() {
        return R.string.verify_phone_number;
    }

    @Override
    protected void initView() {
        mSpinnerCountry.setAdapter(new CountrySpinnerAdapter(this));

        mAPIService = ApiUtils.getAPIService();

        mRegisterRequest = (RegisterRequest) getIntent().getSerializableExtra("registerRequest");

        mSocialRequest = (SocialSignupRequest) getIntent().getSerializableExtra("socialRequest");
    }


    @OnClick(R.id.button_continue)
    void onClickContinue() {
        if (!mInputPhone.getText().toString().isEmpty()) {
            String phone = mSpinnerCountry.getSelectedItem() + mInputPhone.getText().toString();
            VerifyPhoneRequest request = new VerifyPhoneRequest(phone, "VN",
                    Utils.REQUEST_TYPE_REGISTER, Utils.APP_TYPE_CUSTOMER);
            postVerifyPhone(request);
            mRegisterRequest.setPhoneNumber(phone);
            mRegisterRequest.setCountryCode("VN");
        }
    }

    @OnClick(R.id.button_verify)
    void onClickVerify() {
        if (!mInputCode.getText().toString().isEmpty() && !mRegisterRequest.getVerifyRequestId().isEmpty()) {
            Toast.makeText(this, mRegisterRequest.getVerifyRequestId(), Toast.LENGTH_SHORT).show();
            postVerifyPincode(new VerifyPincodeRequest(mRegisterRequest.getVerifyRequestId(), mInputCode.getText().toString()));
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

    private void postVerifyPincode(VerifyPincodeRequest request) {
        mAPIService.postVerifyPincode(request)
                .enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equals("success")) {

                                mRegisterRequest.setVerifyRequestId(request.getVerifyRequestId());
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
                    public void onFailure(Call<BaseResponse> call, Throwable t) {

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
