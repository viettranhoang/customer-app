package com.vit.customerapp.ui.feature.choose_service.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.vit.customerapp.R;
import com.vit.customerapp.ui.base.BaseViewHolder;

import butterknife.BindView;

public class ChooseServiceColorAdapter extends RecyclerView.Adapter<ChooseServiceColorAdapter.ChooseServiceColorViewHolder> {

    private int mSelectedId = -1;

    private int mImageResource = -1;

    private int sizeList = 10;

    public ChooseServiceColorAdapter() {
    }

    public void setSelectedId(int selectedId) {
        this.mSelectedId = selectedId;
        notifyDataSetChanged();
    }

    public void setmImageResource(int mImageResource) {
        this.mImageResource = mImageResource;
    }

    public int getSizeList() {
        return sizeList;
    }

    @NonNull
    @Override
    public ChooseServiceColorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.choose_service_detail_booking_color_item, parent, false);
        return new ChooseServiceColorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseServiceColorViewHolder holder, int position) {
        holder.bindData("");
    }

    @Override
    public int getItemCount() {
        return sizeList;
    }

    @Override
    public long getItemId(final int position) {
        return position;
    }

    class ChooseServiceColorViewHolder extends BaseViewHolder<String> {

        @BindView(R.id.image_choose)
        ImageView mImageChoose;

        @BindView(R.id.image_gel)
        ImageView mImageGel;

        public ChooseServiceColorViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bindData(String s) {
            if (mImageResource != -1)
                mImageGel.setImageResource(mImageResource);
            if (getLayoutPosition() == mSelectedId)
                mImageChoose.setVisibility(View.VISIBLE);
            else
                mImageChoose.setVisibility(View.GONE);
        }
    }
}
