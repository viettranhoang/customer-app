package com.vit.customerapp.ui.feature.menu.appointment.upcoming;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.vit.customerapp.R;
import com.vit.customerapp.ui.base.BaseFragment;
import com.vit.customerapp.ui.feature.menu.appointment.upcoming.adapter.AppointmentUpcomingAdapter;
import com.vit.customerapp.ui.feature.menu.appointment.upcoming.listener.OnClickAppointmentUpcomingItemListener;

import butterknife.BindView;

public class AppointmentUpcomingFragment extends BaseFragment implements OnClickAppointmentUpcomingItemListener {

    public static final String TAG = AppointmentUpcomingFragment.class.getSimpleName();

    public static AppointmentUpcomingFragment newInstance() {
        AppointmentUpcomingFragment fragment = new AppointmentUpcomingFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @BindView(R.id.list_appointment_upcoming)
    RecyclerView mRcvUpcoming;

    private AppointmentUpcomingAdapter mUpcomingAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.appointment_upcoming_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mUpcomingAdapter = new AppointmentUpcomingAdapter(this);
        initRcvUpcoming();
    }

    private void initRcvUpcoming() {
        mRcvUpcoming.setLayoutManager(new LinearLayoutManager(getContext()));
        mRcvUpcoming.setHasFixedSize(true);
        mRcvUpcoming.setAdapter(mUpcomingAdapter);
    }

    @Override
    public void onClickkAppointmentUpcoming() {
        showToast("click appointment upcoming");
    }
}
