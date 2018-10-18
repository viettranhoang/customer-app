package com.vit.customerapp.ui.feature.choose_service;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

import com.vit.customerapp.R;
import com.vit.customerapp.ui.base.BaseActivity;

public class ChooseLocationActivity extends BaseActivity {

    public static void moveChooseLocationActivity(Activity activity) {
        Intent intent = new Intent(activity, ChooseLocationActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.choose_location_activity;
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
}
