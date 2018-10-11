package com.vit.customerapp.ui.feature.menu.account;

import android.app.Activity;
import android.content.Intent;

import com.vit.customerapp.R;
import com.vit.customerapp.ui.base.BaseActivity;
import com.vit.customerapp.ui.util.GlideApp;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class AccountUpdateProfileActivity extends BaseActivity {

    public static void moveAccountUpdateProfileActivity(Activity activity) {
        Intent intent = new Intent(activity, AccountUpdateProfileActivity.class);
        activity.startActivity(intent);
    }

    @BindView(R.id.image_profile)
    CircleImageView mImageProfile;

    @Override
    protected int getLayoutId() {
        return R.layout.account_update_profile_activity;
    }

    @Override
    protected int getTitleToolbarId() {
        return R.string.update_profile;
    }

    @Override
    protected void initView() {

        GlideApp.with(this)
                .load("https://kenh14cdn.com/2018/4/13/photo-4-1523613034062930366784.jpg")
                .fitCenter()
                .into(mImageProfile);
    }
}
