package com.vit.customerapp.ui.feature.menu.term_of_use;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.vit.customerapp.R;
import com.vit.customerapp.ui.base.BaseFragment;

public class TermOfUseFragment extends BaseFragment {

    public static final String TAG = TermOfUseFragment.class.getSimpleName();

    public static TermOfUseFragment newInstance() {
        TermOfUseFragment fragment = new TermOfUseFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.term_of_use_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
