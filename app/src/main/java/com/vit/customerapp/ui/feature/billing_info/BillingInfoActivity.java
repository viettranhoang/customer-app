package com.vit.customerapp.ui.feature.billing_info;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import com.vit.customerapp.R;
import com.vit.customerapp.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class BillingInfoActivity extends BaseActivity {

    public static void moveBillingInfoActivity(Activity activity) {
        Intent intent = new Intent(activity, BillingInfoActivity.class);
        activity.startActivity(intent);
    }

    @BindView(R.id.image_choose_appointment)
    ImageView mImageChooseAppointment;

    @Override
    protected int getLayoutId() {
        return R.layout.billing_info_activity;
    }

    @Override
    protected int getTitleToolbarId() {
        mImageToolbar.setVisibility(View.VISIBLE);
        return R.string.app_name;
    }

    @Override
    protected void initView() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @OnClick(R.id.image_choose_appointment)
    void onClickUseGelColor() {
        if (!mImageChooseAppointment.isSelected()) {
            mImageChooseAppointment.setSelected(true);
        } else {
            mImageChooseAppointment.setSelected(false);
        }

    }
}
