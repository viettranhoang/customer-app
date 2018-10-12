package com.vit.customerapp.ui.feature.menu.appointment.past;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.vit.customerapp.R;
import com.vit.customerapp.ui.base.BaseFragment;

public class AppointmentPastFragment extends BaseFragment {

    public static final String TAG = AppointmentPastFragment.class.getSimpleName();

    public static AppointmentPastFragment newInstance() {
        AppointmentPastFragment fragment = new AppointmentPastFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.appointment_past_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
