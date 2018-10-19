package com.vit.customerapp.ui.feature;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

import com.vit.customerapp.R;
import com.vit.customerapp.ui.base.BaseActivity;

public class EmptyActivity extends BaseActivity{

    public static void moveLoginActivity(Activity activity) {
        Intent intent = new Intent(activity, EmptyActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void initView() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_empty;
    }

    @Override
    protected int getTitleToolbarId() {
        mImageToolbar.setVisibility(View.VISIBLE);
        return R.string.app_name;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.notify_menu, menu);
        return true;
    }




}
