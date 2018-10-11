package com.vit.customerapp.ui.feature.menu.notifications.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vit.customerapp.R;
import com.vit.customerapp.data.model.response.Notificaton;
import com.vit.customerapp.ui.base.BaseViewHolder;
import com.vit.customerapp.ui.feature.menu.notifications.listener.OnClickNotificationItemListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.OnClick;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.NotificationsViewHolder> {

    private List<Notificaton> mNotificationList = new ArrayList<>();

    private OnClickNotificationItemListener listener;

    public NotificationsAdapter(OnClickNotificationItemListener listener) {
        this.listener = listener;
    }

    public void setList(List<Notificaton> mNotificationList) {
        this.mNotificationList = mNotificationList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NotificationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notifications_item, parent, false);
        return new NotificationsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationsViewHolder holder, int position) {
        holder.bindData(mNotificationList.get(position));
    }

    @Override
    public int getItemCount() {
        return mNotificationList.size();
    }

    class NotificationsViewHolder extends BaseViewHolder<Notificaton> {

        @BindView(R.id.layout_root)
        LinearLayout mLayoutRoot;

        @BindView(R.id.text_noti)
        TextView mTextNoti;

        @BindView(R.id.text_time)
        TextView mTextTime;

        @BindColor(R.color.scarlet5)
        int scarlet;

        public NotificationsViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bindData(Notificaton notificaton) {
            mTextNoti.setText(Html.fromHtml(notificaton.getNoti()));
            mTextTime.setText(notificaton.getTime());
            mLayoutRoot.setBackgroundColor(notificaton.isSeen() ? Color.WHITE : scarlet);
        }

        @OnClick(R.id.layout_root)
        void onClickItem() {
            listener.onClickNotification(getLayoutPosition());
        }
    }
}
