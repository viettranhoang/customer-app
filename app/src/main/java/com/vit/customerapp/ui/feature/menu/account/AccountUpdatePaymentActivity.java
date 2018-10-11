package com.vit.customerapp.ui.feature.menu.account;

import android.app.Activity;
import android.content.Intent;

import com.vit.customerapp.R;
import com.vit.customerapp.ui.base.BaseActivity;

public class AccountUpdatePaymentActivity extends BaseActivity {


    public static void moveAccountUpdatePaymentActivity(Activity activity) {
        Intent intent = new Intent(activity, AccountUpdatePaymentActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.account_update_payment_activity;
    }

    @Override
    protected int getTitleToolbarId() {
        return R.string.update_payment;
    }

    @Override
    protected void initView() {

    }
}
