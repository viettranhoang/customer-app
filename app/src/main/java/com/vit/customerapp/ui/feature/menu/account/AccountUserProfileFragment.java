package com.vit.customerapp.ui.feature.menu.account;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.vit.customerapp.R;
import com.vit.customerapp.ui.base.BaseFragment;
import com.vit.customerapp.ui.util.GlideApp;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class AccountUserProfileFragment extends BaseFragment {

    public static final String TAG = AccountUserProfileFragment.class.getSimpleName();

    public static AccountUserProfileFragment newInstance() {
        AccountUserProfileFragment fragment = new AccountUserProfileFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @BindView(R.id.image_profile)
    CircleImageView mImageProfile;

    @Override
    protected int getLayoutId() {
        return R.layout.account_user_profile_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        GlideApp.with(this)
                .load("https://kenh14cdn.com/2018/4/13/photo-4-1523613034062930366784.jpg")
                .fitCenter()
                .into(mImageProfile);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.edit_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_edit) {
            AccountUpdateProfileActivity.moveAccountUpdateProfileActivity(mDashboardActivity);
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.text_change)
    void onClickChange() {
        AccountUpdatePaymentActivity.moveAccountUpdatePaymentActivity(mDashboardActivity);
    }
}
