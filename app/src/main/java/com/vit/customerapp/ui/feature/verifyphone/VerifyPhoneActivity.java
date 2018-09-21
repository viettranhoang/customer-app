package com.vit.customerapp.ui.feature.verifyphone;

import android.app.Activity;
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
import com.vit.customerapp.data.model.request.VerifyPhoneRequest;
import com.vit.customerapp.data.model.request.VerifyPincodeRequest;
import com.vit.customerapp.data.model.response.BaseResponse;
import com.vit.customerapp.data.model.response.VerifyPhoneResponse;
import com.vit.customerapp.ui.base.BaseActivity;
import com.vit.customerapp.ui.feature.verifyphone.adapter.CountrySpinnerAdapter;
import com.vit.customerapp.ui.util.Utils;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class VerifyPhoneActivity extends BaseActivity implements VerifyPhoneContract.View {

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

    @BindColor(R.color.darkGreyBlue)
    int mDarkGreyBlue;

    @BindView(R.id.spinner_country)
    Spinner mSpinnerCountry;

    private VerifyPhoneContract.Presenter mPresenter;

    private String mRequestType;

    private String mVerifyRequestId;

    @Override
    protected void initView() {
        new VerifyPhonePresenter(this);

        mRequestType = getIntent().getStringExtra(Utils.EXTRA_REQUEST_TYPE);

        mSpinnerCountry.setAdapter(new CountrySpinnerAdapter(this));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_verify_phone;
    }

    @Override
    protected int getTitleToolbarId() {
        if (mRequestType.equals(Utils.REQUEST_TYPE_RESET_PASS)) {
            mButtonContinue.setText(R.string.send_validation_code);
            mTextContent.setText(R.string.content_forgot_password);
            return R.string.forgot_password;
        }
        mButtonContinue.setText(R.string.button_continue);
        mTextContent.setText(R.string.text_verify1);
        return R.string.verify_phone_number;
    }

    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_CANCELED);
        super.onBackPressed();
    }

    @Override
    public void setPresenter(VerifyPhoneContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onVerifyPhoneSuccess(VerifyPhoneResponse response) {
        if (response.getStatus().equals("success")) {
            changeInterface();
            mVerifyRequestId = response.getPayload().getVerifyingRequestId();
        } else {
            showDialog(response.getCode(), response.getMessage());
        }

        Log.i(TAG, "postVerifyPhone: " + response.getStatus());
    }

    @Override
    public void onVerifyPincodeSuccess(BaseResponse response) {
        if (response.getStatus().equals("success")) {
            Toast.makeText(VerifyPhoneActivity.this, "postVerifyPincode: success", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent();
            intent.putExtra(Utils.EXTRA_REQUEST_TYPE, mInputPhone.getText().toString());
            intent.putExtra(Utils.EXTRA_VERIFY_REQUEST_ID, mVerifyRequestId);
            setResult(Activity.RESULT_OK, intent);
            finish();
        } else {
            mTextTwoMin.setText(R.string.text_invalid_verification_code);
            mTextTwoMin.setTextColor(Color.RED);
            Toast.makeText(VerifyPhoneActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onVerifyPhoneError(Throwable t) {
        Log.e(TAG, "onVerifyPhoneError: " + t.getMessage());
    }

    @Override
    public void onVerifyPincodeError(Throwable t) {
        Log.e(TAG, "onVerifyPincodeError: " + t.getMessage());
    }

    @OnClick(R.id.button_continue)
    void onClickContinue() {
        if (!mInputPhone.getText().toString().isEmpty() && !mRequestType.isEmpty()) {
            String phone = mSpinnerCountry.getSelectedItem() + mInputPhone.getText().toString();
            mPresenter.postVerifyPhone(new VerifyPhoneRequest(phone, "VN",
                    mRequestType, Utils.APP_TYPE_CUSTOMER));
        }
    }

    @OnClick(R.id.button_verify)
    void onClickVerify() {
        if (!mInputCode.getText().toString().isEmpty() && !mVerifyRequestId.isEmpty()) {
            Toast.makeText(this, mVerifyRequestId, Toast.LENGTH_SHORT).show();
            mPresenter.postVerifyPincode(new VerifyPincodeRequest(mVerifyRequestId, mInputCode.getText().toString()));
        }
    }

    @OnTextChanged(R.id.input_phone_number)
    void onInputPhoneChanged() {
        mTextTwoMin.setText(R.string.text_the_code_will_expire);
        mTextTwoMin.setTextColor(mDarkGreyBlue);
    }

    // ---------------------------------------------------------------------------------------------
    // PRIBATE METHODS
    // ---------------------------------------------------------------------------------------------

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
