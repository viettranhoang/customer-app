package com.vit.customerapp.ui.feature.signup;

import android.accounts.Account;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.Gender;
import com.google.api.services.people.v1.model.Person;
import com.vit.customerapp.R;
import com.vit.customerapp.data.model.RegisterRequest;
import com.vit.customerapp.data.model.SocialSignupRequest;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

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

    @BindView(R.id.button_g)
    LinearLayout mButtonGoogle;

    @BindView(R.id.button_fb)
    LinearLayout mButtonFacebook;


    // ---------------------------------------------------------------------------------------------
    // FIELDS
    // ---------------------------------------------------------------------------------------------

    private static final String TAG = SignUpActivity.class.getSimpleName();

    private static final int RC_SIGN_IN = 001;

    private GoogleSignInClient mGoogleSignInClient;

    private RegisterRequest mRegisterRequest;

    private WeakReference<SignUpActivity> weakAct = new WeakReference<>(this);

    private SocialSignupRequest mSocialSignupRequest;


    // ---------------------------------------------------------------------------------------------
    // OVERRIDE METHODS
    // ---------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        initGoogleSignIn();

    }

    @OnClick(R.id.button_sign_up)
    void onClickSignUp() {
        if (isInfoAlready()) {
            Intent intent = new Intent(SignUpActivity.this, VerifyPhoneActivity.class);
            intent.putExtra("registerRequest", mRegisterRequest);
            startActivity(intent);
        }
    }

    @OnClick(R.id.button_g)
    void onClickGoogle() {
        signInGoogle();
    }

    @OnClick(R.id.button_fb)
    void onClickFacebook() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }


    // ---------------------------------------------------------------------------------------------
    // PRIVATE METHODS
    // ---------------------------------------------------------------------------------------------

    private void initGoogleSignIn() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void signInGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            new GetProfileDetails(account, weakAct, TAG).execute();
        } catch (ApiException e) {
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
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

    private void jumpVerifyActivity(Activity activity, SocialSignupRequest request) {
        Intent intent = new Intent(activity, VerifyPhoneActivity.class);
        intent.putExtra("socialRequest", request);
        activity.startActivity(intent);
    }

    // ---------------------------------------------------------------------------------------------
    // CLASSES
    // ---------------------------------------------------------------------------------------------

    static class GetProfileDetails extends AsyncTask<Void, Void, Person> {

        private PeopleService mPeopleService;
        private int mAuthError = -1;
        private WeakReference<SignUpActivity> mWeakAct;
        private String TAG;
        private GoogleSignInAccount mAcount;

        GetProfileDetails(GoogleSignInAccount account, WeakReference<SignUpActivity> weakAct, String TAG) {
            this.TAG = TAG;
            this.mWeakAct = weakAct;
            this.mAcount = account;
            GoogleAccountCredential credential = GoogleAccountCredential.usingOAuth2(
                    this.mWeakAct.get(), Collections.singleton(Scopes.PROFILE));
            credential.setSelectedAccount(
                    new Account(account.getEmail(), "com.google"));
            HttpTransport HTTP_TRANSPORT = AndroidHttp.newCompatibleTransport();
            JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
            mPeopleService = new PeopleService.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                    .setApplicationName("Google Sign In Quickstart")
                    .build();
        }

        @Override
        protected Person doInBackground(Void... params) {
            Person meProfile = null;
            try {
                meProfile = mPeopleService
                        .people()
                        .get("people/me")
                        .setPersonFields("genders")
                        .execute();
            } catch (UserRecoverableAuthIOException e) {
                e.printStackTrace();
                mAuthError = 0;
            } catch (GoogleJsonResponseException e) {
                e.printStackTrace();
                mAuthError = 1;
            } catch (IOException e) {
                e.printStackTrace();
                mAuthError = 2;
            }
            return meProfile;
        }

        @Override
        protected void onPostExecute(Person meProfile) {
            SignUpActivity mainAct = mWeakAct.get();
            if (mainAct != null) {
                if (mAuthError == 0) {
                    mainAct.signInGoogle();
                } else if (mAuthError == 1) {
                    Log.w(TAG, "People API might not enable at" +
                            " https://console.developers.google.com/apis/library/people.googleapis.com/?project=<project name>");
                } else if (mAuthError == 2) {
                    Log.w(TAG, "API io error");
                } else {
                    SocialSignupRequest request = new SocialSignupRequest(mAcount.getPhotoUrl().toString(),
                            mAcount.getEmail(), mAcount.getFamilyName(), mAcount.getGivenName(),
                            mAcount.getIdToken(), "GMAIL", mAcount.getId());
                    if (meProfile != null) {
                        List<Gender> genders = meProfile.getGenders();
                        if (genders != null && genders.size() > 0) {
                            String gender = genders.get(0).getValue();
                            Log.d(TAG, "onPostExecute gender: " + gender);
                            request.setGender(gender);

                        } else {
                            Log.d(TAG, "onPostExecute no gender if set to private ");
                        }
                    } else {
                        request.setGender("MALE");
                    }
                    mainAct.jumpVerifyActivity(mainAct, request);

                }
            }
        }
    }


}
