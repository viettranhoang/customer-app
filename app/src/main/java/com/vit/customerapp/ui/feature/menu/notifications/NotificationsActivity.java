package com.vit.customerapp.ui.feature.menu.notifications;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vit.customerapp.R;
import com.vit.customerapp.data.model.response.Notificaton;
import com.vit.customerapp.ui.base.BaseActivity;
import com.vit.customerapp.ui.feature.menu.notifications.adapter.NotificationsAdapter;
import com.vit.customerapp.ui.feature.menu.notifications.listener.OnClickNotificationItemListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class NotificationsActivity extends BaseActivity implements OnClickNotificationItemListener {


    public static void moveNotificationsActivity(Activity activity) {
        Intent intent = new Intent(activity, NotificationsActivity.class);
        activity.startActivity(intent);
    }


    @BindView(R.id.list_noti)
    RecyclerView mRcvNoti;

    private List<Notificaton> mNotificationsList;
    private NotificationsAdapter mAdapter;

    @Override
    protected void initView() {
        mAdapter = new NotificationsAdapter(this);
        initNotificationsRcv();
        mNotificationsList = new ArrayList<Notificaton>() {{
            add(new Notificaton(getString(R.string.sat_19_may_2018_ppointment), getString(R.string.thank_you_i_hope_that), getString(R.string.two_minutes), false));
            add(new Notificaton(getString(R.string.sat_19_may_2018_ppointment), getString(R.string.your_tech_is_on_the_way), getString(R.string.two_minutes), true));
            add(new Notificaton(getString(R.string.sat_19_may_2018_ppointment), getString(R.string.thank_you_i_hope_that), getString(R.string.two_minutes), false));
            add(new Notificaton(getString(R.string.sat_19_may_2018_ppointment), getString(R.string.thank_you_i_hope_that), getString(R.string.two_minutes), false));
            add(new Notificaton(getString(R.string.sat_19_may_2018_ppointment), getString(R.string.your_tech_is_on_the_way), getString(R.string.two_minutes), true));
        }};
        mAdapter.setList(mNotificationsList);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.notifications_activity;
    }

    @Override
    protected int getTitleToolbarId() {
        return R.string.app_name;
    }

    @Override
    public void initActionBar() {
        super.initActionBar();
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
    }

    @Override
    public void onClickNotification() {
        showToast("click");
    }

    private void initNotificationsRcv() {
        mRcvNoti.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRcvNoti.setLayoutManager(new LinearLayoutManager(this));
        mRcvNoti.setHasFixedSize(true);
        mRcvNoti.setItemAnimator(null);
        mRcvNoti.setAdapter(mAdapter);
    }
}
