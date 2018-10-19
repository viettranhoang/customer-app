package com.vit.customerapp.ui.feature.choose_service;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.vit.customerapp.R;
import com.vit.customerapp.ui.base.BaseActivity;
import com.vit.customerapp.ui.feature.choose_service.adapter.ChooseServiceAdapter;
import com.vit.customerapp.ui.feature.choose_service.listener.OnClickChooseServiceItemListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ChooseServiceActivity extends BaseActivity implements OnClickChooseServiceItemListener {

    public static void moveChooseServiceActivity(Activity activity) {
        Intent intent = new Intent(activity, ChooseServiceActivity.class);
        activity.startActivity(intent);
    }

    @BindView(R.id.view_manicure)
    ChooseServiceView mViewManicure;

    @BindView(R.id.view_pedicure)
    ChooseServiceView mViewPedicure;

    @BindView(R.id.view_packages)
    ChooseServiceView mViewPackages;

    @BindView(R.id.view_add_on)
    ChooseServiceView mViewAddOn;

    @BindView(R.id.text_total_price)
    TextView mTextTotalPrice;

    @BindView(R.id.layout_total)
    CardView mLayoutTotal;

    private List<ChooseServiceView> mChooseServiceViewList;

    private ChooseServiceAdapter mChooseServiceAdapter;

    private int mTotalPrice = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.choose_service_activity;
    }

    @Override
    protected int getTitleToolbarId() {
        mImageToolbar.setVisibility(View.VISIBLE);
        return R.string.app_name;
    }

    @Override
    protected void initView() {
        mChooseServiceAdapter = new ChooseServiceAdapter();
        mChooseServiceAdapter.setListener(this);

        mViewManicure.setInfoService(getString(R.string.manicure), R.drawable.bg_manicure);
        mViewPedicure.setInfoService(getString(R.string.pedicure), R.drawable.bg_pedicure);
        mViewPackages.setInfoService(getString(R.string.packages), R.drawable.bg_packages);
        mViewAddOn.setInfoService(getString(R.string.add_on), R.drawable.bg_add_on);

        mChooseServiceViewList = new ArrayList<ChooseServiceView>() {{
            add(mViewAddOn);
            add(mViewPedicure);
            add(mViewPackages);
            add(mViewManicure);
        }};

        for (ChooseServiceView view : mChooseServiceViewList) {
            view.setAdapter(mChooseServiceAdapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.notify_menu, menu);
        return true;
    }

    @Override
    public void onClickChooseService(int itemPrice, boolean isChoose) {
        if (isChoose) {
            mTotalPrice += itemPrice;
        } else {
            mTotalPrice -= itemPrice;
        }

        mTextTotalPrice.setText(String.valueOf(mTotalPrice));
        if (mTotalPrice == 0) {
            mLayoutTotal.setAlpha((float) 0.5);
        } else {
            mLayoutTotal.setAlpha((float) 0.95);
        }
    }


    @OnClick(R.id.view_manicure)
    void onClickViewManicure() {
        showView(mViewManicure);
    }

    @OnClick(R.id.view_pedicure)
    void onClickViewPedicure() {
        showView(mViewPedicure);
    }

    @OnClick(R.id.view_packages)
    void onClickViewPackages() {
        showView(mViewPackages);
    }

    @OnClick(R.id.view_add_on)
    void onClickViewAddOn() {
        showView(mViewAddOn);
    }

    @OnClick(R.id.button_continue)
    void onClickContinue() {
        if (mTotalPrice > 0) {
            ChooseServiceDetailBookingActivity.moveChooseServiceDetailBookingActivity(this);
        } else {
            showToast("You need choose a service to continue!");
        }
    }

    @Override
    public void onBackPressed() {
        if (mTotalPrice != 0) {
            showCancelConfirmDialog();
        } else {
            super.onBackPressed();
        }
    }

    private void showView(ChooseServiceView view) {
        if (!view.isEnable()) {
            view.showRcv();
        } else {
            view.hideRcv();
        }
        for (ChooseServiceView v : mChooseServiceViewList) {
            if (v != view) {
                v.hideRcv();
            }
        }
    }

    public void showCancelConfirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.choose_service_cancel_dialog, null);
        builder.setView(dialogView);

        Button buttonYes = dialogView.findViewById(R.id.button_yes);
        Button buttonNo = dialogView.findViewById(R.id.button_no);

        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.show();

        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseServiceActivity.this.finish();
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
