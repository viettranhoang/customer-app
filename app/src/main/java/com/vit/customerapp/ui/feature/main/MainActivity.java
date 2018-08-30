package com.vit.customerapp.ui.feature.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.vit.customerapp.R;
import com.vit.customerapp.ui.feature.signup.VerifyPhoneActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(this, VerifyPhoneActivity.class));
    }
}
