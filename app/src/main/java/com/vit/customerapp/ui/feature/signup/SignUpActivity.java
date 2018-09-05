package com.vit.customerapp.ui.feature.signup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.vit.customerapp.R;
import com.vit.customerapp.data.model.RegisterRequest;
import com.vit.customerapp.ui.feature.signup.adapter.CountrySpinnerAdapter;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.input_first_name)
    EditText mInputFirstName;

    @BindView(R.id.input_last_name)
    EditText mInputLastName;

    @BindView(R.id.input_email)
    EditText mInputEmail;

    @BindView(R.id.input_password)
    EditText mInputPassword;

    @BindView(R.id.input_password_confirm)
    EditText mInputPasswordConfirm;


    private RegisterRequest mRegisterRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.button_sign_up)
    void onClickSignUp() {
        if (isInfoAlready()) {
            Intent intent = new Intent(SignUpActivity.this, VerifyPhoneActivity.class);
            intent.putExtra("registerRequest", mRegisterRequest);
            startActivity(intent);
        }
    }

    private boolean isInfoAlready() {
        String firstName = mInputFirstName.getText().toString();
        String lastName = mInputLastName.getText().toString();
        String email = mInputEmail.getText().toString();
        String password = mInputPassword.getText().toString();
        String passwordConfirm = mInputPasswordConfirm.getText().toString();

        if (firstName.isEmpty() ||
                lastName.isEmpty() ||
                email.isEmpty() ||
                password.isEmpty() ||
                passwordConfirm.isEmpty()) {
            return false;
        }

        if (!password.equals(passwordConfirm)) {
            Toast.makeText(this, getString(R.string.password_confirm_is_not_true), Toast.LENGTH_SHORT).show();
            return false;
        }

        mRegisterRequest = new RegisterRequest(password, email, lastName, firstName);
        return true;
    }
}
