package com.vit.customerapp.ui.feature.choose_service;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;
import com.vit.customerapp.R;
import com.vit.customerapp.ui.base.BaseActivity;
import com.vit.customerapp.ui.feature.choose_service.adapter.ChooseServiceColorAdapter;
import com.vit.customerapp.ui.feature.choose_service.adapter.ChooseServiceDateTimeAdapter;

import butterknife.BindView;
import butterknife.OnClick;

public class ChooseServiceDetailBookingActivity extends BaseActivity {

    public static void moveChooseServiceDetailBookingActivity(Activity activity) {
        Intent intent = new Intent(activity, ChooseServiceDetailBookingActivity.class);
        activity.startActivity(intent);
    }

    @BindView(R.id.list_manicure_color)
    RecyclerView mRcvManicureColor;

    @BindView(R.id.list_pedicure_color)
    RecyclerView mRcvPedicureColor;

    @BindView(R.id.list_time)
    RecyclerView mRcvDateTime;

    @BindView(R.id.image_use_gel_color_manicure)
    ImageView mImageUseGelColor;

    @BindView(R.id.image_use_gel_color_pedicure)
    ImageView mImageUseGelColorPedicure;

    @BindView(R.id.button_either)
    Button mButtonEither;

    @BindView(R.id.button_male)
    Button mButtonMale;

    @BindView(R.id.button_female)
    Button mButtonFemale;

    @BindView(R.id.image_arrow_right_manicure)
    ImageView mImageArrowRightManicure;

    @BindView(R.id.image_arrow_right_pedicure)
    ImageView mImageArrowRightPedicure;

    @BindView(R.id.image_arrow_left_manicure)
    ImageView mImageArrowLeftManicure;

    @BindView(R.id.image_arrow_left_pedicure)
    ImageView mImageArrowLeftPedicure;

    private ChooseServiceColorAdapter mPedicureColorAdapter;
    private ChooseServiceColorAdapter mManicureColorAdapter;
    private CarouselLayoutManager mPedicureLayoutManager;
    private CarouselLayoutManager mManicureLayoutManager;

    private ChooseServiceDateTimeAdapter mDateTimeAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.choose_service_detail_booking_activity;
    }

    @Override
    protected int getTitleToolbarId() {
        mImageToolbar.setVisibility(View.VISIBLE);
        return R.string.app_name;
    }

    @Override
    protected void initView() {
        handleButtonGender(mButtonEither);
        mImageUseGelColor.setSelected(false);
        mImageUseGelColorPedicure.setSelected(false);
        mPedicureColorAdapter = new ChooseServiceColorAdapter();
        mPedicureColorAdapter.setmImageResource(R.drawable.ic_gel2);
        mManicureColorAdapter = new ChooseServiceColorAdapter();
        initManicureRcv();
        initPedicureRcv();

        mDateTimeAdapter = new ChooseServiceDateTimeAdapter();
        initDateTimeRcv();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @OnClick(R.id.image_use_gel_color_manicure)
    void onClickUseGelColor() {
        if (!mImageUseGelColor.isSelected()) {
            mImageUseGelColor.setSelected(true);
            mManicureColorAdapter.setSelectedId(-1);
        } else {
            mImageUseGelColor.setSelected(false);
            mManicureColorAdapter.setSelectedId(mManicureLayoutManager.getCenterItemPosition());
        }

    }

    @OnClick(R.id.image_use_gel_color_pedicure)
    void onClickUseGelColorPedicure() {
        if (!mImageUseGelColorPedicure.isSelected()) {
            mImageUseGelColorPedicure.setSelected(true);
            mPedicureColorAdapter.setSelectedId(-1);
        } else {
            mImageUseGelColorPedicure.setSelected(false);
            mPedicureColorAdapter.setSelectedId(mPedicureLayoutManager.getCenterItemPosition());
        }
    }

    @OnClick(R.id.button_female)
    void onClickFemale() {
        handleButtonGender(mButtonFemale);
    }

    @OnClick(R.id.button_male)
    void onClickMale() {
        handleButtonGender(mButtonMale);
    }

    @OnClick(R.id.button_either)
    void onClickEither() {
        handleButtonGender(mButtonEither);
    }

    private void initManicureRcv() {

//        mRcvManicureColor.setLayoutManager(new CenterZoomLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mManicureLayoutManager = new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL, false);
        mManicureLayoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());
        mManicureLayoutManager.setMaxVisibleItems(1);
        mRcvManicureColor.setLayoutManager(mManicureLayoutManager);

        mRcvManicureColor.setHasFixedSize(true);
        mRcvManicureColor.setAdapter(mManicureColorAdapter);
        mRcvManicureColor.addOnScrollListener(new CenterScrollListener());

        mManicureLayoutManager.addOnItemSelectionListener(new CarouselLayoutManager.OnCenterItemSelectionListener() {

            @Override
            public void onCenterItemChanged(final int adapterPosition) {
                if (CarouselLayoutManager.INVALID_POSITION != adapterPosition) {
                    if (!mImageUseGelColor.isSelected())
                        mManicureColorAdapter.setSelectedId(adapterPosition);

                    if (adapterPosition == 0)
                        mImageArrowLeftManicure.setAlpha(0.2f);
                    else
                        mImageArrowLeftManicure.setAlpha(1f);

                    if (adapterPosition == mManicureColorAdapter.getSizeList() - 1)
                        mImageArrowRightManicure.setAlpha(0.2f);
                    else
                        mImageArrowRightManicure.setAlpha(1f);
                }
            }
        });

    }

    private void initPedicureRcv() {

        mPedicureLayoutManager = new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL, false);
        mPedicureLayoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());
        mPedicureLayoutManager.setMaxVisibleItems(1);
        mRcvPedicureColor.setLayoutManager(mPedicureLayoutManager);

        mRcvPedicureColor.setHasFixedSize(true);
        mRcvPedicureColor.setAdapter(mPedicureColorAdapter);
        mRcvPedicureColor.addOnScrollListener(new CenterScrollListener());

        mPedicureLayoutManager.addOnItemSelectionListener(new CarouselLayoutManager.OnCenterItemSelectionListener() {

            @Override
            public void onCenterItemChanged(final int adapterPosition) {
                if (CarouselLayoutManager.INVALID_POSITION != adapterPosition) {
                    if (!mImageUseGelColorPedicure.isSelected())
                        mPedicureColorAdapter.setSelectedId(adapterPosition);

                    if (adapterPosition == 0)
                        mImageArrowLeftPedicure.setAlpha(0.2f);
                    else
                        mImageArrowLeftPedicure.setAlpha(1f);

                    if (adapterPosition == mPedicureColorAdapter.getSizeList() - 1)
                        mImageArrowRightPedicure.setAlpha(0.2f);
                    else
                        mImageArrowRightPedicure.setAlpha(1f);

                }
            }
        });
    }

    private void initDateTimeRcv() {
        mRcvDateTime.setLayoutManager(new LinearLayoutManager(this));
        mRcvDateTime.setHasFixedSize(true);
        mRcvDateTime.setAdapter(mDateTimeAdapter);
    }

    private void handleButtonGender(Button button) {
        button.setSelected(true);
        if (!mButtonEither.equals(button)) mButtonEither.setSelected(false);
        if (!mButtonMale.equals(button)) mButtonMale.setSelected(false);
        if (!mButtonFemale.equals(button)) mButtonFemale.setSelected(false);
    }
}
