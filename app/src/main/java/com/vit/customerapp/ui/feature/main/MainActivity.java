package com.vit.customerapp.ui.feature.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.vit.customerapp.R;
import com.vit.customerapp.ui.feature.signup.SignUpActivity;
import com.vit.customerapp.ui.feature.signup.VerifyPhoneActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        startActivity(new Intent(this, SignUpActivity.class));
    }
}
