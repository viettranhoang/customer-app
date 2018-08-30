package com.vit.customerapp.ui.feature.signup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Spinner;
import android.widget.Toast;

import com.vit.customerapp.R;
import com.vit.customerapp.data.model.RegisterRequest;
import com.vit.customerapp.data.model.RegisterResponse;
import com.vit.customerapp.data.model.VerifyPhoneResponse;
import com.vit.customerapp.data.remote.APIService;
import com.vit.customerapp.data.remote.ApiUtils;
import com.vit.customerapp.ui.feature.signup.adapter.CountrySpinnerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyPhoneActivity extends AppCompatActivity {

    private static final String TAG = "VerifyPhone: ";
    @BindView(R.id.spinner_country)
    Spinner mSpinnerCountry;

    private APIService mAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone);
        ButterKnife.bind(this);

        mSpinnerCountry.setAdapter(new CountrySpinnerAdapter(this));

        mAPIService = ApiUtils.getAPIService();

        postRegister(new RegisterRequest("913c6dee-a210-4648-b625-1287ce02db6b",
                "USA", "string","(817) 569-8900",
                "khanh.tran@savvycomsoftware.com", "Khanh","Tran"));

//        postVerifyPhone("(817) 569-8900", "USA", "REGISTER", "CUSTOMER");
    }

    private void postRegister(RegisterRequest request) {
        mAPIService.postRegisterResponse(request).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(VerifyPhoneActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "postRegister: " + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Log.e(TAG, "error postRegister: " + t.toString());
            }
        });

    }

    private void postVerifyPhone(String phoneNumber, String countryCode, String requestType, String appType) {
        mAPIService.postVerifyPhoneCall(phoneNumber, countryCode, requestType, appType)
                .enqueue(new Callback<VerifyPhoneResponse>() {
            @Override
            public void onResponse(Call<VerifyPhoneResponse> call, Response<VerifyPhoneResponse> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(VerifyPhoneActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "postVerifyPhone: " + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<VerifyPhoneResponse> call, Throwable t) {
                Log.e(TAG, "error postVerifyPhone: " + t.toString());
            }
        });
    }
}
