package com.vit.customerapp.ui.feature.menu.appointment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.vit.customerapp.R;
import com.vit.customerapp.ui.base.BaseFragment;
import com.vit.customerapp.ui.feature.menu.appointment.past.AppointmentPastFragment;
import com.vit.customerapp.ui.feature.menu.appointment.upcoming.AppointmentUpcomingFragment;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.OnClick;

public class AppointmentFragment extends BaseFragment {

    public static final String TAG = AppointmentFragment.class.getSimpleName();

    public static AppointmentFragment newInstance() {
        AppointmentFragment fragment = new AppointmentFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @BindView(R.id.text_upcoming)
    TextView mTextUpcoming;

    @BindView(R.id.text_past)
    TextView mTextPast;

    @BindColor(R.color.greenText)
    int greenText;

    @BindColor(R.color.darkGreyBlue)
    int darkGreyBlue;

    private FragmentManager fragmentManager;
    private Handler fragmentHandler;

    @Override
    protected int getLayoutId() {
        return R.layout.appointment_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        haldleTextSelected(mTextUpcoming);
        fragmentManager = mDashboardActivity.getSupportFragmentManager();
        fragmentHandler = new Handler();
        switchFragment(AppointmentUpcomingFragment.newInstance(), AppointmentUpcomingFragment.TAG, false, true);

    }

    @OnClick(R.id.text_upcoming)
    void onClickUpcoming() {
        haldleTextSelected(mTextUpcoming);
        switchFragment(AppointmentUpcomingFragment.newInstance(), AppointmentUpcomingFragment.TAG, false, true);

    }

    @OnClick(R.id.text_past)
    void onClickPast() {
        haldleTextSelected(mTextPast);
        switchFragment(AppointmentPastFragment.newInstance(), AppointmentPastFragment.TAG, false, true);
    }

    private void haldleTextSelected(TextView textSelected) {
        textSelected.setTextColor(darkGreyBlue);
        textSelected.setTypeface(textSelected.getTypeface(), Typeface.BOLD);
        if (textSelected.equals(mTextPast)) {
            textSelected.setBackgroundResource(R.drawable.round_corners_gradient_green_right);
            mTextUpcoming.setBackgroundColor(Color.TRANSPARENT);
            mTextUpcoming.setTextColor(greenText);
            mTextUpcoming.setTypeface(null, Typeface.NORMAL);
        } else {
            textSelected.setBackgroundResource(R.drawable.round_corners_gradient_green_left);
            mTextPast.setBackgroundColor(Color.TRANSPARENT);
            mTextPast.setTextColor(greenText);
            mTextPast.setTypeface(null, Typeface.NORMAL);
        }
    }

    public void switchFragment(Fragment fragment, String tag, boolean addToBackStack, boolean clearBackStack) {
        fragmentHandler.post(new RunFragment(fragment, tag, addToBackStack, clearBackStack));
    }

    private class RunFragment implements Runnable {
        Fragment bf;
        String tag;
        boolean addToBackStack;
        boolean clearBackStack;

        RunFragment(Fragment bf, String tag, boolean addToBackStack, boolean clearBackStack) {
            this.bf = bf;
            this.tag = tag;
            this.addToBackStack = addToBackStack;
            this.clearBackStack = clearBackStack;
        }

        public void run() {
            if (clearBackStack) {
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }

            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.view_fragment, bf, tag);
            if (addToBackStack) {
                ft.addToBackStack(null);
            }
            ft.commit();
        }
    }
}
