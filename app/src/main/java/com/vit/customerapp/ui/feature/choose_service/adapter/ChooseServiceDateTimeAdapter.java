package com.vit.customerapp.ui.feature.choose_service.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.vit.customerapp.R;
import com.vit.customerapp.ui.base.BaseViewHolder;

import butterknife.BindView;
import butterknife.OnClick;

public class ChooseServiceDateTimeAdapter extends RecyclerView.Adapter<ChooseServiceDateTimeAdapter.ChooseServiceDateTimeViewHolder> {

    private int mSelectedId = 0;

    public ChooseServiceDateTimeAdapter() {
    }

    @NonNull
    @Override
    public ChooseServiceDateTimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.choose_service_date_time_item, parent, false);
        return new ChooseServiceDateTimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseServiceDateTimeViewHolder holder, int position) {
        holder.bindData("abc");
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public long getItemId(final int position) {
        return position;
    }

    class ChooseServiceDateTimeViewHolder extends BaseViewHolder<String> {

        @BindView(R.id.layout_root)
        LinearLayout mLayoutRoot;

        public ChooseServiceDateTimeViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bindData(String s) {
            mLayoutRoot.setSelected(getLayoutPosition() == mSelectedId);
        }

        @OnClick(R.id.layout_root)
        void onClickItem() {
            mSelectedId = getAdapterPosition();
            notifyDataSetChanged();
        }
    }
}
