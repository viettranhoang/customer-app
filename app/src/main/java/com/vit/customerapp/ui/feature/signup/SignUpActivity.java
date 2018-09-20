package com.vit.customerapp.ui.feature.signup;

import android.accounts.Account;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
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
import com.vit.customerapp.data.model.request.RegisterRequest;
import com.vit.customerapp.data.model.request.SocialSignupRequest;
import com.vit.customerapp.ui.feature.signin.SignInActivity;
import com.vit.customerapp.ui.util.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = SignUpActivity.class.getSimpleName();

    private static final int RC_SIGN_IN = 001;

    @BindView(R.id.layout_sign_out)
    LinearLayout mLayoutSignOut;

    @BindView(R.id.input_first_name)
    TextInputEditText mInputFirstName;

    @BindView(R.id.input_last_name)
    TextInputEditText mInputLastName;

    @BindView(R.id.input_email)
    TextInputEditText mInputEmail;

    @BindView(R.id.input_password)
    TextInputEditText mInputPassword;

    @BindView(R.id.input_password_confirm)
    TextInputEditText mInputPasswordConfirm;

    @BindView(R.id.text_password_not_match)
    TextView mTextPasswordNotMatch;

    @BindView(R.id.button_g)
    LinearLayout mButtonGoogle;

    @BindView(R.id.button_fb)
    LinearLayout mButtonFacebook;


    // ---------------------------------------------------------------------------------------------
    // FIELDS
    // ---------------------------------------------------------------------------------------------

    private GoogleSignInClient mGoogleSignInClient;

    private RegisterRequest mRegisterRequest;

    private WeakReference<SignUpActivity> mWeakAct = new WeakReference<>(this);

    private SocialSignupRequest mSocialSignupRequest;

    private CallbackManager mCallbackManager;


    // ---------------------------------------------------------------------------------------------
    // OVERRIDE METHODS
    // ---------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        initGoogleSignIn();

        initFacebookSignIn();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }

        mCallbackManager.onActivityResult(requestCode, resultCode, data);
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
        LoginManager.getInstance().logInWithReadPermissions(SignUpActivity.this,
                Arrays.asList("public_profile", "email", "user_gender"));
    }

    @OnClick(R.id.text_sign_in)
    void onClickSignIn() {
        startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
    }

    @OnTextChanged({R.id.input_first_name, R.id.input_last_name, R.id.input_email,
            R.id.input_password, R.id.input_password_confirm})
    void onInputChanged() {
        mTextPasswordNotMatch.setVisibility(View.INVISIBLE);
        if (!isAllInputEmpty()) {
            mLayoutSignOut.setAlpha(1);
        } else {
            mLayoutSignOut.setAlpha((float) 0.5);
        }
    }


    // ---------------------------------------------------------------------------------------------
    // PRIVATE METHODS
    // ---------------------------------------------------------------------------------------------

    private void initFacebookSignIn() {
        mCallbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                getFacebookProfile(loginResult);

            }

            @Override
            public void onCancel() {
                Log.d(TAG, "onCancel: Login canceled");
            }

            @Override
            public void onError(FacebookException error) {
                Log.e(TAG, "onError: Login failed");
            }
        });
    }

    private void getFacebookProfile(LoginResult loginResult) {
        Profile profile = Profile.getCurrentProfile();

        String id = loginResult.getAccessToken().getUserId();
        String token = loginResult.getAccessToken().getToken();
        String firstName = profile.getFirstName();
        String lastName = profile.getLastName();
        String avatar = profile.getProfilePictureUri(300, 300).toString();

        GraphRequest request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {

                        Log.v("LoginActivity Response ", response.toString());

                        try {
                            String email = object.getString("email");
                            String gender = object.getString("gender");

                            mSocialSignupRequest = new SocialSignupRequest(
                                    avatar, email, lastName, firstName,
                                    token, Utils.SOCIAL_TYPE_FACEBOOK, id);

                            mSocialSignupRequest.setGender(!gender.isEmpty() ? gender : "");

                            SignUpActivity mainAct = mWeakAct.get();
                            jumpVerifyActivity(mainAct, mSocialSignupRequest);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,gender");
        request.setParameters(parameters);
        request.executeAsync();
    }

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

            new GetGoogleProfileDetails(account, mWeakAct, TAG).execute();
        } catch (ApiException e) {
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
        }
    }

    private boolean isInfoAlready() {
        if (!isAllInputEmpty()) {
            String firstName = mInputFirstName.getText().toString();
            String lastName = mInputLastName.getText().toString();
            String email = mInputEmail.getText().toString();
            String password = mInputPassword.getText().toString();
            String passwordConfirm = mInputPasswordConfirm.getText().toString();

            if (!password.equals(passwordConfirm)) {
                mTextPasswordNotMatch.setVisibility(View.VISIBLE);
                return false;
            }
            mRegisterRequest = new RegisterRequest(password, email, lastName, firstName);
            return true;
        }
        return false;
    }

    private boolean isAllInputEmpty() {
        if (mInputFirstName.getText().toString().isEmpty() ||
                mInputLastName.getText().toString().isEmpty() ||
                mInputEmail.getText().toString().isEmpty() ||
                mInputPassword.getText().toString().isEmpty() ||
                mInputPasswordConfirm.getText().toString().isEmpty()) {
            return true;
        }

        return false;
    }

    private void jumpVerifyActivity(Activity activity, SocialSignupRequest request) {
        Intent intent = new Intent(activity, VerifyPhoneActivity.class);
        intent.putExtra("socialRequest", request);
        activity.startActivity(intent);
    }


    // ---------------------------------------------------------------------------------------------
    // CLASSES
    // ---------------------------------------------------------------------------------------------

    static class GetGoogleProfileDetails extends AsyncTask<Void, Void, Person> {

        private PeopleService mPeopleService;
        private int mAuthError = -1;
        private WeakReference<SignUpActivity> mWeakAct;
        private String TAG;
        private GoogleSignInAccount mAcount;

        GetGoogleProfileDetails(GoogleSignInAccount account, WeakReference<SignUpActivity> weakAct, String TAG) {
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
                            mAcount.getIdToken(), Utils.SOCIAL_TYPE_GMAIL, mAcount.getId());
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
