package com.vit.customerapp.ui.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.vit.customerapp.R;
import com.vit.customerapp.ui.feature.password.NewPasswordSettingActivity;
import com.vit.customerapp.ui.feature.signin.SignInActivity;

public class Utils {
    public static final String SOCIAL_TYPE_GMAIL = "GMAIL";
    public static final String SOCIAL_TYPE_FACEBOOK = "FACEBOOK";
    public static final String STATUS_SUCCESS = "success";

    public static final String EXTRA_VERIFY_REQUEST_ID = "EXTRA_VERIFY_REQUEST_ID";
    public static final String EXTRA_REQUEST_TYPE = "EXTRA_REQUEST_TYPE";
    public static final String REQUEST_TYPE_REGISTER = "REGISTER";
    public static final String REQUEST_TYPE_RESET_PASS = "RESET_PASSWORD";
    public static final String REQUEST_TYPE_UPDATE_PHONE = "UPDATE_PHONE";
    public static final String APP_TYPE_CUSTOMER = "CUSTOMER";
    public static final String APP_TYPE_TECHNICIAN = "TECHNICIAN";

    public static void showSuccessfullyDialog(Activity activity, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.dialog_password_success, null);
        builder.setView(dialogView);
        builder.setCancelable(false);

        TextView textContent = dialogView.findViewById(R.id.text_content_dialog);
        textContent.setText(message);

        dialogView.findViewById(R.id.button_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.startActivity(new Intent(activity, SignInActivity.class));
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.show();
    }
}
