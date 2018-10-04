package com.vit.customerapp.ui.feature.choose_service;

import android.view.Menu;
import android.view.View;

import com.vit.customerapp.R;
import com.vit.customerapp.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ChooseServiceActivity extends BaseActivity {

    @BindView(R.id.view_manicure)
    ChooseServiceView mViewManicure;

    @BindView(R.id.view_pedicure)
    ChooseServiceView mViewPedicure;

    @BindView(R.id.view_packages)
    ChooseServiceView mViewPackages;

    @BindView(R.id.view_add_on)
    ChooseServiceView mViewAddOn;

    private List<ChooseServiceView> mChooseServiceViewList;

    @Override
    protected void initView() {
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
    }

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
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
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


}
