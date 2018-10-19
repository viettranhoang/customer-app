package com.vit.customerapp.ui.feature.review_appointment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.vit.customerapp.R;
import com.vit.customerapp.ui.base.BaseActivity;
import com.vit.customerapp.ui.feature.choose_service.ChooseServiceActivity;
import com.vit.customerapp.ui.feature.menu.activity.DashboardActivity;
import com.vit.customerapp.ui.feature.review_appointment.adapter.ReviewApointmentServicesAdapter;

import butterknife.BindView;
import butterknife.OnClick;

public class ReviewAppointmentActivity extends BaseActivity {


    public static void moveReviewAppointmentActivity(Activity activity) {
        Intent intent = new Intent(activity, ReviewAppointmentActivity.class);
        activity.startActivity(intent);
    }


    @BindView(R.id.list_services)
    RecyclerView mRcvService;

    private ReviewApointmentServicesAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.review_appointment_activity;
    }

    @Override
    protected int getTitleToolbarId() {
        mImageToolbar.setVisibility(View.VISIBLE);
        return R.string.app_name;
    }

    @Override
    protected void initView() {
        mAdapter = new ReviewApointmentServicesAdapter();
        initRcv();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_edit) {
            ChooseServiceActivity.moveChooseServiceActivity(this);
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.button_confirm)
    void onClickConfirm() {
        showSuccessfullyDialog();
    }

    private void initRcv() {
        mRcvService.setLayoutManager(new LinearLayoutManager(this));
        mRcvService.setHasFixedSize(true);
        mRcvService.setAdapter(mAdapter);
    }

    private void showSuccessfullyDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.dialog_password_success, null);
        builder.setView(dialogView);
        builder.setCancelable(false);

        TextView textContent = dialogView.findViewById(R.id.text_content_dialog);
        textContent.setText(getString(R.string.your_request_is_now_being_processed));

        Button buttonOk = dialogView.findViewById(R.id.button_login);
        buttonOk.setText("OK");
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReviewAppointmentActivity.this, DashboardActivity.class));
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.show();
    }
}
