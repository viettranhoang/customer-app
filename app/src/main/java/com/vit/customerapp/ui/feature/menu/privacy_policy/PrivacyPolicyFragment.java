package com.vit.customerapp.ui.feature.menu.privacy_policy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.vit.customerapp.R;
import com.vit.customerapp.ui.base.BaseFragment;

public class PrivacyPolicyFragment extends BaseFragment {

    public static final String TAG = PrivacyPolicyFragment.class.getSimpleName();

    public static PrivacyPolicyFragment newInstance() {
        PrivacyPolicyFragment fragment = new PrivacyPolicyFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.privacy_policy_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
