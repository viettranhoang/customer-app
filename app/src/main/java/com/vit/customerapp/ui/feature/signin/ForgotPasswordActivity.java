package com.vit.customerapp.ui.feature.signin;

import android.app.AlertDialog;
import android.content.Intent;
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
import com.vit.customerapp.data.model.request.VerifyPhoneRequest;
import com.vit.customerapp.data.model.response.VerifyPhoneResponse;
import com.vit.customerapp.data.model.request.VerifyPincodeRequest;
import com.vit.customerapp.data.remote.ApiUtils;
import com.vit.customerapp.ui.base.BaseActivity;
import com.vit.customerapp.ui.feature.signup.adapter.CountrySpinnerAdapter;
import com.vit.customerapp.ui.util.Utils;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends BaseActivity {


    @BindView(R.id.spinner_country)
    Spinner mSpinnerCountry;

    @BindView(R.id.input_phone_number)
    EditText mInputPhone;

    @BindView(R.id.button_verify)
    Button mButtonVerify;

    @BindView(R.id.button_send_code)
    Button mButtonSendCode;

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

    @BindColor(R.color.darkGreyBlue)
    int mDarkGreyBlue;

    private String mVerifyRequestId;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_forgot_password;
    }

    @Override
    protected int getTitleToolbarId() {
        return R.string.forgot_password;
    }

    @Override
    protected void initView() {
        mSpinnerCountry.setAdapter(new CountrySpinnerAdapter(this));
    }

    @OnClick(R.id.button_send_code)
    void onClickSendCode() {
        if (!mInputPhone.getText().toString().isEmpty()) {
            String phone = mSpinnerCountry.getSelectedItem() + mInputPhone.getText().toString();
            VerifyPhoneRequest request = new VerifyPhoneRequest(phone, "VN",
                    Utils.REQUEST_TYPE_RESET_PASS, Utils.APP_TYPE_CUSTOMER);
            postVerifyPhone(request);
        }
    }

    @OnClick(R.id.button_verify)
    void onClickVerify() {
        if (!mInputCode.getText().toString().isEmpty() && !mVerifyRequestId.isEmpty()) {
            Toast.makeText(this, mVerifyRequestId + " " + mInputCode.getText().toString(), Toast.LENGTH_LONG).show();
            postVerifyPincode(new VerifyPincodeRequest(mVerifyRequestId, mInputCode.getText().toString()));
        } else {
            Log.e(TAG, "onClickVerify: pinCode or verifyRequestId null");
        }
    }

    @OnTextChanged(R.id.input_phone_number)
    void onInputPhoneChanged() {
        mTextTwoMin.setText(R.string.text_the_code_will_expire);
        mTextTwoMin.setTextColor(mDarkGreyBlue);
    }

    private void postVerifyPhone(VerifyPhoneRequest request) {
        ApiUtils.getAPIService().postVerifyPhoneCall(request)
                .enqueue(new Callback<VerifyPhoneResponse>() {
                    @Override
                    public void onResponse(Call<VerifyPhoneResponse> call, Response<VerifyPhoneResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            VerifyPhoneResponse verifyResponse = response.body();
                            if (verifyResponse.getStatus().equals("success")) {
                                changeInterface();
                                mVerifyRequestId = verifyResponse.getPayload().getVerifyingRequestId();
                            } else {
                                showDialog(verifyResponse.getCode(), verifyResponse.getMessage());
                            }

                            Log.i(TAG, "postVerifyPhone: " + verifyResponse.getStatus());
                        }
                    }

                    @Override
                    public void onFailure(Call<VerifyPhoneResponse> call, Throwable t) {

                    }
                });
    }

    private void postVerifyPincode(VerifyPincodeRequest request) {
        ApiUtils.getAPIService().postVerifyPincode(request)
                .enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            if (response.body().getStatus().equals("success")) {
                                Toast.makeText(ForgotPasswordActivity.this, "postVerifyPincode: success", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(ForgotPasswordActivity.this, NewPasswordSettingActivity.class);
                                intent.putExtra("verify_request_id", mVerifyRequestId);
                                startActivity(intent);

                            } else {
                                mTextTwoMin.setText(R.string.text_invalid_verification_code);
                                mTextTwoMin.setTextColor(Color.RED);
                                Toast.makeText(ForgotPasswordActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {

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
        mButtonSendCode.setVisibility(View.GONE);
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
