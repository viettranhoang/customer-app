package com.vit.customerapp.ui.feature.menu.support;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.vit.customerapp.R;
import com.vit.customerapp.ui.base.BaseFragment;

public class CustomerSupportFragment extends BaseFragment {

    public static final String TAG = CustomerSupportFragment.class.getSimpleName();

    public static CustomerSupportFragment newInstance() {
        CustomerSupportFragment fragment = new CustomerSupportFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.customer_support_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
