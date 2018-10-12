package com.vit.customerapp.ui.feature.menu.appointment.upcoming.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vit.customerapp.R;
import com.vit.customerapp.ui.base.BaseViewHolder;
import com.vit.customerapp.ui.feature.menu.appointment.upcoming.listener.OnClickAppointmentUpcomingItemListener;

import butterknife.OnClick;

public class AppointmentUpcomingAdapter extends RecyclerView.Adapter<AppointmentUpcomingAdapter.AppointmentUpcomingViewHolder> {

    private OnClickAppointmentUpcomingItemListener listener;

    public AppointmentUpcomingAdapter(OnClickAppointmentUpcomingItemListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public AppointmentUpcomingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.appointment_upcoming_item, parent, false);
        return new AppointmentUpcomingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentUpcomingViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class AppointmentUpcomingViewHolder extends BaseViewHolder<String> {

        public AppointmentUpcomingViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bindData(String s) {

        }

        @OnClick(R.id.layout_root)
        void onClickItem() {
            listener.onClickkAppointmentUpcoming();
        }
    }
}
