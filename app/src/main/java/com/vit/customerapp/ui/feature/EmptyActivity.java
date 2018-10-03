package com.vit.customerapp.ui.feature;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.vit.customerapp.R;
import com.vit.customerapp.ui.base.BaseActivity;

import butterknife.BindView;

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
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }




}
