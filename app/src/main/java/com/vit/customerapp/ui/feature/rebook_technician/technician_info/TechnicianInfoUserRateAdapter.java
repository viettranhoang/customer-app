package com.vit.customerapp.ui.feature.rebook_technician.technician_info;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vit.customerapp.R;
import com.vit.customerapp.ui.base.BaseViewHolder;
import com.vit.customerapp.ui.util.GlideApp;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class TechnicianInfoUserRateAdapter extends RecyclerView.Adapter<TechnicianInfoUserRateAdapter.TechnicianInfoUserRateViewHolder> {

    public TechnicianInfoUserRateAdapter() {
    }

    @NonNull
    @Override
    public TechnicianInfoUserRateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.technician_info_user_rate_item, parent, false);
        return new TechnicianInfoUserRateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TechnicianInfoUserRateViewHolder holder, int position) {
       holder.bindData("");
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class TechnicianInfoUserRateViewHolder extends BaseViewHolder<String> {

        @BindView(R.id.image_avatar)
        CircleImageView mImageAvatar;

        public TechnicianInfoUserRateViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bindData(String s) {
            GlideApp.with(itemView.getContext())
                    .load("https://images.pexels.com/photos/324658/pexels-photo-324658.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
                    .circleCrop()
                    .placeholder(R.drawable.ic_avt_default)
                    .into(mImageAvatar);

        }
    }
}
