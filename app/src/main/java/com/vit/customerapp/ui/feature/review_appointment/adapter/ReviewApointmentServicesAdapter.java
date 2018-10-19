package com.vit.customerapp.ui.feature.review_appointment.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vit.customerapp.R;
import com.vit.customerapp.ui.base.BaseViewHolder;

public class ReviewApointmentServicesAdapter extends RecyclerView.Adapter<ReviewApointmentServicesAdapter.ReviewApointmentServicesViewHolder> {


    public ReviewApointmentServicesAdapter() {
    }

    @NonNull
    @Override
    public ReviewApointmentServicesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_appointment_services_item, parent, false);
        return new ReviewApointmentServicesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewApointmentServicesViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class ReviewApointmentServicesViewHolder extends BaseViewHolder<String> {

        public ReviewApointmentServicesViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bindData(String s) {

        }
    }
}
