package com.vit.customerapp.ui.feature.rebook_technician.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vit.customerapp.R;
import com.vit.customerapp.data.model.response.Technician;
import com.vit.customerapp.ui.base.BaseViewHolder;
import com.vit.customerapp.ui.feature.rebook_technician.listener.OnClickTechnicianItemClickListener;
import com.vit.customerapp.ui.util.GlideApp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class RebookTechnicianAdapter extends RecyclerView.Adapter<RebookTechnicianAdapter.RebookTechnicianViewHolder> {

    private List<Technician> mTechniciansList = new ArrayList<>();

    private OnClickTechnicianItemClickListener listener;

    public RebookTechnicianAdapter(OnClickTechnicianItemClickListener listener) {
        this.listener = listener;
    }

    public void setTechniciansList(List<Technician> techniciansList) {
        this.mTechniciansList = techniciansList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RebookTechnicianViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rebook_technician_item, parent, false);
        return new RebookTechnicianViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RebookTechnicianViewHolder holder, int position) {
        holder.bindData(mTechniciansList.get(position));
    }

    @Override
    public int getItemCount() {
        return mTechniciansList.size();
    }

    class RebookTechnicianViewHolder extends BaseViewHolder<Technician> {

        @BindView(R.id.image_avatar)
        ImageView mImageAvatar;

        @BindView(R.id.text_name)
        TextView mTextName;

        @BindView(R.id.text_rates)
        TextView mTextRates;

        public RebookTechnicianViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bindData(Technician technician) {
            mTextName.setText(String.format("%s %s", technician.getFirstName(), technician.getLastName()));
            mTextRates.setText(String.valueOf(technician.getAverageRates()));
            GlideApp.with(itemView.getContext())
                    .load(technician.getAvatarLink())
                    .fitCenter()
                    .into(mImageAvatar);
        }

        @OnClick(R.id.layout_root)
        void onClickTechnician() {
            listener.onClickTechnician();
        }
    }
}
