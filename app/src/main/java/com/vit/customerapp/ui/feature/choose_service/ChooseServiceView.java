package com.vit.customerapp.ui.feature.choose_service;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vit.customerapp.R;
import com.vit.customerapp.ui.feature.choose_service.adapter.ChooseServiceAdapter;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ChooseServiceView extends LinearLayout {

    private ChooseServiceAdapter mAdapter;

    @BindView(R.id.text_service)
    TextView mTextService;

    @BindView(R.id.image_arrow)
    ImageView mImageArrow;

    @BindView(R.id.layout_service)
    RelativeLayout mLayoutService;

    @BindView(R.id.list_service)
    RecyclerView mRcvService;

    @BindDrawable(R.drawable.ic_arrow_up)
    Drawable icArrowUp;

    @BindDrawable(R.drawable.ic_arrow_down)
    Drawable icArrowDown;

    private boolean enable = false;

    public ChooseServiceView(Context context) {
        super(context);
        initialize();
    }

    public ChooseServiceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public ChooseServiceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    public void setAdapter(ChooseServiceAdapter adapter) {
        this.mAdapter = adapter;
        initRcv();
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void setInfoService(String serviceName, int backgroundDrawable) {
        mTextService.setText(serviceName);
        mLayoutService.setBackgroundResource(backgroundDrawable);
    }

    private void initialize() {
        inflate(getContext(), R.layout.choose_service_view, this);
        ButterKnife.bind(this);
    }

    public void hideRcv() {
        mRcvService.setVisibility(View.GONE);
        mImageArrow.setImageDrawable(icArrowDown);

        this.enable = false;
    }

    public void showRcv() {
        mRcvService.setVisibility(View.VISIBLE);
        mImageArrow.setImageDrawable(icArrowUp);

        this.enable = true;
    }

    public void initRcv() {
        mRcvService.addItemDecoration(new DividerItemDecoration(mRcvService.getContext(), DividerItemDecoration.VERTICAL));
        mRcvService.setLayoutManager(new LinearLayoutManager(getContext()));
        mRcvService.setHasFixedSize(true);
        mRcvService.setItemAnimator(null);
        mRcvService.setAdapter(mAdapter);
    }
}
