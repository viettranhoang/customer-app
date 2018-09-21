package com.vit.customerapp.ui.feature.verifyphone;

import android.graphics.Color;
import android.util.Log;
import android.widget.Toast;

import com.vit.customerapp.R;
import com.vit.customerapp.data.model.request.VerifyPhoneRequest;
import com.vit.customerapp.data.model.request.VerifyPincodeRequest;
import com.vit.customerapp.data.model.response.BaseResponse;
import com.vit.customerapp.data.model.response.VerifyPhoneResponse;
import com.vit.customerapp.data.remote.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyPhonePresenter implements VerifyPhoneContract.Presenter {

    private VerifyPhoneContract.View mView;

    public VerifyPhonePresenter(VerifyPhoneContract.View mView) {
        this.mView = mView;
        this.mView.setPresenter(this);
    }

    @Override
    public void postVerifyPhone(VerifyPhoneRequest request) {
        ApiUtils.getAPIService().postVerifyPhoneCall(request)
                .enqueue(new Callback<VerifyPhoneResponse>() {
                    @Override
                    public void onResponse(Call<VerifyPhoneResponse> call, Response<VerifyPhoneResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            mView.onVerifyPhoneSuccess(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<VerifyPhoneResponse> call, Throwable t) {
                        mView.onVerifyPhoneError(t);
                    }
                });
    }

    @Override
    public void postVerifyPincode(VerifyPincodeRequest request) {
        ApiUtils.getAPIService().postVerifyPincode(request)
                .enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            mView.onVerifyPincodeSuccess(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {
                        mView.onVerifyPincodeError(t);
                    }
                });
    }
}
