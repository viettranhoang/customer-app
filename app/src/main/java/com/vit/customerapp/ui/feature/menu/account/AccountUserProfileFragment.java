package com.vit.customerapp.ui.feature.menu.account;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.vit.customerapp.R;
import com.vit.customerapp.ui.base.BaseFragment;

public class AccountUserProfileFragment extends BaseFragment {

    public static final String TAG = AccountUserProfileFragment.class.getSimpleName();

    public static AccountUserProfileFragment newInstance() {
        AccountUserProfileFragment fragment = new AccountUserProfileFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.account_user_profile_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
