package com.vit.customerapp.ui.feature.menu.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.vit.customerapp.R;
import com.vit.customerapp.ui.base.BaseFragment;
import com.vit.customerapp.ui.feature.choose_service.ChooseServiceActivity;
import com.vit.customerapp.ui.feature.rebook_technician.RebookTechnicianActivity;

import butterknife.OnClick;

public class HomeFragment extends BaseFragment {

    public static final String TAG = HomeFragment.class.getSimpleName();

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @OnClick(R.id.layout_book_service)
    void onClickBookService() {
        ChooseServiceActivity.moveChooseServiceActivity(mDashboardActivity);
    }

    @OnClick(R.id.layout_book_family)
    void onClickBookFamily() {
    }

    @OnClick(R.id.layout_rebook)
    void onClickRebook() {
        RebookTechnicianActivity.moveRebookTechnicianActivity(mDashboardActivity);
    }
}
