package com.vit.customerapp.ui.feature.menu.appointment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.vit.customerapp.R;
import com.vit.customerapp.ui.base.BaseActivity;
import com.vit.customerapp.ui.feature.review_appointment.adapter.ReviewApointmentServicesAdapter;

import butterknife.BindView;
import butterknife.OnClick;

public class AppointmentDetailActivity extends BaseActivity {

    public static void moveAppointmentDetailActivity(Activity activity) {
        Intent intent = new Intent(activity, AppointmentDetailActivity.class);
        activity.startActivity(intent);
    }

    @BindView(R.id.list_services)
    RecyclerView mRcvService;

    private ReviewApointmentServicesAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.appointment_detail_activity;
    }

    @Override
    protected int getTitleToolbarId() {
        return R.string.appointment_detail;
    }

    @Override
    protected void initView() {
        mAdapter = new ReviewApointmentServicesAdapter();
        initRcv();
    }

    @OnClick(R.id.button_reschedule)
    void onClickReschedule() {
        showRescheduleDialog();
    }

    private void initRcv() {
        mRcvService.setLayoutManager(new LinearLayoutManager(this));
        mRcvService.setHasFixedSize(true);
        mRcvService.setAdapter(mAdapter);
    }

    public void showRescheduleDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.appointment_detail_reschedule_confirm_dialog, null);
        builder.setView(dialogView);

        Button buttonYes = dialogView.findViewById(R.id.button_yes);
        Button buttonNo = dialogView.findViewById(R.id.button_no);

        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.show();

        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        buttonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

    }
}
