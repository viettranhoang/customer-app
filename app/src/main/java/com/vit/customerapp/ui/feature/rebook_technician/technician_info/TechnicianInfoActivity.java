package com.vit.customerapp.ui.feature.rebook_technician.technician_info;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vit.customerapp.R;
import com.vit.customerapp.ui.base.BaseActivity;

import butterknife.BindView;

public class TechnicianInfoActivity extends BaseActivity {



    public static void moveTechnicianInfoActivity(Activity activity) {
        Intent intent = new Intent(activity, TechnicianInfoActivity.class);
        activity.startActivity(intent);
    }

    @BindView(R.id.list_rating)
    RecyclerView mRcvUserRating;

    private TechnicianInfoUserRateAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.technician_info_activity;
    }

    @Override
    protected int getTitleToolbarId() {
        return R.string.technician_info;
    }

    @Override
    protected void initView() {
        mAdapter = new TechnicianInfoUserRateAdapter();
        initRcv();
    }

    private void initRcv() {
        mRcvUserRating.setLayoutManager(new LinearLayoutManager(this));
        mRcvUserRating.setHasFixedSize(true);
        mRcvUserRating.setAdapter(mAdapter);
    }
}
