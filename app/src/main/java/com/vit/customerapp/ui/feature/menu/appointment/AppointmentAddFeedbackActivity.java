package com.vit.customerapp.ui.feature.menu.appointment;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import com.vit.customerapp.R;
import com.vit.customerapp.ui.base.BaseActivity;

import java.util.List;

import butterknife.BindViews;
import butterknife.OnClick;

public class AppointmentAddFeedbackActivity extends BaseActivity {

    public static void moveAppointmentAddFeedbackActivity(Activity activity) {
        Intent intent = new Intent(activity, AppointmentAddFeedbackActivity.class);
        activity.startActivity(intent);
    }

    @BindViews({R.id.image_star1, R.id.image_star2, R.id.image_star3, R.id.image_star4, R.id.image_star5})
    List<ImageView> mStarsList;

    @Override
    protected int getLayoutId() {
        return R.layout.appointment_add_feedback_activity;
    }

    @Override
    protected int getTitleToolbarId() {
        return R.string.none;
    }

    @Override
    protected void initView() {
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.skip_menu, menu);
        return true;
    }

    @OnClick({R.id.image_star1, R.id.image_star2, R.id.image_star3, R.id.image_star4, R.id.image_star5})
    void onClickStar(View v) {
        int starRating = 0;

        switch (v.getId()) {
            case R.id.image_star1:
                starRating = 1;
                break;
            case R.id.image_star2:
                starRating = 2;
                break;
            case R.id.image_star3:
                starRating = 3;
                break;
            case R.id.image_star4:
                starRating = 4;
                break;
            case R.id.image_star5:
                starRating = 5;
                break;
        }

        for (int i = 0; i < mStarsList.size(); i++) {
            if (i < starRating) mStarsList.get(i).setSelected(true);
            else  mStarsList.get(i).setSelected(false);
        }
    }
}
