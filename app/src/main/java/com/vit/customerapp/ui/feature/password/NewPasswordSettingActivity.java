package com.vit.customerapp.ui.feature.password;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vit.customerapp.R;
import com.vit.customerapp.data.model.response.BaseResponse;
import com.vit.customerapp.data.model.request.ResetPasswordRequest;
import com.vit.customerapp.data.remote.ApiUtils;
import com.vit.customerapp.ui.base.BaseActivity;
import com.vit.customerapp.ui.feature.signin.SignInActivity;
import com.vit.customerapp.ui.util.Utils;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewPasswordSettingActivity extends BaseActivity {

    @BindView(R.id.layout_new_password)
    LinearLayout mLayoutNewPassword;

    @BindView(R.id.input_new_password)
    TextInputEditText mInputNewPassword;

    @BindView(R.id.input_confirm_new_password)
    TextInputEditText mInputConfirmNewPassword;

    @BindView(R.id.text_password_not_match)
    TextView mTextPasswordNotMatch;

    private String mVerifyRequestId;

    @Override
    protected void initView() {
        mVerifyRequestId = getIntent().getStringExtra(Utils.EXTRA_VERIFY_REQUEST_ID);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_password_setting;
    }

    @Override
    protected int getTitleToolbarId() {
        return R.string.new_password_setting;
    }

    @OnClick(R.id.button_sumbit)
    void onClickSumbit() {
        if (!isInputEmpty()) {
            String pass = mInputNewPassword.getText().toString();
            if (!pass.equals(mInputConfirmNewPassword.getText().toString())) {
                mTextPasswordNotMatch.setVisibility(View.VISIBLE);
            } else {
                if (!mVerifyRequestId.isEmpty()) {
                    postResetPassword(new ResetPasswordRequest(pass, mVerifyRequestId, Utils.APP_TYPE_CUSTOMER));
                }
                Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @OnTextChanged({R.id.input_new_password, R.id.input_confirm_new_password})
    void onInputChanged() {
        mTextPasswordNotMatch.setVisibility(View.INVISIBLE);
        if (!isInputEmpty()) {
            mLayoutNewPassword.setAlpha(1);
        } else {
            mLayoutNewPassword.setAlpha((float) 0.5);
        }
    }

    private boolean isInputEmpty() {
        if (mInputNewPassword.getText().toString().isEmpty() |
                mInputConfirmNewPassword.getText().toString().isEmpty()) {
            return true;
        }
        return false;
    }

    private void postResetPassword(ResetPasswordRequest request) {
        ApiUtils.getAPIService().postResetPassword(request)
                .enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            if (response.body().getStatus().equals(Utils.STATUS_SUCCESS)) {
                                Utils.showSuccessfullyDialog(NewPasswordSettingActivity.this, getString(R.string.password_updated_successfully));
                            } else {
                                Toast.makeText(NewPasswordSettingActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getMessage());
                    }
                });
    }


}
